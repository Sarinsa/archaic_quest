package com.obsidian_core.archaic_quest.common.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class SimpleWeaponItem extends SwordItem {
    
    public SimpleWeaponItem( Tier itemTier, int durability, int damage, float attackSpeed ) {
        super( itemTier, damage, attackSpeed, new Item.Properties()
                .stacksTo( 1 )
                .durability( durability )
        );
    }
}
