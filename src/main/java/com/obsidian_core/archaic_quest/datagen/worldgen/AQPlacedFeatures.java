package com.obsidian_core.archaic_quest.datagen.worldgen;

import com.google.common.collect.ImmutableList;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class AQPlacedFeatures {
    
    //
    // ----------------------- FEATURE KEYS -----------------------
    //
    public static final ResourceKey<PlacedFeature> AZTEC_JUNGLE_TREE = key( "aztec_jungle_tree" );
    
    public static final ResourceKey<PlacedFeature> TIN_ORE = key( "tin_ore" );
    public static final ResourceKey<PlacedFeature> SILVER_ORE = key( "silver_ore" );
    public static final ResourceKey<PlacedFeature> GRANITE_QUARTZ_ORE = key( "granite_quartz_ore" );
    
    
    public static void bootstrap( BootstapContext<PlacedFeature> context ) {
        // Trees
        register( context, AZTEC_JUNGLE_TREE, AQConfiguredFeatures.AZTEC_JUNGLE_TREE,
                PlacementUtils.filteredByBlockSurvival( AQBlocks.AZTEC_JUNGLE_SAPLING.get() )
        );
        
        // Ores
        register( context, TIN_ORE, AQConfiguredFeatures.TIN_ORE,
                commonOrePlacement( 16, HeightRangePlacement.triangle( VerticalAnchor.absolute( -16 ), VerticalAnchor.absolute( 70 ) ) )
        );
        register( context, SILVER_ORE, AQConfiguredFeatures.SILVER_ORE,
                commonOrePlacement( 16, HeightRangePlacement.triangle( VerticalAnchor.absolute( -16 ), VerticalAnchor.absolute( 70 ) ) )
        );
        register( context, GRANITE_QUARTZ_ORE, AQConfiguredFeatures.GRANITE_QUARTZ_ORE,
                commonOrePlacement( 16, HeightRangePlacement.triangle( VerticalAnchor.absolute( -16 ), VerticalAnchor.absolute( 70 ) ) )
        );
    }
    
    
    //
    // ---------------------------- ORE ----------------------------
    //
    private static List<PlacementModifier> orePlacement( PlacementModifier countMod, PlacementModifier modifier2 ) {
        return List.of( countMod, InSquarePlacement.spread(), modifier2, BiomeFilter.biome() );
    }
    
    private static List<PlacementModifier> commonOrePlacement( int count, PlacementModifier modifier ) {
        return orePlacement( CountPlacement.of( count ), modifier );
    }
    
    private static List<PlacementModifier> rareOrePlacement( int count, PlacementModifier modifier ) {
        return orePlacement( RarityFilter.onAverageOnceEvery( count ), modifier );
    }
    
    
    //
    // ---------------------------- TREE ----------------------------
    //
    private static ImmutableList.Builder<PlacementModifier> treePlacementBase( PlacementModifier modifier ) {
        return ImmutableList.<PlacementModifier>builder()
                .add( modifier )
                .add( InSquarePlacement.spread() )
                .add( SurfaceWaterDepthFilter.forMaxDepth( 0 ) )
                .add( PlacementUtils.HEIGHTMAP_OCEAN_FLOOR )
                .add( BiomeFilter.biome() );
    }
    
    public static List<PlacementModifier> treePlacement( PlacementModifier modifier ) {
        return treePlacementBase( modifier )
                .build();
    }
    
    public static List<PlacementModifier> treePlacement( PlacementModifier modifier, Block block ) {
        return treePlacementBase( modifier )
                .add( BlockPredicateFilter.forPredicate( BlockPredicate.wouldSurvive( block.defaultBlockState(), BlockPos.ZERO ) ) )
                .build();
    }
    
    
    //
    // ---------------------------- CONVENIENCE ----------------------------
    //
    protected static void register( BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedKey,
                                    ResourceKey<ConfiguredFeature<?, ?>> configuredKey, PlacementModifier... modifiers ) {
        register( context, placedKey, configuredKey, List.of( modifiers ) );
    }
    
    protected static void register( BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedKey,
                                    ResourceKey<ConfiguredFeature<?, ?>> configuredKey, List<PlacementModifier> modifiers ) {
        final HolderGetter<ConfiguredFeature<?, ?>> featureLookup = context.lookup( Registries.CONFIGURED_FEATURE );
        context.register( placedKey, new PlacedFeature( featureLookup.getOrThrow( configuredKey ), modifiers ) );
    }
    
    /** @return A placed feature key with the given name. */
    public static ResourceKey<PlacedFeature> key( String name ) {
        return ResourceKey.create( Registries.PLACED_FEATURE, ArchaicQuest.rl( name ) );
    }
}
