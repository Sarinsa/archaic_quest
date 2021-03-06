package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.register.AQBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = ArchaicQuest.MODID)
public class ClientRegister {

    @SubscribeEvent
    public static void register(FMLClientSetupEvent event) {
        setBlockRenderTypes();
    }

    private static void setBlockRenderTypes() {
        RenderTypeLookup.setRenderLayer(AQBlocks.AZTEC_CRAFTING_STATION.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.DUNGEON_DOOR_BARS.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_0.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_1.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(AQBlocks.MEDIEVAL_DOOR_2.get(), RenderType.cutout());
    }
}
