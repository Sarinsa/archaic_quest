package com.obsidian_core.archaic_quest.common.core.register;

import com.mojang.serialization.Codec;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.CustomLeavesVineDecorator;
import com.obsidian_core.archaic_quest.common.worldgen.feature.decorators.CustomTrunkVineDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQTreeDecoratorType {
    
    public static final DeferredRegister<TreeDecoratorType<?>> REGISTRY = DeferredRegister.create( ForgeRegistries.TREE_DECORATOR_TYPES, ArchaicQuest.MODID );
    
    
    public static final RegistryObject<TreeDecoratorType<CustomTrunkVineDecorator>> CUSTOM_TRUNK_VINES = register( "custom_trunk_vines", CustomTrunkVineDecorator.CODEC );
    public static final RegistryObject<TreeDecoratorType<CustomLeavesVineDecorator>> CUSTOM_LEAVES_VINES = register( "custom_leaves_vines", CustomLeavesVineDecorator.CODEC );
    
    
    private static <P extends TreeDecorator> RegistryObject<TreeDecoratorType<P>> register( String name, Codec<P> codec ) {
        return REGISTRY.register( name, () -> new TreeDecoratorType<>( codec ) );
    }
}
