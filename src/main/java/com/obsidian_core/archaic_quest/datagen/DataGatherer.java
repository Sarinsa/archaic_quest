package com.obsidian_core.archaic_quest.datagen;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.datagen.blockstate.AQBlockStateProvider;
import com.obsidian_core.archaic_quest.datagen.damage.AQDamageTypes;
import com.obsidian_core.archaic_quest.datagen.lang.AQLanguageProvider;
import com.obsidian_core.archaic_quest.datagen.loot_modifier.AQGlobalLootModifierProvider;
import com.obsidian_core.archaic_quest.datagen.loot_table.AQLootTableProvider;
import com.obsidian_core.archaic_quest.datagen.model.AQItemModelProvider;
import com.obsidian_core.archaic_quest.datagen.recipe.AQRecipeProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQBiomeTagProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQBlockTagProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQDamageTypeTagProvider;
import com.obsidian_core.archaic_quest.datagen.tag.AQItemTagProvider;
import com.obsidian_core.archaic_quest.datagen.worldgen.AQBiomes;
import com.obsidian_core.archaic_quest.datagen.worldgen.AQConfiguredFeatures;
import com.obsidian_core.archaic_quest.datagen.worldgen.AQPlacedFeatures;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber( modid = ArchaicQuest.MODID, bus = Mod.EventBusSubscriber.Bus.MOD )
public class DataGatherer {
    
    private static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add( Registries.DAMAGE_TYPE, AQDamageTypes::bootstrap )
            .add( Registries.CONFIGURED_FEATURE, AQConfiguredFeatures::bootstrap )
            .add( Registries.PLACED_FEATURE, AQPlacedFeatures::bootstrap )
            .add( Registries.BIOME, AQBiomes::bootstrap );
    
    @SubscribeEvent
    public static void onGatherData( GatherDataEvent event ) {
        final DataGenerator dataGen = event.getGenerator();
        final PackOutput packOutput = dataGen.getPackOutput();
        final ExistingFileHelper fileHelper = event.getExistingFileHelper();
        final CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        
        if( event.includeClient() ) {
            dataGen.addProvider( true, new AQLanguageProvider( packOutput ) );
            dataGen.addProvider( true, new AQBlockStateProvider( packOutput, fileHelper ) );
            dataGen.addProvider( true, new AQItemModelProvider( packOutput, fileHelper ) );
        }
        if( event.includeServer() ) {
            dataGen.addProvider( true, new DatapackBuiltinEntriesProvider( packOutput, lookupProvider, BUILDER, Set.of( ArchaicQuest.MODID ) ) );
            
            dataGen.addProvider( true, new AQGlobalLootModifierProvider( packOutput ) );
            dataGen.addProvider( true, new AQRecipeProvider( packOutput ) );
            dataGen.addProvider( true, new AQLootTableProvider( packOutput ) );
            
            AQBlockTagProvider blockTagProvider = new AQBlockTagProvider( packOutput, lookupProvider, fileHelper );
            dataGen.addProvider( true, blockTagProvider );
            dataGen.addProvider( true, new AQItemTagProvider( packOutput, lookupProvider, blockTagProvider, fileHelper ) );
            dataGen.addProvider( true, new AQBiomeTagProvider( packOutput, lookupProvider, fileHelper ) );
            dataGen.addProvider( true, new AQDamageTypeTagProvider( packOutput, lookupProvider, fileHelper ) );
        }
    }
}
