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
    
    @Override
    protected boolean placeBlock( BlockPlaceContext context, BlockState state ) {
        return placeMultiblock( context );
    }
    
    @Override
    protected boolean canPlace( BlockPlaceContext context, BlockState state ) {
        return canPlaceMultiBlock( context );
    }
    
    /** Attempts to place this multiblock item's multiblock construct. */
    private boolean placeMultiblock( BlockPlaceContext context ) {
        try {
            placeableConstruct.place( context.getLevel(), context.getClickedPos(), context.getHorizontalDirection() );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }
    
    /**
     * @return This multiblock item's {@link PlaceableConstruct}
     * that is used to place the multiblock.
     */
    public PlaceableConstruct getConstruct() {
        return placeableConstruct;
    }
    
    /**
     * @return True if this multiblock item's construct can
     * be placed, using the provided block place context.
     */
    public boolean canPlaceMultiBlock( BlockPlaceContext context ) {
        Level level = context.getLevel();
        BlockPos origin = context.getClickedPos();
        Direction direction = context.getHorizontalDirection();
        
        return placeableConstruct.isSpaceUnoccupied( level, origin, direction );
    }
}
