package com.obsidian_core.archaic_quest.datagen.model;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSetRegObj;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class AQItemModelProvider extends ItemModelProvider {

    public AQItemModelProvider(DataGenerator generator, ExistingFileHelper fileHelper) {
        super(generator, ArchaicQuest.MODID, fileHelper);
    }

    @Override
    protected void registerModels() {
        AQItems.SIMPLE_ITEMS.forEach((item) -> simpleItem(item.get(), false));
        AQItems.SPAWN_EGGS.forEach((egg) -> spawnEgg(egg.get()));

        WoodSetRegObj.WOOD_SETS.forEach(woodSet -> {
            simpleItem(woodSet.getSapling().get().asItem(), true);
            existingBlock(woodSet.getLeaves());
            existingBlock(woodSet.getLog());
            //existingBlock(woodSet.getStrippedLog());
            existingBlock(woodSet.getPlanks());
            existingBlock(woodSet.getSlab());
            existingBlock(woodSet.getVertSlab());
            existingBlock(woodSet.getStairs());
            existingBlock(woodSet.getFenceGate());
            existingBlock(woodSet.getPressurePlate());

            withExistingParent(woodSet.getFence().getId().getPath(), new ResourceLocation("minecraft:block/fence_inventory"))
                    .texture("texture", blockTexture(woodSet.getPlanks().get()));

            withExistingParent(woodSet.getButton().getId().getPath(), new ResourceLocation("minecraft:block/button_inventory"))
                    .texture("texture", blockTexture(woodSet.getPlanks().get()));

            //simpleItem(woodSet.getDoor().get().asItem(), false);
        });
    }

    private void existingBlock(RegistryObject<? extends Block> block) {
        withExistingParent(block.getId().getPath(), new ResourceLocation(ArchaicQuest.MODID, "block/" + block.getId().getPath()));
    }

    private <T extends Item> void simpleItem(T item, boolean blockTexture) {
        ResourceLocation regName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
        singleTexture(regName.getPath(), mcLoc("item/generated"), "layer0", ArchaicQuest.resourceLoc((blockTexture ? "block" : "item") + "/" + regName.getPath()));
    }

    private void spawnEgg(ForgeSpawnEggItem spawnEgg) {
        ResourceLocation regName = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(spawnEgg));
        withExistingParent(regName.getPath(), mcLoc("item/template_spawn_egg"));
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    public ResourceLocation blockTexture(Block block) {
        ResourceLocation name = key(block);
        return new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath());
    }
}
