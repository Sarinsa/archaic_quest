package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.util.DirectionalShape;
import com.obsidian_core.archaic_quest.common.item.misc.IMacheteCuttable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.IForgeShearable;

import javax.annotation.Nullable;

public class CuttableVinesBlock extends Block implements IForgeShearable, IMacheteCuttable {
    
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty CUT = BooleanProperty.create( "cut" );
    public static final BooleanProperty CAN_GROW = BooleanProperty.create( "can_grow" );
    
    private static final DirectionalShape FULL_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 15.0, 16.0 )
            .build();
    private static final DirectionalShape CUT_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 8.0, 16.0 )
            .z( 15.0, 16.0 )
            .build();
    
    
    public CuttableVinesBlock( Properties properties ) {
        super( properties );
        registerDefaultState( stateDefinition.any()
                .setValue( FACING, Direction.NORTH )
                .setValue( CUT, false )
                .setValue( CAN_GROW, true )
        );
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public VoxelShape getShape( BlockState state, BlockGetter world, BlockPos pos, CollisionContext context ) {
        Direction dir = state.getValue( FACING );
        return state.getValue( CUT ) ? CUT_SHAPE.getFor( dir ) : FULL_SHAPE.getFor( dir );
    }
    
    public boolean isCut( BlockState state ) {
        return state.getValue( CUT );
    }
    
    public boolean canGrow( BlockState state ) {
        return state.getValue( CAN_GROW );
    }
    
    @Override
    public boolean isRandomlyTicking( BlockState state ) {
        return canGrow( state );
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public void randomTick( BlockState state, ServerLevel level, BlockPos pos, RandomSource random ) {
        if( !level.isAreaLoaded( pos, 1 ) ) return;
        
        if( random.nextInt( 10 ) == 0 ) {
            BlockPos belowPos = pos.below();
            
            if( belowPos.getY() > 0 && level.getBlockState( belowPos ).isAir() ) {
                level.setBlock( belowPos, state, 2 );
            }
        }
    }
    
    @Override
    @Nullable
    public BlockState getStateForPlacement( BlockPlaceContext context ) {
        Direction face = context.getClickedFace();
        BlockPos pos = context.getClickedPos();
        BlockPos behindPos = pos.relative( face.getOpposite() );
        Level level = context.getLevel();
        
        if( face == Direction.DOWN || face == Direction.UP ) {
            face = context.getHorizontalDirection().getOpposite();
        }
        BlockState behindState = level.getBlockState( behindPos );
        BlockState aboveState = level.getBlockState( pos.above() );
        
        if( Block.isFaceFull( level.getBlockState( behindPos ).getCollisionShape( level, behindPos ), face )
                || behindState.isFaceSturdy( level, behindPos, face )
                || (behindState.getBlock() instanceof SlabBlock && behindState.getValue( SlabBlock.TYPE ) == SlabType.TOP)
                || (aboveState.getBlock() instanceof VerticalSlabBlock && aboveState.getValue( VerticalSlabBlock.SLAB_STATE ).getDirection() == face)
                || aboveState.isFaceSturdy( level, pos.above(), Direction.DOWN ) ) {
            return defaultBlockState().setValue( FACING, face );
        }
        return null;
    }
    
    @Deprecated
    @SuppressWarnings( "deprecation" )
    public boolean canSurvive( BlockState state, LevelReader level, BlockPos pos ) {
        Direction face = state.getValue( FACING );
        BlockPos behindPos = pos.relative( face.getOpposite() );
        BlockState behindState = level.getBlockState( behindPos );
        BlockState aboveState = level.getBlockState( pos.above() );
        
        return Block.isFaceFull( level.getBlockState( behindPos ).getCollisionShape( level, behindPos ), face )
                || behindState.isFaceSturdy( level, behindPos, face )
                || (behindState.getBlock() instanceof SlabBlock && behindState.getValue( SlabBlock.TYPE ) == SlabType.TOP)
                || (aboveState.getBlock() instanceof VerticalSlabBlock && aboveState.getValue( VerticalSlabBlock.SLAB_STATE ).getDirection() == face)
                || aboveState.isFaceSturdy( level, pos.above(), Direction.DOWN )
                || (aboveState.is( this ) && aboveState.getValue( FACING ) == face && !aboveState.getValue( CUT ));
    }
    
    @Deprecated
    @SuppressWarnings( "deprecation" )
    @Override
    public BlockState updateShape( BlockState newState, Direction direction, BlockState state, LevelAccessor level, BlockPos pos, BlockPos pos1 ) {
        return !newState.canSurvive( level, pos )
                ? Blocks.AIR.defaultBlockState()
                : super.updateShape( newState, direction, state, level, pos, pos1 );
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
        stateBuilder.add( FACING, CUT, CAN_GROW );
    }
    
    @Override
    public boolean onCut( UseOnContext useContext ) {
        final Level level = useContext.getLevel();
        final BlockPos pos = useContext.getClickedPos();
        final BlockState state = level.getBlockState( pos );
        final Player player = useContext.getPlayer();
        
        if( player != null && player.isShiftKeyDown() ) {
            level.setBlock( pos, state.setValue( CuttableVinesBlock.CAN_GROW, false ), 2 );
        }
        else {
            level.setBlock( pos, state.setValue( CuttableVinesBlock.CUT, true ).setValue( CuttableVinesBlock.CAN_GROW, false ), 2 );
        }
        level.playSound( player, pos, SoundEvents.MOSS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F );
        return true;
    }
}
