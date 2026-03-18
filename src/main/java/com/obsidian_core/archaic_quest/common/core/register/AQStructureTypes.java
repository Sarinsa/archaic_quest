package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.worldgen.structure.GeneralStructureType;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AQStructureTypes {
    
    public static final DeferredRegister<StructureType<?>> REGISTRY = DeferredRegister.create( Registries.STRUCTURE_TYPE, ArchaicQuest.MODID );
    
    
    public static final RegistryObject<StructureType<GeneralStructureType>> GENERAL = REGISTRY.register( "general", () -> type( GeneralStructureType.CODEC ) );
    
    
    private static <T extends Structure> StructureType<T> type( Codec<T> codec ) {
        return () -> codec;
    }
}
