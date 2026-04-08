package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.common.block.multiblock.AztecDungeonDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import static com.obsidian_core.archaic_quest.common.block.multiblock.AztecDungeonDoorBlock.BlockType;

public class AztecDungeonDoorBlockItem extends BlockItem {
    
    private static final EnumProperty<BlockType> BLOCK_TYPE = AztecDungeonDoorBlock.BLOCK_TYPE;
    
    public AztecDungeonDoorBlockItem( Block block ) {
        super( block, new Item.Properties() );
    }
    
    @Override
    protected boolean placeBlock( BlockPlaceContext context, BlockState blockState ) {
        Direction direction = context.getHorizontalDirection().getOpposite();
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        
        switch( direction ) {
            case SOUTH, NORTH -> {
                level.setBlock( pos.west(), blockState.setValue( BLOCK_TYPE, BlockType.LOWER_LEFT ), Block.UPDATE_ALL );
                level.setBlock( pos.east(), blockState.setValue( BLOCK_TYPE, BlockType.LOWER_RIGHT ), Block.UPDATE_ALL );
                level.setBlock( pos.above(), blockState.setValue( BLOCK_TYPE, BlockType.MIDDLE ), Block.UPDATE_ALL );
                level.setBlock( pos.above().west(), blockState.setValue( BLOCK_TYPE, BlockType.LEFT ), Block.UPDATE_ALL );
                level.setBlock( pos.above().east(), blockState.setValue( BLOCK_TYPE, BlockType.RIGHT ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ), blockState.setValue( BLOCK_TYPE, BlockType.TOP ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ).west(), blockState.setValue( BLOCK_TYPE, BlockType.LEFT_TOP ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ).east(), blockState.setValue( BLOCK_TYPE, BlockType.RIGHT_TOP ), Block.UPDATE_ALL );
            }
            case EAST, WEST -> {
                level.setBlock( pos.south(), blockState.setValue( BLOCK_TYPE, BlockType.LOWER_LEFT ), Block.UPDATE_ALL );
                level.setBlock( pos.north(), blockState.setValue( BLOCK_TYPE, BlockType.LOWER_RIGHT ), Block.UPDATE_ALL );
                level.setBlock( pos.above(), blockState.setValue( BLOCK_TYPE, BlockType.MIDDLE ), Block.UPDATE_ALL );
                level.setBlock( pos.above().south(), blockState.setValue( BLOCK_TYPE, BlockType.LEFT ), Block.UPDATE_ALL );
                level.setBlock( pos.above().north(), blockState.setValue( BLOCK_TYPE, BlockType.RIGHT ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ), blockState.setValue( BLOCK_TYPE, BlockType.TOP ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ).south(), blockState.setValue( BLOCK_TYPE, BlockType.LEFT_TOP ), Block.UPDATE_ALL );
                level.setBlock( pos.above( 2 ).north(), blockState.setValue( BLOCK_TYPE, BlockType.RIGHT_TOP ), Block.UPDATE_ALL );
            }
        }
        return super.placeBlock( context, blockState );
    }
}
