package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.common.block.*;
import com.obsidian_core.archaic_quest.common.block.base.*;
import com.obsidian_core.archaic_quest.common.block.misc.AQSoundTypes;
import com.obsidian_core.archaic_quest.common.block.skull.SimpleSkullBlock;
import com.obsidian_core.archaic_quest.common.block.skull.SimpleWallSkullBlock;
import com.obsidian_core.archaic_quest.common.block.skull.SkullGobletBlock;
import com.obsidian_core.archaic_quest.common.block.tree.AztecJungleTreeGrower;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.util.EmptyTreeGrower;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecDungeonChestBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecDungeonDoorBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecThroneBlockItem;
import com.obsidian_core.archaic_quest.common.item.blockitem.AztecWorktableBlockItem;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class AQBlocks {
    
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create( ForgeRegistries.BLOCKS, ArchaicQuest.MODID );
    
    public static final Map<RegistryObject<? extends Block>, TagKey<Block>[]> BLOCK_TAGS = new HashMap<>();
    public static final List<RegistryObject<Block>> SIMPLE_BLOCKS = new ArrayList<>();
    public static final Map<RegistryObject<Block>, RegistryObject<VerticalSlabBlock>> VERT_SLAB_VARIANTS = new HashMap<>();
    public static final Map<RegistryObject<Block>, RegistryObject<SlabBlock>> SLAB_VARIANTS = new HashMap<>();
    public static final Map<RegistryObject<Block>, RegistryObject<StairBlock>> STAIRS_VARIANTS = new HashMap<>();
    
    public static final BlockBehaviour.Properties ANDESITE_BRICKS_PROP;
    public static final BlockBehaviour.Properties GOLD_BRICKS_PROP;
    
    
    static {
        ANDESITE_BRICKS_PROP = BlockBehaviour.Properties.of().mapColor( MapColor.STONE ).requiresCorrectToolForDrops().strength( 1.5F, 6.0F );
        GOLD_BRICKS_PROP = BlockBehaviour.Properties.of().mapColor( MapColor.GOLD ).requiresCorrectToolForDrops().strength( 3.0F, 6.0F ).sound( SoundType.METAL );
    }
    
    
    // VEGETATION
    public static final RegistryObject<Block> VINES_1 = registerBlock( "vines_1", AQCreativeTabs.Keys.BLOCKS, () -> new CuttableVinesBlock( BlockBehaviour.Properties.copy( Blocks.VINE ) ) );
    public static final RegistryObject<Block> AZTEC_JUNGLE_SAPLING = registerBlock( "aztec_jungle_sapling", AQCreativeTabs.Keys.BLOCKS, () -> new SaplingBlock( new AztecJungleTreeGrower(), BlockBehaviour.Properties.copy( Blocks.JUNGLE_SAPLING ) ) );
    
    public static final WoodSet AHUEHUETE_WOOD_SET = new WoodSet( "ahuehuete", BlockBehaviour.Properties.copy( Blocks.OAK_PLANKS ), EmptyTreeGrower.INSTANCE );
    
    // CROPS
    public static final RegistryObject<Block> CORN_CROP = registerBlockNoItem( "corn_crop", CornCropBlock::new );
    
    // ORES
    public static final RegistryObject<Block> TIN_ORE = simpleBlock( "tin_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ) ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL );
    public static final RegistryObject<Block> SILVER_ORE = simpleBlock( "silver_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ) ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> BASALT_ORE = simpleBlock( "basalt_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ) ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GRANITE_QUARTZ_ORE = simpleBlock( "granite_quartz_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ), 2, 5 ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_STONE_TOOL );
    public static final RegistryObject<Block> ANDESITE_TURQUOISE_ORE = simpleBlock( "andesite_turquoise_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ), 2, 5 ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> DIORITE_JADE_ORE = simpleBlock( "diorite_jade_ore", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ), 2, 5 ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> ONYX = simpleBlock( "onyx", AQCreativeTabs.Keys.BLOCKS, () -> new BaseOreBlock( BlockBehaviour.Properties.of().sound( SoundType.STONE ).requiresCorrectToolForDrops().strength( 3.0F, 3.0F ) ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    
    
    // AZTEC STUFF
    public static final RegistryObject<Block> ANDESITE_BRICKS = simpleBlockWithVars( "andesite_bricks", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> MOSSY_ANDESITE_BRICKS = simpleBlockWithVars( "mossy_andesite_bricks", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_0 = simpleBlockWithVars( "andesite_aztec_bricks_0", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_1 = simpleBlockWithVars( "andesite_aztec_bricks_1", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2 = simpleBlockWithVars( "andesite_aztec_bricks_2", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_2_CRACKED = simpleBlockWithVars( "andesite_aztec_bricks_2_cracked", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_3 = simpleBlockWithVars( "andesite_aztec_bricks_3", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_4 = simpleBlockWithVars( "andesite_aztec_bricks_4", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_5 = simpleBlockWithVars( "andesite_aztec_bricks_5", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_6 = simpleBlockWithVars( "andesite_aztec_bricks_6", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_7 = simpleBlockWithVars( "andesite_aztec_bricks_7", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_8 = simpleBlockWithVars( "andesite_aztec_bricks_8", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_9 = simpleBlockWithVars( "andesite_aztec_bricks_9", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_10 = simpleBlockWithVars( "andesite_aztec_bricks_10", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_11 = simpleBlockWithVars( "andesite_aztec_bricks_11", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_12 = simpleBlockWithVars( "andesite_aztec_bricks_12", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_13 = simpleBlockWithVars( "andesite_aztec_bricks_13", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_14 = simpleBlockWithVars( "andesite_aztec_bricks_14", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> MOSSY_ANDESITE_AZTEC_BRICKS_14 = simpleBlockWithVars( "mossy_andesite_aztec_bricks_14", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_15 = simpleBlockWithVars( "andesite_aztec_bricks_15", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_16 = registerBlock( "andesite_aztec_bricks_16", AQCreativeTabs.Keys.BLOCKS, () -> new Block( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_16_VERT_SLAB = registerBlock( "andesite_aztec_bricks_16_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_17 = simpleBlockWithVars( "andesite_aztec_bricks_17", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_18 = simpleBlockWithVars( "andesite_aztec_bricks_18", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_19 = simpleBlockWithVars( "andesite_aztec_bricks_19", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_20 = simpleBlockWithVars( "andesite_aztec_bricks_20", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_21 = simpleBlockWithVars( "andesite_aztec_bricks_21", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_22 = simpleBlockWithVars( "andesite_aztec_bricks_22", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_23 = simpleBlockWithVars( "andesite_aztec_bricks_23", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_24 = registerBlock( "andesite_aztec_bricks_24", AQCreativeTabs.Keys.BLOCKS, () -> new Block( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_24_VERT_SLAB = registerBlock( "andesite_aztec_bricks_24_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_25 = simpleBlockWithVars( "andesite_aztec_bricks_25", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_26 = registerBlock( "andesite_aztec_bricks_26", AQCreativeTabs.Keys.BLOCKS, () -> new Block( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_26_VERT_SLAB = registerBlock( "andesite_aztec_bricks_26_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_27 = registerBlock( "andesite_aztec_bricks_27", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_27_VERT_SLAB = registerBlock( "andesite_aztec_bricks_27_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_28 = simpleBlockWithVars( "andesite_aztec_bricks_28", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_29 = registerBlock( "andesite_aztec_bricks_29", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_29_VERT_SLAB = registerBlock( "andesite_aztec_bricks_29_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_30 = simpleBlockWithVars( "andesite_aztec_bricks_30", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_31 = simpleBlockWithVars( "andesite_aztec_bricks_31", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_32 = registerBlock( "andesite_aztec_bricks_32", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_32_VERT_SLAB = registerBlock( "andesite_aztec_bricks_32_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_33 = registerBlock( "andesite_aztec_bricks_33", AQCreativeTabs.Keys.BLOCKS, () -> new Block( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_33_VERT_SLAB = registerBlock( "andesite_aztec_bricks_33_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_34 = registerBlock( "andesite_aztec_bricks_34", AQCreativeTabs.Keys.BLOCKS, () -> new Block( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_34_VERT_SLAB = registerBlock( "andesite_aztec_bricks_34_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_35 = simpleBlockWithVars( "andesite_aztec_bricks_35", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_36 = registerBlock( "andesite_aztec_bricks_36", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_36_VERT_SLAB = registerBlock( "andesite_aztec_bricks_36_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_37 = simpleBlockWithVars( "andesite_aztec_bricks_37", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_38 = simpleBlockWithVars( "andesite_aztec_bricks_38", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_39 = simpleBlockWithVars( "andesite_aztec_bricks_39", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_40 = simpleBlockWithVars( "andesite_aztec_bricks_40", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_41 = simpleBlockWithVars( "andesite_aztec_bricks_41", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_42 = simpleBlockWithVars( "andesite_aztec_bricks_42", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_43 = simpleBlockWithVars( "andesite_aztec_bricks_43", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_44 = simpleBlockWithVars( "andesite_aztec_bricks_44", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_45 = simpleBlockWithVars( "andesite_aztec_bricks_45", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_46 = simpleBlockWithVars( "andesite_aztec_bricks_46", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_47 = simpleBlockWithVars( "andesite_aztec_bricks_47", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_48 = simpleBlockWithVars( "andesite_aztec_bricks_48", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_49 = simpleBlockWithVars( "andesite_aztec_bricks_49", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_50 = simpleBlockWithVars( "andesite_aztec_bricks_50", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_51 = simpleBlockWithVars( "andesite_aztec_bricks_51", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_52 = simpleBlockWithVars( "andesite_aztec_bricks_52", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_53 = simpleBlockWithVars( "andesite_aztec_bricks_53", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_54 = simpleBlockWithVars( "andesite_aztec_bricks_54", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_55 = simpleBlockWithVars( "andesite_aztec_bricks_55", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_56 = simpleBlockWithVars( "andesite_aztec_bricks_56", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_57 = simpleBlockWithVars( "andesite_aztec_bricks_57", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> MOSSY_ANDESITE_AZTEC_BRICKS_57 = simpleBlockWithVars( "mossy_andesite_aztec_bricks_57", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_58 = simpleBlockWithVars( "andesite_aztec_bricks_58", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_59 = simpleBlockWithVars( "andesite_aztec_bricks_59", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_60 = simpleBlockWithVars( "andesite_aztec_bricks_60", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_61 = simpleBlockWithVars( "andesite_aztec_bricks_61", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_62 = simpleBlockWithVars( "andesite_aztec_bricks_62", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_63 = simpleBlockWithVars( "andesite_aztec_bricks_63", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_64 = simpleBlockWithVars( "andesite_aztec_bricks_64", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_65 = simpleBlockWithVars( "andesite_aztec_bricks_65", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_66 = simpleBlockWithVars( "andesite_aztec_bricks_66", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_67 = simpleBlockWithVars( "andesite_aztec_bricks_67", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_68 = simpleBlockWithVars( "andesite_aztec_bricks_68", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_69 = simpleBlockWithVars( "andesite_aztec_bricks_69", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_70 = simpleBlockWithVars( "andesite_aztec_bricks_70", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_71 = simpleBlockWithVars( "andesite_aztec_bricks_71", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_72 = simpleBlockWithVars( "andesite_aztec_bricks_72", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_73 = simpleBlockWithVars( "andesite_aztec_bricks_73", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_74 = simpleBlockWithVars( "andesite_aztec_bricks_74", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_75 = simpleBlockWithVars( "andesite_aztec_bricks_75", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_76 = simpleBlockWithVars( "andesite_aztec_bricks_76", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_77 = registerBlock( "andesite_aztec_bricks_77", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_77_VERT_SLAB = registerBlock( "andesite_aztec_bricks_77_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_78 = registerBlock( "andesite_aztec_bricks_78", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_78_VERT_SLAB = registerBlock( "andesite_aztec_bricks_78_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_79 = registerBlock( "andesite_aztec_bricks_79", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_79_VERT_SLAB = registerBlock( "andesite_aztec_bricks_79_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_80 = registerBlock( "andesite_aztec_bricks_80", AQCreativeTabs.Keys.BLOCKS, () -> new SimpleHorizontalBlock( AQBlocks.ANDESITE_BRICKS_PROP, false ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<VerticalSlabBlock> ANDESITE_AZTEC_BRICKS_80_VERT_SLAB = registerBlock( "andesite_aztec_bricks_80_vertical_slab", AQCreativeTabs.Keys.BLOCKS, () -> new VerticalSlabBlock( ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_81 = simpleBlockWithVars( "andesite_aztec_bricks_81", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_82 = simpleBlockWithVars( "andesite_aztec_bricks_82", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_83 = simpleBlockWithVars( "andesite_aztec_bricks_83", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_84 = simpleBlockWithVars( "andesite_aztec_bricks_84", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_85 = simpleBlockWithVars( "andesite_aztec_bricks_85", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_86 = simpleBlockWithVars( "andesite_aztec_bricks_86", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_BRICKS_87 = simpleBlockWithVars( "andesite_aztec_bricks_87", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    
    public static final RegistryObject<Block> AZTEC_PILLAR = registerBlock( "aztec_pillar", AQCreativeTabs.Keys.DECORATION, () -> new ChiselPillarBlock( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_0 = registerDungeonDoor( "aztec_dungeon_door_0", DungeonDoorType.AZTEC_DOOR_0, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_1 = registerDungeonDoor( "aztec_dungeon_door_1", DungeonDoorType.AZTEC_DOOR_1, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_FRAME_0 = registerDungeonDoor( "aztec_dungeon_door_frame_0", DungeonDoorType.AZTEC_DOOR_FRAME_0, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecDungeonDoorBlock> AZTEC_DUNGEON_DOOR_FRAME_1 = registerDungeonDoor( "aztec_dungeon_door_frame_1", DungeonDoorType.AZTEC_DOOR_FRAME_1, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecThroneBlock> AZTEC_THRONE = registerThrone( "aztec_throne", ThroneType.THRONE, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecThroneBlock> MOSSY_AZTEC_THRONE = registerThrone( "mossy_aztec_throne", ThroneType.MOSSY_THRONE, BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<AztecDungeonChestBlock> AZTEC_DUNGEON_CHEST = registerBlock( "aztec_dungeon_chest",
            () -> new AztecDungeonChestBlock( AQBlocks.ANDESITE_BRICKS_PROP ),
            () -> new AztecDungeonChestBlockItem( AQBlocks.AZTEC_DUNGEON_CHEST.get(), new Item.Properties() ),
            AQCreativeTabs.Keys.BLOCKS,
            BlockTags.MINEABLE_WITH_PICKAXE
    );
    
    public static final RegistryObject<Block> STONE_AZTEC_BRICKS_0 = simpleBlockWithVars( "stone_aztec_bricks_0", AQCreativeTabs.Keys.BLOCKS, AQBlocks.ANDESITE_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE );
    
    public static final RegistryObject<Block> BRONZE_SPEAR_TRAP = registerBlock( "bronze_spear_trap", AQCreativeTabs.Keys.DECORATION, () -> new SpearTrapBlock( 2.0F, BlockBehaviour.Properties.of().mapColor( MapColor.TERRACOTTA_BROWN ).strength( 1.0F ).sound( SoundType.WOOD ) ), BlockTags.MINEABLE_WITH_AXE );
    public static final RegistryObject<Block> GOLD_SPEAR_TRAP = registerBlock( "gold_spear_trap", AQCreativeTabs.Keys.DECORATION, () -> new SpearTrapBlock( 1.75F, BlockBehaviour.Properties.of().mapColor( MapColor.GOLD ).strength( 0.9F ).sound( SoundType.WOOD ) ), BlockTags.MINEABLE_WITH_AXE );
    public static final RegistryObject<AztecPoisonTrapBlock> AZTEC_POISON_TRAP = registerBlock( "aztec_poison_trap", AQCreativeTabs.Keys.DECORATION, () -> new AztecPoisonTrapBlock( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<SpikeTrapBlock> AZTEC_ANDESITE_SPIKE_TRAP = registerBlock( "aztec_andesite_spike_trap", AQCreativeTabs.Keys.DECORATION, () -> new SpikeTrapBlock( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_0 = registerBlock( "andesite_aztec_trap_0", AQCreativeTabs.Keys.BLOCKS, () -> new AztecTrapBlock( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> ANDESITE_AZTEC_TRAP_1 = registerBlock( "andesite_aztec_trap_1", AQCreativeTabs.Keys.BLOCKS, () -> new AztecTrapBlock( AQBlocks.ANDESITE_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE );
    
    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR = registerBlock( "aztec_spruce_wood_pillar", AQCreativeTabs.Keys.DECORATION, () -> new AztecWoodPillarBlock( BlockBehaviour.Properties.copy( Blocks.SPRUCE_WOOD ) ), BlockTags.MINEABLE_WITH_AXE );
    public static final RegistryObject<Block> AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE = registerBlock( "aztec_spruce_wood_pillar_andesite_base", AQCreativeTabs.Keys.DECORATION, () -> new BaseAztecWoodPillarBlock( BlockBehaviour.Properties.copy( Blocks.COBBLESTONE ) ), BlockTags.MINEABLE_WITH_PICKAXE );
    
    public static final RegistryObject<CeramicVaseBlock> AZTEC_VASE = registerBlock( "aztec_vase", AQCreativeTabs.Keys.DECORATION, () -> new CeramicVaseBlock( BlockBehaviour.Properties.of().sound( AQSoundTypes.CERAMIC_VASE ).strength( 0.7F ) ), BlockTags.MINEABLE_WITH_PICKAXE );
    public static final RegistryObject<Block> INFESTED_VASE = registerBlock( "aztec_infested_vase", AQCreativeTabs.Keys.DECORATION, () -> new InfestedVaseBlock( BlockBehaviour.Properties.of().sound( AQSoundTypes.CERAMIC_VASE ).strength( 0.35F ) ), BlockTags.MINEABLE_WITH_PICKAXE );
    
    public static final RegistryObject<Block> AZTEC_WORKTABLE = registerBlock( "aztec_worktable",
            () -> new AztecWorktableBlock( BlockBehaviour.Properties.copy( Blocks.STONE ).noOcclusion().strength( 1.5F, 4.0F ) ),
            () -> new AztecWorktableBlockItem( AQBlocks.AZTEC_WORKTABLE.get(), new Item.Properties() ),
            AQCreativeTabs.Keys.DECORATION,
            BlockTags.MINEABLE_WITH_PICKAXE
    );
    
    public static final RegistryObject<Block> SKULL_GOBLET = registerBlock( "skull_goblet", AQCreativeTabs.Keys.DECORATION, SkullGobletBlock::new );
    
    public static final Pair<RegistryObject<SimpleSkullBlock>, RegistryObject<SimpleWallSkullBlock>>
            JAGUAR_SKULL = registerSkull( "jaguar", true, () -> BlockBehaviour.Properties.of().pushReaction( PushReaction.DESTROY ).strength( 1.0F ).sound( SoundType.BONE_BLOCK ).instrument( NoteBlockInstrument.SKELETON ) );
    public static final Pair<RegistryObject<SimpleSkullBlock>, RegistryObject<SimpleWallSkullBlock>>
            OLD_SKULL = registerSkull( "old", false, () -> BlockBehaviour.Properties.copy( AQBlocks.JAGUAR_SKULL.getFirst().get() ).sound( SoundType.STONE ) );
    public static final Pair<RegistryObject<SimpleSkullBlock>, RegistryObject<SimpleWallSkullBlock>>
            CRYSTAL_SKULL = registerSkull( "crystal", false, () -> BlockBehaviour.Properties.copy( AQBlocks.JAGUAR_SKULL.getFirst().get() ).sound( SoundType.STONE ).instrument( NoteBlockInstrument.CHIME ) );
    public static final Pair<RegistryObject<SimpleSkullBlock>, RegistryObject<SimpleWallSkullBlock>>
            STONE_SKULL = registerSkull( "stone", false, () -> BlockBehaviour.Properties.copy( AQBlocks.JAGUAR_SKULL.getFirst().get() ).sound( SoundType.STONE ).instrument( NoteBlockInstrument.IRON_XYLOPHONE ) );
    
    public static final RegistryObject<Block> GOLDEN_IDOL = registerBlock( "golden_idol", AQCreativeTabs.Keys.DECORATION, GoldenIdolBlock::new );
    
    
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS = simpleBlockWithVars( "gold_aztec_bricks", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_0 = simpleBlockWithVars( "gold_aztec_bricks_0", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_1 = simpleBlockWithVars( "gold_aztec_bricks_1", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_2 = simpleBlockWithVars( "gold_aztec_bricks_2", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_3 = simpleBlockWithVars( "gold_aztec_bricks_3", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_4 = simpleBlockWithVars( "gold_aztec_bricks_4", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_5 = simpleBlockWithVars( "gold_aztec_bricks_5", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_6 = simpleBlockWithVars( "gold_aztec_bricks_6", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_7 = simpleBlockWithVars( "gold_aztec_bricks_7", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_8 = simpleBlockWithVars( "gold_aztec_bricks_8", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_9 = simpleBlockWithVars( "gold_aztec_bricks_9", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_10 = simpleBlockWithVars( "gold_aztec_bricks_10", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_11 = simpleBlockWithVars( "gold_aztec_bricks_11", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_12 = simpleBlockWithVars( "gold_aztec_bricks_12", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_13 = simpleBlockWithVars( "gold_aztec_bricks_13", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_14 = simpleBlockWithVars( "gold_aztec_bricks_14", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_15 = simpleBlockWithVars( "gold_aztec_bricks_15", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_16 = simpleBlockWithVars( "gold_aztec_bricks_16", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_17 = simpleBlockWithVars( "gold_aztec_bricks_17", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_18 = simpleBlockWithVars( "gold_aztec_bricks_18", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_19 = simpleBlockWithVars( "gold_aztec_bricks_19", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_20 = simpleBlockWithVars( "gold_aztec_bricks_20", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_21 = simpleBlockWithVars( "gold_aztec_bricks_21", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_22 = simpleBlockWithVars( "gold_aztec_bricks_22", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_BRICKS_23 = simpleBlockWithVars( "gold_aztec_bricks_23", AQCreativeTabs.Keys.BLOCKS, AQBlocks.GOLD_BRICKS_PROP, BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    
    public static final RegistryObject<Block> GOLD_AZTEC_TRAP_0 = registerBlock( "gold_aztec_trap_0", AQCreativeTabs.Keys.BLOCKS, () -> new AztecTrapBlock( AQBlocks.GOLD_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    public static final RegistryObject<Block> GOLD_AZTEC_TRAP_1 = registerBlock( "gold_aztec_trap_1", AQCreativeTabs.Keys.BLOCKS, () -> new AztecTrapBlock( AQBlocks.GOLD_BRICKS_PROP ), BlockTags.MINEABLE_WITH_PICKAXE, BlockTags.NEEDS_IRON_TOOL );
    
    
    // MISC
    /*
    public static final RegistryObject<DoorBlock> DUNGEON_DOOR_BARS = registerDoorBlock("dungeon_door_bars", () -> new DoorBlock(AbstractBlock.Properties.of(Material.METAL, MaterialColor.METAL).requiresCorrectToolForDrops().strength(5.0F).sound(SoundType.METAL).noOcclusion().harvestTool(ToolType.PICKAXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_0 = registerDoorBlock("medieval_door_0", () -> new DoorBlock(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.COLOR_BROWN).strength(3.0F).sound(SoundType.WOOD).noOcclusion().harvestTool(ToolType.AXE)));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_1 = registerDoorBlock("medieval_door_1", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
    public static final RegistryObject<DoorBlock> MEDIEVAL_DOOR_2 = registerDoorBlock("medieval_door_2", () -> new DoorBlock(AbstractBlock.Properties.copy(MEDIEVAL_DOOR_0.get())));
     */
    
    public static final RegistryObject<Block> KNAPPING_TABLE = registerBlock( "knapping_table", AQCreativeTabs.Keys.DECORATION, KnappingTableBlock::new );
    public static final RegistryObject<Block> FOUNDRY = registerBlock( "foundry", AQCreativeTabs.Keys.DECORATION, FoundryBlock::new );
    
    
    @SafeVarargs
    private static RegistryObject<Block> simpleBlock( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<Block> blockSupplier, TagKey<Block>... tags ) {
        RegistryObject<Block> registryObject = registerBlock( name, creativeTab, blockSupplier );
        SIMPLE_BLOCKS.add( registryObject );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    /**
     * Registers a simple block with subtypes (slab, vertical slab and stairs)
     */
    @SafeVarargs
    private static RegistryObject<Block> simpleBlockWithVars( String name, ResourceKey<CreativeModeTab> creativeTab, BlockBehaviour.Properties properties, TagKey<Block>... tags ) {
        RegistryObject<Block> registryObject = registerBlock( name, creativeTab, () -> new Block( properties ) );
        SIMPLE_BLOCKS.add( registryObject );
        
        RegistryObject<SlabBlock> slabRegObject = registerBlock( name + "_slab", creativeTab, () -> new SlabBlock( properties ) );
        SLAB_VARIANTS.put( registryObject, slabRegObject );
        
        RegistryObject<VerticalSlabBlock> vertSlabRegObject = registerBlock( name + "_vertical_slab", creativeTab, () -> new VerticalSlabBlock( properties ) );
        VERT_SLAB_VARIANTS.put( registryObject, vertSlabRegObject );
        
        RegistryObject<StairBlock> stairsRegObject = registerBlock( name + "_stairs", creativeTab, () -> new StairBlock( registryObject.get()::defaultBlockState, properties ) );
        STAIRS_VARIANTS.put( registryObject, stairsRegObject );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
            BLOCK_TAGS.put( slabRegObject, tags );
            BLOCK_TAGS.put( vertSlabRegObject, tags );
            BLOCK_TAGS.put( stairsRegObject, tags );
        }
        /*
        BlockFamRegObject famRegObject = new BlockFamRegObject(new Pair<BlockFamily.Variant, RegistryObject<Block>>[]{
                Pair.of(null, registryObject),
                Pair.of(BlockFamily.Variant.SLAB, slabRegObject)
            }
        );

         */
        
        return registryObject;
    }
    
    /**
     * Registers a simple block with vertical slab subtype.
     */
    private static RegistryObject<Block> simpleBlockWithVertSlab( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<Block> blockSupplier ) {
        RegistryObject<Block> registryObject = registerBlock( name, creativeTab, blockSupplier );
        SIMPLE_BLOCKS.add( registryObject );
        
        RegistryObject<VerticalSlabBlock> vertSlabRegObject = registerBlock( name + "_vertical_slab", creativeTab, () -> new VerticalSlabBlock( BlockBehaviour.Properties.copy( registryObject.get() ) ) );
        VERT_SLAB_VARIANTS.put( registryObject, vertSlabRegObject );
        
        return registryObject;
    }
    
    @SafeVarargs
    private static <T extends Block> RegistryObject<T> registerBlock( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<T> blockSupplier, TagKey<Block>... tags ) {
        RegistryObject<T> registryObject = REGISTRY.register( name, blockSupplier );
        AQItems.registerItem( name, creativeTab, () -> new BlockItem( registryObject.get(), new Item.Properties() ) );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    @SafeVarargs
    private static <T extends Block> RegistryObject<T> registerBlockNoItem( String name, Supplier<T> blockSupplier, TagKey<Block>... tags ) {
        RegistryObject<T> registryObject = REGISTRY.register( name, blockSupplier );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    @SafeVarargs
    private static Pair<RegistryObject<SimpleSkullBlock>, RegistryObject<SimpleWallSkullBlock>>
    registerSkull( String name, boolean animal, Supplier<BlockBehaviour.Properties> properties, TagKey<Block>... tags ) {
        
        RegistryObject<SimpleSkullBlock> skull = REGISTRY.register( name + "_skull", () -> new SimpleSkullBlock( properties.get(), animal, name ) );
        RegistryObject<SimpleWallSkullBlock> wallSkull = REGISTRY.register( name + "_wall_skull", () -> new SimpleWallSkullBlock( properties.get().dropsLike( skull.get() ), animal, name ) );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( skull, tags );
            BLOCK_TAGS.put( wallSkull, tags );
        }
        RegistryObject<Item> skullItem = AQItems.registerItem( skull.getId().getPath(), AQCreativeTabs.Keys.DECORATION, () -> new StandingAndWallBlockItem( skull.get(), wallSkull.get(), new Item.Properties(), Direction.DOWN ) );
        AQItems.SIMPLE_ITEMS.add( skullItem );
        
        return Pair.of( skull, wallSkull );
    }
    
    @SafeVarargs
    private static <T extends DoorBlock> RegistryObject<T> registerDoorBlock( String name, Supplier<T> doorBlockSupplier, TagKey<Block>... tags ) {
        RegistryObject<T> registryObject = REGISTRY.register( name, doorBlockSupplier );
        AQItems.registerItem( name, AQCreativeTabs.Keys.BLOCKS, () -> new DoubleHighBlockItem( registryObject.get(), new Item.Properties().stacksTo( 16 ) ) );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    @SafeVarargs
    private static <T extends Block, B extends BlockItem> RegistryObject<T> registerBlock( String name, Supplier<T> blockSupplier, Supplier<B> blockItemSupplier,
                                                                                           ResourceKey<CreativeModeTab> creativeTab, TagKey<Block>... tags ) {
        RegistryObject<T> registryObject = REGISTRY.register( name, blockSupplier );
        AQItems.registerItem( name, creativeTab, blockItemSupplier );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    @SafeVarargs
    private static RegistryObject<AztecDungeonDoorBlock> registerDungeonDoor( String name, DungeonDoorType doorType, TagKey<Block>... tags ) {
        RegistryObject<AztecDungeonDoorBlock> registryObject = REGISTRY.register( name, () -> new AztecDungeonDoorBlock( doorType ) );
        AQItems.registerItem( name, AQCreativeTabs.Keys.DECORATION, () -> new AztecDungeonDoorBlockItem( registryObject.get() ) );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }
    
    @SafeVarargs
    private static RegistryObject<AztecThroneBlock> registerThrone( String name, ThroneType throneType, TagKey<Block>... tags ) {
        RegistryObject<AztecThroneBlock> registryObject = REGISTRY.register( name, () -> new AztecThroneBlock( throneType ) );
        AQItems.registerItem( name, AQCreativeTabs.Keys.DECORATION, () -> new AztecThroneBlockItem( registryObject.get(), throneType ) );
        
        if( tags != null && tags.length > 0 ) {
            BLOCK_TAGS.put( registryObject, tags );
        }
        return registryObject;
    }

    /*
    private static boolean always(BlockState state, IBlockReader world, BlockPos pos) {
        return true;
    }

     */
}
