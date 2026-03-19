package com.obsidian_core.archaic_quest.common.core.register.util;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

/**
 * An implementation of {@link net.minecraft.world.level.block.grower.AbstractTreeGrower}
 * that does not generate anything. Useful placeholder when working with new wood sets
 * that don't have a tree feature yet.
 */
public class EmptyTreeGrower extends AbstractTreeGrower {
    
    public static EmptyTreeGrower INSTANCE = new EmptyTreeGrower();
    
    
    @Override
    @Nullable
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature( RandomSource random, boolean nearbyFlowers ) {
        return null;
    }
    
    @Override
    public boolean growTree( ServerLevel serverLevel, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random ) {
        return false;
    }
}
