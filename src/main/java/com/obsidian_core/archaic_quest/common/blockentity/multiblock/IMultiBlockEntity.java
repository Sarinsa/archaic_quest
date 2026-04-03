package com.obsidian_core.archaic_quest.common.blockentity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;

/** Implemented by block entities that have multiblock behavior. */
public interface IMultiBlockEntity<T extends BlockEntity> {
    
    // NBT keys
    String KEY_MASTER_POSITION = "MasterPosition";
    String KEY_IS_MASTER = "IsMaster";
    
    
    /** @return This multiblock entity as a block entity. */
    default T asBlockEntity() {
        // noinspection unchecked
        return (T) this;
    }
    
    /**
     * @return True if this multiblock entity is considered
     * the main block entity of the whole.
     */
    boolean isMaster();
    
    /**
     * @return The block position of the master block entity.
     * This should return null for non-master block entities.
     */
    @Nullable
    BlockPos getMasterPos();
    
    /** Saves multiblock related data to the save tag. */
    default void saveMultiData( CompoundTag saveTag ) {
        saveTag.putBoolean( KEY_IS_MASTER, isMaster() );
        
        final BlockPos pos = getMasterPos();
        if( pos != null ) {
            saveTag.putIntArray( KEY_MASTER_POSITION, new int[] { pos.getX(), pos.getY(), pos.getZ() } );
        }
    }
    
    /** Loads multiblock related data from save tag. */
    void loadMultiData( CompoundTag saveTag );
}
