package com.obsidian_core.archaic_quest.datagen.model;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.AQItems;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class AQItemModelProvider extends ItemModelProvider {
    
    public AQItemModelProvider( PackOutput packOutput, ExistingFileHelper fileHelper ) {
        super( packOutput, ArchaicQuest.MODID, fileHelper );
    }
    
    @Override
    protected void registerModels() {
        AQItems.SIMPLE_ITEMS.forEach( ( item ) -> simpleItem( item.get(), false ) );
        AQItems.SPAWN_EGGS.forEach( ( egg ) -> spawnEgg( egg.get() ) );
        WoodSet.WOOD_SETS.forEach( this::woodSet );
    }
    
    private void existingBlock( RegistryObject<? extends Block> block ) {
        withExistingParent( block.getId().getPath(), ArchaicQuest.rl( "block/" + block.getId().getPath() ) );
    }
    
    private <T extends Item> void simpleItem( T item, boolean blockTexture ) {
        ResourceLocation regName = Objects.requireNonNull( ForgeRegistries.ITEMS.getKey( item ) );
        singleTexture( regName.getPath(), mcLoc( "item/generated" ), "layer0", ArchaicQuest.rl( (blockTexture ? "block" : "item") + "/" + regName.getPath() ) );
    }
    
    private void spawnEgg( ForgeSpawnEggItem spawnEgg ) {
        ResourceLocation regName = Objects.requireNonNull( ForgeRegistries.ITEMS.getKey( spawnEgg ) );
        withExistingParent( regName.getPath(), mcLoc( "item/template_spawn_egg" ) );
    }
    
    private void woodSet( WoodSet woodSet ) {
        simpleItem( woodSet.getSapling().get().asItem(), true );
        existingBlock( woodSet.getLeaves() );
        existingBlock( woodSet.getWood() );
        existingBlock( woodSet.getStrippedWood() );
        existingBlock( woodSet.getLog() );
        existingBlock( woodSet.getStrippedLog() );
        existingBlock( woodSet.getPlanks() );
        existingBlock( woodSet.getSlab() );
        existingBlock( woodSet.getVertSlab() );
        existingBlock( woodSet.getStairs() );
        existingBlock( woodSet.getFenceGate() );
        existingBlock( woodSet.getPressurePlate() );
        
        withExistingParent( woodSet.getFence().getId().getPath(), ResourceLocation.withDefaultNamespace( "block/fence_inventory" ) )
                .texture( "texture", blockTexture( woodSet.getPlanks().get() ) );
        
        withExistingParent( woodSet.getButton().getId().getPath(), ResourceLocation.withDefaultNamespace( "block/button_inventory" ) )
                .texture( "texture", blockTexture( woodSet.getPlanks().get() ) );
        
        withExistingParent( woodSet.getTrapdoor().getId().getPath(), resLoc( "block/" + woodSet.getTrapdoor().getId().getPath() + "_bottom" ) );
        
        simpleItem( woodSet.getDoor().get().asItem(), false );
        simpleItem( woodSet.getSign().get().asItem(), false );
        simpleItem( woodSet.getBoat().get(), false );
        simpleItem( woodSet.getChestBoat().get(), false );
    }
    
    private static ResourceLocation resLoc( String path ) {
        return ArchaicQuest.rl( path );
    }
    
    private ResourceLocation key( Block block ) {
        return ForgeRegistries.BLOCKS.getKey( block );
    }
    
    private String name( Block block ) {
        return key( block ).getPath();
    }
    
    public ResourceLocation blockTexture( Block block ) {
        ResourceLocation name = key( block );
        return ResourceLocation.fromNamespaceAndPath( name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath() );
    }
}
