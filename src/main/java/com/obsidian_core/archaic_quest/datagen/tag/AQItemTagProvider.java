package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import com.obsidian_core.archaic_quest.common.tag.AQItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;


public class AQItemTagProvider extends ItemTagsProvider {
    
    public AQItemTagProvider( PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider,
                              BlockTagsProvider blockTagsProvider, @Nullable ExistingFileHelper fileHelper ) {
        super( packOutput, lookupProvider, blockTagsProvider.contentsGetter(), ArchaicQuest.MODID, fileHelper );
    }
    
    @Override
    protected void addTags( HolderLookup.Provider lookupProvider ) {
        copy( BlockTags.SAPLINGS, ItemTags.SAPLINGS );
        copy( BlockTags.LEAVES, ItemTags.LEAVES );
        copy( BlockTags.LOGS_THAT_BURN, ItemTags.LOGS_THAT_BURN );
        copy( BlockTags.PLANKS, ItemTags.PLANKS );
        copy( BlockTags.WOODEN_SLABS, ItemTags.WOODEN_SLABS );
        copy( BlockTags.WOODEN_STAIRS, ItemTags.WOODEN_STAIRS );
        copy( BlockTags.FENCES, ItemTags.FENCES );
        copy( BlockTags.WOODEN_PRESSURE_PLATES, ItemTags.WOODEN_PRESSURE_PLATES );
        copy( BlockTags.WOODEN_BUTTONS, ItemTags.WOODEN_BUTTONS );
        copy( BlockTags.WOODEN_DOORS, ItemTags.WOODEN_DOORS );
        copy( BlockTags.WOODEN_TRAPDOORS, ItemTags.WOODEN_TRAPDOORS );
        
        WoodSet.WOOD_SETS.forEach( woodSet -> {
            tag( ItemTags.SIGNS ).add( woodSet.getSign().get().asItem() );
        } );
        
        copy( AQBlockTags.ORE_TIN, AQItemTags.ORE_TIN );
        copy( AQBlockTags.ORE_SILVER, AQItemTags.ORE_SILVER );
        copy( AQBlockTags.ORE_QUARTZ, AQItemTags.ORE_QUARTZ );
        
        copy( BlockTags.SAPLINGS, ItemTags.SAPLINGS );
        
        tag( AQItemTags.INGOT_TIN ).add( AQItems.TIN_INGOT.get() );
        tag( AQItemTags.INGOT_SILVER ).add( AQItems.SILVER_INGOT.get() );
        tag( Tags.Items.GEMS ).add(
                AQItems.JADE.get(),
                AQItems.TURQUOISE.get() );
    }
}
