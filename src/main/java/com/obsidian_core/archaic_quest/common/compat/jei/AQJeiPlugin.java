package com.obsidian_core.archaic_quest.common.compat.jei;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings( "unused" )
@JeiPlugin
public class AQJeiPlugin implements IModPlugin {
    
    private static final ResourceLocation ID = ArchaicQuest.rl( "jei_plugin" );
    
    
    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }
    
    
    @Override
    public void registerRecipes( IRecipeRegistration registration ) {
        ItemDescs.load();
        
        ItemDescs.ITEM_INGREDIENT_INFO.forEach( ( supplier, function ) -> {
            ItemStack itemStack = supplier.get();
            registration.addIngredientInfo( itemStack, VanillaTypes.ITEM_STACK, function.apply( supplier.get() ) );
        } );
    }
}
