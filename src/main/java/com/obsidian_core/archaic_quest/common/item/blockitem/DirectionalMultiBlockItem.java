package com.obsidian_core.archaic_quest.common.item.blockitem;

import com.obsidian_core.archaic_quest.common.util.PlaceableConstruct;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Can be used for both normal multiblock constructs and ones that are not symmetrical
 * and should have their parts rotated depending on placement direction.
 */
public class DirectionalMultiBlockItem extends BlockItem {
    
    private final PlaceableConstruct placeableConstruct;
    
    
    public DirectionalMultiBlockItem( Block block, Properties properties, PlaceableConstruct placeableConstruct ) {
        super( block, properties );
        this.placeableConstruct = placeableConstruct;
    }
    
    
    private boolean placeMultiblock( BlockPlaceContext context ) {
        try {
            placeableConstruct.place( context.getLevel(), context.getClickedPos(), context.getHorizontalDirection() );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    
    @Override
    protected boolean placeBlock( BlockPlaceContext context, BlockState state ) {
        return placeMultiblock( context );
    }
    
    @Override
    protected boolean canPlace( BlockPlaceContext context, BlockState state ) {
        return canPlaceMultiBlock( context );
    }
    
    public PlaceableConstruct getPlaceableConstruct() {
        return placeableConstruct;
    }
    
    public boolean canPlaceMultiBlock( BlockPlaceContext context ) {
        Level level = context.getLevel();
        BlockPos origin = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        
        return placeableConstruct.isSpaceUnoccupied( level, origin, direction );
    }
}
