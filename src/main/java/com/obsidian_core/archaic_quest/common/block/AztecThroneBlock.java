package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.base.ThroneType;
import com.obsidian_core.archaic_quest.common.block.multiblock.BaseEntityMultiBlock;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecThroneBlock extends BaseEntityMultiBlock {
    
    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    private static final VoxelShape shape = Block.box( 0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D );
    
    private final ThroneType throneType;
    
    
    public AztecThroneBlock( ThroneType throneType ) {
        super( BlockBehaviour.Properties.copy( AQBlocks.ANDESITE_AZTEC_BRICKS_0.get() ).noOcclusion() );
        registerDefaultState( stateDefinition.any().setValue( FACING, Direction.NORTH ) );
        this.throneType = throneType;
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public VoxelShape getShape( BlockState state, BlockGetter world, BlockPos pos, CollisionContext context ) {
        return shape;
    }
    
    public ThroneType getThroneType() {
        return throneType;
    }
    
    @Override
    public RenderShape getRenderShape( BlockState state ) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
    
    @Override
    public BlockState getStateForPlacement( BlockPlaceContext context ) {
        Direction direction = context.getHorizontalDirection();
        return defaultBlockState().setValue( FACING, direction );
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public BlockState rotate( BlockState state, Rotation rotation ) {
        return state.setValue( FACING, rotation.rotate( state.getValue( FACING ) ) );
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public BlockState mirror( BlockState state, Mirror mirror ) {
        return state.rotate( mirror.getRotation( state.getValue( FACING ) ) );
    }
    
    @Override
    protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> stateBuilder ) {
        super.createBlockStateDefinition( stateBuilder.add( FACING ) );
    }
    
    
    @Override
    @Nullable
    public IMultiBlockEntity<?> newMultiBlockEntity( BlockPos pos, BlockState state, boolean isMaster ) {
        return new AztecThroneBlockEntity( pos, state, isMaster );
    }
}
