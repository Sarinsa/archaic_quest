package com.obsidian_core.archaic_quest.common.block.misc;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

/**
 * Helper class for creating and wrapping a {@link VoxelShape} array containing one
 * VoxelShape per direction.
 */
public class DirectionalShape {
    
    /** The internal array of shapes per direction. */
    private final VoxelShape[] shapes = new VoxelShape[6];
    
    
    /**
     * Creates a new directional shape using the given bounds.
     * <br><br>
     * The base shape created from the bounds will then be used to create
     * one shape per direction using {@link DirectionalShape#rotate(VoxelShape, Direction)}.
     * <br>
     * The base shape itself will be associated
     * with {@link Direction#NORTH}, which is considered the default direction here.
     *
     * @param verticalOverride If provided, the given shape will be used for the direction DOWN,
     *                         and an opposite will be created and used for the direction UP.
     */
    public DirectionalShape( double minX, double minY, double minZ, double maxX, double maxY, double maxZ, @Nullable VoxelShape verticalOverride ) {
        final VoxelShape baseShape = Block.box( minX, minY, minZ, maxX, maxY, maxZ );
        
        for( Direction direction : Direction.values() ) {
            int ordinal = direction.ordinal();
            shapes[ordinal] = rotate( baseShape, Direction.values()[ordinal] );
        }
        
        if( verticalOverride != null ) {
            shapes[Direction.DOWN.ordinal()] = verticalOverride;
            // This looks dumb, but since NORTH is the default, we
            // must rotate around SOUTH to get the opposite for UP.
            shapes[Direction.UP.ordinal()] = rotate( verticalOverride, Direction.SOUTH );
        }
    }
    
    /** Creates a new directional shape using the given bounds, with no vertical override shape. */
    public DirectionalShape( double minX, double minY, double minZ, double maxX, double maxY, double maxZ ) {
        this( minX, minY, minZ, maxX, maxY, maxZ, null );
    }
    
    /**
     * @return The VoxelShape in this directional shape's internal shape array
     * that corresponds to the given direction.
     */
    public VoxelShape getFor( Direction direction ) {
        return shapes[direction.ordinal()];
    }
    
    /**
     * Convenience method for getting the VoxelShape of the opposite
     * direction of the one specified.
     */
    public VoxelShape getForOpposite( Direction direction ) {
        return getFor( direction.getOpposite() );
    }
    
    /**
     * @return A new VoxelShape with the same dimensions as the original shape,
     * then rotated relative to the given direction.<br>
     * {@link Direction#NORTH} is treated as the default direction, and will return
     * the original shape.
     */
    public static VoxelShape rotate( VoxelShape original, Direction direction ) {
        final double minX = original.min( Direction.Axis.X );
        final double minY = original.min( Direction.Axis.Y );
        final double minZ = original.min( Direction.Axis.Z );
        
        final double maxX = original.max( Direction.Axis.X );
        final double maxY = original.max( Direction.Axis.Y );
        final double maxZ = original.max( Direction.Axis.Z );
        
        final Vec3 corner1;
        final Vec3 corner2;
        
        switch( direction ) {
            case DOWN -> {
                corner1 = new Vec3( minX, 1.0 - minZ, minY );
                corner2 = new Vec3( maxX, 1.0 - maxZ, maxY );
            }
            case UP -> {
                corner1 = new Vec3( minX, minZ, 1.0 - minY );
                corner2 = new Vec3( maxX, maxZ, 1.0 - maxY );
            }
            case SOUTH -> {
                corner1 = new Vec3( 1.0 - minX, minY, 1.0 - minZ );
                corner2 = new Vec3( 1.0 - maxX, maxY, 1.0 - maxZ );
            }
            case WEST -> {
                corner1 = new Vec3( minZ, minY, 1.0 - minX );
                corner2 = new Vec3( maxZ, maxY, 1.0 - maxX );
            }
            case EAST -> {
                corner1 = new Vec3( 1.0 - minZ, minY, minX );
                corner2 = new Vec3( 1.0 - maxZ, maxY, maxX );
            }
            // Default (NORTH)
            default -> {
                corner1 = new Vec3( minX, minY, minZ );
                corner2 = new Vec3( maxX, maxY, maxZ );
            }
        }
        return Shapes.box(
                Math.min( corner1.x, corner2.x ),
                Math.min( corner1.y, corner2.y ),
                Math.min( corner1.z, corner2.z ),
                Math.max( corner1.x, corner2.x ),
                Math.max( corner1.y, corner2.y ),
                Math.max( corner1.z, corner2.z )
        );
    }
    
    /** @return A new directional shape builder. */
    public static Builder builder() {
        return new Builder();
    }
    
    
    /// This builder is not a requirement for creating a DirectionalShape instance,
    /// the public constructor is the easiest way. This only exists because it helps my
    /// dumb brain read and understand things a bit better.
    ///
    /// -Sarinsa
    ///
    public static class Builder {
        
        private double minX, maxX;
        private double minY, maxY;
        private double minZ, maxZ;
        
        @Nullable
        private VoxelShape verticalOverride;
        
        
        private Builder() {
            minX = maxX = 0;
            minY = maxY = 0;
            minZ = maxZ = 0;
        }
        
        /** Sets the X bounds for the base shape. */
        public Builder x( double min, double max ) {
            this.minX = min;
            this.maxX = max;
            return this;
        }
        
        /** Sets the Y bounds for the base shape. */
        public Builder y( double min, double max ) {
            this.minY = min;
            this.maxY = max;
            return this;
        }
        
        /** Sets the Z bounds for the base shape. */
        public Builder z( double min, double max ) {
            this.minZ = min;
            this.maxZ = max;
            return this;
        }
        
        /**
         * Sets the given shape for {@link Direction#UP} and {@link Direction#DOWN}.
         * This can come in handy if the base shape is not symmetrical on the X and Z axis,
         * which can make the calculated UP and DOWN shapes weird.
         */
        public Builder verticalOverride( @Nullable VoxelShape shape ) {
            verticalOverride = shape;
            return this;
        }
        
        public DirectionalShape build() {
            return new DirectionalShape( minX, minY, minZ, maxX, maxY, maxZ, verticalOverride );
        }
    }
}
