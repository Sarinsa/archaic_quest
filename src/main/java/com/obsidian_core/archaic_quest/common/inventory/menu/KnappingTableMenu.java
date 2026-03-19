package com.obsidian_core.archaic_quest.common.inventory.menu;

import com.obsidian_core.archaic_quest.common.core.register.AQContainers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;

public class KnappingTableMenu extends AbstractContainerMenu {
    
    private final Container container;
    @Nullable
    private final BlockPos openedPos;
    
    
    public KnappingTableMenu( int id, Inventory inventory ) {
        this( id, inventory, null );
    }
    
    public KnappingTableMenu( int id, Inventory inventory, @Nullable BlockPos openedPos ) {
        super( AQContainers.KNAPPING.get(), id );
        this.openedPos = openedPos;
        container = new SimpleContainer( 11 ) {
            @Override
            public void stopOpen( Player player ) {
                for( int slot = 0; slot < container.getContainerSize(); slot++ ) {
                    if( !container.getItem( slot ).isEmpty() )
                        Block.popResource( player.level(), player.blockPosition(), container.getItem( slot ) );
                }
            }
        };
        checkContainerSize( container, 11 );
        container.startOpen( inventory.player );
        
        // Crafting slots
        int slotId = 0;
        for( int row = 0; row < 3; ++row ) {
            for( int column = 0; column < 3; ++column ) {
                addSlot( new Slot( container, slotId, 62 + column * 18, row * 18 + 17 ) );
                ++slotId;
            }
        }
        // Tool slot
        addSlot( new Slot( container, 9, 26, 53 ) );
        // Result slot
        addSlot( new ResultSlot( container, 10, 143, 35 ) );
        
        // Player inventory
        for( int row = 0; row < 3; ++row ) {
            for( int column = 0; column < 9; ++column ) {
                addSlot( new Slot( inventory, column + row * 9 + 9, 8 + column * 18, row * 18 + 84 ) );
            }
        }
        // Hotbar slots
        for( int i = 0; i < 9; ++i ) {
            addSlot( new Slot( inventory, i, 8 + i * 18, 142 ) );
        }
    }
    
    @SuppressWarnings( "ConstantConditions" )
    @Override
    public ItemStack quickMoveStack( Player player, int slotId ) {
        ItemStack returnedItem = ItemStack.EMPTY;
        Slot slot = slots.get( slotId );
        
        if( slot != null && slot.hasItem() ) {
            ItemStack itemInSlot = slot.getItem();
            returnedItem = itemInSlot.copy();
            
            if( slotId < container.getContainerSize() ) {
                if( !moveItemStackTo( itemInSlot, container.getContainerSize(), slots.size(), true ) ) {
                    return ItemStack.EMPTY;
                }
            }
            else if( !moveItemStackTo( itemInSlot, 9, 10, false ) ) {
                return ItemStack.EMPTY;
            }
            
            if( itemInSlot.isEmpty() ) {
                slot.set( ItemStack.EMPTY );
            }
            else {
                slot.setChanged();
            }
        }
        return returnedItem;
    }
    
    @Override
    public boolean stillValid( Player player ) {
        return openedPos == null || player.distanceToSqr( openedPos.getX() + 0.5D, openedPos.getY() + 0.5D, openedPos.getZ() + 0.5D ) < 64.0D;
    }
    
    private static class ResultSlot extends Slot {
        
        public ResultSlot( Container container, int id, int x, int y ) {
            super( container, id, x, y );
        }
        
        @Override
        public boolean mayPlace( ItemStack itemStack ) {
            return false;
        }
    }
}
