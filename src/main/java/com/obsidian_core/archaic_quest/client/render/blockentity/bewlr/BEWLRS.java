package com.obsidian_core.archaic_quest.client.render.blockentity.bewlr;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraftforge.client.event.EntityRenderersEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Utility class that holds Archaic Quest's BEWLR instances,
 * in the form of holders
 */
public class BEWLRS {
    
    private static final List<Holder> BEWLR_LIST = new ArrayList<>();
    
    public static final Holder AZTEC_CRAFTING_STATION = new Holder( AztecCraftingStationBEWLR::new );
    public static final Holder AZTEC_THRONE = new Holder( AztecThroneBEWLR::new );
    public static final Holder AZTEC_DUNGEON_CHEST = new Holder( AztecDungeonChestBEWLR::new );
    
    
    /** @return An iterator for the holder list. */
    public static Iterator<Holder> holders() {
        return BEWLR_LIST.iterator();
    }
    
    /**
     * Contains a BEWLR factory that creates a renderer instance
     * when {@link EntityRenderersEvent.RegisterRenderers} is fired on the client.
     * The instance is then cached in this holder for later access.
     */
    public static class Holder {
        
        /** This holder's BEWLR factory. */
        private final BiFunction<BlockEntityRenderDispatcher, EntityModelSet, BlockEntityWithoutLevelRenderer> factory;
        /** The BEWLR instance created from this holder's factory, when it has been created. */
        private BlockEntityWithoutLevelRenderer instance;
        
        public Holder( BiFunction<BlockEntityRenderDispatcher, EntityModelSet, BlockEntityWithoutLevelRenderer> factory ) {
            this.factory = factory;
            BEWLR_LIST.add( this );
        }
        
        /**
         * Called from {@link com.obsidian_core.archaic_quest.client.ClientRegister#registerRenderers(EntityRenderersEvent.RegisterRenderers)}.
         */
        public void populate( BlockEntityRenderDispatcher renderDispatcher ) {
            instance = factory.apply( renderDispatcher, Minecraft.getInstance().getEntityModels() );
        }
        
        /**
         * @return This holder's BEWLR instance.
         * @throws IllegalStateException If {@link Holder#instance} is null.
         */
        public BlockEntityWithoutLevelRenderer getInstance() {
            if( instance == null ) {
                throw new IllegalStateException( "Attempted to access a BlockEntityWithoutLevelRenderer instance that had not been created." );
            }
            return instance;
        }
    }
}
