package com.obsidian_core.archaic_quest.common.block;

import com.obsidian_core.archaic_quest.common.block.base.ThroneType;
import com.obsidian_core.archaic_quest.common.block.misc.DirectionalShape;
import com.obsidian_core.archaic_quest.common.block.multiblock.BaseEntityMultiBlock;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class AztecThroneBlock extends BaseEntityMultiBlock {
    
    public static DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static EnumProperty<Part> PART = EnumProperty.create( "part", Part.class );
    
    private static final VoxelShape BASE_SHAPE = Block.box( 0.0D, 0.0D, 0.0D, 16.0D, 11.0D, 16.0D );
    private static final DirectionalShape BACK_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 10.0, 16.0 )
            .build();
    private static final DirectionalShape UPPER_BACK_SHAPE = DirectionalShape.builder()
            .x( 0.0, 16.0 )
            .y( 0.0, 12.0 )
            .z( 10.0, 16.0 )
            .build();
    private static final DirectionalShape ARM_SHAPE = DirectionalShape.builder()
            .x( 10.0, 16.0 )
            .y( 0.0, 16.0 )
            .z( 0.0, 16.0 )
            .build();
    
    private final ThroneType throneType;
    
    
    public AztecThroneBlock( ThroneType throneType ) {
        super( BlockBehaviour.Properties.copy( AQBlocks.ANDESITE_AZTEC_BRICKS_0.get() ).noOcclusion() );
        registerDefaultState( stateDefinition.any()
                .setValue( MASTER, false )
                .setValue( FACING, Direction.NORTH )
                .setValue( PART, Part.BASE )
        );
        this.throneType = throneType;
    }
    
    @Override
    @SuppressWarnings( "deprecation" )
    public VoxelShape getShape( BlockState state, BlockGetter world, BlockPos pos, CollisionContext context ) {
        Direction facing = state.getValue( FACING );
        
        return switch( state.getValue( PART ) ) {
            case BACK -> BACK_SHAPE.getFor( facing );
            case UPPER_BACK -> UPPER_BACK_SHAPE.getFor( facing );
            case ARM -> ARM_SHAPE.getFor( facing );
            default -> BASE_SHAPE;
        };
    }
    
    public ThroneType getThroneType() {
        return throneType;
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
        super.createBlockStateDefinition( stateBuilder.add( FACING, PART ) );
    }
    
    @Override
    @Nullable
    public IMultiBlockEntity<?> newMultiBlockEntity( BlockPos pos, BlockState state, boolean isMaster ) {
        return new AztecThroneBlockEntity( pos, state, isMaster );
    }
    
    public enum Part implements StringRepresentable {
        BASE( "base" ),
        ARM( "arm" ),
        BACK( "back" ),
        UPPER_BACK( "upper_back" );
        
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
