package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;

public class FramedCalenderBlock extends Block {
    
    public FramedCalenderBlock() {
        super( Properties.copy( Blocks.OAK_PLANKS )
                .strength( 1.0F, 0.5F )
                .sound( SoundType.WOOD )
                .noOcclusion()
        );
    }
}
