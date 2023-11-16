package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.client.AQModelLayers;
import com.obsidian_core.archaic_quest.common.block.AztecDungeonChestBlock;
import com.obsidian_core.archaic_quest.common.block.SimpleSkullBlock;
import com.obsidian_core.archaic_quest.common.block.SimpleWallSkullBlock;
import com.obsidian_core.archaic_quest.common.blockentity.SimpleSkullBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SimpleSkullRenderer implements BlockEntityRenderer<SimpleSkullBlockEntity> {

    private final ModelPart skullModel;
    //private final ModelPart wallSkullModel;
    private final ModelPart animalSkullModel;
    //private final ModelPart wallAnimalSkull;


    public SimpleSkullRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart skullRoot = context.bakeLayer(AQModelLayers.SKULL);
        //ModelPart wallSkullRoot = context.bakeLayer(AQModelLayers.WALL_SKULL);
        ModelPart animalRoot = context.bakeLayer(AQModelLayers.ANIMAL_SKULL);
        //ModelPart animalWallRoot = context.bakeLayer(AQModelLayers.ANIMAL_WALL_SKULL);

        this.skullModel = skullRoot.getChild("skull");
        //this.wallSkullModel = wallSkullRoot.getChild("wall_skull");
        this.animalSkullModel = animalRoot.getChild("skull");
        //this.wallAnimalSkull = animalWallRoot.getChild("animal_wall_skull");
    }


    public static LayerDefinition createAnimalSkullBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skull = partdefinition.addOrReplaceChild("skull", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 2.5F, -3.0F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, -1.0F));

        PartDefinition cube_r1 = skull.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(15, 37).mirror().addBox(-2.49F, -1.75F, -4.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(15, 37).addBox(1.49F, -1.75F, -4.25F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(5, 37).addBox(-1.5F, -1.75F, -4.2F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 30).addBox(-2.5F, -3.75F, -4.25F, 5.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.5F, -6.25F, -0.5F, 7.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 10).addBox(-2.5F, -6.0F, 0.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 8.0F, -0.25F, -0.3927F, 0.0F, 0.0F));

        PartDefinition lowerJaw = skull.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(26, 6).addBox(-2.0F, 2.0F, -3.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(31, 3).addBox(-1.5F, 1.25F, -3.45F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 2).addBox(0.99F, 0.5F, -3.49F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(40, 2).mirror().addBox(-1.99F, 0.5F, -3.49F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 5.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    public static LayerDefinition createSkullBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition skull = partdefinition.addOrReplaceChild("skull", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 2.0F));

        PartDefinition chromedome = skull.addOrReplaceChild("chromedome", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, 0.0F));

        PartDefinition cube_r1 = chromedome.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(18, 13).addBox(-2.0F, -0.75F, -5.75F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.01F))
                .texOffs(0, 14).addBox(-4.0F, -4.75F, -6.0F, 8.0F, 4.0F, 1.0F, new CubeDeformation(0.01F))
                .texOffs(0, 0).addBox(-3.5F, -6.75F, -5.75F, 7.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition lowerJaw = skull.addOrReplaceChild("lowerJaw", CubeListBuilder.create().texOffs(0, 25).addBox(-3.0F, -0.964F, -5.421F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 20).addBox(-3.0F, -1.964F, -3.421F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(20, 17).addBox(-2.0F, -1.964F, -5.421F, 4.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 8.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void render(SimpleSkullBlockEntity skull, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        boolean isAnimal = skull.getSkull().isAnimal();
        boolean isWall = skull.getSkull() instanceof SimpleWallSkullBlock;

        float rot = isWall
                ? skull.getBlockState().getValue(SimpleWallSkullBlock.FACING).getOpposite().toYRot()
                : 22.5F * (float) skull.getBlockState().getValue(SimpleSkullBlock.ROTATION);

        poseStack.translate(0.5D, 1.5F, 0.5D);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-rot));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(skull.getSkull().getTexture()));

        ModelPart modelPart = isAnimal ? animalSkullModel : skullModel;

        modelPart.render(poseStack, vertexConsumer, packedLight, textureOverlay);
    }

    @Override
    public int getViewDistance() {
        return 80;
    }
}
