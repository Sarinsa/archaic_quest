package com.obsidian_core.archaic_quest.common.util;

import com.obsidian_core.archaic_quest.common.block.multiblock.IMultiBlockEntityProvider;
import com.obsidian_core.archaic_quest.common.block.util.AQStateProperties;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Helper class for defining placeable multiblock constructs
 * that can be rotated upon placement.
 */
public class PlaceableConstruct {
    
    /** The list of placeable blocks that make up this multiblock construct. */
    private final List<PlaceableBlock> blocksToPlace;
    /**
     * True if this construct will be placing blocks
     * that have multiblock block entities with master
     * and sub/child block entities.
     */
    private final boolean isMultiBlocKEntity;
    
    /**
     * Creates a new placeable construct instance with
     * the provided list of blocks to place. The list
     * cannot be null and cannot be empty.
     */
    public PlaceableConstruct( List<PlaceableBlock> blocksToPlace, boolean isMultiBlocKEntity ) {
        if( blocksToPlace == null || blocksToPlace.isEmpty() ) {
            throw new IllegalArgumentException( "PlaceableConstruct must have a non-empty list of blocks to place!" );
        }
        this.blocksToPlace = blocksToPlace;
        this.isMultiBlocKEntity = isMultiBlocKEntity;
        sanitizePlacements();
    }
    
    /**
     * Places this construct into the world.
     *
     * @param level     The world to place the construct in.
     * @param origin    The position of origin where we are placing.
     * @param direction The direction to rotate the blocks to place around. Can be null.
     */
    public void place( LevelAccessor level, BlockPos origin, @Nullable Direction direction ) {
        if( isMultiBlocKEntity ) {
            List<BlockPos> childPositions = new ArrayList<>();
            BlockPos masterPos = null;
            
            for( PlaceableBlock placeable : blocksToPlace ) {
                // Place all blocks first
                placeable.placeRelative( level, origin, DirectionUtil.fromDirection( direction ) );
                
                // Check if the placed state is a multiblock entity provider
                if( placeable.getBlockState().getBlock() instanceof IMultiBlockEntityProvider ) {
                    if( placeable.getBlockState().getValue( AQStateProperties.MASTER ) ) {
                        if( masterPos == null ) {
                            masterPos = PlaceableBlock.transform( origin, placeable, DirectionUtil.fromDirection( direction ) );
                        }
                        else
                            throw new IllegalArgumentException( "PlaceableConstruct marked as multiblock entity construct is placing more than one master block entity!" );
                    }
                    else
                        childPositions.add( PlaceableBlock.transform( origin, placeable, DirectionUtil.fromDirection( direction ) ) );
                }
            }
            // No master pos indicates no master block entity was placed. Throw an error
            if( masterPos == null )
                throw new IllegalArgumentException( "PlaceableConstruct marked as multiblock entity construct is missing its master placement!" );
            
            // Loop through child block entities and inform them of the master's position
            for( BlockPos childPos : childPositions ) {
                if( level.getBlockEntity( childPos ) instanceof IMultiBlockEntity<?> multiBlockEntity ) {
                    multiBlockEntity.setMasterPos( masterPos );
                }
            }
            // Inform the master block entity of where its children are
            if( level.getBlockEntity( masterPos ) instanceof IMultiBlockEntity<?> multiBlockEntity ) {
                multiBlockEntity.setChildPositions( childPositions.toArray( new BlockPos[0] ) );
            }
        }
        // Process placements normally for non-multiblock entity constructs
        else {
            for( PlaceableBlock placeable : blocksToPlace ) {
                placeable.placeRelative( level, origin, DirectionUtil.fromDirection( direction ) );
            }
        }
    }
    
    /**
     * Places this construct into the world without any rotation transforms.
     *
     * @param level  The world to place the construct in.
     * @param origin The position of origin where we are placing.
     */
    public void place( LevelAccessor level, BlockPos origin ) {
        place( level, origin, null );
    }
    
    /**
     * @return True if the space this construct will occupy upon placement is
     * either free (air) or replaceable. This is primarily used by block item
     * to check if the construct can be placed.
     */
    public boolean isSpaceUnoccupied( LevelAccessor level, BlockPos origin, @Nullable Direction direction ) {
        for( PlaceableBlock placeableBlock : blocksToPlace ) {
            BlockPos pos = PlaceableBlock.transform( origin, placeableBlock, direction );
            BlockState state = level.getBlockState( pos );
            
            if( !state.isAir() && !state.is( BlockTags.REPLACEABLE ) )
                return false;
        }
        return true;
    }
    
