package com.obsidian_core.archaic_quest.common.worldgen.feature.decorators;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;

public final class DecoratorUtil {
    
    public static void placeCustomVine( TreeDecorator.Context context, BlockState vine, BlockPos pos, Direction facing ) {
        context.setBlock( pos, vine.setValue( BlockStateProperties.HORIZONTAL_FACING, facing ) );
    }
    
    public static void placeCustomVine( TreeDecorator.Context context, Block vine, BlockPos pos, Direction facing ) {
        context.setBlock( pos, vine.defaultBlockState().setValue( BlockStateProperties.HORIZONTAL_FACING, facing ) );
    }
}
