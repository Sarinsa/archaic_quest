package com.obsidian_core.archaic_quest.client.render.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class IchcahuipilliArmorModel<T extends LivingEntity> extends HumanoidModel<T> {

    private final EquipmentSlot slot;

    private final ModelPart headgear;
    private final ModelPart shirt;
    private final ModelPart armorLeftArm;
    private final ModelPart armorRightArm;
    private final ModelPart armorLeftLeg;
    private final ModelPart armorRightLeg;
    private final ModelPart leftBoot;
    private final ModelPart rightBoot;
    private final ModelPart loincloth;

    public IchcahuipilliArmorModel(ModelPart playerRoot, ModelPart root, EquipmentSlot slot) {
        super(playerRoot);
        this.headgear = root.getChild("head");
        this.shirt = root.getChild("body");
        this.armorLeftArm = root.getChild("left_arm").getChild("armorLeftArm");
        this.armorRightArm = root.getChild("right_arm").getChild("armorRightArm");
        this.armorLeftLeg = root.getChild("left_leg");
        this.armorRightLeg = root.getChild("right_leg");
        this.leftBoot = root.getChild("left_leg").getChild("armorLeftBoot");
        this.rightBoot = root.getChild("right_leg").getChild("armorRightBoot");
        this.loincloth = root.getChild("loincloth");
        this.slot = slot;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDef = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        PartDefinition root = meshDef.getRoot();
        PartDefinition head = root.getChild("head");
        PartDefinition body = root.getChild("body");
        PartDefinition rightLeg = root.getChild("right_leg");
        PartDefinition leftLeg = root.getChild("left_leg");
        PartDefinition rightArm = root.getChild("right_arm");
        PartDefinition leftArm = root.getChild("left_arm");

        PartDefinition armorHead = head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(0, 104).addBox(-4.5F, -7.0F, -4.5F, 9.0F, 2.0F, 9.0F, new CubeDeformation(0.2F))
                .texOffs(14, 116).addBox(4.0F, -5.5F, -2.5F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 116).mirror().addBox(-5.0F, -5.5F, -2.5F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 118).addBox(-1.5F, -9.0F, -5.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r1 = armorHead.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(27, 119).addBox(-4.0F, -7.0F, -0.5F, 8.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.0F, -4.0F, -0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r2 = armorHead.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 96).mirror().addBox(0.49F, -8.5F, -1.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(32, 96).addBox(9.51F, -8.5F, -1.5F, 0.0F, 9.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -7.0F, -2.0F, -0.4363F, 0.0F, 0.0F));

        PartDefinition armorBody = body.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(89, 0).addBox(-4.5F, -0.5F, -2.5F, 9.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorRightArm = rightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(89, 29).mirror().addBox(-2.5F, -2.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(-0.02F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorLeftArm = leftArm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(89, 29).addBox(-0.5F, -2.5F, -2.5F, 3.0F, 6.0F, 5.0F, new CubeDeformation(-0.02F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition armorLeftLeg = leftLeg.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition lSkirtSide = armorLeftLeg.addOrReplaceChild("lSkirtSide", CubeListBuilder.create(), PartPose.offset(6.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = lSkirtSide.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(95, 62).addBox(6.25F, -1.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.0873F));

        PartDefinition lSkirtBack = armorLeftLeg.addOrReplaceChild("lSkirtBack", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, 2.0F));

        PartDefinition cube_r4 = lSkirtBack.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(118, 67).addBox(-2.25F, -1.5F, 0.25F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition lSkirtFront = armorLeftLeg.addOrReplaceChild("lSkirtFront", CubeListBuilder.create(), PartPose.offset(4.0F, 0.0F, -2.0F));

        PartDefinition cube_r5 = lSkirtFront.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(107, 67).addBox(-2.25F, -1.5F, -0.25F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition lLegband = armorLeftLeg.addOrReplaceChild("lLegband", CubeListBuilder.create().texOffs(90, 79).addBox(1.5F, 5.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r6 = lLegband.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(63, 118).addBox(-1.75F, -3.0F, -3.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(-0.2F)), PartPose.offsetAndRotation(6.25F, 8.0F, 0.0F, 0.0F, 0.0F, 0.7854F));

        PartDefinition armorLeftBoot = leftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(76, 112).mirror().addBox(-3.5F, 10.5F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.02F)).mirror(false)
                .texOffs(89, 120).mirror().addBox(-3.5F, 8.6F, 0.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.04F)).mirror(false), PartPose.offset(5.0F, 0.0F, 0.0F));

        PartDefinition cube_r7 = armorLeftBoot.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(77, 116).mirror().addBox(1.5F, -2.5F, -1.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 11.0F, 1.0F, 0.3491F, 0.2182F, 0.0F));

        PartDefinition armorRightLeg = rightLeg.addOrReplaceChild("armorRightLeg", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rSkirtSide = armorRightLeg.addOrReplaceChild("rSkirtSide", CubeListBuilder.create(), PartPose.offset(-6.0F, 0.0F, 0.0F));

        PartDefinition cube_r8 = rSkirtSide.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(95, 62).mirror().addBox(-6.25F, -1.0F, -2.5F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0873F));

        PartDefinition rSkirtBack = armorRightLeg.addOrReplaceChild("rSkirtBack", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 2.0F));

        PartDefinition cube_r9 = rSkirtBack.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(118, 67).mirror().addBox(-2.75F, -1.5F, 0.25F, 5.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0436F, 0.0F, 0.0F));

        PartDefinition rSkirtFront = armorRightLeg.addOrReplaceChild("rSkirtFront", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, -2.0F));

        PartDefinition cube_r10 = rSkirtFront.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(107, 67).mirror().addBox(-2.75F, -1.5F, -0.25F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

        PartDefinition rLegband = armorRightLeg.addOrReplaceChild("rLegband", CubeListBuilder.create().texOffs(90, 79).mirror().addBox(-6.5F, 5.0F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r11 = rLegband.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(63, 118).mirror().addBox(-0.25F, -3.0F, -3.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(-0.2F)).mirror(false), PartPose.offsetAndRotation(-6.25F, 8.0F, 0.0F, 0.0F, 0.0F, -0.7854F));

        PartDefinition armorRightBoot = rightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(76, 112).addBox(-6.5F, 10.5F, -2.5F, 5.0F, 2.0F, 5.0F, new CubeDeformation(-0.02F))
                .texOffs(89, 120).addBox(-6.5F, 8.6F, 0.5F, 5.0F, 2.0F, 2.0F, new CubeDeformation(-0.04F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r12 = armorRightBoot.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(77, 116).addBox(-1.5F, -2.5F, -1.5F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 11.0F, 1.0F, 0.3491F, -0.2182F, 0.0F));

        PartDefinition loincloth = root.addOrReplaceChild("loincloth", CubeListBuilder.create().texOffs(88, 19).addBox(-4.5F, -14.5F, -2.5F, 9.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition loinclothFront = loincloth.addOrReplaceChild("loinclothFront", CubeListBuilder.create().texOffs(78, 103).addBox(-2.0F, 0.0F, -0.5F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(91, 104).addBox(-2.0F, 5.0F, -0.4F, 4.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.75F, -2.0F, -0.0436F, 0.0F, 0.0F));

        return LayerDefinition.create(meshDef, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        headgear.setRotation(head.xRot, head.yRot, head.zRot);
        armorLeftLeg.setRotation(leftLeg.xRot, leftLeg.yRot, leftLeg.zRot);
        armorRightLeg.setRotation(rightLeg.xRot, rightLeg.yRot, rightLeg.zRot);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        switch (slot) {
            case HEAD -> headgear.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            case CHEST -> {
                shirt.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                armorLeftArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                armorRightArm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                loincloth.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            }
            case LEGS -> {
                armorLeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                armorRightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            }
            case FEET -> {
                leftBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
                rightBoot.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
            }
        }
    }
}
