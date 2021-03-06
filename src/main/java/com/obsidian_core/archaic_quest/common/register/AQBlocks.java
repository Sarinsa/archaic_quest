package com.obsidian_core.archaic_quest.common.register;

import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.AQCreativeTabs;
import com.obsidian_core.archaic_quest.datagen.blockstate.AQBlockStateProvider;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.TallBlockItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class AQBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArchaicQuest.MODID);

    public static final RegistryObject<Block> TIN_ORE = registerSimpleBlock("tin_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> SILVER_ORE = registerSimpleBlock("silver_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> BASALT_ORE = registerSimpleBlock("basalt_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> GRANITE_QUARTZ_ORE = registerSimpleBlock("granite_quartz_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(1)));
    public static final RegistryObject<Block> ANDESITE_TURQUOISE_ORE = registerSimpleBlock("andesite_turquoise_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> DIORITE_JADE_ORE = registerSimpleBlock("diorite_jade_ore", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(2)));
    public static final RegistryObject<Block> ONYX = registerSimpleBlock("onyx", AQCreativeTabs.BLOCKS, () -> new OreBlock(AbstractBlock.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.0F, 3.0F).harvestTool(ToolType.PICKAXE).harvestLevel(3)));

    public static final RegistryObject<Block> ANDESITE_BRICKS = registerSimpleBlock("andesite_bricks", AQCreativeTabs.BLOCKS, () ->  new Block(AbstractBlock.Properties.of(Material.STONE, MaterialColor.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_0 = registerSimpleBlock("andesite_aztec_bricks_0", AQCreativeTabs.BLOCKS , () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_1 = registerSimpleBlock("andesite_aztec_bricks_1", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2 = registerSimpleBlock("andesite_aztec_bricks_2", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_3 = registerSimpleBlock("andesite_aztec_bricks_3", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_4 = registerSimpleBlock("andesite_aztec_bricks_4", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_5 = registerSimpleBlock("andesite_aztec_bricks_5", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_6 = registerSimpleBlock("andesite_aztec_bricks_6", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_7 = registerSimpleBlock("andesite_aztec_bricks_7", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_8 = registerSimpleBlock("andesite_aztec_bricks_8", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_9 = registerSimpleBlock("andesite_aztec_bricks_9", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_10 = registerSimpleBlock("andesite_aztec_bricks_10", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_11 = registerSimpleBlock("andesite_aztec_bricks_11", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_12 = registerSimpleBlock("andesite_aztec_bricks_12", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_13 = registerSimpleBlock("andesite_aztec_bricks_13", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_14 = registerSimpleBlock("andesite_aztec_bricks_14", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_15 = registerSimpleBlock("andesite_aztec_bricks_15", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_16 = registerBlock("andesite_aztec_bricks_16", AQCreativeTabs.BLOCKS, () -> new SimpleDirectionalBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_17 = registerSimpleBlock("andesite_aztec_bricks_17", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_18 = registerSimpleBlock("andesite_aztec_bricks_18", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_19 = registerSimpleBlock("andesite_aztec_bricks_19", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_20 = registerSimpleBlock("andesite_aztec_bricks_20", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_21 = registerSimpleBlock("andesite_aztec_bricks_21", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_22 = registerSimpleBlock("andesite_aztec_bricks_22", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_23 = registerSimpleBlock("andesite_aztec_bricks_23", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_24 = registerSimpleBlock("andesite_aztec_bricks_24", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_25 = registerSimpleBlock("andesite_aztec_bricks_25", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_26 = registerSimpleBlock("andesite_aztec_bricks_26", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_27 = registerBlock("andesite_aztec_bricks_27", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_28 = registerSimpleBlock("andesite_aztec_bricks_28", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_29 = registerSimpleBlock("andesite_aztec_bricks_29", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_BRICK_VERTICAL_SLAB = registerBlock("andesite_brick_vertical_slab", AQCreativeTabs.BLOCKS, () ->  new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_0 = registerBlock("andesite_aztec_brick_vertical_slab_0", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_1 = registerBlock("andesite_aztec_brick_vertical_slab_1", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_2 = registerBlock("andesite_aztec_brick_vertical_slab_2", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_3 = registerBlock("andesite_aztec_brick_vertical_slab_3", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_4 = registerBlock("andesite_aztec_brick_vertical_slab_4", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_5 = registerBlock("andesite_aztec_brick_vertical_slab_5", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_6 = registerBlock("andesite_aztec_brick_vertical_slab_6", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_7 = registerBlock("andesite_aztec_brick_vertical_slab_7", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_8 = registerBlock("andesite_aztec_brick_vertical_slab_8", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_9 = registerBlock("andesite_aztec_brick_vertical_slab_9", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_10 = registerBlock("andesite_aztec_brick_vertical_slab_10", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_11 = registerBlock("andesite_aztec_brick_vertical_slab_11", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_12 = registerBlock("andesite_aztec_brick_vertical_slab_12", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_13 = registerBlock("andesite_aztec_brick_vertical_slab_13", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_14 = registerBlock("andesite_aztec_brick_vertical_slab_14", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_15 = registerBlock("andesite_aztec_brick_vertical_slab_15", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_17 = registerBlock("andesite_aztec_brick_vertical_slab_17", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_18 = registerBlock("andesite_aztec_brick_vertical_slab_18", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_19 = registerBlock("andesite_aztec_brick_vertical_slab_19", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_20 = registerBlock("andesite_aztec_brick_vertical_slab_20", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_21 = registerBlock("andesite_aztec_brick_vertical_slab_21", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_22 = registerBlock("andesite_aztec_brick_vertical_slab_22", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_23 = registerBlock("andesite_aztec_brick_vertical_slab_23", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_24 = registerBlock("andesite_aztec_brick_vertical_slab_24", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_25 = registerBlock("andesite_aztec_brick_vertical_slab_25", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_26 = registerBlock("andesite_aztec_brick_vertical_slab_26", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_27 = registerBlock("andesite_aztec_brick_vertical_slab_27", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_28 = registerBlock("andesite_aztec_brick_vertical_slab_28", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICK_VERTICAL_SLAB_29 = registerBlock("andesite_aztec_brick_vertical_slab_29", AQCreativeTabs.BLOCKS, () -> new VerticalSlabBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> AZTEC_PILLAR = registerBlock("aztec_pillar", AQCreativeTabs.DECORATION, () -> new ChiselPillarBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get()).noCollission()));

    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_0 = registerBlock("andesite_aztec_trap_0", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_1 = registerBlock("andesite_aztec_trap_1", AQCreativeTabs.BLOCKS, () -> new AztecTrapBlock(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));
    public static final RegistryObject<Block> STONE_AZTEC_BRICKS_0 = registerBlock("stone_aztec_bricks_0", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.copy(ANDESITE_BRICKS.get())));

    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR = registerBlock("aztec_spruce_wood_pillar", AQCreativeTabs.BLOCKS, () -> new AztecWoodPillarBlock(AbstractBlock.Properties.copy(Blocks.SPRUCE_WOOD)));
    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE = registerBlock("aztec_spruce_wood_pillar_andesite_base", AQCreativeTabs.BLOCKS, () -> new AztecWoodPillarBaseBlock(AbstractBlock.Properties.copy(Blocks.COBBLESTONE)));

    public static final RegistryObject<DoorBlock> DUNGEON_DOOR_BARS = registerDoorBlock("dungeon_door_bars", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_0 = registerDoorBlock("medieval_door_0", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().harvestTool(ToolType.AXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_1 = registerDoorBlock("medieval_door_1", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_2 = registerDoorBlock("medieval_door_2", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block", AQCreativeTabs.BLOCKS, () -> new Block(AbstractBlock.Properties.of(Material.STONE).strength(1.0F, 1.0F).sound(SoundType.STONE).hasPostProcess(AQBlocks::always).emissiveRendering(AQBlocks::always)));

    public static final RegistryObject<Block> AZTEC_CRAFTING_STATION = registerBlock("aztec_crafting_station", AQCreativeTabs.BLOCKS, () -> new AztecCraftingStationBlock(AbstractBlock.Properties.of(Material.STONE).noOcclusion().strength(1.5F, 4.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)));

    private static RegistryObject<Block> registerSimpleBlock(String name, ItemGroup itemGroup, Supplier<Block> blockSupplier) {
        RegistryObject<Block> registryObject = registerBlock(name, itemGroup, blockSupplier);
        AQBlockStateProvider.SIMPLE_BLOCKS.add(registryObject);
        return registryObject;
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, ItemGroup itemGroup, Supplier<T> blockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(name, blockSupplier);
        AQItems.ITEMS.register(name, () -> new BlockItem(registryObject.get(), new Item.Properties().tab(itemGroup)));
        return registryObject;
    }

    private static <T extends Block> RegistryObject<T> registerBlockNoBlockItem(String name, Supplier<T> blockSupplier) {
        return BLOCKS.register(name, blockSupplier);
    }

    private static <T extends DoorBlock> RegistryObject<T> registerDoorBlock(String name, Supplier<T> doorBlockSupplier) {
        RegistryObject<T> registryObject = BLOCKS.register(name, doorBlockSupplier);
        AQItems.ITEMS.register(name, () -> new TallBlockItem(registryObject.get(), new Item.Properties().tab(AQCreativeTabs.BLOCKS).stacksTo(16)));
        return registryObject;
    }

    private static boolean always(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }
}
