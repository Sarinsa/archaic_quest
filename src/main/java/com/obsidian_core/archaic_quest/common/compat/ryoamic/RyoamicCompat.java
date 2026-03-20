package com.obsidian_core.archaic_quest.common.compat.ryoamic;

import com.obsidian_core.archaic_quest.api.TorchLitType;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.item.AdventurersTorchItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.thinkingstudio.ryoamiclights.RyoamicLights;
import org.thinkingstudio.ryoamiclights.api.item.ItemLightSource;
import org.thinkingstudio.ryoamiclights.api.item.ItemLightSources;

public class RyoamicCompat {
    
    private static final String RYOAMIC = "ryoamiclights";
    
    
    /** Called from {@link com.obsidian_core.archaic_quest.client.ClientRegister#onClientSetup(FMLClientSetupEvent)}. */
    public static void init() {
        if( FMLEnvironment.dist != Dist.CLIENT ) return;
        
        if( ModList.get().isLoaded( RYOAMIC ) ) {
            registerItemLightSources();
            registerDynamicHandlers();
        }
    }
    
    /** Here we register item light sources. */
    private static void registerItemLightSources() {
        // Adventurer's Torch
        ItemLightSources.registerItemLightSource( new ItemLightSource( AQItems.ADVENTURERS_TORCH.getId(), AQItems.ADVENTURERS_TORCH.get(), true ) {
            @Override
            public int getLuminance( ItemStack itemStack ) {
                return AdventurersTorchItem.getLitFlag( itemStack ) == TorchLitType.UNLIT ? 0 : 13;
            }
        } );
    }
    
    /** Here we register our dynamic light handlers. */
    private static void registerDynamicHandlers() {
    
    }
    
    /**
     * @return Whichever value is greater if RyoamicLights is installed; block light or dynamic light.
     * If the mod is not installed, this just returns the block light at the given position.
     * <br><br>
     * If RyoamicLights is installed, but we fail to look up the dynamic light
     * for whatever reason, we print a warning and return the block light.
     */
    public static int getBlockOrDynamicLightAt( Level level, BlockPos pos ) {
        int blockLight = level.getBrightness( LightLayer.BLOCK, pos );
        
        if( ModList.get().isLoaded( RYOAMIC ) ) {
            try {
                RyoamicLights instance = RyoamicLights.get();
                int dynamicLight = (int) instance.getDynamicLightLevel( pos );
                return Math.max( dynamicLight, blockLight );
            }
            catch( Exception e ) {
                warn( "Failed to look up dynamic light at position: {}", pos.toString() );
                return blockLight;
            }
        }
        return blockLight;
    }
    
    /** Convenience method for logging warnings. */
    @SuppressWarnings( { "StringConcatenationArgumentToLogCall", "SameParameterValue" } )
    private static void warn( String message, Object... args ) {
        ArchaicQuest.LOG.warn( "[RyoamicLights compat] " + message, args );
    }
}
