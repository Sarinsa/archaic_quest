package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.SimpleAbstractSkullBlock;
import com.obsidian_core.archaic_quest.common.block.SimpleSkullBlock;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class SimpleSkullBlockEntity extends BlockEntity {

    public SimpleSkullBlockEntity(BlockPos pos, BlockState state) {
        super(AQBlockEntities.SIMPLE_SKULL.get(), pos, state);
    }

    public SimpleAbstractSkullBlock getSkull() {
        return (SimpleAbstractSkullBlock) getBlockState().getBlock();
    }
}
