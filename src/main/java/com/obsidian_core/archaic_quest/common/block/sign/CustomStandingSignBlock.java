package com.obsidian_core.archaic_quest.common.block.sign;

import com.obsidian_core.archaic_quest.common.blockentity.CustomSignBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;

import javax.annotation.Nullable;

public class CustomStandingSignBlock extends StandingSignBlock {
    
    public CustomStandingSignBlock( Properties properties, WoodType woodType ) {
        super( properties, woodType );
    }
    
    @Override
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        return new CustomSignBlockEntity( pos, state );
    }
    
    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker( Level level, BlockState state, BlockEntityType<T> type ) {
        return createTickerHelper( type, AQBlockEntities.CUSTOM_SIGN.get(), SignBlockEntity::tick );
    }
}
