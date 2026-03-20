package com.obsidian_core.archaic_quest.common.compat.jei;

import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import mezz.jei.api.registration.IRecipeRegistration;
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
    
    
    /** Called from {@link AQJeiPlugin#registerRecipes(IRecipeRegistration)}. */
    protected static void load() {
        add( AQItems.MACHETE );
    }
    
    /** Adds a description component for the given item with a default translation key. */
    private static void add( Supplier<Item> itemSupplier ) {
        add( () -> new ItemStack( itemSupplier.get() ), ItemDescs::normal );
    }
    
    /** Adds a description component for the given item stack. */
    private static void add( Supplier<ItemStack> itemSupplier, Function<ItemStack, Component> descriptionMaker ) {
        ITEM_INGREDIENT_INFO.put( itemSupplier, descriptionMaker );
    }
    
    /**
     * @return A translatable component with the given ItemStack's item's description ID as the key,
     * with ".jei_desc" appended to it.
     */
    private static Component normal( ItemStack itemStack ) {
        return Component.translatable( itemStack.getItem().getDescriptionId() + ".jei_desc" );
    }
}
