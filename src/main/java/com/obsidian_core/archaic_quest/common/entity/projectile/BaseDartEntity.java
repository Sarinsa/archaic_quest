package com.obsidian_core.archaic_quest.common.entity.projectile;

import com.google.common.collect.Lists;
import com.obsidian_core.archaic_quest.common.core.register.AQEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.datagen.damage.AQDamageTypes;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.entity.ProjectileImpactEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

// TODO - Properly clean up and finish this implementation
//
@SuppressWarnings( "resource" )
public class BaseDartEntity extends Projectile {
    
    private static final double BASE_DAMAGE = 1.0D;
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId( BaseDartEntity.class, EntityDataSerializers.BYTE );
    private static final EntityDataAccessor<Byte> PIERCE_LEVEL = SynchedEntityData.defineId( BaseDartEntity.class, EntityDataSerializers.BYTE );
    private static final int FLAG_CRIT = 1;
    private static final int FLAG_NO_PHYSICS = 2;
    
    @Nullable
    private BlockState lastState;
    protected boolean inGround;
    protected int inGroundTime;
    public Pickup pickup = Pickup.DISALLOWED;
    public int shakeTime;
    private int life;
    private double baseDamage = 1.0D;
    private int knockback;
    private SoundEvent soundEvent = this.getDefaultHitGroundSoundEvent();
    
    @Nullable
    private IntOpenHashSet piercingIgnoreEntityIds;
    @Nullable
    private List<Entity> piercedAndKilledEntities;
    
    private final IntOpenHashSet ignoredEntities = new IntOpenHashSet();
    
    
    public BaseDartEntity( EntityType<? extends BaseDartEntity> type, Level level ) {
        super( type, level );
    }
    
    public BaseDartEntity( double x, double y, double z, Level level ) {
        this( AQEntities.DART.get(), level );
        setPos( x, y, z );
    }
    
    public BaseDartEntity( LivingEntity shooter, Level level ) {
        this( shooter.getX(), shooter.getEyeY() - (double) 0.1F, shooter.getZ(), level );
        setOwner( shooter );
        
        if( shooter instanceof Player ) {
            pickup = Pickup.ALLOWED;
        }
    }
    
    public void setSoundEvent( SoundEvent sound ) {
        soundEvent = sound;
    }
    
    @Override
    public boolean shouldRenderAtSqrDistance( double dist ) {
        double bbSize = getBoundingBox().getSize() * 10.0D;
        
        if( Double.isNaN( bbSize ) ) {
            bbSize = 1.0D;
        }
        bbSize *= 64.0D * getViewScale();
        return dist < bbSize * bbSize;
    }
    
    @Override
    protected void defineSynchedData() {
        entityData.define( ID_FLAGS, (byte) 0 );
        entityData.define( PIERCE_LEVEL, (byte) 0 );
    }
    
    @Override
    public void shoot( double dX, double dY, double dZ, float power, float projectileVariance ) {
        super.shoot( dX, dY, dZ, power, projectileVariance );
        life = 0;
    }
    
    @Override
    public void lerpMotion( double dX, double dY, double dZ ) {
        super.lerpMotion( dX, dY, dZ );
        life = 0;
    }
    
