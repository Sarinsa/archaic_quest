package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSetRegObj;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import static com.obsidian_core.archaic_quest.common.core.register.AQBlocks.*;


public class AQBlockStateProvider extends AbstractBlockStateProvider {

    public AQBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        SIMPLE_BLOCKS.forEach((block) -> simpleBlockAndItem(block.get()));
        VERT_SLAB_VARIANTS.forEach((block, vertSlab) -> simpleVerticalSlab(vertSlab.get(), block.get()));
        SLAB_VARIANTS.forEach((block, slab) -> slab(slab.get(), block.get()));

        STAIRS_VARIANTS.forEach((block, stairs) -> {
            stairsBlock(stairs.get(), blockTexture(block.get()));
            ModelFile model = models().withExistingParent(name(stairs.get()), mcLoc("block/stairs"));

            simpleBlockItem(stairs.get(), model);
        });

        WoodSetRegObj.WOOD_SETS.forEach((woodSet) -> {
            Block planks = woodSet.getPlanks().get();

            sapling(woodSet.getSapling().get());
            leaves(woodSet.getLeaves().get());
            logBlock(woodSet.getLog().get());
            //logBlock(woodSet.getStrippedLog().get());
            simpleBlock(planks);
            slab(woodSet.getSlab().get(), planks);
            simpleVerticalSlab(woodSet.getVertSlab().get(), planks);
            stairsBlock(woodSet.getStairs().get(), blockTexture(planks));
            fenceBlock(woodSet.getFence().get(), blockTexture(planks));
            fenceGateBlock(woodSet.getFenceGate().get(), blockTexture(planks));
            pressurePlateBlock(woodSet.getPressurePlate().get(), blockTexture(planks));
            buttonBlock(woodSet.getButton().get(), blockTexture(planks));

        });

        for (RegistryObject<Block> regObject : REGISTRY.getEntries()) {
            Block block = regObject.get();

            if (block instanceof DoubleCropBlock doubleCropBlock) {
                doubleCrop(doubleCropBlock);
            }
            else if (block instanceof CoolVinesBlock vine) {
                vine(vine);
            }
            else if (block instanceof AztecDungeonDoorBlock) {
                simpleBlock(block, models().withExistingParent(name(block), ArchaicQuest.resourceLoc("block/aztec_dungeon_door")));
            }
            else if (block instanceof AztecThroneBlock) {
                simpleBlock(block, models().withExistingParent(name(block), ArchaicQuest.resourceLoc("block/template_throne")));
            }
            else if (block instanceof SpearTrapBlock spearTrap) {
                spearTrap(spearTrap);
            }
            else if (block instanceof AztecWoodPillarBlock woodPillar) {
                woodPillar(woodPillar);
            }
        }
    }
}
