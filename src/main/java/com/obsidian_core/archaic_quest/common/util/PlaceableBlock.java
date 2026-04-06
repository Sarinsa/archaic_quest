package com.obsidian_core.archaic_quest.common.util;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Helper class for storing a block state and position
 * for placing a block later.
 */
public class PlaceableBlock {
    
    /** The block state to place. */
    private BlockState blockState;
    /** The offset position to place at. */
    private Vec3i placementOffset;
    
    
    /** Creates a new placeable block instance with the given state and placement offset. */
    public PlaceableBlock( BlockState blockState, @Nullable Vec3i placementOffset ) {
        Objects.requireNonNull( blockState );
        
        this.placementOffset = Objects.requireNonNullElse( placementOffset, Vec3i.ZERO );
        this.blockState = blockState;
    }
    
    /** Creates a new placeable block instance with the given state and no offset. */
    public PlaceableBlock( BlockState blockState ) {
        this( blockState, null );
    }
    
    /** Sets this placeable block's state for placement. */
    public void setBlock( BlockState blockState ) {
        this.blockState = blockState;
    }
    
    /**
     * Sets this placeable block's state for placement
     * to the given block's default stat.
     */
    public void setBlock( Block block ) {
        setBlock( block.defaultBlockState() );
    }
    
    /**
     * Sets this placeable block's state for placement
     * to the given block's default stat.
     */
    public void setBlock( Supplier<Block> block ) {
        setBlock( block.get() );
    }
    
    /** Sets this placeable block's placement position. */
    public void setOffset( Vec3i pos ) {
        this.placementOffset = pos;
    }
    
    /** Sets this placeable block's placement position. */
    public void setOffset( Vec3 vec3 ) {
        setOffset( new Vec3i( Mth.floor( vec3.x ), Mth.floor( vec3.y ), Mth.floor( vec3.z ) ) );
    }
    
    /** @return This placeable block's placement block state. */
    public BlockState getBlockState() {
        return blockState;
    }
    
    /** @return This placeable block's placement offset. */
    public Vec3i getOffset() {
        return placementOffset;
    }
    
    /** Places this placeable block in the world. */
    public void place( LevelAccessor level, BlockPos origin, int updateFlags ) {
        level.setBlock( origin.offset( placementOffset ), blockState, updateFlags );
    }
    
    /** Places this placeable block in the world with the default update flags. */
    public void place( LevelAccessor level, BlockPos origin ) {
        level.setBlock( origin.offset( placementOffset ), blockState, Block.UPDATE_ALL );
    }
    
    /**
     * Places this placeable block in the world and
     * applies rotation transforms to both the block state and the placement position
     * using the provided {@link Rotation}.
     *
     * @param level       The world to place the block in.
     * @param origin      The position we are placing at.
     * @param rotation    The rotation to use when transforming the placement position and block state.
     * @param updateFlags The update flags to use when placing the block.
     */
    public void placeRelative( LevelAccessor level, BlockPos origin, Rotation rotation, int updateFlags ) {
        BlockPos transformedPos = transform( origin, this, rotation );
        level.setBlock( transformedPos, blockState.rotate( level, transformedPos, rotation ), updateFlags );
    }
    
    /**
     * Places this placeable block in the world using the default update flags and
     * applies rotation transforms to both the block state and the placement offset
     * using the provided {@link Rotation}.
     *
     * @param level    The world to place the block in.
     * @param origin   The position we are placing at.
     * @param rotation The rotation to use when transforming the placement position and block state.
     */
    public void placeRelative( LevelAccessor level, BlockPos origin, Rotation rotation ) {
        placeRelative( level, origin, rotation, Block.UPDATE_ALL );
    }
    
    public static BlockPos transform( BlockPos origin, PlaceableBlock placeableBlock, @Nullable Direction direction ) {
        return StructureTemplate.transform( origin.offset( placeableBlock.getOffset() ), Mirror.NONE, DirectionUtil.fromDirection( direction ), origin );
    }
    
    public static BlockPos transform( BlockPos origin, PlaceableBlock placeableBlock, @Nullable Rotation rotation ) {
        return StructureTemplate.transform( origin.offset( placeableBlock.getOffset() ), Mirror.NONE, rotation == null ? Rotation.NONE : rotation, origin );
    }
}
