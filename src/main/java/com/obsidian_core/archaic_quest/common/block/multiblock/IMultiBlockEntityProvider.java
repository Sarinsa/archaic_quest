package com.obsidian_core.archaic_quest.common.block.multiblock;

import com.obsidian_core.archaic_quest.common.blockentity.multiblock.IMultiBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public interface IMultiBlockEntityProvider {
    
    @Nullable
    IMultiBlockEntity<?> newMultiBlockEntity( BlockPos pos, BlockState state, boolean isMaster );
}
