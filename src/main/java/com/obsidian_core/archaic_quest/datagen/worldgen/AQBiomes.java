package com.obsidian_core.archaic_quest.datagen.worldgen;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class AQBiomes {
    
    public static final ResourceKey<Biome> AZTEC_JUNGLE = key( "aztec_jungle" );
    
    
    public static void bootstrap( BootstapContext<Biome> context ) {
        HolderGetter<PlacedFeature> placedFeatureLookup = context.lookup( Registries.PLACED_FEATURE );
        HolderGetter<ConfiguredWorldCarver<?>> carverLookup = context.lookup( Registries.CONFIGURED_CARVER );
        
        register( context, AZTEC_JUNGLE, createAztecJungleBiome( placedFeatureLookup, carverLookup ) );
    }
    
    
    private static Biome createAztecJungleBiome( HolderGetter<PlacedFeature> placedFeatureLookup, HolderGetter<ConfiguredWorldCarver<?>> carverLookup ) {
        BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder( placedFeatureLookup, carverLookup );
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        
        BiomeDefaultFeatures.baseJungleSpawns( spawnSettings );
        
        globalOverworldGeneration( generationSettings );
        BiomeDefaultFeatures.addDefaultOres( generationSettings );
        BiomeDefaultFeatures.addDefaultSoftDisks( generationSettings );
        BiomeDefaultFeatures.addSparseJungleTrees( generationSettings );
        //generationSettings.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AQConfiguredFeatures.PLACED_AZTEC_JUNGLE_TREES);
        BiomeDefaultFeatures.addWarmFlowers( generationSettings );
        BiomeDefaultFeatures.addJungleGrass( generationSettings );
        BiomeDefaultFeatures.addDefaultMushrooms( generationSettings );
        BiomeDefaultFeatures.addDefaultExtraVegetation( generationSettings );
        BiomeDefaultFeatures.addSparseJungleMelons( generationSettings );
        
        
        return new Biome.BiomeBuilder()
                .generationSettings( generationSettings.build() )
                .mobSpawnSettings( spawnSettings.build() )
                .hasPrecipitation( true )
                .temperature( 0.95F )
                .downfall( 0.9F )
                .specialEffects( new BiomeSpecialEffects.Builder()
                        .waterColor( 4159204 )
                        .waterFogColor( 329011 )
                        .fogColor( 12638463 )
                        .skyColor( calculateSkyColor( 0.95F ) )
                        .ambientMoodSound( AmbientMoodSettings.LEGACY_CAVE_SETTINGS )
                        .backgroundMusic( Musics.createGameMusic( SoundEvents.MUSIC_BIOME_JUNGLE ) )
                        .build() )
                .build();
    }
    
    /** Adds default features found everywhere in the overworld to the given builder. */
    private static void globalOverworldGeneration( BiomeGenerationSettings.Builder builder ) {
        BiomeDefaultFeatures.addDefaultCarversAndLakes( builder );
        BiomeDefaultFeatures.addDefaultCrystalFormations( builder );
        BiomeDefaultFeatures.addDefaultMonsterRoom( builder );
        BiomeDefaultFeatures.addDefaultUndergroundVariety( builder );
        BiomeDefaultFeatures.addDefaultSprings( builder );
        BiomeDefaultFeatures.addSurfaceFreezing( builder );
    }
    
    /** This is some voodoo-shit from vanilla, no idea how this works. */
    protected static int calculateSkyColor( float f ) {
        float val = f / 3.0F;
        val = Mth.clamp( val, -1.0F, 1.0F );
        return Mth.hsvToRgb( 0.62222224F - val * 0.05F, 0.5F + val * 0.1F, 1.0F );
    }
    
    private static void register( BootstapContext<Biome> context, ResourceKey<Biome> key, Biome biome ) {
        context.register( key, biome );
    }
    
    /** @return A biome key with the given name. */
    private static ResourceKey<Biome> key( String name ) {
        return ResourceKey.create( Registries.BIOME, ArchaicQuest.rl( name ) );
    }
}
