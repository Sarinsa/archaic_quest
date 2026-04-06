package com.obsidian_core.archaic_quest.common.util;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Rotation;

import javax.annotation.Nullable;

public class DirectionUtil {
    
    /**
     * @return The rotation corresponding to the given horizontal {@link Direction}.
     * This method assumes {@link Direction#NORTH} is the default (no rotation).
     * <br>
     * If direction is null, {@link Rotation#NONE} is returned.
     */
    public static Rotation fromDirection( @Nullable Direction direction ) {
        if( direction == null ) {
            return Rotation.NONE;
        }
        return switch( direction ) {
            case EAST -> Rotation.CLOCKWISE_90;
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
            default -> Rotation.NONE;
        };
    }
}
