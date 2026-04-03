package com.obsidian_core.archaic_quest.common.blockentity.multiblock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public abstract class BaseMultiBlockEntity<T extends BlockEntity> extends BlockEntity implements IMultiBlockEntity<T> {
    
    @Nullable
    private BlockPos masterPos;
    private boolean isMaster;
    
    
    public BaseMultiBlockEntity( BlockEntityType<?> type, BlockPos pos, BlockState state ) {
        this( type, pos, state, false );
    }
    
    public BaseMultiBlockEntity( BlockEntityType<?> type, BlockPos pos, BlockState state, boolean isMaster ) {
        super( type, pos, state );
        this.isMaster = isMaster;
    }
    
    
    @Override
    public boolean isMaster() {
        return isMaster;
    }
    
    @Override
    @Nullable
    public BlockPos getMasterPos() {
        return masterPos;
    }
    
    @Override
    public void loadMultiData( CompoundTag saveTag ) {
        if( saveTag.contains( KEY_MASTER_POSITION, CompoundTag.TAG_ANY_NUMERIC ) ) {
            isMaster = saveTag.getBoolean( KEY_MASTER_POSITION );
        }
        if( saveTag.contains( KEY_MASTER_POSITION, CompoundTag.TAG_INT_ARRAY ) ) {
            final int[] coords = saveTag.getIntArray( KEY_MASTER_POSITION );
            
            if( coords.length == 3 )
                masterPos = new BlockPos( coords[0], coords[1], coords[2] );
        }
    }
    
    @Override
    protected void saveAdditional( CompoundTag saveTag ) {
        super.saveAdditional( saveTag );
        saveMultiData( saveTag );
    }
    
    @Override
    public void load( CompoundTag saveTag ) {
        super.load( saveTag );
        loadMultiData( saveTag );
    }
}
