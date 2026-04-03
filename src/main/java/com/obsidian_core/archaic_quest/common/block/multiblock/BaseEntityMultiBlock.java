package com.obsidian_core.archaic_quest.common.block.multiblock;

import com.obsidian_core.archaic_quest.common.block.misc.AQStateProperties;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

public abstract class BaseEntityMultiBlock extends BaseEntityBlock {
    
    public static final BooleanProperty MASTER = AQStateProperties.MASTER;
    
    
    protected BaseEntityMultiBlock( Properties properties ) {
        super( properties );
        registerDefaultState( stateDefinition.any().setValue( MASTER, true ) );
    }
    
    @Override
    @Nullable
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        IMultiBlockEntity<?> multiBlockEntity = newMultiBlockEntity( pos, state, state.getValue( MASTER ) );
        return multiBlockEntity == null ? null : multiBlockEntity.asBlockEntity();
    }
    
    @Nullable
    public abstract IMultiBlockEntity<?> newMultiBlockEntity( BlockPos pos, BlockState state, boolean isMaster );
    
    @Override
    public PushReaction getPistonPushReaction( BlockState state ) {
        return PushReaction.BLOCK;
    }
    
    @Override
    protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
        super.createBlockStateDefinition( builder.add( MASTER ) );
    }
}
