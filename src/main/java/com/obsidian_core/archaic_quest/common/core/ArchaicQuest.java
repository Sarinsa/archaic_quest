package com.obsidian_core.archaic_quest.common.core;

import com.obsidian_core.archaic_quest.common.compat.terrablender.AQTerraBlender;
import com.obsidian_core.archaic_quest.common.core.register.*;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import com.obsidian_core.archaic_quest.common.item.AdventurersTorchItem;
import com.obsidian_core.archaic_quest.common.network.PacketHandler;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod( ArchaicQuest.MODID )
public class ArchaicQuest {
    
    /** The mod ID. */
    public static final String MODID = "archaic_quest";
    /** A logger instance with our namespace. */
    public static final Logger LOG = LogManager.getLogger( MODID );
    
    @SuppressWarnings( "FieldCanBeLocal" )
    private final PacketHandler packetHandler = new PacketHandler();
    
    public ArchaicQuest( FMLJavaModLoadingContext context ) {
        packetHandler.registerMessages();
        
        AQCreativeTabs.init();
        
        IEventBus eventBus = context.getModEventBus();
        
        eventBus.addListener( this::onCommonSetup );
        eventBus.addListener( AQEntities::registerAttributes );
        
        AQBlocks.REGISTRY.register( eventBus );
        AQItems.REGISTRY.register( eventBus );
        AQCreativeTabs.REGISTRY.register( eventBus );
        AQEntities.REGISTRY.register( eventBus );
        AQParticles.REGISTRY.register( eventBus );
        AQBiomeModifiers.REGISTRY.register( eventBus );
        AQGlobalLootModifiers.REGISTRY.register( eventBus );
        AQSoundEvents.REGISTRY.register( eventBus );
        AQContainers.REGISTRY.register( eventBus );
        AQBlockEntities.REGISTRY.register( eventBus );
        AQTreeDecoratorType.REGISTRY.register( eventBus );
        AQStructureTypes.REGISTRY.register( eventBus );
    }
    
    private void onCommonSetup( FMLCommonSetupEvent event ) {
        event.enqueueWork( () -> {
            AdventurersTorchItem.registerDefaults();
            
            // Terrablender compat setup
            if( ModList.get().isLoaded( "terrablender" ) ) {
                AQTerraBlender.setup();
            }
        } );
        
        // TODO - This can probably be moved to the wood set class
        for( WoodSet woodSet : WoodSet.WOOD_SETS ) {
            ComposterBlock.COMPOSTABLES.put( woodSet.getLeaves().get(), 0.3F );
            ComposterBlock.COMPOSTABLES.put( woodSet.getSapling().get(), 0.3F );
        }
    }
    
    public static ResourceLocation rl( String path ) {
        return ResourceLocation.fromNamespaceAndPath( MODID, path );
    }
}
