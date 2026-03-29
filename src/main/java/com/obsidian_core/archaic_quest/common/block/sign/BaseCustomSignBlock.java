package com.obsidian_core.archaic_quest.common.block.sign;

import com.obsidian_core.archaic_quest.common.blockentity.CustomSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

public abstract class BaseCustomSignBlock extends SignBlock {
    
    protected BaseCustomSignBlock( Properties properties, WoodType woodType ) {
        super( properties, woodType );
    }
    
    @Override
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        return new CustomSignBlockEntity( pos, state );
    }
}
