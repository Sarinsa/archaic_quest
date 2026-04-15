package com.obsidian_core.archaic_quest.datagen.lang;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQBlocks;
import com.obsidian_core.archaic_quest.common.core.register.AQCreativeTabs;
import com.obsidian_core.archaic_quest.common.core.register.AQEntities;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import com.obsidian_core.archaic_quest.common.util.TranslationHelper;
import com.obsidian_core.archaic_quest.datagen.damage.AQDamageTypes;
import com.obsidian_core.archaic_quest.datagen.worldgen.AQBiomes;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WallHangingSignBlock;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;

public class AQLanguageProvider extends AbstractLanguageProvider {
    
    private static final String ARG_1 = "%1$s";
    private static final String ARG_2 = "%2$s";
    
    
    public AQLanguageProvider( PackOutput packOutput ) {
        super( packOutput, ArchaicQuest.MODID, "en_us" );
    }
    
    
    @Override
    protected void addTranslations() {
        addItemGroup( AQCreativeTabs.BLOCKS, "Blocks" );
        addItemGroup( AQCreativeTabs.ITEMS, "Items" );
        addItemGroup( AQCreativeTabs.DECORATION, "Decoration" );
        addItemGroup( AQCreativeTabs.FOOD, "Food" );
        addItemGroup( AQCreativeTabs.TOOLS, "Tools" );
        addItemGroup( AQCreativeTabs.WEAPONS, "Weapons" );
        addItemGroup( AQCreativeTabs.ARMOR, "Armor" );
        
        addBlock( AQBlocks.AZTEC_JUNGLE_SAPLING, "Aztec Jungle Sapling" );
        
        addBlock( AQBlocks.TIN_ORE, "Tin Ore" );
        addBlock( AQBlocks.SILVER_ORE, "Silver Ore" );
        addBlock( AQBlocks.BASALT_ORE, "Basalt Ore" );
        addBlock( AQBlocks.DIORITE_JADE_ORE, "Diorite Jade Ore" );
        addBlock( AQBlocks.ANDESITE_TURQUOISE_ORE, "Andesite Turquoise Ore" );
        addBlock( AQBlocks.GRANITE_QUARTZ_ORE, "Granite Quartz Ore" );
        addBlock( AQBlocks.ONYX, "Onyx" );
        
        addBlock( AQBlocks.SKULL_GOBLET, "Skull Goblet" );
        
        addBlockPair( AQBlocks.JAGUAR_SKULL, "Jaguar Skull" );
        addBlockPair( AQBlocks.OLD_SKULL, "Old Skull" );
        addBlockPair( AQBlocks.CRYSTAL_SKULL, "Crystal Skull" );
        addBlockPair( AQBlocks.STONE_SKULL, "Stone Skull" );
        
        addBlock( AQBlocks.AZTEC_VASE, "Aztec Vase" );
        addBlock( AQBlocks.INFESTED_VASE, "Infested Aztec Vase" );
        addBlock( AQBlocks.AZTEC_PILLAR, "Aztec Pillar" );
        addBlock( AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR, "Aztec Spruce Pillar" );
        addBlock( AQBlocks.AZTEC_SPRUCE_WOOD_PILLAR_ANDESITE_BASE, "Aztec Spruce Pillar Base" );
        addBlock( AQBlocks.AZTEC_DUNGEON_DOOR_0, "Aztec Dungeon Door 0" );
        addBlock( AQBlocks.AZTEC_DUNGEON_DOOR_1, "Aztec Dungeon Door 1" );
        addBlock( AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_0, "Aztec Dungeon Door Frame 0" );
        addBlock( AQBlocks.AZTEC_DUNGEON_DOOR_FRAME_1, "Aztec Dungeon Door Frame 1" );
        addBlock( AQBlocks.AZTEC_THRONE, "Aztec Throne" );
        addBlock( AQBlocks.MOSSY_AZTEC_THRONE, "Mossy Aztec Throne" );
        addBlock( AQBlocks.AZTEC_DUNGEON_CHEST, "Aztec Dungeon Chest" );
        
        addBlock( AQBlocks.BRONZE_SPEAR_TRAP, "Bronze Spear Trap" );
        addBlock( AQBlocks.GOLD_SPEAR_TRAP, "Gold Spear Trap" );
        addBlock( AQBlocks.AZTEC_POISON_TRAP, "Aztec Poison Trap" );
        addBlock( AQBlocks.AZTEC_ANDESITE_SPIKE_TRAP, "Andesite Aztec Spike Trap" );
        
        addBlock( AQBlocks.KNAPPING_TABLE, "Knapping Table" );
        addBlock( AQBlocks.AZTEC_WORKTABLE, "Aztec Worktable" );
        
        addItem( AQItems.CORN, "Corn Cob" );
        
        addItem( AQItems.TIN_INGOT, "Tin Ingot" );
        addItem( AQItems.SILVER_INGOT, "Silver Ingot" );
        addItem( AQItems.JADE, "Jade" );
        addItem( AQItems.TURQUOISE, "Turquoise" );
        addItem( AQItems.PEBBLE, "Pebble" );
        
        addItem( AQItems.ADVENTURERS_BAG, "Adventurer's Bag" );
        addItem( AQItems.ADVENTURERS_GLOBE, "Adventurer's Globe" );
        addItem( AQItems.ADVENTURERS_HAT, "Adventurer's Hat" );
        addItem( AQItems.ADVENTURERS_MAGNIFYING_GLASS, "Adventurer's Magnifying Glass" );
        addItem( AQItems.ADVENTURERS_SPYGLASS, "Adventurer's Spyglass" );
        addItem( AQItems.ADVENTURERS_TORCH, "Adventurer's Torch" );
        addItem( AQItems.AMBER, "Amber" );
        addItem( AQItems.AMBER_FOSSIL_0, "Amber Fossil 0" );
        addItem( AQItems.AMBER_FOSSIL_1, "Amber Fossil 1" );
        addItem( AQItems.AZTEC_GOLD_TALISMAN, "Aztec Gold Talisman" );
        addItem( AQItems.AZTEC_GUIDE_BOOK, "Aztec Guide Book" );
        addItem( AQItems.AZTEC_JADE_TALISMAN, "Aztec Jade Talisman" );
        addItem( AQItems.AZTEC_PAN_FLUTE, "Aztec Pan Flute" );
        addItem( AQItems.AZTEC_PIPE, "Aztec Pipe" );
        addItem( AQItems.AZTEC_RITUAL_CHALICE, "Aztec Ritual Chalice" );
        addItem( AQItems.AZTEC_RITUAL_STAFF, "Aztec Ritual Staff" );
        addItem( AQItems.BONE_BLOWPIPE, "Bone Blowpipe" );
        addItem( AQItems.WOODEN_BLOWPIPE, "Wooden Blowpipe" );
        addItem( AQItems.AZTEC_DEATH_WHISTLE, "Aztec Death Whistle" );
        addItem( AQItems.HEART, "Heart" );
        addItem( AQItems.JAGUAR_HIDE, "Jaguar Hide" );
        addItem( AQItems.LEATHER_QUIVER, "Leather Quiver" );
        addItem( AQItems.AZTEC_JAGUAR_QUIVER, "Aztec Jaguar Quiver" );
        addItem( AQItems.OLD_BONE, "Old Bone" );
        addItem( AQItems.POISONOUS_FROG_0, "Poisonous Frog 0" );
        addItem( AQItems.POISONOUS_FROG_1, "Poisonous Frog 1" );
        addItem( AQItems.MACHETE, "Machete" );
        addItem( AQItems.HAMMER_AND_CHISEL, "Hammer and Chisel" );
        addItem( AQItems.WHIP, "Whip" );
        addItem( AQItems.WOOD_BONE_DAGGER, "Wooden Bone Dagger" );
        addItem( AQItems.WOOD_JADE_DAGGER, "Wooden Jade Dagger" );
        addItem( AQItems.WOOD_OBSIDIAN_DAGGER, "Wooden Obsidian Dagger" );
        addItem( AQItems.WOODEN_DART, "Wooden Dart" );
        addItem( AQItems.BONE_DART, "Bone Dart" );
        addItem( AQItems.BONE_CLUB, "Bone Club" );
        
        addEntityType( AQEntities.TLATLAOMI, "Tlatlaomi" );
        addEntityType( AQEntities.AQ_BOAT, "Boat" );
        addEntityType( AQEntities.AQ_CHEST_BOAT, "Boat with Chest" );
        
        addBiome( AQBiomes.AZTEC_JUNGLE, "Aztec Jungle" );
        
        addTranslationComponent( TranslationHelper.AZTEC_DUNGEON_CHEST_CONTAINER, "Aztec Dungeon Chest" );
        addTranslationComponent( TranslationHelper.AZTEC_WORKTABLE_CONTAINER, "Aztec Worktable" );
        addTranslationComponent( TranslationHelper.KNAPPING_TABLE_CONTAINER, "Knapping Table" );
        
        addDamageType( AQDamageTypes.SPEAR_TRAP,
                ARG_1 + " was impaled on a spear trap",
                ARG_1 + " landed on a spear trap whilst running from " + ARG_2 );
        addDamageType( AQDamageTypes.SPIKE_TRAP,
                ARG_1 + " got skewered by a spike trap",
                ARG_1 + " got skewered by a spike trap whilst running from " + ARG_2 );
        
        addJeiInfo( AQItems.MACHETE.get(), "The machete is a light weapon that can be swung faster, but deals less damage than a sword. It can also be used to cut vines. " +
                "Right-clicking will cut a vine shorter and stop it from growing, while sneak-right-clicking will only make the vine stop growing." );
        
        AQItems.SPAWN_EGGS.forEach( ( egg ) -> {
            // noinspection ConstantConditions
            String translation = egg.getId().getPath().replaceAll( "_", " " );
            // noinspection deprecation
            addItem( egg, WordUtils.capitalizeFully( translation ) );
        } );
        
        WoodSet.WOOD_SETS.forEach( this::woodSet );
    }
    
