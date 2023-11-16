package com.obsidian_core.archaic_quest.common.entity;

import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundAddEntityPacket;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class AQChestBoat extends ChestBoat implements IModBoat {

    public AQChestBoat(EntityType<? extends Boat> type, Level level) {
        super(type, level);
    }

    public AQChestBoat(Level level, double x, double y, double z) {
        this(AQEntities.AQ_CHEST_BOAT.get(), level);
        setPos(x, y, z);
        xo = x;
        yo = y;
        zo = z;
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return new ClientboundAddEntityPacket(this);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag compoundTag) {
        compoundTag.putString("model", _getBoatType().getName());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag compoundTag) {
        if (compoundTag.contains("model", Tag.TAG_STRING)) {
            entityData.set(DATA_ID_TYPE, AQBoat.BoatType.byName(compoundTag.getString("model")).ordinal());
        }
    }

    @Override
    protected void checkFallDamage(double y, boolean onGround, BlockState state, BlockPos pos) {
        lastYd = getDeltaMovement().y;

        if (!isPassenger()) {
            if (onGround) {
                if (fallDistance > 3.0F) {
                    if (status != Boat.Status.ON_LAND) {
                        resetFallDistance();
                        return;
                    }

                    causeFallDamage(fallDistance, 1.0F, DamageSource.FALL);
                    if (!level.isClientSide && !isRemoved()) {
                        kill();

                        if (level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            for (int i = 0; i < 3; ++i) {
                                spawnAtLocation(_getBoatType().getPlanks());
                            }

                            for (int j = 0; j < 2; ++j) {
                                spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }
                resetFallDistance();
            }
            else if (!level.getFluidState(blockPosition().below()).is(FluidTags.WATER) && y < 0.0D) {
                fallDistance -= (float)y;
            }
        }
    }

    @Override
    public Item getDropItem() {
        switch (AQBoat.BoatType.byId(entityData.get(DATA_ID_TYPE))) {
            case AHUEHUETE:
                return AQBlocks.AHUEHUETE_WOOD_SET.getBoat().get();
        }
        return Items.OAK_BOAT;
    }

    @Override
    public void setBoatType(AQBoat.BoatType type) {
        entityData.set(DATA_ID_TYPE, type.ordinal());
    }

    public AQBoat.BoatType _getBoatType() {
        return AQBoat.BoatType.byId(entityData.get(DATA_ID_TYPE));
    }

    @Deprecated
    @Override
    public Type getBoatType() {
        return Type.OAK;
    }
}
