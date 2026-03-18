package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.item.*;
import com.obsidian_core.archaic_quest.common.item.data.AQItemTier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static com.obsidian_core.archaic_quest.common.core.register.AQCreativeTabs.Keys;

public class AQItems {
    
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create( ForgeRegistries.ITEMS, ArchaicQuest.MODID );
    
    private static final Map<ResourceKey<CreativeModeTab>, List<RegistryObject<? extends Item>>> ITEMS_FOR_TABS = new HashMap<>();
    
    public static final List<RegistryObject<? extends Item>> SIMPLE_ITEMS = new ArrayList<>();
    public static final List<RegistryObject<ForgeSpawnEggItem>> SPAWN_EGGS = new ArrayList<>();
    
    
    // FOOD
    public static final RegistryObject<Item> CORN = registerSimpleItem( "corn_cob", Keys.FOOD, () -> new ItemNameBlockItem( AQBlocks.CORN_CROP.get(), new Item.Properties()
            .food( new FoodProperties.Builder()
                    .nutrition( 2 )
                    .saturationMod( 1.0F )
                    .build() ) ) );
    
    // MISC
    public static final RegistryObject<Item> TIN_INGOT = registerSimpleItem( "tin_ingot", Keys.ITEMS );
    public static final RegistryObject<Item> SILVER_INGOT = registerSimpleItem( "silver_ingot", Keys.ITEMS );
    public static final RegistryObject<Item> TURQUOISE = registerSimpleItem( "turquoise", Keys.ITEMS );
    public static final RegistryObject<Item> JADE = registerSimpleItem( "jade", Keys.ITEMS );
    public static final RegistryObject<Item> PEBBLE = registerSimpleItem( "pebble", Keys.ITEMS, PebbleItem::new );
    
