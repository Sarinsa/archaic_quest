package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.client.particle.PoisonCloudParticle;
import com.obsidian_core.archaic_quest.client.render.blockentity.*;
import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.client.render.entity.living.TlatlaomiRenderer;
import com.obsidian_core.archaic_quest.client.render.entity.misc.AQBoatRenderer;
import com.obsidian_core.archaic_quest.client.render.entity.model.IchcahuipilliArmorModel;
import com.obsidian_core.archaic_quest.client.render.entity.model.TlatlaomiModel;
import com.obsidian_core.archaic_quest.client.screen.KnappingTableScreen;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.*;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSetRegObj;
import com.obsidian_core.archaic_quest.common.entity.AQBoat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.FoliageColor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArchaicQuest.MODID)
public class ClientRegister {

    // Armor models
    public static final Map<EquipmentSlot, HumanoidModel<LivingEntity>> ICHCAHUIPILLI_ARMOR_MODELS = new HashMap<>();



    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        AQModelLayers.init();
        MinecraftForge.EVENT_BUS.register(new ClientEvents());

        setBlockRenderTypes();
        registerScreenMenus();
        addSkippedHighlightBlocks();

        event.enqueueWork(() -> {
            AQItemModelProps.register();

            WoodSetRegObj.WOOD_SETS.forEach((woodSet) -> Sheets.addWoodType(woodSet.getWoodType()));
        });
    }

    @SubscribeEvent
    public static void onAddLayer(EntityRenderersEvent.AddLayers event) {
        EquipmentSlot[] armorSlots = new EquipmentSlot[] { EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET };

        for (EquipmentSlot slot : armorSlots) {
            ICHCAHUIPILLI_ARMOR_MODELS.put(slot,
                    new IchcahuipilliArmorModel<>(event.getEntityModels().bakeLayer(ModelLayers.PLAYER), event.getEntityModels().bakeLayer(AQModelLayers.ICHCAHUIPILLI_ARMOR), slot));
        }
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {

    }

    @SubscribeEvent
    public static void onRegisterAtlases(RegisterTextureAtlasSpriteLoadersEvent event) {

    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event) {
        event.register(AQParticles.POISON_CLOUD.get(), PoisonCloudParticle.Factory::new);
    }

    private static void registerScreenMenus() {
        MenuScreens.register(AQContainers.KNAPPING.get(), KnappingTableScreen::new);
    }

    @SubscribeEvent
    public static void registerLayerDefs(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(AQModelLayers.AZTEC_DUNGEON_DOOR, AztecDungeonDoorRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.AZTEC_CRAFTING_STATION, AztecWorktableRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.AZTEC_THRONE, AztecThroneRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.SPIKE_TRAP, SpikeTrapRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.SPIKE_TRAP_OVERLAY, SpikeTrapRenderer::createOverlayBodyLayer);
        event.registerLayerDefinition(AQModelLayers.AZTEC_DUNGEON_CHEST, AztecDungeonChestRenderer::createBodyLayer);
        event.registerLayerDefinition(AQModelLayers.SKULL, SimpleSkullRenderer::createSkullBodyLayer);
        event.registerLayerDefinition(AQModelLayers.ANIMAL_SKULL, SimpleSkullRenderer::createAnimalSkullBodyLayer);

        event.registerLayerDefinition(AQModelLayers.TLATLAOMI, TlatlaomiModel::createBodyLayer);

        event.registerLayerDefinition(AQModelLayers.ICHCAHUIPILLI_ARMOR, IchcahuipilliArmorModel::createBodyLayer);

        // Boats
        LayerDefinition boatDef = BoatModel.createBodyModel(false);
        LayerDefinition chestDef = BoatModel.createBodyModel(true);

        for (AQBoat.BoatType type : AQBoat.BoatType.values()) {
            event.registerLayerDefinition(AQBoatRenderer.createBoatModelName(type), () -> boatDef);
            event.registerLayerDefinition(AQBoatRenderer.createChestBoatModelName(type), () -> chestDef);
        }
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_DUNGEON_DOOR.get(), AztecDungeonDoorRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_CRAFTING_STATION.get(), AztecWorktableRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_THRONE.get(), AztecThroneRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.SPIKE_TRAP.get(), SpikeTrapRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AZTEC_DUNGEON_CHEST.get(), AztecDungeonChestRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.SIMPLE_SKULL.get(), SimpleSkullRenderer::new);
        event.registerBlockEntityRenderer(AQBlockEntities.AQ_SIGN.get(), SignRenderer::new);


        for (BEWLRS.Holder holder : BEWLRS.BEWLR_LIST) {
            holder.populate(Minecraft.getInstance().getBlockEntityRenderDispatcher());
        }
        event.registerEntityRenderer(AQEntities.TLATLAOMI.get(), TlatlaomiRenderer::new);
        event.registerEntityRenderer(AQEntities.AQ_BOAT.get(), context -> new AQBoatRenderer(context, false));
        event.registerEntityRenderer(AQEntities.AQ_CHEST_BOAT.get(), context -> new AQBoatRenderer(context, true));
    }

    @SubscribeEvent
    public static void registerReloadListeners(RegisterClientReloadListenersEvent event) {
        event.registerReloadListener(BEWLRS.MODEL_SET);
    }

    @Deprecated // Render type should be specified in block model
    private static void setBlockRenderTypes() {

    }

    private static void addSkippedHighlightBlocks() {
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_0);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_1);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0);
        ClientEvents.skipHighlight(AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        event.register((state, blockDisplayReader, pos, color) -> blockDisplayReader != null && pos != null ? BiomeColors.getAverageFoliageColor(blockDisplayReader, pos) : FoliageColor.getDefaultColor(),
                AQBlocks.VINES_1.get());
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item event) {
        BlockColors blockColors = event.getBlockColors();

        event.register((itemStack, color) -> {
            BlockState blockState = ((BlockItem) itemStack.getItem()).getBlock().defaultBlockState();
            return blockColors.getColor(blockState, null, null, color);
        }, AQBlocks.VINES_1.get());
    }
}
