package com.obsidian_core.archaic_quest.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class FoundryBlock extends Block implements EntityBlock {
    
    public FoundryBlock() {
        super( BlockBehaviour.Properties.copy( Blocks.STONE )
                .requiresCorrectToolForDrops()
                .randomTicks()
                .strength( 2.0F ) );
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity( BlockPos pos, BlockState state ) {
        return null;
    }
    
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker( Level level, BlockState state, BlockEntityType<T> blockEntityType ) {
        return null;
        //return (lvl, blockState, pos, blockEntity) -> FoundryBlockEntity.tick(lvl, blockState, pos, (FoundryBlockEntity) blockEntity);
    }
}
