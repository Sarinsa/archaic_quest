package com.obsidian_core.archaic_quest.common.compat.jei;

import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

/** Utility class for creating translatable item description components. */
public class ItemDescs {
    
    protected static final Map<Supplier<ItemStack>, Function<ItemStack, Component>> ITEM_INGREDIENT_INFO = new HashMap<>();
    
    
    static {
        add( AQItems.MACHETE );
    }
    
    
    private static void add( Supplier<Item> itemSupplier ) {
        add( () -> new ItemStack( itemSupplier.get() ), ItemDescs::regular );
    }
    
    private static void add( Supplier<ItemStack> itemSupplier, Function<ItemStack, Component> descriptionMaker ) {
        ITEM_INGREDIENT_INFO.put( itemSupplier, descriptionMaker );
    }
    
    /**
     * @return A translatable component with the given ItemStack's item's description ID as the key,
     * with ".jei_desc" appended to it.
     */
    private static Component regular( ItemStack itemStack ) {
        return Component.translatable( itemStack.getItem().getDescriptionId() + ".jei_desc" );
    }
}
