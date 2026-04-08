package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.world.level.block.entity.BlockEntity;

/** Base renderer implementation for {@link IMultiBlockEntity}. */
public abstract class BaseMultiblockEntityRenderer<T extends BlockEntity & IMultiBlockEntity<T>> implements BlockEntityRenderer<T> {
    
    private final int viewDist;
    
    
    public BaseMultiblockEntityRenderer( int viewDist ) {
        this.viewDist = viewDist;
    }
    
    public BaseMultiblockEntityRenderer() {
        // Default view distance
        this( 64 );
    }
    
    /**
     * Renders the block entity.
     * <br><br>
     * Overridden to split rendering into two methods; one for rendering
     * master block entities and one for rendering child block entities.
     */
    @Override
    public void render( T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay ) {
        // Also render master if the block entity has no level
        if( blockEntity.isMaster() || !blockEntity.hasLevel() ) {
            renderMaster( blockEntity, partialTick, poseStack, bufferSource, packedLight, textureOverlay );
        }
        else {
            renderChild( blockEntity, partialTick, poseStack, bufferSource, packedLight, textureOverlay );
        }
    }
    
    /**
     * Called from {@link BaseMultiblockEntityRenderer#render(BlockEntity, float, PoseStack, MultiBufferSource, int, int)}
     * to render the block entity, if it is a master block entity.
     */
    public abstract void renderMaster( T blockEntity, float partialTick, PoseStack poseStack,
                                       MultiBufferSource bufferSource, int packedLight, int textureOverlay );
    
    /**
     * Called from {@link BaseMultiblockEntityRenderer#render(BlockEntity, float, PoseStack, MultiBufferSource, int, int)}
     * to render the block entity, if it is not a child block entity.
     * <br><br>
     * By default, child block entities do not render anything.
     */
    public void renderChild( T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay ) {
        // Do nothing by default
    }
    
    /**
     * @return The max distance in blocks away from the camera the block entity can be
     * before it stops rendering and is "out of view".
     */
    @Override
    public int getViewDistance() {
        return viewDist;
    }
}