    @Override
    public void tick() {
        super.tick();
        
        boolean isNoPhysics = isNoPhysics();
        Vec3 deltaMovement = getDeltaMovement();
        
        if( xRotO == 0.0F && yRotO == 0.0F ) {
            double hzDist = deltaMovement.horizontalDistance();
            setYRot( (float) (Mth.atan2( deltaMovement.x, deltaMovement.z ) * (double) (180F / (float) Math.PI)) );
            setXRot( (float) (Mth.atan2( deltaMovement.y, hzDist ) * (double) (180F / (float) Math.PI)) );
            yRotO = getYRot();
            xRotO = getXRot();
        }
        BlockPos blockPos = blockPosition();
        BlockState blockState = level().getBlockState( blockPos );
        
        if( !blockState.isAir() && !isNoPhysics ) {
            VoxelShape collisionShape = blockState.getCollisionShape( level(), blockPos );
            
            if( !collisionShape.isEmpty() ) {
                Vec3 position = position();
                
                for( AABB box : collisionShape.toAabbs() ) {
                    if( box.move( blockPos ).contains( position ) ) {
                        inGround = true;
                        break;
                    }
                }
            }
        }
        if( shakeTime > 0 ) {
            --shakeTime;
        }
        if( isInWaterOrRain() || blockState.is( Blocks.POWDER_SNOW )
                || isInFluidType( ( fluidType, height ) -> canFluidExtinguish( fluidType ) ) ) {
            clearFire();
        }
        if( inGround && !isNoPhysics ) {
            if( lastState != blockState && shouldFall() ) {
                startFalling();
            }
            else if( !level().isClientSide ) {
                tickDespawn();
            }
            ++inGroundTime;
        }
        else {
            inGroundTime = 0;
            Vec3 startPos = position();
            Vec3 endPos = startPos.add( deltaMovement );
            HitResult hitResult = level().clip( new ClipContext( startPos, endPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this ) );
            
            if( hitResult.getType() != HitResult.Type.MISS ) {
                endPos = hitResult.getLocation();
            }
            while( !isRemoved() ) {
                EntityHitResult entityHit = findHitEntity( startPos, endPos );
                
                if( entityHit != null ) {
                    hitResult = entityHit;
                }
                if( hitResult != null && hitResult.getType() == HitResult.Type.ENTITY ) {
                    // noinspection DataFlowIssue
                    Entity target = ((EntityHitResult) hitResult).getEntity();
                    Entity shooter = getOwner();
                    
                    if( target instanceof Player playerTarget && shooter instanceof Player playerShooter && !playerShooter.canHarmPlayer( playerTarget ) ) {
                        hitResult = null;
                        entityHit = null;
                    }
                }
                if( hitResult != null && hitResult.getType() != HitResult.Type.MISS && !isNoPhysics ) {
                    var result = ForgeEventFactory.onProjectileImpactResultNullable( this, hitResult );
                    
                    if( result == null ) {
                        if( hitResult.getType() != HitResult.Type.ENTITY )
                            break;
                        result = ProjectileImpactEvent.ImpactResult.SKIP_ENTITY;
                    }
                    switch( result ) {
                        case SKIP_ENTITY:
                            if( hitResult.getType() != HitResult.Type.ENTITY ) { // If there is no entity, we just return default behaviour
                                onHit( hitResult );
                                hasImpulse = true;
                                break;
                            }
                            ignoredEntities.add( entityHit.getEntity().getId() );
                            entityHit = null; // Don't process any further
                            break;
                        case STOP_AT_CURRENT_NO_DAMAGE:
                            discard();
                            entityHit = null; // Don't process any further
                            break;
                        case STOP_AT_CURRENT:
                            setPierceLevel( (byte) 0 );
                        case DEFAULT:
                            onHit( hitResult );
                            hasImpulse = true;
                            break;
                    }
                }
                if( entityHit == null || getPierceLevel() <= 0 ) {
                    break;
                }
                hitResult = null;
            }
            if( isRemoved() )
                return;
            
            deltaMovement = getDeltaMovement();
            double dX = deltaMovement.x;
            double dY = deltaMovement.y;
            double dZ = deltaMovement.z;
            
            if( isCritArrow() ) {
                for( int count = 0; count < 4; ++count ) {
                    level().addParticle(
                            ParticleTypes.CRIT,
                            getX() + dX * (double) count / 4.0D,
                            getY() + dY * (double) count / 4.0D,
                            getZ() + dZ * (double) count / 4.0D,
                            -dX,
                            -dY + 0.2D,
                            -dZ
                    );
                }
            }
            double posX = getX() + dX;
            double posY = getY() + dY;
            double posZ = getZ() + dZ;
            double hzDist = deltaMovement.horizontalDistance();
            
            if( isNoPhysics ) {
                setYRot( (float) (Mth.atan2( -dX, -dZ ) * (double) (180F / (float) Math.PI)) );
            }
            else {
                setYRot( (float) (Mth.atan2( dX, dZ ) * (double) (180F / (float) Math.PI)) );
            }
            setXRot( (float) (Mth.atan2( dY, hzDist ) * (double) (180F / (float) Math.PI)) );
            setXRot( lerpRotation( this.xRotO, this.getXRot() ) );
            setYRot( lerpRotation( this.yRotO, this.getYRot() ) );
            
            final float gravity = 0.05F;
            float waterInertia = 0.99F;
            
            if( this.isInWater() ) {
                float speedMult = 0.25F;
                
                for( int j = 0; j < 4; ++j ) {
                    level().addParticle(
                            ParticleTypes.BUBBLE,
                            posX - dX * speedMult,
                            posY - dY * speedMult,
                            posZ - dZ * speedMult,
                            dX,
                            dY,
                            dZ
                    );
                }
                waterInertia = getWaterInertia();
            }
            setDeltaMovement( deltaMovement.scale( waterInertia ) );
            
            if( !isNoGravity() && !isNoPhysics ) {
                Vec3 vec = getDeltaMovement();
                setDeltaMovement( vec.x, vec.y - (double) gravity, vec.z );
            }
            setPos( posX, posY, posZ );
            checkInsideBlocks();
        }
    }
    
