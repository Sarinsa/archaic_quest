package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.common.block.misc.AQStateProperties;
import com.obsidian_core.archaic_quest.common.block.multiblock.AztecWorktableBlock;
import com.obsidian_core.archaic_quest.common.util.PlaceableConstruct;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Vec3i;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AztecWorktableBlockItem extends DirectionalMultiBlockItem {
    
    public AztecWorktableBlockItem( Block block, Properties properties ) {
        super( block, properties, PlaceableConstruct.builder()
                .add( block.defaultBlockState().setValue( AQStateProperties.MASTER, true ), Vec3i.ZERO )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.SIDE ), -1, 0, 0 )
                .addOpposite( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.SIDE ), 1, 0, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.FRONT ), 0, 0, 1 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.LEFT_CORNER ), -1, 0, 1 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.RIGHT_CORNER ), 1, 0, 1 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), 0, 1, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), 1, 1, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), -1, 1, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), 0, 2, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), 1, 2, 0 )
                .add( block.defaultBlockState().setValue( AztecWorktableBlock.PART, AztecWorktableBlock.Part.PANEL ), -1, 2, 0 )
                .multiblockEntity()
                .build()
        );
    }
    
    @Override
    public void initializeClient( Consumer<IClientItemExtensions> consumer ) {
        consumer.accept( new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return BEWLRS.AZTEC_CRAFTING_STATION.getInstance();
            }
        } );
    }
}
