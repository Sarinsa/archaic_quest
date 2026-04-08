package com.obsidian_core.archaic_quest.common.block.multiblock;

import com.obsidian_core.archaic_quest.common.block.misc.DirectionalShape;
import com.obsidian_core.archaic_quest.common.blockentity.AztecWorktableBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecWorktableBlock extends BaseEntityMultiBlock {
    
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final EnumProperty<Part> PART = EnumProperty.create( "part", Part.class );
    
    private static final DirectionalShape BASE_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 16.0 )
            .build();
    private static final DirectionalShape SIDE_SHAPE = DirectionalShape.builder()
            .x( 5.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 16.0 )
            .build();
    private static final DirectionalShape FRONT_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 8.0 )
            .build();
    private static final DirectionalShape LEFT_CORNER_SHAPE = DirectionalShape.builder()
            .x( 5.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 8.0 )
            .build();
    private static final DirectionalShape RIGHT_CORNER_SHAPE = DirectionalShape.builder()
            .x( 0.0, 11.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 8.0 )
            .build();
    private static final DirectionalShape PANEL_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 8.0 )
            .build();
    
    
    public AztecWorktableBlock( Properties properties ) {
        super( properties );
        registerDefaultState( stateDefinition.any()
                .setValue( FACING, Direction.NORTH )
                .setValue( PART, Part.BASE )
                .setValue( MASTER, false )
        );
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public InteractionResult use( BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult ) {
        Part part = state.getValue( PART );
        
        // Only allow opening the container when clicking one of the "base" parts
        if( part != Part.PANEL ) {
            if( level.isClientSide ) {
                return InteractionResult.SUCCESS;
            }
            else {
                openContainer( level, pos, player );
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.PASS;
    }
    
    @Deprecated
    @SuppressWarnings( "deprecation" )
    public VoxelShape getShape( BlockState state, BlockGetter level, BlockPos pos, CollisionContext context ) {
        Part part = state.getValue( PART );
        Direction facing = state.getValue( FACING );
        
        return switch( part ) {
            case BASE -> BASE_SHAPE.getFor( facing );
            case SIDE -> SIDE_SHAPE.getFor( facing );
            case FRONT -> FRONT_SHAPE.getFor( facing );
            case LEFT_CORNER -> LEFT_CORNER_SHAPE.getFor( facing );
            case RIGHT_CORNER -> RIGHT_CORNER_SHAPE.getFor( facing );
            case PANEL -> PANEL_SHAPE.getFor( facing );
        };
    }
    
    protected void openContainer( Level level, BlockPos pos, Player player ) {
        BlockEntity blockEntity = level.getExistingBlockEntity( pos );
        
        if( blockEntity instanceof AztecWorktableBlockEntity ) {
            player.openMenu( (MenuProvider) blockEntity );
        }
    }
    
    @Override
    @Nullable
    public IMultiBlockEntity<?> newMultiBlockEntity( BlockPos pos, BlockState state, boolean isMaster ) {
        return new AztecWorktableBlockEntity( pos, state, isMaster );
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
    @SuppressWarnings( "deprecation" )
    public boolean isPathfindable( BlockState state, BlockGetter level, BlockPos pos, PathComputationType pathType ) {
        return false;
    }
    
    @Override
    protected void createBlockStateDefinition( StateDefinition.Builder<Block, BlockState> stateBuilder ) {
        super.createBlockStateDefinition( stateBuilder.add( FACING, PART ) );
    }
    
    public enum Part implements StringRepresentable {
        BASE( "base" ),
        SIDE( "side" ),
        FRONT( "front" ),
        LEFT_CORNER( "left_corner" ),
        RIGHT_CORNER( "right_corner" ),
        PANEL( "panel" );
        
        private final String name;
        
        Part( String name ) {
            this.name = name;
        }
        
        @Override
        public String getSerializedName() {
            return name;
        }
    }
}
