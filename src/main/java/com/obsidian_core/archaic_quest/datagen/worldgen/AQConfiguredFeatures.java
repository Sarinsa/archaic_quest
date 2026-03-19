package com.obsidian_core.archaic_quest.datagen.worldgen;

import com.google.common.collect.ImmutableList;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.CustomLeavesVineDecorator;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.CustomTrunkVineDecorator;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.MegaJungleFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.MegaJungleTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockStateMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class AQConfiguredFeatures {
    
    //
    // ----------------------- FEATURE KEYS -----------------------
    //
    public static final ResourceKey<ConfiguredFeature<?, ?>> AZTEC_JUNGLE_TREE = key( "aztec_jungle_tree" );
    
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_ORE = key( "tin_ore" );
    public static final ResourceKey<ConfiguredFeature<?, ?>> SILVER_ORE = key( "silver_ore" );
    public static final ResourceKey<ConfiguredFeature<?, ?>> GRANITE_QUARTZ_ORE = key( "granite_quartz_ore" );
    
    
    //
    // ----------------------- RULE TESTS -----------------------
    //
    private static final RuleTest IS_STONE = new TagMatchTest( BlockTags.BASE_STONE_OVERWORLD );
    private static final RuleTest IS_GRANITE = new BlockStateMatchTest( Blocks.GRANITE.defaultBlockState() );
    
    
    public static void bootstrap( BootstapContext<ConfiguredFeature<?, ?>> context ) {
        // Trees
        register( context, AZTEC_JUNGLE_TREE, new ConfiguredFeature<>( Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple( Blocks.JUNGLE_LOG ),
                        new MegaJungleTrunkPlacer( 10, 2, 19 ),
                        BlockStateProvider.simple( Blocks.JUNGLE_LEAVES ),
                        new MegaJungleFoliagePlacer( ConstantInt.of( 2 ), ConstantInt.of( 0 ), 2 ),
                        new TwoLayersFeatureSize( 1, 1, 2 )
                ).decorators(
                        ImmutableList.of(
                                new CustomTrunkVineDecorator( 0.3F, AQBlocks.VINES_1.get().defaultBlockState() ),
                                new CustomLeavesVineDecorator( 0.4F, UniformInt.of( 3, 6 ), AQBlocks.VINES_1.get().defaultBlockState() )
                        )
                ).build() ) );
        
        // Ores
        simpleOre( context, TIN_ORE, IS_STONE, AQBlocks.TIN_ORE.get().defaultBlockState(), 9 );
        simpleOre( context, SILVER_ORE, IS_STONE, AQBlocks.SILVER_ORE.get().defaultBlockState(), 9 );
        simpleOre( context, GRANITE_QUARTZ_ORE, IS_GRANITE, AQBlocks.GRANITE_QUARTZ_ORE.get().defaultBlockState(), 9 );
    }
    
    
    private static void simpleOre( BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, RuleTest target, BlockState ore, int size ) {
        context.register( key, new ConfiguredFeature<>( Feature.ORE, new OreConfiguration( target, ore, size ) ) );
    }
    
    protected static void register( BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, ConfiguredFeature<?, ?> configuredFeature ) {
        context.register( key, configuredFeature );
    }
    
    /** @return A configured feature key with the given name. */
    public static ResourceKey<ConfiguredFeature<?, ?>> key( String name ) {
        return ResourceKey.create( Registries.CONFIGURED_FEATURE, ArchaicQuest.rl( name ) );
    }
}
