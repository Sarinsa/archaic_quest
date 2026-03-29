package com.obsidian_core.archaic_quest.common.block.base;


import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.DropExperienceBlock;

public class BaseOreBlock extends DropExperienceBlock {
    
    public BaseOreBlock( Properties properties ) {
        super( properties );
    }
    
    public BaseOreBlock( Properties properties, int minXp, int maxXp ) {
        super( properties, UniformInt.of( minXp, maxXp ) );
    }
}
