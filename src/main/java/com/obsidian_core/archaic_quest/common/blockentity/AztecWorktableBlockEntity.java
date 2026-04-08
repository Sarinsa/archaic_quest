package com.obsidian_core.archaic_quest.common.blockentity;

import com.obsidian_core.archaic_quest.common.block.AztecDungeonDoorBlock;
import com.obsidian_core.archaic_quest.common.blockentity.multiblock.BaseMultiBlockEntity;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import com.obsidian_core.archaic_quest.common.util.TranslationHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

public class AztecWorktableBlockEntity extends BaseMultiBlockEntity<AztecWorktableBlockEntity> implements MenuProvider {
    
    public AztecWorktableBlockEntity( BlockPos pos, BlockState state, boolean isMaster ) {
        super( AQBlockEntities.AZTEC_WORKTABLE.get(), pos, state, isMaster );
    }
    
    public AztecWorktableBlockEntity( BlockPos pos, BlockState state ) {
        this( pos, state, false );
    }
    
    @Override
    public Component getDisplayName() {
        return TranslationHelper.AZTEC_WORKTABLE_CONTAINER;
    }
    
    // TODO
    @Nullable
    @Override
    public AbstractContainerMenu createMenu( int id, Inventory inventory, Player player ) {
        return null;
    }
    
    @OnlyIn( Dist.CLIENT )
    @Override
    public AABB getRenderBoundingBox() {
        BlockPos pos = getBlockPos();
        return getBlockState().getBlock() instanceof AztecDungeonDoorBlock
                ? new AABB( pos.offset( -2, 0, -2 ), pos.offset( 2, 2, 2 ) )
                : INFINITE_EXTENT_AABB;
    }
}
