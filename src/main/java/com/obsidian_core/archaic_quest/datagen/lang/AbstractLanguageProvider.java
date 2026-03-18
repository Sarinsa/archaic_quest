package com.obsidian_core.archaic_quest.datagen.lang;

import com.mojang.datafixers.util.Pair;
import com.obsidian_core.archaic_quest.datagen.damage.AQDamageTypes;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;

public abstract class AbstractLanguageProvider extends LanguageProvider {
    
    public AbstractLanguageProvider( PackOutput packOutput, String modid, String locale ) {
        super( packOutput, modid, locale );
    }
    
    protected void addItemGroup( RegistryObject<CreativeModeTab> creativeTab, String localized ) {
        Component component = creativeTab.get().getDisplayName();
        
        if( component.getContents() instanceof TranslatableContents translatableContents )
            add( translatableContents.getKey(), "Archaic Quest - " + localized );
        else
            throw new IllegalArgumentException( "Attempted to add translation for creative tab with non-translatable contents." );
    }
    
    /** Used for block Pairs where both blocks should use the same localized name. */
    protected <FIRST extends Block, SECOND extends Block> void addBlockPair( Pair<RegistryObject<FIRST>, RegistryObject<SECOND>> regObjects, String localized ) {
        addBlock( regObjects.getFirst(), localized );
        addBlock( regObjects.getSecond(), localized );
    }
    
    protected void addBiome( ResourceKey<Biome> biomeKey, String localized ) {
        this.add( "biome.archaic_quest." + biomeKey.location().getPath(), localized );
    }
    
    protected void addJeiInfo( Item item, String localized ) {
        this.add( item.getDescriptionId() + ".jei_desc", localized );
    }
    
    protected void addDamageType( ResourceKey<DamageType> typeKey, String death, String combatLoggedDeath ) {
        String deathString = "death.attack." + AQDamageTypes.msg( typeKey.location().getPath() );
        String combatLoggedDeathString = deathString + ".player";
        
        add( deathString, death );
        add( combatLoggedDeathString, combatLoggedDeath );
    }
    
    protected void addTranslationComponent( Component component, String localized ) {
        if( component.getContents() instanceof TranslatableContents translatableContents ) {
            add( translatableContents.getKey(), localized );
        }
    }
}
