package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.obsidian_core.archaic_quest.client.AQModelLayers;
import com.obsidian_core.archaic_quest.common.blockentity.SimpleSkullBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;

public class SimpleSkullRenderer implements BlockEntityRenderer<SimpleSkullBlockEntity> {

    private final ModelPart skullModel;
    private final ModelPart wallSkullModel;
    private final ModelPart animalSkull;
    private final ModelPart wallAnimalSkull;


    public SimpleSkullRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart skullRoot = context.bakeLayer(AQModelLayers.SKULL);
        ModelPart wallSkullRoot = context.bakeLayer(AQModelLayers.WALL_SKULL);
        ModelPart animalRoot = context.bakeLayer(AQModelLayers.ANIMAL_SKULL);
        ModelPart animalWallRoot = context.bakeLayer(AQModelLayers.ANIMAL_WALL_SKULL);

        this.skullModel = skullRoot.getChild("skull");
        this.wallSkullModel = wallSkullRoot.getChild("wall_skull");
        this.animalSkull = animalRoot.getChild("animal_skull");
        this.wallAnimalSkull = animalWallRoot.getChild("animal_wall_skull");
    }




    @Override
    public void render(SimpleSkullBlockEntity skull, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {

    }

    @Override
    public int getViewDistance() {
        return 80;
    }
}
