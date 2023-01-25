package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.worldgen.biome.modifier.AddOreModifier;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class AQBiomeModifiers {

    public static final DeferredRegister<Codec<? extends BiomeModifier>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, ArchaicQuest.MODID);


    public static final RegistryObject<Codec<AddOreModifier>> ADD_ORE = register("add_ore", AddOreModifier::create);


    private static <T extends BiomeModifier> RegistryObject<Codec<T>> register(String name, Supplier<Codec<T>> codecSupplier) {
        return REGISTRY.register(name, codecSupplier);
    }
}
