package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import com.obsidian_core.archaic_quest.client.AQModelLayers;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.blockentity.AztecWorktableBlockEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class AztecWorktableRenderer implements BlockEntityRenderer<AztecWorktableBlockEntity> {

    private static final ResourceLocation texture = ArchaicQuest.resourceLoc("textures/tile/aztec_worktable.png");

    private final ModelPart table;
    private final ModelPart greebles;


    public AztecWorktableRenderer(BlockEntityRendererProvider.Context context) {
        ModelPart root = context.bakeLayer(AQModelLayers.AZTEC_CRAFTING_STATION);
        this.table = root.getChild("table");
        this.greebles = root.getChild("greebles");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();

        PartDefinition table = partDefinition.addOrReplaceChild("table", CubeListBuilder.create().texOffs(0, 34).addBox(-14.0F, -9.0F, -6.001F, 28.0F, 9.0F, 20.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-19.0F, -16.0F, -8.0F, 38.0F, 7.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, -8.0F));

        PartDefinition bottomSlopeL = table.addOrReplaceChild("bottomSlopeL", CubeListBuilder.create().texOffs(0, 64).addBox(-7.0F, -6.0F, -7.0F, 15.0F, 11.0F, 22.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(10.0F, -3.75F, 0.0F, 0.0F, 0.0F, -0.1309F));

        PartDefinition bottomSlopeR = table.addOrReplaceChild("bottomSlopeR", CubeListBuilder.create().texOffs(0, 64).mirror().addBox(-8.0F, -6.0F, -7.0F, 15.0F, 11.0F, 22.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-10.0F, -3.75F, 0.0F, 0.0F, 0.0F, 0.1309F));

        PartDefinition back = table.addOrReplaceChild("back", CubeListBuilder.create().texOffs(0, 104).addBox(-8.0F, -33.0F, 8.0F, 16.0F, 16.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(84, 106).addBox(-3.0F, -43.0F, 8.0F, 6.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        PartDefinition cube5_r1 = back.addOrReplaceChild("cube5_r1", CubeListBuilder.create().texOffs(53, 102).mirror().addBox(-12.0F, -7.0F, 8.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(1.3397F, -22.7155F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition cube4_r1 = back.addOrReplaceChild("cube4_r1", CubeListBuilder.create().texOffs(53, 102).addBox(6.0F, -7.0F, 8.0F, 6.0F, 18.0F, 8.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-1.3397F, -22.7155F, 0.0F, 0.0F, 0.0F, -0.3054F));

        PartDefinition xiuhcoatlR = back.addOrReplaceChild("xiuhcoatlR", CubeListBuilder.create().texOffs(116, 114).mirror().addBox(-6.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(224, 108).mirror().addBox(-5.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-10.0F, -25.0F, 12.0F));

        PartDefinition cube_r1 = xiuhcoatlR.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(225, 117).mirror().addBox(-2.0F, 2.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition ltail2 = xiuhcoatlR.addOrReplaceChild("ltail2", CubeListBuilder.create(), PartPose.offset(-3.0F, -4.0F, 0.0F));

        PartDefinition cube_r2 = ltail2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(138, 113).mirror().addBox(-4.0F, -9.75F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition ltail3 = ltail2.addOrReplaceChild("ltail3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.0F, -4.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition cube_r3 = ltail3.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(207, 110).mirror().addBox(5.5F, -8.25F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(207, 120).mirror().addBox(1.5F, -8.75F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(158, 113).mirror().addBox(-3.5F, -7.75F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(-0.02F)).mirror(false), PartPose.offsetAndRotation(3.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

        PartDefinition lTailSpike2 = ltail3.addOrReplaceChild("lTailSpike2", CubeListBuilder.create(), PartPose.offset(9.75F, -5.0F, 0.0F));

        PartDefinition cube_r4 = lTailSpike2.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(226, 100).mirror().addBox(-1.0F, 1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.6109F));

        PartDefinition cube_r5 = lTailSpike2.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(204, 101).mirror().addBox(-1.0F, -3.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

        PartDefinition lHead2 = xiuhcoatlR.addOrReplaceChild("lHead2", CubeListBuilder.create().texOffs(179, 121).mirror().addBox(-6.0F, 1.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(177, 100).mirror().addBox(-2.0F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.25F, 3.0F, 0.0F));

        PartDefinition cube_r6 = lHead2.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(248, 118).mirror().addBox(-7.5F, -5.5F, 0.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(244, 105).mirror().addBox(-5.5F, -3.5F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(179, 113).mirror().addBox(-5.5F, -0.5F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition xiuhcoatlL = back.addOrReplaceChild("xiuhcoatlL", CubeListBuilder.create().texOffs(116, 114).addBox(0.0F, -4.0F, -2.0F, 6.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(224, 108).addBox(0.0F, -1.0F, -3.0F, 5.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -25.0F, 12.0F));

        PartDefinition cube_r7 = xiuhcoatlL.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(225, 117).addBox(0.0F, 2.0F, -3.0F, 2.0F, 5.0F, 6.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition ltail01 = xiuhcoatlL.addOrReplaceChild("ltail01", CubeListBuilder.create(), PartPose.offset(3.0F, -4.0F, 0.0F));

        PartDefinition cube_r8 = ltail01.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(138, 113).addBox(-1.0F, -9.75F, -2.0F, 5.0F, 6.0F, 4.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition ltail02 = ltail01.addOrReplaceChild("ltail02", CubeListBuilder.create(), PartPose.offsetAndRotation(2.0F, -4.0F, 0.0F, 0.0F, 0.0F, 0.3491F));

        PartDefinition cube_r9 = ltail02.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(207, 110).addBox(-9.5F, -8.25F, -1.0F, 4.0F, 5.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(207, 120).addBox(-5.5F, -8.75F, -1.0F, 4.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(158, 113).addBox(-1.5F, -7.75F, -2.0F, 5.0F, 4.0F, 4.0F, new CubeDeformation(-0.02F)), PartPose.offsetAndRotation(-3.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition lTailSpike = ltail02.addOrReplaceChild("lTailSpike", CubeListBuilder.create(), PartPose.offset(-9.75F, -5.0F, 0.0F));

        PartDefinition cube_r10 = lTailSpike.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(226, 100).addBox(-6.0F, 1.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(-0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.6109F));

        PartDefinition cube_r11 = lTailSpike.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(204, 101).addBox(-6.0F, -3.0F, -1.0F, 7.0F, 2.0F, 2.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

        PartDefinition lHead = xiuhcoatlL.addOrReplaceChild("lHead", CubeListBuilder.create().texOffs(179, 121).addBox(2.0F, 1.0F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(177, 100).addBox(-3.0F, -3.0F, -3.0F, 5.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(8.25F, 3.0F, 0.0F));

        PartDefinition cube_r12 = lHead.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(248, 118).addBox(3.5F, -5.5F, 0.0F, 4.0F, 7.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(244, 105).addBox(3.5F, -3.5F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(179, 113).addBox(1.5F, -0.5F, -2.5F, 4.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.3491F));

        PartDefinition greebles = partDefinition.addOrReplaceChild("greebles", CubeListBuilder.create(), PartPose.offset(0.0F, 8.0F, -4.0F));

        PartDefinition patterntiles = greebles.addOrReplaceChild("patterntiles", CubeListBuilder.create(), PartPose.offsetAndRotation(13.0F, 0.0F, -1.0F, 0.0F, -2.5307F, 0.0F));

        PartDefinition cube13_r1 = patterntiles.addOrReplaceChild("cube13_r1", CubeListBuilder.create().texOffs(128, 37).addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition cube12_r1 = patterntiles.addOrReplaceChild("cube12_r1", CubeListBuilder.create().texOffs(128, 37).addBox(-4.0F, -6.1F, -0.25F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube14_r1 = patterntiles.addOrReplaceChild("cube14_r1", CubeListBuilder.create().texOffs(128, 37).mirror().addBox(-3.0F, -7.0F, 2.0F, 6.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, 3.0F, 1.5708F, 0.1745F, 0.0F));

        PartDefinition reedtool = greebles.addOrReplaceChild("reedtool", CubeListBuilder.create().texOffs(199, 18).addBox(-3.0F, -1.0F, -1.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(212, 24).addBox(3.0F, -1.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(207, 31).addBox(1.0F, -1.0F, -3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 0.0F, -6.0F, 0.0F, -0.7418F, 0.0F));

        PartDefinition codex = greebles.addOrReplaceChild("codex", CubeListBuilder.create().texOffs(134, 0).addBox(-1.0F, -6.0F, -4.0F, 1.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-16.0F, 0.0F, -9.0F, 0.0F, -1.3963F, 0.0F));

        PartDefinition page01 = codex.addOrReplaceChild("page01", CubeListBuilder.create().texOffs(172, 0).addBox(0.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, -3.75F, 0.0F, -0.829F, 0.0F));

        PartDefinition page2 = page01.addOrReplaceChild("page2", CubeListBuilder.create().texOffs(188, 0).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, -1.0036F, 0.0F));

        PartDefinition page3 = page2.addOrReplaceChild("page3", CubeListBuilder.create(), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.0F));

        PartDefinition cube_r13 = page3.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(203, 0).addBox(-6.0F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition page4 = page3.addOrReplaceChild("page4", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.0F, 2.3998F, 0.0F));

        PartDefinition cube_r14 = page4.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(219, 0).addBox(-6.75F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.75F, 0.0F, -1.5708F, 0.0F));

        PartDefinition page5 = page4.addOrReplaceChild("page5", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, -6.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition cube_r15 = page5.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(235, 0).addBox(-0.75F, -3.0F, 0.0F, 6.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.75F, 0.0F, -1.5708F, 0.0F));

        PartDefinition backcover = page5.addOrReplaceChild("backcover", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.0F, 1.8762F, 0.0F));

        PartDefinition cube_r16 = backcover.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(151, 0).addBox(-0.75F, -3.0F, 0.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.75F, 0.0F, -1.5708F, 0.0F));

        PartDefinition chisel = greebles.addOrReplaceChild("chisel", CubeListBuilder.create().texOffs(230, 18).addBox(-1.0F, -1.0F, -1.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(12.0F, 0.0F, -7.0F, 0.0F, 0.5672F, 0.0F));

        PartDefinition cube_r17 = chisel.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(230, 25).addBox(-3.25F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -0.5F, -0.5F, 0.7854F, 0.0F, 0.0F));

        PartDefinition cube_r18 = chisel.addOrReplaceChild("cube_r18", CubeListBuilder.create().texOffs(230, 27).addBox(-3.25F, 0.0F, -0.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.75F, -0.5F, -0.5F, -0.7854F, 0.0F, 0.0F));

        PartDefinition threadspool = greebles.addOrReplaceChild("threadspool", CubeListBuilder.create().texOffs(159, 33).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(161, 18).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(162, 26).addBox(-1.5F, -4.0F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 2.0944F, 0.0F));

        PartDefinition cube_r19 = threadspool.addOrReplaceChild("cube_r19", CubeListBuilder.create().texOffs(174, 25).addBox(0.0F, -0.25F, -1.5F, 3.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.3054F));

        PartDefinition gemPile = greebles.addOrReplaceChild("gemPile", CubeListBuilder.create(), PartPose.offset(-7.0F, 0.0F, -4.0F));

        PartDefinition cube_r20 = gemPile.addOrReplaceChild("cube_r20", CubeListBuilder.create().texOffs(140, 63).addBox(-3.0F, -2.0F, -4.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.829F, 0.0F));

        PartDefinition cube_r21 = gemPile.addOrReplaceChild("cube_r21", CubeListBuilder.create().texOffs(118, 63).addBox(3.0F, -2.0F, 0.0F, 3.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.1309F, 0.0F));

        PartDefinition cube_r22 = gemPile.addOrReplaceChild("cube_r22", CubeListBuilder.create().texOffs(127, 70).addBox(-5.0F, -1.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3998F, 0.0F));

        PartDefinition cube_r23 = gemPile.addOrReplaceChild("cube_r23", CubeListBuilder.create().texOffs(118, 70).addBox(-6.0F, -1.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(130, 63).addBox(-4.0F, -2.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6545F, 0.0F));

        PartDefinition cube_r24 = gemPile.addOrReplaceChild("cube_r24", CubeListBuilder.create().texOffs(126, 51).addBox(-1.0F, -3.0F, -2.0F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.1745F, 0.0F));

        return LayerDefinition.create(meshDefinition, 256, 128);
    }

    @Override
    public void render(AztecWorktableBlockEntity craftingStation, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay) {
        BlockState state = craftingStation.getLevel() == null ? AQBlocks.AZTEC_WORKTABLE.get().defaultBlockState() : craftingStation.getBlockState();
        Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
        float rotation = direction.toYRot();

        poseStack.translate(0.5D, 1.5D, 0.5D);

        poseStack.mulPose(Vector3f.YP.rotationDegrees(-rotation));
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180.0F));

        VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(texture));
        table.render(poseStack, vertexConsumer, packedLight, textureOverlay);
        greebles.render(poseStack, vertexConsumer, packedLight, textureOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
