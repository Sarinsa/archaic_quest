package com.obsidian_core.archaic_quest.datagen.tag;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.datagen.damage.AQDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.DamageTypeTagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class AQDamageTypeTagProvider extends DamageTypeTagsProvider {
    
    public AQDamageTypeTagProvider( PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper fileHelper ) {
        super( packOutput, lookupProvider, ArchaicQuest.MODID, fileHelper );
    }
    
    @Override
    protected void addTags( HolderLookup.Provider lookupProvider ) {
        tag( DamageTypeTags.IS_PROJECTILE )
                .addOptional( AQDamageTypes.DART.location() );
        
        tag( DamageTypeTags.BYPASSES_SHIELD )
                .addOptional( AQDamageTypes.SPIKE_TRAP.location() )
                .addOptional( AQDamageTypes.SPEAR_TRAP.location() );
    }
}
