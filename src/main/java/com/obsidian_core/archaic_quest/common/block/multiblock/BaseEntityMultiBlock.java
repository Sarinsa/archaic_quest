package com.obsidian_core.archaic_quest.common.block.multiblock;

import com.obsidian_core.archaic_quest.common.block.misc.AQStateProperties;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.PushReaction;

import javax.annotation.Nullable;

public abstract class BaseEntityMultiBlock extends BaseEntityBlock implements IMultiBlockEntityProvider {
    
    public static final BooleanProperty MASTER = AQStateProperties.MASTER;
    
    
    protected BaseEntityMultiBlock( Properties properties ) {
        super( properties );
        registerDefaultState( stateDefinition.any().setValue( MASTER, false ) );
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
    public RenderShape getRenderShape( BlockState state ) {
        return state.getValue( MASTER ) ? RenderShape.ENTITYBLOCK_ANIMATED : RenderShape.INVISIBLE;
    }
    
    /**
     * Destroy all blocks that are part of the multiblock construct.
     */
    @Override
    @SuppressWarnings( "deprecation" )
    public void onRemove( BlockState state, Level level, BlockPos pos, BlockState newState, boolean flag ) {
        IMultiBlockEntity<?> multiBlockEntity = IMultiBlockEntity.getExisting( level, pos );
        
        // Multiblock entity should be present, but let's check to be safe
        if( multiBlockEntity == null ) return;
        
        if( !multiBlockEntity.isMaster() && multiBlockEntity.getMasterPos() != null )
            multiBlockEntity = IMultiBlockEntity.getExisting( level, multiBlockEntity.getMasterPos() );
        
        // Master block entity should be present, but let's check to be safe
        if( multiBlockEntity == null ) return;
        
        final BlockPos[] childPositions = multiBlockEntity.getChildPositions();
        
        if( childPositions != null ) {
            for( BlockPos childPos : childPositions ) {
                if( !childPos.equals( pos ) ) {
                    level.destroyBlock( childPos, false );
                }
            }
        }
        BlockPos masterPos = multiBlockEntity.asBlockEntity().getBlockPos();
        
        if( !masterPos.equals( pos ) ) {
            level.destroyBlock( masterPos, true );
        }
        super.onRemove( state, level, pos, newState, flag );
    }
    
    @Override
    protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> builder ) {
        super.createBlockStateDefinition( builder.add( MASTER ) );
    }
}
