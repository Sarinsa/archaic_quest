package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.datagen.worldgen.AQBiomes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.tags.BiomeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AQBiomeTagProvider extends BiomeTagsProvider {
    
    public AQBiomeTagProvider( PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper ) {
        super( packOutput, lookupProvider, ArchaicQuest.MODID, fileHelper );
    }
    
    @Override
    protected void addTags( HolderLookup.Provider lookupProvider ) {
        tag( BiomeTags.IS_JUNGLE ).addOptional(
                AQBiomes.AZTEC_JUNGLE.location()
        );
        tag( BiomeTags.SPAWNS_WARM_VARIANT_FROGS ).addOptional(
                AQBiomes.AZTEC_JUNGLE.location()
        );
    }
}