    public static final RegistryObject<Item> ADVENTURERS_BAG = registerSimpleItem( "adventurers_bag", Keys.ITEMS );
    public static final RegistryObject<Item> ADVENTURERS_GLOBE = registerSimpleItem( "adventurers_globe", Keys.ITEMS );
    public static final RegistryObject<Item> ADVENTURERS_HAT = registerSimpleItem( "adventurers_hat", Keys.ITEMS );
    public static final RegistryObject<Item> ADVENTURERS_MAGNIFYING_GLASS = registerSimpleItem( "adventurers_magnifying_glass", Keys.ITEMS );
    public static final RegistryObject<Item> ADVENTURERS_SPYGLASS = registerSimpleItem( "adventurers_spyglass", Keys.ITEMS );
    public static final RegistryObject<Item> ADVENTURERS_TORCH = registerItem( "adventurers_torch", Keys.TOOLS, AdventurersTorchItem::new );
    public static final RegistryObject<Item> AMBER = registerSimpleItem( "amber", Keys.ITEMS );
    public static final RegistryObject<Item> AMBER_FOSSIL_0 = registerSimpleItem( "amber_fossil_0", Keys.ITEMS );
    public static final RegistryObject<Item> AMBER_FOSSIL_1 = registerSimpleItem( "amber_fossil_1", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_GOLD_TALISMAN = registerSimpleItem( "aztec_gold_talisman", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_GUIDE_BOOK = registerSimpleItem( "aztec_guide_book", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_JADE_TALISMAN = registerSimpleItem( "aztec_jade_talisman", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_PAN_FLUTE = registerSimpleItem( "aztec_pan_flute", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_PIPE = registerSimpleItem( "aztec_pipe", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_RITUAL_CHALICE = registerSimpleItem( "aztec_ritual_chalice", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_RITUAL_STAFF = registerSimpleItem( "aztec_ritual_staff", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_DEATH_WHISTLE = registerSimpleItem( "aztec_death_whistle", Keys.ITEMS, AztecDeathWhistleItem::new );
    public static final RegistryObject<Item> HEART = registerSimpleItem( "heart", Keys.ITEMS );
    public static final RegistryObject<Item> JAGUAR_HIDE = registerSimpleItem( "jaguar_hide", Keys.ITEMS );
    public static final RegistryObject<Item> LEATHER_QUIVER = registerSimpleItem( "leather_quiver", Keys.ITEMS );
    public static final RegistryObject<Item> AZTEC_JAGUAR_QUIVER = registerSimpleItem( "aztec_jaguar_quiver", Keys.ITEMS );
    public static final RegistryObject<Item> OLD_BONE = registerSimpleItem( "old_bone", Keys.ITEMS );
    public static final RegistryObject<Item> POISONOUS_FROG_0 = registerSimpleItem( "poisonous_frog_0", Keys.ITEMS );
    public static final RegistryObject<Item> POISONOUS_FROG_1 = registerSimpleItem( "poisonous_frog_1", Keys.ITEMS );
    
    
    // TOOLS & WEAPONS
    public static final RegistryObject<Item> MACHETE = registerSimpleItem( "machete", Keys.WEAPONS, () -> new MacheteItem( Tiers.IRON, 200, 2, -1.0F ) );
    public static final RegistryObject<Item> HAMMER_AND_CHISEL = registerSimpleItem( "hammer_and_chisel", Keys.TOOLS, HammerAndChiselItem::new );
    public static final RegistryObject<Item> WHIP = registerSimpleItem( "whip", Keys.ITEMS );
    
    public static final RegistryObject<Item> WOOD_BONE_DAGGER = registerSimpleItem( "wood_bone_dagger", Keys.WEAPONS, () -> new AQSimpleWeaponItem( Tiers.WOOD, 60, 3, 0.0F ) );
    public static final RegistryObject<Item> WOOD_JADE_DAGGER = registerSimpleItem( "wood_jade_dagger", Keys.WEAPONS, () -> new AQSimpleWeaponItem( AQItemTier.JADE, 60, 3, 0.0F ) );
    public static final RegistryObject<Item> WOOD_OBSIDIAN_DAGGER = registerSimpleItem( "wood_obsidian_dagger", Keys.WEAPONS, () -> new AQSimpleWeaponItem( AQItemTier.OBSIDIAN, 60, 3, 0.0F ) );
    public static final RegistryObject<Item> BONE_CLUB = registerSimpleItem( "bone_club", Keys.WEAPONS, () -> new AQSimpleWeaponItem( Tiers.WOOD, 60, 5, -3.0F ) );
    
    public static final RegistryObject<Item> BONE_BLOWPIPE = registerSimpleItem( "bone_blowpipe", Keys.WEAPONS );
    public static final RegistryObject<Item> WOODEN_BLOWPIPE = registerSimpleItem( "wooden_blowpipe", Keys.WEAPONS );
    public static final RegistryObject<Item> WOODEN_DART = registerSimpleItem( "wooden_dart", Keys.WEAPONS );
    public static final RegistryObject<Item> BONE_DART = registerSimpleItem( "bone_dart", Keys.WEAPONS );
    
    public static final RegistryObject<ArmorItem> ICHCAHUIPILLI_HEADGEAR = registerArmor( "ichcahuipilli_headgear", () -> new IchcahuipilliArmorItem( ArmorMaterials.LEATHER, ArmorItem.Type.HELMET ) );
    public static final RegistryObject<ArmorItem> ICHCAHUIPILLI_SHIRT = registerArmor( "ichcahuipilli_shirt", () -> new IchcahuipilliArmorItem( ArmorMaterials.LEATHER, ArmorItem.Type.CHESTPLATE ) );
    public static final RegistryObject<ArmorItem> ICHCAHUIPILLI_SKIRT = registerArmor( "ichcahuipilli_skirt", () -> new IchcahuipilliArmorItem( ArmorMaterials.LEATHER, ArmorItem.Type.LEGGINGS ) );
    public static final RegistryObject<ArmorItem> ICHCAHUIPILLI_BOOTS = registerArmor( "ichcahuipilli_boots", () -> new IchcahuipilliArmorItem( ArmorMaterials.LEATHER, ArmorItem.Type.BOOTS ) );
    
    
    public static <T extends Item> RegistryObject<T> registerItem( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<T> itemSupplier ) {
        RegistryObject<T> regObj = REGISTRY.register( name, itemSupplier );
        addToTab( regObj, creativeTab );
        return regObj;
    }
    
    /** Used to register block items that should not use the block's description ID. */
    protected static RegistryObject<Item> registerItemNameBlock( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<? extends Block> supplier ) {
        RegistryObject<Item> regObj = REGISTRY.register( name, () -> new ItemNameBlockItem( supplier.get(), new Item.Properties() ) );
        addToTab( regObj, creativeTab );
        return regObj;
    }
    
    /** Used for items that should have a simple generated item model. */
    public static <T extends Item> RegistryObject<T> registerSimpleItem( String name, ResourceKey<CreativeModeTab> creativeTab, Supplier<T> itemSupplier ) {
        RegistryObject<T> regObj = REGISTRY.register( name, itemSupplier );
        SIMPLE_ITEMS.add( regObj );
        addToTab( regObj, creativeTab );
        return regObj;
    }
    
    /** Used for items that should have a simple generated item model. */
    protected static RegistryObject<Item> registerSimpleItem( String name, ResourceKey<CreativeModeTab> creativeTab ) {
        RegistryObject<Item> regObj = REGISTRY.register( name, () -> new Item( new Item.Properties() ) );
        SIMPLE_ITEMS.add( regObj );
        addToTab( regObj, creativeTab );
        return regObj;
    }
    
    /** Used for food items that should have a simple generated item model. */
    private static RegistryObject<Item> registerSimpleFood( String name, ResourceKey<CreativeModeTab> creativeTab, FoodProperties food ) {
        RegistryObject<Item> regObj = REGISTRY.register( name, () -> new Item( new Item.Properties().food( food ) ) );
        SIMPLE_ITEMS.add( regObj );
        addToTab( regObj, creativeTab );
        return regObj;
    }
    
    private static RegistryObject<ArmorItem> registerArmor( String name, Supplier<ArmorItem> armor ) {
        RegistryObject<ArmorItem> regObj = REGISTRY.register( name, armor );
        SIMPLE_ITEMS.add( regObj );
        addToTab( regObj, Keys.ARMOR );
        return regObj;
    }
    
    protected static <T extends Mob> RegistryObject<ForgeSpawnEggItem> registerSpawnEgg( RegistryObject<EntityType<T>> entityType, int primaryColor, int secondaryColor ) {
        RegistryObject<ForgeSpawnEggItem> regObj = REGISTRY.register( entityType.getId().getPath() + "_spawn_egg", () -> new ForgeSpawnEggItem( entityType, primaryColor, secondaryColor, new Item.Properties() ) );
        SPAWN_EGGS.add( regObj );
        return regObj;
    }
    
    /**
     * Fetches the item registry object list associated with the given creative tab's resource key
     * and adds the specified item registry object to it. This makes it way easier to build our
     * creative mode tab's content later.
     *
     * @param regObj         The item to add to the desired creative mode tab.
     * @param creativeTabKey The key of the creative mode tab to add the item to.
     */
    private static void addToTab( RegistryObject<? extends Item> regObj, ResourceKey<CreativeModeTab> creativeTabKey ) {
        // Create a fresh empty list if there isn't one already.
        if( !ITEMS_FOR_TABS.containsKey( creativeTabKey ) ) {
            ITEMS_FOR_TABS.put( creativeTabKey, new ArrayList<>() );
        }
        if( !ITEMS_FOR_TABS.get( creativeTabKey ).contains( regObj ) )
            ITEMS_FOR_TABS.get( creativeTabKey ).add( regObj );
    }
    
    /**
     * Used in {@link AQCreativeTabs}.
     *
     * @return A {@link net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator} instance to be used
     * by the creative tab associated with the given resource key.
     */
    protected static CreativeModeTab.DisplayItemsGenerator forTab( ResourceKey<CreativeModeTab> key ) {
        if( !ITEMS_FOR_TABS.containsKey( key ) ) {
            // "Empty" generator
            return ( parameters, output ) -> { };
        }
        return ( parameters, output ) -> {
            ITEMS_FOR_TABS.get( key ).forEach( regObj -> {
                output.accept( new ItemStack( regObj.get() ) );
            } );
        };
    }
}
