package com.obsidian_core.archaic_quest.common.blockentity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntArrayTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;

import javax.annotation.Nullable;

/** Implemented by block entities that have multiblock behavior. */
public interface IMultiBlockEntity<T extends BlockEntity> {
    
    // NBT keys
    String KEY_CHILD_POSITIONS = "ChildPositions";
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
    
    /**
     * Sets the block position of the master block entity.
     * The master block entity itself does not need to know this,
     * but child block entities generally do.
     */
    void setMasterPos( @Nullable BlockPos pos );
    
    /**
     * @return A {@link BlockPos} array containing all
     * the positions of all the multiblock's
     * parts except for the master block entity itself.
     * This is expected to return null for non-master
     * block entities.
     */
    @Nullable
    BlockPos[] getChildPositions();
    
    /** Sets the child positions for this multiblock entity. */
    void setChildPositions( @Nullable BlockPos[] childPositions );
    
    /** Saves multiblock related data to the save tag. */
    default void saveMultiData( CompoundTag saveTag ) {
        saveTag.putBoolean( KEY_IS_MASTER, isMaster() );
        
        final BlockPos pos = getMasterPos();
        if( pos != null ) {
            saveTag.putIntArray( KEY_MASTER_POSITION, new int[] { pos.getX(), pos.getY(), pos.getZ() } );
        }
        final BlockPos[] childPositions = getChildPositions();
        if( childPositions != null && childPositions.length > 0 ) {
            ListTag listTag = new ListTag();
            for( BlockPos childPosition : childPositions ) {
                listTag.add( new IntArrayTag( new int[] {
                        childPosition.getX(),
                        childPosition.getY(),
                        childPosition.getZ()
                } ) );
            }
            saveTag.put( KEY_CHILD_POSITIONS, listTag );
        }
    }
    
    /** Loads multiblock related data from save tag. */
    void loadMultiData( CompoundTag saveTag );
    
    /**
     * @return The current block entity at the given block position, ONLY if it is
     * an instance of {@link IMultiBlockEntity}. Returns null otherwise.
     */
    @Nullable
    static IMultiBlockEntity<?> getExisting( LevelAccessor level, BlockPos pos ) {
        BlockEntity blockEntity = level.getExistingBlockEntity( pos );
        return blockEntity instanceof IMultiBlockEntity ? (IMultiBlockEntity<?>) blockEntity : null;
    }
}