    private boolean shouldFall() {
        return inGround && level().noCollision( (new AABB( position(), position() )).inflate( 0.06D ) );
    }
    
    private void startFalling() {
        inGround = false;
        Vec3 deltaMovement = getDeltaMovement();
        setDeltaMovement( deltaMovement.multiply( random.nextFloat() * 0.2F, random.nextFloat() * 0.2F, random.nextFloat() * 0.2F ) );
        life = 0;
    }
    
    @Override
    public void move( MoverType moverType, Vec3 moveVec ) {
        super.move( moverType, moveVec );
        if( moverType != MoverType.SELF && shouldFall() ) {
            startFalling();
        }
    }
    
    protected void tickDespawn() {
        ++life;
        if( life >= 1200 ) {
            discard();
        }
    }
    
    private void resetPiercedEntities() {
        if( piercedAndKilledEntities != null ) {
            piercedAndKilledEntities.clear();
        }
        if( piercingIgnoreEntityIds != null ) {
            piercingIgnoreEntityIds.clear();
        }
    }
    
    @Override
    protected void onHitEntity( EntityHitResult hitResult ) {
        super.onHitEntity( hitResult );
        Entity entity = hitResult.getEntity();
        float speed = (float) getDeltaMovement().length();
        int damage = Mth.ceil( Mth.clamp( (double) speed * baseDamage, 0.0D, Integer.MAX_VALUE ) );
        
        if( getPierceLevel() > 0 ) {
            if( piercingIgnoreEntityIds == null ) {
                piercingIgnoreEntityIds = new IntOpenHashSet( 5 );
            }
            if( piercedAndKilledEntities == null ) {
                piercedAndKilledEntities = Lists.newArrayListWithCapacity( 5 );
            }
            if( piercingIgnoreEntityIds.size() >= getPierceLevel() + 1 ) {
                discard();
                return;
            }
            piercingIgnoreEntityIds.add( entity.getId() );
        }
        
        if( isCritArrow() ) {
            long critBonus = random.nextInt( damage / 2 + 2 );
            damage = (int) Math.min( critBonus + (long) damage, 2147483647L );
        }
        Entity owner = getOwner();
        DamageSource damageSource;
        
        if( owner == null ) {
            damageSource = AQDamageTypes.dart( level(), this, null );
        }
        else {
            damageSource = AQDamageTypes.dart( level(), this, owner );
            if( owner instanceof LivingEntity livingOwner ) {
                livingOwner.setLastHurtMob( entity );
            }
        }
        boolean isEnderman = entity.getType() == EntityType.ENDERMAN;
        int hitTicksOnFire = entity.getRemainingFireTicks();
        
        if( isOnFire() && !isEnderman ) {
            entity.setSecondsOnFire( 5 );
        }
        if( entity.hurt( damageSource, (float) damage ) ) {
            if( isEnderman ) {
                return;
            }
            if( entity instanceof LivingEntity livingEntity ) {
                if( !level().isClientSide && getPierceLevel() <= 0 ) {
                    livingEntity.setArrowCount( livingEntity.getArrowCount() + 1 );
                }
                if( knockback > 0 ) {
                    double knockbackRes = Math.max( 0.0D, 1.0D - livingEntity.getAttributeValue( Attributes.KNOCKBACK_RESISTANCE ) );
                    
                    Vec3 deltaMovement = getDeltaMovement()
                            .multiply( 1.0D, 0.0D, 1.0D )
                            .normalize()
                            .scale( (double) knockback * 0.6D * knockbackRes );
                    
                    if( deltaMovement.lengthSqr() > 0.0D ) {
                        livingEntity.push( deltaMovement.x, 0.1D, deltaMovement.z );
                    }
                }
                if( !level().isClientSide && owner instanceof LivingEntity livingOwner ) {
                    EnchantmentHelper.doPostHurtEffects( livingEntity, livingOwner );
                    EnchantmentHelper.doPostDamageEffects( livingOwner, livingEntity );
                }
                doPostHurtEffects( livingEntity );
                
                // Send "hit player" sound to client
                if( owner instanceof ServerPlayer playerOwner && livingEntity != owner && livingEntity instanceof Player && !isSilent() ) {
                    playerOwner.connection.send( new ClientboundGameEventPacket( ClientboundGameEventPacket.ARROW_HIT_PLAYER, 0.0F ) );
                }
                if( !entity.isAlive() && piercedAndKilledEntities != null ) {
                    piercedAndKilledEntities.add( livingEntity );
                }
                // TODO - Remove or replace this with something else, this is crossbow stuff
                if( !level().isClientSide && owner instanceof ServerPlayer serverPlayer ) {
                    if( piercedAndKilledEntities != null && shotFromCrossbow() ) {
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger( serverPlayer, piercedAndKilledEntities );
                    }
                    else if( !entity.isAlive() && shotFromCrossbow() ) {
                        //noinspection ArraysAsListWithZeroOrOneArgument
                        CriteriaTriggers.KILLED_BY_CROSSBOW.trigger( serverPlayer, Arrays.asList( entity ) );
                    }
                }
            }
            playSound( soundEvent, 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F) );
            
            if( getPierceLevel() <= 0 ) {
                discard();
            }
        }
        else {
            entity.setRemainingFireTicks( hitTicksOnFire );
            setDeltaMovement( getDeltaMovement().scale( -0.1D ) );
            setYRot( getYRot() + 180.0F );
            yRotO += 180.0F;
            
            if( !level().isClientSide && getDeltaMovement().lengthSqr() < 1.0E-7D ) {
                if( pickup == Pickup.ALLOWED ) {
                    spawnAtLocation( getPickupItem(), 0.1F );
                }
                discard();
            }
        }
    }
    
    @Override
    protected void onHitBlock( BlockHitResult hitResult ) {
        lastState = level().getBlockState( hitResult.getBlockPos() );
        
        super.onHitBlock( hitResult );
        
        // I don't understand this math lol
        Vec3 pos = hitResult.getLocation().subtract( getX(), getY(), getZ() );
        setDeltaMovement( pos );
        Vec3 vec = pos.normalize().scale( 0.05F );
        
        setPosRaw( getX() - vec.x, getY() - vec.y, getZ() - vec.z );
        playSound( getHitGroundSoundEvent(), 1.0F, 1.2F / (random.nextFloat() * 0.2F + 0.9F) );
        
        inGround = true;
        shakeTime = 7;
        
        setCritArrow( false );
        setPierceLevel( (byte) 0 );
        setSoundEvent( SoundEvents.ARROW_HIT );
        resetPiercedEntities();
    }
    
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.ARROW_HIT;
    }
    
    protected final SoundEvent getHitGroundSoundEvent() {
        return soundEvent;
    }
    
    protected void doPostHurtEffects( LivingEntity livingEntity ) { }
    
    @Nullable
    protected EntityHitResult findHitEntity( Vec3 start, Vec3 end ) {
        return ProjectileUtil.getEntityHitResult(
                level(),
                this,
                start,
                end,
                getBoundingBox().expandTowards( getDeltaMovement() ).inflate( 1.0D ),
                this::canHitEntity
        );
    }
    
    @Override
    protected boolean canHitEntity( Entity entity ) {
        return super.canHitEntity( entity )
                && (piercingIgnoreEntityIds == null || !piercingIgnoreEntityIds.contains( entity.getId() ))
                && !ignoredEntities.contains( entity.getId() );
    }
    
    @Override
    public void addAdditionalSaveData( CompoundTag saveTag ) {
        super.addAdditionalSaveData( saveTag );
        
        saveTag.putShort( "life", (short) life );
        
        if( lastState != null ) {
            saveTag.put( "inBlockState", NbtUtils.writeBlockState( lastState ) );
        }
        saveTag.putByte( "shake", (byte) shakeTime );
        saveTag.putBoolean( "inGround", inGround );
        saveTag.putByte( "pickup", (byte) pickup.ordinal() );
        saveTag.putDouble( "damage", baseDamage );
        saveTag.putBoolean( "crit", isCritArrow() );
        saveTag.putByte( "PierceLevel", getPierceLevel() );
        saveTag.putString( "SoundEvent", BuiltInRegistries.SOUND_EVENT.getKey( soundEvent ).toString() );
        saveTag.putBoolean( "ShotFromCrossbow", shotFromCrossbow() );
    }
    
    @Override
    public void readAdditionalSaveData( CompoundTag saveTag ) {
        super.readAdditionalSaveData( saveTag );
        
        life = saveTag.getShort( "life" );
        
        if( saveTag.contains( "inBlockState", Tag.TAG_COMPOUND ) ) {
            lastState = NbtUtils.readBlockState( level().holderLookup( Registries.BLOCK ), saveTag.getCompound( "inBlockState" ) );
        }
        shakeTime = saveTag.getByte( "shake" ) & 255;
        inGround = saveTag.getBoolean( "inGround" );
        
        if( saveTag.contains( "damage", Tag.TAG_ANY_NUMERIC ) ) {
            baseDamage = saveTag.getDouble( "damage" );
        }
        pickup = Pickup.byOrdinal( saveTag.getByte( "pickup" ) );
        setCritArrow( saveTag.getBoolean( "crit" ) );
        setPierceLevel( saveTag.getByte( "PierceLevel" ) );
        
        if( saveTag.contains( "SoundEvent", Tag.TAG_STRING ) ) {
            soundEvent = BuiltInRegistries.SOUND_EVENT.getOptional( ResourceLocation.parse( saveTag.getString( "SoundEvent" ) ) )
                    .orElse( getDefaultHitGroundSoundEvent() );
        }
    }
    
    @Override
    public void setOwner( @Nullable Entity owner ) {
        super.setOwner( owner );
        
        if( owner instanceof Player ) {
            pickup = ((Player) owner).getAbilities().instabuild ? Pickup.CREATIVE_ONLY : Pickup.ALLOWED;
        }
    }
    
    @Override
    public void playerTouch( Player player ) {
        if( !level().isClientSide && (inGround || isNoPhysics()) && shakeTime <= 0 ) {
            if( tryPickup( player ) ) {
                player.take( this, 1 );
                discard();
            }
        }
    }
    
    protected boolean tryPickup( Player player ) {
        return switch( pickup ) {
            case ALLOWED -> player.getInventory().add( getPickupItem() );
            case CREATIVE_ONLY -> player.getAbilities().instabuild;
            default -> false;
        };
    }
    
    protected ItemStack getPickupItem() {
        return new ItemStack( AQItems.WOODEN_DART.get() );
    }
    
    @Override
    protected Entity.MovementEmission getMovementEmission() {
        return Entity.MovementEmission.NONE;
    }
    
    public void setBaseDamage( double damage ) {
        this.baseDamage = damage;
    }
    
    public double getBaseDamage() {
        return baseDamage;
    }
    
    public void setKnockback( int knockbackLevel ) {
        knockback = knockbackLevel;
    }
    
    public int getKnockback() {
        return knockback;
    }
    
    @Override
    public boolean isAttackable() {
        return false;
    }
    
    @Override
    protected float getEyeHeight( Pose pose, EntityDimensions dimensions ) {
        return 0.13F;
    }
    
    public void setCritArrow( boolean crit ) {
        setFlag( FLAG_CRIT, crit );
    }
    
    public void setPierceLevel( byte pierceLevel ) {
        entityData.set( PIERCE_LEVEL, pierceLevel );
    }
    
    private void setFlag( int flag, boolean value ) {
        byte flags = entityData.get( ID_FLAGS );
        
        if( value ) {
            entityData.set( ID_FLAGS, (byte) (flags | flag) );
        }
        else {
            entityData.set( ID_FLAGS, (byte) (flags & ~flag) );
        }
    }
    
    public boolean isCritArrow() {
        byte b0 = entityData.get( ID_FLAGS );
        return (b0 & 1) != 0;
    }
    
    public boolean shotFromCrossbow() {
        byte b0 = entityData.get( ID_FLAGS );
        return (b0 & 4) != 0;
    }
    
    public byte getPierceLevel() {
        return this.entityData.get( PIERCE_LEVEL );
    }
    
    public void setEnchantmentEffectsFromEntity( LivingEntity livingEntity, float f ) {
        int power = EnchantmentHelper.getEnchantmentLevel( Enchantments.POWER_ARROWS, livingEntity );
        int punch = EnchantmentHelper.getEnchantmentLevel( Enchantments.PUNCH_ARROWS, livingEntity );
        setBaseDamage( (double) (f * 2.0F) + random.triangle( (double) level().getDifficulty().getId() * 0.11D, 0.57425D ) );
        
        if( power > 0 ) {
            setBaseDamage( getBaseDamage() + (double) power * 0.5D + 0.5D );
        }
        if( punch > 0 ) {
            setKnockback( punch );
        }
        if( EnchantmentHelper.getEnchantmentLevel( Enchantments.FLAMING_ARROWS, livingEntity ) > 0 ) {
            setSecondsOnFire( 100 );
        }
    }
    
    protected float getWaterInertia() {
        return 0.6F;
    }
    
    public void setNoPhysics( boolean value ) {
        noPhysics = value;
        setFlag( FLAG_NO_PHYSICS, value );
    }
    
    public boolean isNoPhysics() {
        if( !level().isClientSide ) {
            return noPhysics;
        }
        else {
            return (entityData.get( ID_FLAGS ) & 2) != 0;
        }
    }
    
    public enum Pickup {
        DISALLOWED,
        ALLOWED,
        CREATIVE_ONLY;
        
        public static Pickup byOrdinal( int ordinal ) {
            if( ordinal < 0 || ordinal > values().length ) {
                ordinal = 0;
            }
            return values()[ordinal];
        }
    }
}
