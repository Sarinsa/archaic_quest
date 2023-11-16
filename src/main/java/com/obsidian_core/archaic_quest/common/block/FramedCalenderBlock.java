package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class FramedCalenderBlock extends Block {

    public FramedCalenderBlock() {
        super(
                Properties.of(Material.WOOD)
                .strength(1.0F, 0.5F)
                .sound(SoundType.WOOD)
                .noOcclusion()
        );
    }

}
