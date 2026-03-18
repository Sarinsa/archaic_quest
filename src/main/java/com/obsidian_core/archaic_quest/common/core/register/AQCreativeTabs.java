package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQCreativeTabs {
    
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create( Registries.CREATIVE_MODE_TAB, ArchaicQuest.MODID );
    
    
    public static final class Keys {
        public static final ResourceKey<CreativeModeTab> BLOCKS = key( "blocks" );
        public static final ResourceKey<CreativeModeTab> ITEMS = key( "items" );
        public static final ResourceKey<CreativeModeTab> DECORATION = key( "decoration" );
        public static final ResourceKey<CreativeModeTab> TOOLS = key( "tools" );
        public static final ResourceKey<CreativeModeTab> WEAPONS = key( "weapons" );
        public static final ResourceKey<CreativeModeTab> ARMOR = key( "armor" );
        public static final ResourceKey<CreativeModeTab> FOOD = key( "food" );
        
        private static void init() { }
    }
    
    public static final RegistryObject<CreativeModeTab> BLOCKS = registerSimple( Keys.BLOCKS, iconFrom( AQBlocks.ANDESITE_AZTEC_BRICKS_0 ) );
    public static final RegistryObject<CreativeModeTab> ITEMS = registerSimple( Keys.ITEMS, iconFrom( () -> AQBlocks.CRYSTAL_SKULL.getFirst().get() ) );
    public static final RegistryObject<CreativeModeTab> DECORATION = registerSimple( Keys.DECORATION, iconFrom( AQBlocks.VINES_1 ) );
    public static final RegistryObject<CreativeModeTab> TOOLS = registerSimple( Keys.TOOLS, iconFrom( AQItems.HAMMER_AND_CHISEL ) );
    public static final RegistryObject<CreativeModeTab> WEAPONS = registerSimple( Keys.WEAPONS, iconFrom( AQItems.BONE_CLUB ) );
    public static final RegistryObject<CreativeModeTab> ARMOR = registerSimple( Keys.ARMOR, iconFrom( Items.IRON_CHESTPLATE ) );
    public static final RegistryObject<CreativeModeTab> FOOD = registerSimple( Keys.FOOD, iconFrom( AQItems.CORN ) );
    
    /**
     * Convenience method for easily building and registering
     * a creative mode tab.
     *
     * @param key          The resource key pointing this creative tab.
     * @param iconSupplier A supplier that returns an ItemStack to be used as the tab icon.
     * @return A registry object referencing the creative tab, when it is built.
     */
    private static RegistryObject<CreativeModeTab> registerSimple( ResourceKey<CreativeModeTab> key, Supplier<ItemStack> iconSupplier ) {
        return register( key.location().getPath(), () -> CreativeModeTab.builder()
                .icon( iconSupplier )
                .title( Component.translatable( key.location().getNamespace() + "." + key.location().getPath() ) )
                .displayItems( AQItems.forTab( key ) )
                .build()
        );
    }
    
    /** Convenience method for building and registering a creative mode tab. */
    private static RegistryObject<CreativeModeTab> register( String name, Supplier<CreativeModeTab> sup ) {
        return REGISTRY.register( name, sup );
    }
    
    private static ResourceKey<CreativeModeTab> key( String name ) {
        return ResourceKey.create( Registries.CREATIVE_MODE_TAB, ArchaicQuest.rl( name ) );
    }
    
    /** Use this for modded items. */
    private static Supplier<ItemStack> iconFrom( Supplier<? extends ItemLike> iconSupplier ) {
        return () -> new ItemStack( iconSupplier.get() );
    }
    
    /** Use this with vanilla items. */
    private static Supplier<ItemStack> iconFrom( ItemLike icon ) {
        return () -> new ItemStack( icon );
    }
    
    /**
     * Called from {@link ArchaicQuest#ArchaicQuest(FMLJavaModLoadingContext)}
     * to load this class and ensure keys are loaded before anything tries accessing them.
     */
    public static void init() {
        Keys.init();
    }
}
