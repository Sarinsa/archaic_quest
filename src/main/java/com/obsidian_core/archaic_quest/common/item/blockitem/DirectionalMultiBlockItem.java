package com.obsidian_core.archaic_quest.common.item.blockitem;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

/**
 * Used for multiblock constructs that are not symmetrical
 * and should have their pattern rotated depending on placement direction.
 */
// TODO
public class DirectionalMultiBlockItem extends BlockItem {
    
    public DirectionalMultiBlockItem( Block block, Properties properties ) {
        super( block, properties );
    }
}
