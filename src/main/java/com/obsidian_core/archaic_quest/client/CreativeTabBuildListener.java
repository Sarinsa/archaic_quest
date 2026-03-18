package com.obsidian_core.archaic_quest.client;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber( modid = ArchaicQuest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT )
public class CreativeTabBuildListener {
    
    @SubscribeEvent
    public static void onPopulateCreativeTabs( BuildCreativeModeTabContentsEvent event ) {
        // Add all our spawn eggs to the vanilla spawn egg tab
        if( event.getTabKey() == CreativeModeTabs.SPAWN_EGGS ) {
            AQItems.SPAWN_EGGS.forEach( egg -> event.accept( new ItemStack( egg.get() ) ) );
        }
    }
}
