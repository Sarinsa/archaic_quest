package com.obsidian_core.archaic_quest.common.item.misc;

import net.minecraft.world.item.context.UseOnContext;

/**
 * Can be implemented by blocks that should do something or transform
 * when right-clicked with the {@link com.obsidian_core.archaic_quest.common.core.register.AQItems#MACHETE} item.
 */
public interface IMacheteCuttable {
    
    /**
     * Called from {@link com.obsidian_core.archaic_quest.common.item.MacheteItem#useOn(UseOnContext)}.
     * <br><br>
     * The return value of this method is used to determine the returned {@link net.minecraft.world.InteractionResult}
     * in the machete item's <strong>useOn()</strong> method. If true, <strong>InteractionResult.sidedSuccess(level.isClientSide)</strong>
     * is returned. Otherwise, {@link net.minecraft.world.InteractionResult#FAIL} is returned.
     *
     * @return True if "this" was successfully cut by the machete. Returns false otherwise.
     */
    boolean onCut( UseOnContext useContext );
}
