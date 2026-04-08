package com.obsidian_core.archaic_quest.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class RenderUtils {
    
    /**
     * Applies some default transformations to the given pose stack
     * that are usually necessary for block entities with horizontal facing and models
     * made in Block Bench (flipping the model and moving it accordingly).
     * <br><br>
     * <strong>ONLY</strong> use this with block states that have the {@link BlockStateProperties#HORIZONTAL_FACING} property.
     *
     * @param state     The block state to get Y-rotation from using its horizontal facing property.
     * @param poseStack The pose stack to apply transformations to.
     */
    public static void defaultBETransforms( BlockState state, PoseStack poseStack ) {
        Direction facing = state.getValue( BlockStateProperties.HORIZONTAL_FACING );
        poseStack.translate( 0.5D, 1.5F, 0.5D );
        poseStack.mulPose( Axis.YN.rotationDegrees( facing.toYRot() ) );
        poseStack.mulPose( Axis.ZP.rotationDegrees( 180.0F ) );
    }
    
    /**
     * Applies some default transformations to the given pose stack
     * that are usually necessary for block entities with models
     * made in Block Bench (flipping the model and moving it accordingly).
     *
     * @param poseStack The pose stack to apply transformations to.
     * @param yRotation The Y-rotation (in degrees) to apply to the pose stack.
     */
    public static void defaultBETransforms( PoseStack poseStack, float yRotation ) {
        poseStack.translate( 0.5D, 1.5F, 0.5D );
        poseStack.mulPose( Axis.YN.rotationDegrees( yRotation ) );
        poseStack.mulPose( Axis.ZP.rotationDegrees( 180.0F ) );
    }
}
