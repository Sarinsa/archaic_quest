package com.obsidian_core.archaic_quest.common.block.skull;

import com.obsidian_core.archaic_quest.common.block.util.DirectionalShape;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

@SuppressWarnings( "deprecation" )
public class SimpleWallSkullBlock extends SimpleAbstractSkullBlock {
    
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    
    private static final DirectionalShape SHAPE = DirectionalShape.builder()
            .x( 4.0, 12.0 )
            .y( 4.0, 12.0 )
            .z( 8.0, 16.0 )
            .build();
    
    
    public SimpleWallSkullBlock( Properties properties, boolean animal, String textureName ) {
        super( properties, animal, textureName );
        registerDefaultState( stateDefinition.any().setValue( FACING, Direction.NORTH ) );
    }
    
    
    @Override
    public VoxelShape getShape( BlockState state, BlockGetter level, BlockPos pos, CollisionContext context ) {
        return SHAPE.getFor( state.getValue( FACING ) );
    }
    
    @Override
    @Nullable
    public BlockState getStateForPlacement( BlockPlaceContext context ) {
        BlockState state = defaultBlockState();
        BlockGetter level = context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction[] lookingDir = context.getNearestLookingDirections();
        
        for( Direction direction : lookingDir ) {
            if( direction.getAxis().isHorizontal() ) {
                Direction oppositeDir = direction.getOpposite();
                state = state.setValue( FACING, oppositeDir );
                
                if( !level.getBlockState( clickedPos.relative( direction ) ).canBeReplaced( context ) ) {
                    return state;
                }
            }
        }
        return null;
    }
    
    @Override
    public BlockState rotate( BlockState state, Rotation rotation ) {
        return state.setValue( FACING, rotation.rotate( state.getValue( FACING ) ) );
    }
    
    @Override
    public BlockState mirror( BlockState state, Mirror mirror ) {
        return state.rotate( mirror.getRotation( state.getValue( FACING ) ) );
    }
    
    @Override
    protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> stateBuilder ) {
        stateBuilder.add( FACING );
    }
}
