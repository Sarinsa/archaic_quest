package com.obsidian_core.archaic_quest.common.item;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.api.TorchInteraction;
import com.obsidian_core.archaic_quest.api.TorchLitType;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.misc.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.ApiStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class AdventurersTorchItem extends Item {
    
    
    private static final Map<Block, Pair<Predicate<BlockState>, TorchLitType>> TORCH_LIGHTERS = new HashMap<>();
    private static final Map<Block, TorchInteraction> TORCH_INTERACTIONS = new HashMap<>();
    
    private static final String KEY_MOD_DATA = "ArchaicQuestData";
    private static final String KEY_LIT_FLAG = "LitFlag";
    
    
    //
    // ----------------- Item implementation -----------------
    //
    
    public AdventurersTorchItem() {
        super( new Item.Properties()
                .stacksTo( 1 )
                .rarity( Rarity.UNCOMMON )
        );
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use( Level level, Player player, InteractionHand hand ) {
        BlockHitResult hitResult = getPlayerPOVHitResult( level, player, ClipContext.Fluid.WATER );
        BlockState hitState = level.getBlockState( hitResult.getBlockPos() );
        ItemStack heldItem = player.getItemInHand( hand );
        
        // Return early if we are not clicking water or the torch is unlit.
        if( !hitState.getFluidState().is( FluidTags.WATER ) || getLitFlag( heldItem ) == TorchLitType.UNLIT )
            return super.use( level, player, hand );
        
        // Extinguish the torch and play some effects.
        setLitFlag( heldItem, TorchLitType.UNLIT );
        level.playSound( null, hitResult.getBlockPos(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.8F, 1.0F );
        
        double x = hitResult.getBlockPos().getX();
        double y = hitResult.getBlockPos().getY();
        double z = hitResult.getBlockPos().getZ();
        
        for( int count = 0; count < 8; ++count ) {
            level.addParticle(
                    ParticleTypes.LARGE_SMOKE,
                    x + Math.random(),
                    y + Math.random(),
                    z + Math.random(),
                    0.0D,
                    0.0D,
                    0.0D
            );
        }
        return InteractionResultHolder.sidedSuccess( heldItem, level.isClientSide );
    }
    
    @Override
    public InteractionResult useOn( UseOnContext context ) {
        final ItemStack torch = context.getItemInHand();
        final Level level = context.getLevel();
        final BlockPos clickedPos = context.getClickedPos();
        final BlockState clickedState = level.getBlockState( clickedPos );
        final Block clickedBlock = clickedState.getBlock();
        final TorchLitType type = getLitFlag( torch );
        
        // If the torch is not unlit, check if the clicked state has a registered interaction.
        if( type != TorchLitType.UNLIT ) {
            if( clickedState.getFluidState().isEmpty() && TORCH_INTERACTIONS.containsKey( clickedBlock ) ) {
                return TORCH_INTERACTIONS.get( clickedBlock ).interact( level, clickedState, clickedPos, type )
                        ? InteractionResult.SUCCESS
                        : InteractionResult.FAIL;
            }
        }
        // Check if the clicked state can light the torch.
        else {
            if( TORCH_LIGHTERS.containsKey( clickedBlock ) ) {
                if( TORCH_LIGHTERS.get( clickedBlock ).getFirst().test( clickedState ) ) {
                    TorchLitType torchLitType = TORCH_LIGHTERS.get( clickedBlock ).getSecond();
                    setLitFlag( torch, torchLitType );
                    return InteractionResult.sidedSuccess( level.isClientSide );
                }
            }
        }
        return super.useOn( context );
    }
    
    
    //
    // ----------------- Torch interaction registry -----------------
    //
    
    /** Archaic Quest's default behaviors. */
    public static void registerDefaults() {
        registerTorchLightable( Blocks.FIRE, ( state ) -> true, TorchLitType.NORMAL );
        registerTorchLightable( Blocks.SOUL_FIRE, ( state ) -> true, TorchLitType.SOULFIRE );
        registerTorchLightable( Blocks.CAMPFIRE, ( state ) -> state.getValue( CampfireBlock.LIT ), TorchLitType.NORMAL );
        registerTorchLightable( Blocks.SOUL_CAMPFIRE, ( state ) -> state.getValue( CampfireBlock.LIT ), TorchLitType.SOULFIRE );
        
        registerTorchInteraction( Blocks.CAMPFIRE, ( level, state, pos, type ) -> {
            if( !state.getValue( CampfireBlock.LIT ) ) {
                if( type == TorchLitType.SOULFIRE ) {
                    level.setBlockAndUpdate( pos, Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue( CampfireBlock.LIT, true ) );
                }
                else {
                    level.setBlockAndUpdate( pos, state.setValue( CampfireBlock.LIT, true ) );
                }
                return true;
            }
            return false;
        } );
        
        registerTorchInteraction( Blocks.SOUL_CAMPFIRE, ( level, state, pos, type ) -> {
            if( !state.getValue( CampfireBlock.LIT ) ) {
                if( type == TorchLitType.SOULFIRE ) {
                    level.setBlockAndUpdate( pos, state.setValue( CampfireBlock.LIT, true ) );
                }
                else {
                    level.setBlockAndUpdate( pos, Blocks.CAMPFIRE.defaultBlockState().setValue( CampfireBlock.LIT, true ) );
                }
                return true;
            }
            return false;
        } );
    }
    
    /**
     * Registers a block that can light the Adventurer's Torch.
     * This is for internal use; other mods should use the Archaic Quest API.
     *
     * @param block     The block that is able to light the torch.
     * @param predicate The predicate to test if the block can light the torch.
     * @param type      The lit type to apply to the torch. Using {@link TorchLitType#UNLIT}
     *                  effectively makes this an extinguisher.
     */
    @ApiStatus.Internal
    public static void registerTorchLightable( Block block, Predicate<BlockState> predicate, TorchLitType type ) {
        Objects.requireNonNull( block );
        Objects.requireNonNull( predicate );
        Objects.requireNonNull( type );
        
        if( !ForgeRegistries.BLOCKS.containsValue( block ) ) {
            ArchaicQuest.LOGGER.warn( "Attempted to register torch lighter for unregistered block! Block obj: {}", block.toString() );
        }
        else if( TORCH_LIGHTERS.containsKey( block ) ) {
            ArchaicQuest.LOGGER.warn( "Attempted to register duplicate torch lighter for block '{}'", ForgeRegistries.BLOCKS.getKey( block ) );
        }
        else {
            TORCH_LIGHTERS.put( block, Pair.of( predicate, type ) );
        }
    }
    
    /**
     * Registers a block that can be interacted with using a lit Adventurer's Torch.
     * This is for internal use; other mods should use the Archaic Quest API.
     *
     * @param block            The block that is able to light the torch.
     * @param torchInteraction The logic to use when the block is clicked with a lit adventurer's torch.
     */
    @ApiStatus.Internal
    public static void registerTorchInteraction( Block block, TorchInteraction torchInteraction ) {
        Objects.requireNonNull( block );
        Objects.requireNonNull( torchInteraction );
        
        if( !ForgeRegistries.BLOCKS.containsValue( block ) ) {
            ArchaicQuest.LOGGER.warn( "Attempted to register torch interactor for unregistered block! Block obj: {}", block.toString() );
        }
        else if( TORCH_INTERACTIONS.containsKey( block ) ) {
            ArchaicQuest.LOGGER.warn( "Attempted to register duplicate torch interactor for block '{}'", ForgeRegistries.BLOCKS.getKey( block ) );
        }
        else {
            TORCH_INTERACTIONS.put( block, torchInteraction );
        }
    }
    
    /**
     * @return The TorchLitType in the given item stack's NBT, or {@link TorchLitType#UNLIT}
     * if something went wrong or the tag didn't contain an ordinal for a TorchLitType.
     */
    public static TorchLitType getLitFlag( ItemStack itemStack ) {
        if( !(itemStack.getItem() instanceof AdventurersTorchItem) )
            return TorchLitType.UNLIT;
        
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        CompoundTag modData = NBTHelper.getOrCreate( compoundTag, KEY_MOD_DATA );
        
        if( modData.contains( KEY_LIT_FLAG, Tag.TAG_ANY_NUMERIC ) ) {
            return TorchLitType.fromOrdinal( modData.getInt( KEY_LIT_FLAG ) );
        }
        return TorchLitType.UNLIT;
    }
    
    /**
     * Saves a TorchLitType to the specified item stack's NBT.
     */
    public static void setLitFlag( ItemStack itemStack, TorchLitType flag ) {
        if( !(itemStack.getItem() instanceof AdventurersTorchItem) ) return;
        CompoundTag compoundTag = itemStack.getOrCreateTag();
        CompoundTag modData = NBTHelper.getOrCreate( compoundTag, KEY_MOD_DATA );
        modData.putInt( KEY_LIT_FLAG, flag.ordinal() );
    }
}
