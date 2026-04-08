package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.client.render.blockentity.bewlr.BEWLRS;
import com.obsidian_core.archaic_quest.common.block.base.ThroneType;
import com.obsidian_core.archaic_quest.common.block.multiblock.AztecThroneBlock;
import com.obsidian_core.archaic_quest.common.util.PlaceableConstruct;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AztecThroneBlockItem extends DirectionalMultiBlockItem {
    
    private final ThroneType throneType;
    
    public AztecThroneBlockItem( Block block, ThroneType type ) {
        super( block, new Item.Properties(),
                PlaceableConstruct.builder()
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.MASTER, true ), Vec3i.ZERO )
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.PART, AztecThroneBlock.Part.BACK ), 0, 0, -1 )
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.PART, AztecThroneBlock.Part.BACK ), 0, 1, -1 )
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.PART, AztecThroneBlock.Part.UPPER_BACK ), 0, 2, -1 )
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.PART, AztecThroneBlock.Part.ARM ), -1, 0, 0 )
                        .add( block.defaultBlockState().setValue( AztecThroneBlock.PART, AztecThroneBlock.Part.ARM ).setValue( AztecThroneBlock.FACING, Direction.SOUTH ), 1, 0, 0 )
                        .multiblockEntity()
                        .build()
        );
        throneType = type;
    }
    
    public ThroneType getThroneType() {
        return throneType;
    }
    
    @Override
    public void initializeClient( Consumer<IClientItemExtensions> consumer ) {
        consumer.accept( new IClientItemExtensions() {
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                return BEWLRS.AZTEC_THRONE.getInstance();
            }
        } );
    }
}
