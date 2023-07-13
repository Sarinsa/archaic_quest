package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.block.CoolVinesBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSetRegObj;
import com.obsidian_core.archaic_quest.common.tag.AQBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class AQBlockTagProvider extends BlockTagsProvider {

    public AQBlockTagProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGenerator, ArchaicQuest.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(AQBlockTags.ORE_TIN).add(AQBlocks.TIN_ORE.get());
        tag(AQBlockTags.ORE_SILVER).add(AQBlocks.SILVER_ORE.get());
        tag(AQBlockTags.ORE_QUARTZ).add(AQBlocks.GRANITE_QUARTZ_ORE.get());

        WoodSetRegObj.WOOD_SETS.forEach((woodSet) -> {
            tag(BlockTags.SAPLINGS).add(woodSet.getSapling().get());
            tag(BlockTags.LEAVES).add(woodSet.getLeaves().get());
            //tag(BlockTags.LOGS).add(woodSet.getLog().get(), woodSet.getStrippedLog().get());
            tag(BlockTags.LOGS_THAT_BURN).add(woodSet.getLog().get(), woodSet.getStrippedLog().get(), woodSet.getWood().get(), woodSet.getStrippedWood().get());
            tag(BlockTags.PLANKS).add(woodSet.getPlanks().get());
            //tag(BlockTags.SLABS).add(woodSet.getSlab().get());
            tag(BlockTags.WOODEN_SLABS).add(woodSet.getSlab().get());
            tag(AQBlockTags.VERT_SLAB).add(woodSet.getVertSlab().get());
            //tag(BlockTags.STAIRS).add(woodSet.getStairs().get());
            tag(BlockTags.WOODEN_STAIRS).add(woodSet.getStairs().get());
            tag(BlockTags.FENCES).add(woodSet.getFence().get());
            tag(BlockTags.FENCE_GATES).add(woodSet.getFenceGate().get());
            //tag(BlockTags.PRESSURE_PLATES).add(woodSet.getPressurePlate().get());
            tag(BlockTags.WOODEN_PRESSURE_PLATES).add(woodSet.getPressurePlate().get());
            //tag(BlockTags.BUTTONS).add(woodSet.getButton().get());
            tag(BlockTags.WOODEN_BUTTONS).add(woodSet.getButton().get());
            //tag(BlockTags.DOORS).add(woodSet.getDoor().get());
            tag(BlockTags.WOODEN_DOORS).add(woodSet.getDoor().get());
            //tag(BlockTags.TRAPDOORS).add(woodSet.getTrapDoor().get());
            tag(BlockTags.WOODEN_TRAPDOORS).add(woodSet.getTrapdoor().get());
            tag(BlockTags.STANDING_SIGNS).add(woodSet.getSign().get());
            tag(BlockTags.WALL_SIGNS).add(woodSet.getWallSign().get());
        });

        TagsProvider.TagAppender<Block> CLIMBABLE = tag(BlockTags.CLIMBABLE);

        for (RegistryObject<Block> regObject : AQBlocks.REGISTRY.getEntries()) {
            Block block = regObject.get();

            if (block instanceof CoolVinesBlock) {
                CLIMBABLE.add(block);
            }
        }

        AQBlocks.BLOCK_TAGS.forEach((registryObject, tagKeys) -> {
            for (TagKey<Block> tagKey : tagKeys) {
                tag(tagKey).add(registryObject.get());
            }
        });
    }
}
