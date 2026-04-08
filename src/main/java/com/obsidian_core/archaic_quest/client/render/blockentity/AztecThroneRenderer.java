package com.obsidian_core.archaic_quest.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.obsidian_core.archaic_quest.client.AQModelLayers;
import com.obsidian_core.archaic_quest.client.render.RenderUtils;
import com.obsidian_core.archaic_quest.common.block.base.ThroneType;
import com.obsidian_core.archaic_quest.common.blockentity.AztecThroneBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class AztecThroneRenderer extends BaseMultiblockEntityRenderer<AztecThroneBlockEntity> {
    
    private final ModelPart throneModel;
    
    
    public AztecThroneRenderer( BlockEntityRendererProvider.Context context ) {
        super( 80 );
        ModelPart root = context.bakeLayer( AQModelLayers.AZTEC_THRONE );
        this.throneModel = root.getChild( "throne" );
    }
    
    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        
        PartDefinition throne = partDefinition.addOrReplaceChild( "throne", CubeListBuilder.create().texOffs( 74, 0 ).addBox( -8.0F, -8.0F, -8.0F, 16.0F, 8.0F, 16.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 82, 34 ).addBox( -8.0F, -11.0F, -9.0F, 16.0F, 3.0F, 17.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 37, 13 ).addBox( 8.0F, -13.0F, -11.0F, 6.0F, 13.0F, 25.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 0, 0 ).addBox( -14.0F, -13.0F, -11.0F, 6.0F, 13.0F, 25.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 0, 85 ).addBox( -11.0F, -38.0F, 8.0F, 22.0F, 38.0F, 4.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 42, 55 ).addBox( 7.0F, -17.0F, -11.99F, 8.0F, 4.0F, 26.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 0, 51 ).addBox( -15.0F, -17.0F, -11.99F, 8.0F, 4.0F, 26.0F, new CubeDeformation( 0.0F ) )
                .texOffs( 84, 54 ).addBox( -11.0F, -44.0F, 5.0F, 22.0F, 6.0F, 10.0F, new CubeDeformation( 0.0F ) ), PartPose.offset( 0.0F, 24.0F, 0.0F ) );
        throne.addOrReplaceChild( "cube_r1", CubeListBuilder.create().texOffs( 52, 85 ).addBox( -6.0F, -22.0F, -0.001F, 6.0F, 22.0F, 8.0F, new CubeDeformation( 0.0F ) ), PartPose.offsetAndRotation( 13.0F, -17.0F, 6.0F, 0.0F, 0.0F, -0.1309F ) );
        throne.addOrReplaceChild( "cube_r2", CubeListBuilder.create().texOffs( 80, 85 ).addBox( 0.0F, -22.0F, -0.001F, 6.0F, 22.0F, 8.0F, new CubeDeformation( 0.0F ) ), PartPose.offsetAndRotation( -13.0F, -17.0F, 6.0F, 0.0F, 0.0F, 0.1309F ) );
        
        return LayerDefinition.create( meshDefinition, 256, 256 );
    }
    
    @Override
    public void renderMaster( AztecThroneBlockEntity throne, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int textureOverlay ) {
        BlockState state = throne.getLevel() == null ? AQBlocks.AZTEC_THRONE.get().defaultBlockState() : throne.getBlockState();
        
        RenderUtils.defaultBETransforms( state, poseStack );
        
        ThroneType type = throne.getThroneType() == null ? ThroneType.THRONE : throne.getThroneType();
        VertexConsumer vertexConsumer = bufferSource.getBuffer( RenderType.entityCutout( type.getTextureLocation() ) );
        throneModel.render( poseStack, vertexConsumer, packedLight, textureOverlay );
    }
}