    private void woodSet( WoodSet woodSet ) {
        for( RegistryObject<? extends Block> regObj : woodSet.allBlocks() ) {
            // Avoid duplicate for wall signs
            if( regObj.get() instanceof WallSignBlock || regObj.get() instanceof WallHangingSignBlock ) continue;
            
            if( regObj.get() == woodSet.getStrippedWood().get() || regObj.get() == woodSet.getStrippedLog().get() ) {
                // noinspection ConstantConditions
                String translation = regObj.getId().getPath();
                translation = translation.replaceAll( "stripped", "" );
                translation = "stripped_" + translation;
                translation = translation.replaceAll( "_", " " );
                // noinspection deprecation
                addBlock( regObj, WordUtils.capitalizeFully( translation ) );
                continue;
            }
            addBlock( regObj, regNameToName( regObj ) );
        }
        addItem( woodSet.getBoat(), regNameToName( woodSet.getBoat() ) );
        addItem( woodSet.getChestBoat(), regNameToName( woodSet.getChestBoat() ) );
    }
    
    @SuppressWarnings( "deprecation" )
    private static String regNameToName( RegistryObject<?> registryObject ) {
        // noinspection ConstantConditions
        String translation = registryObject.getId().getPath().replaceAll( "_", " " );
        return WordUtils.capitalizeFully( translation );
    }
}