    /**
     * Ensures there are no duplicate placements or otherwise
     * strange entries in the {@link PlaceableConstruct#blocksToPlace} list.
     */
    private void sanitizePlacements() {
        final List<Vec3i> knownOffsets = new ArrayList<>();
        
        for( PlaceableBlock toPlace : blocksToPlace ) {
            if( knownOffsets.contains( toPlace.getOffset() ) ) {
                throw new IllegalArgumentException( "PlaceableConstruct cannot have multiple placements with the same offset!" +
                        " Duplicate offset: " + toPlace.getOffset() );
            }
            else {
                knownOffsets.add( toPlace.getOffset() );
            }
        }
    }
    
    /** @return A new builder instance. */
    public static Builder builder() {
        return new Builder();
    }
    
    
    public static class Builder {
        
        private final List<PlaceableBlock> blocksToPlace = new ArrayList<>();
        private boolean isMultiBlocKEntity = false;
        
        
        /** For internal use; use {@link PlaceableConstruct#builder()} outside this class. */
        private Builder() { }
        
        
        /**
         * Adds a new placeable block entry to the builder.
         *
         * @param state  The block state to be placed.
         * @param offset The placement offset.
         */
        public Builder add( BlockState state, Vec3i offset ) {
            blocksToPlace.add( new PlaceableBlock( state, offset ) );
            return this;
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         * with the given x, y and z offsets.
         *
         * @param state The block state to be placed.
         */
        public Builder add( BlockState state, int x, int y, int z ) {
            return add( state, new Vec3i( x, y, z ) );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         *
         * @param block  The block to be placed. The block's default state is used.
         * @param offset The placement offset.
         */
        public Builder add( Block block, Vec3i offset ) {
            return add( block.defaultBlockState(), offset );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         * with the given x, y and z offsets.
         *
         * @param block The block to be placed. The block's default state is used.
         */
        public Builder add( Block block, int x, int y, int z ) {
            return add( block, new Vec3i( x, y, z ) );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         *
         * @param blockSupplier The block to be placed. The block's default state is used.
         * @param offset        The placement offset.
         */
        public Builder add( Supplier<Block> blockSupplier, Vec3i offset ) {
            return add( blockSupplier.get(), offset );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         * with the given x, y and z offsets.
         *
         * @param blockSupplier The block to be placed. The block's default state is used.
         */
        public Builder add( Supplier<Block> blockSupplier, int x, int y, int z ) {
            return add( blockSupplier.get(), new Vec3i( x, y, z ) );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         * The given state will be replaced with one that
         * has all the same properties except the facing direction
         * value is set to the opposite to "flip" the block.
         * <br><br>
         * Only use for block states that have the {@link net.minecraft.world.level.block.state.properties.BlockStateProperties#FACING} property!
         *
         * @param state  The block state to be placed.
         * @param offset The placement offset.
         */
        public Builder addOpposite( BlockState state, Vec3i offset ) {
            Direction dir = state.getValue( BlockStateProperties.FACING );
            return add( state.setValue( BlockStateProperties.FACING, dir.getOpposite() ), offset );
        }
        
        /**
         * Adds a new placeable block entry to the builder.
         * The given state will be replaced with one that
         * has all the same properties except the facing direction
         * value is set to the opposite to "flip" the block.
         * <br><br>
         * Only use for block states that have the {@link net.minecraft.world.level.block.state.properties.BlockStateProperties#HORIZONTAL_FACING} property!
         *
         * @param state The block state to be placed.
         * @param x     The X placement offset.
         * @param y     The Y placement offset.
         * @param z     The Z placement offset.
         */
        public Builder addOpposite( BlockState state, int x, int y, int z ) {
            Direction dir = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
            return add( state.setValue( BlockStateProperties.HORIZONTAL_FACING, dir.getOpposite() ), new Vec3i( x, y, z ) );
        }
        
        /** Sets the construct's {@link PlaceableConstruct#isMultiBlocKEntity} flag to true. */
        public Builder multiblockEntity() {
            isMultiBlocKEntity = true;
            return this;
        }
        
        /** Creates a new placeable construct instance from this builder. */
        public PlaceableConstruct build() {
            return new PlaceableConstruct( blocksToPlace, isMultiBlocKEntity );
        }
    }
}
