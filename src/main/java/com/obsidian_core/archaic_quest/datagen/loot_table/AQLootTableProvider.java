package com.obsidian_core.archaic_quest.datagen.loot_table;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.Set;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class AQLootTableProvider extends LootTableProvider {
    
    public AQLootTableProvider( PackOutput packOutput ) {
        super( packOutput, Set.of(), List.of(
                new SubProviderEntry( () -> new AQBlockLootTableProvider( Set.of() ), LootContextParamSets.BLOCK )
        ) );
    }
    
    @Override
    protected void validate( Map<ResourceLocation, LootTable> map, ValidationContext context ) {
        // NOOP
    }
}
