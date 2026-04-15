package com.obsidian_core.archaic_quest.datagen.blockstate;

import com.obsidian_core.archaic_quest.common.block.AztecWoodPillarBlock;
import com.obsidian_core.archaic_quest.common.block.CuttableVinesBlock;
import com.obsidian_core.archaic_quest.common.block.SpearTrapBlock;
import com.obsidian_core.archaic_quest.common.block.VerticalSlabBlock;
import com.obsidian_core.archaic_quest.common.block.base.BaseDoubleCropBlock;
import com.obsidian_core.archaic_quest.common.block.misc.AQStateProperties;
import com.obsidian_core.archaic_quest.common.block.skull.SkullGobletBlock;
import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.core.register.util.WoodSet;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public abstract class AbstractBlockStateProvider extends BlockStateProvider {
    
    public AbstractBlockStateProvider( PackOutput packOutput, ExistingFileHelper exFileHelper ) {
        super( packOutput, ArchaicQuest.MODID, exFileHelper );
    }
    
    protected String name( Block block ) {
        return Objects.requireNonNull( ForgeRegistries.BLOCKS.getKey( block ) ).getPath();
    }
    
    /**
     * Intended for blocks with block entity renderers;
     * no model but with break particles.
     */
    protected void blockNoModel( Block block, ResourceLocation blockForParticle ) {
        ModelFile model = models().getBuilder( name( block ) ).texture( "particle", blockForParticle );
        
        getVariantBuilder( block )
                .partialState()
                .setModels( new ConfiguredModel( model ) );
    }
    
    protected void simpleBlockAndItem( Block block ) {
        this.simpleBlock( block );
        this.simpleBlockItem( block, cubeAll( block ) );
    }
    
    protected void topBottomCube( Block block, ResourceLocation sides, ResourceLocation topBottom ) {
        ModelFile model = models().cubeBottomTop( rl( ":block/" + name( block ) ).toString(), sides, topBottom, topBottom );
        
        getVariantBuilder( block ).forAllStates( state -> ConfiguredModel.builder()
                .modelFile( model )
                .build() );
        
        simpleBlockItem( block, model );
    }
    
    protected void slab( SlabBlock block, Block doubleBlock ) {
        slab( block, doubleBlock, blockTexture( doubleBlock ) );
    }
    
    protected void slab( SlabBlock block, Block doubleBlock, ResourceLocation texture ) {
        slab( block, doubleBlock, texture, texture, texture );
    }
    
    protected void slab( SlabBlock block, Block doubleBlock, ResourceLocation side, ResourceLocation bottom, ResourceLocation top ) {
        ModelFile topModel = models().withExistingParent( name( block ) + "_top", mcLoc( "block/slab_top" ) )
                .texture( "side", side )
                .texture( "bottom", bottom )
                .texture( "top", top );
        
        ModelFile bottomModel = models().withExistingParent( name( block ), mcLoc( "block/slab" ) )
                .texture( "side", side )
                .texture( "bottom", bottom )
                .texture( "top", top );
        
        slab( block, topModel, bottomModel, cubeAll( doubleBlock ) );
        simpleBlockItem( block, bottomModel );
    }
    
    protected void slab( SlabBlock block, ModelFile topModel, ModelFile bottomModel, ModelFile doubleModel ) {
        getVariantBuilder( block )
                .partialState().with( SlabBlock.TYPE, SlabType.BOTTOM ).addModels( new ConfiguredModel( bottomModel ) )
                .partialState().with( SlabBlock.TYPE, SlabType.TOP ).addModels( new ConfiguredModel( topModel ) )
                .partialState().with( SlabBlock.TYPE, SlabType.DOUBLE ).addModels( new ConfiguredModel( doubleModel ) );
    }
    
    protected void simpleVerticalSlab( VerticalSlabBlock block, Block doubleBlock ) {
        verticalSlab( block, doubleBlock, blockTexture( doubleBlock ), blockTexture( doubleBlock ), blockTexture( doubleBlock ) );
    }
    
    protected void verticalSlab( VerticalSlabBlock block, Block doubleBlock, ResourceLocation side, ResourceLocation bottom, ResourceLocation top ) {
        ModelFile model = models().withExistingParent( name( block ), rl( "block/vertical_slab" ) )
                .texture( "side", side )
                .texture( "bottom", bottom )
                .texture( "top", top );
        
        verticalSlab( block, model, cubeAll( doubleBlock ) );
        simpleBlockItem( block, model );
    }
    
    
    protected void verticalSlab( VerticalSlabBlock block, ModelFile model, ModelFile doubleSlab ) {
        getVariantBuilder( block ).forAllStatesExcept( state -> {
            VerticalSlabBlock.SlabState slabState = state.getValue( VerticalSlabBlock.SLAB_STATE );
            
            if( slabState == VerticalSlabBlock.SlabState.DOUBLE ) {
                return ConfiguredModel.builder()
                        .modelFile( doubleSlab )
                        .build();
            }
            Direction facing = slabState.getDirection();
            int yRot = (int) facing.getOpposite().toYRot();
            
            return ConfiguredModel.builder()
                    .modelFile( model )
                    .rotationY( yRot )
                    .uvLock( true )
                    .build();
        }, VerticalSlabBlock.WATERLOGGED );
    }
    
    protected void woodSet( WoodSet woodSet ) {
        Block planks = woodSet.getPlanks().get();
        
        sapling( woodSet.getSapling().get() );
        leaves( woodSet.getLeaves().get() );
        wood( woodSet.getWood().get(), woodSet.getLog().get() );
        wood( woodSet.getStrippedWood().get(), woodSet.getStrippedLog().get() );
        logBlock( woodSet.getLog().get() );
        logBlock( woodSet.getStrippedLog().get() );
        simpleBlock( planks );
        slab( woodSet.getSlab().get(), planks );
        simpleVerticalSlab( woodSet.getVertSlab().get(), planks );
        stairsBlock( woodSet.getStairs().get(), blockTexture( planks ) );
        fenceBlock( woodSet.getFence().get(), blockTexture( planks ) );
        fenceGateBlock( woodSet.getFenceGate().get(), blockTexture( planks ) );
        pressurePlateBlock( woodSet.getPressurePlate().get(), blockTexture( planks ) );
        buttonBlock( woodSet.getButton().get(), blockTexture( planks ) );
        trapDoor( woodSet.getTrapdoor().get(), true, true );
        door( woodSet.getDoor().get(), true );
        
        blockNoModel( woodSet.getSign().get(), blockTexture( woodSet.getPlanks().get() ) );
        blockNoModel( woodSet.getWallSign().get(), blockTexture( woodSet.getPlanks().get() ) );
        blockNoModel( woodSet.getHangingSign().get(), blockTexture( woodSet.getPlanks().get() ) );
        blockNoModel( woodSet.getWallHangingSign().get(), blockTexture( woodSet.getPlanks().get() ) );
    }
    
    protected void leaves( Block block ) {
        ModelFile model = models().withExistingParent( name( block ), ResourceLocation.withDefaultNamespace( "block/leaves" ) )
                .texture( "all", blockTexture( block ) );
        
        getVariantBuilder( block ).partialState().setModels( new ConfiguredModel( model ) );
    }
    
    protected void wood( RotatedPillarBlock block, RotatedPillarBlock log ) {
        ResourceLocation texture = blockTexture( log );
        
        axisBlock( block,
                models().cubeColumn( name( block ), texture, texture ),
                models().cubeColumnHorizontal( name( block ) + "_horizontal", texture, texture ) );
    }
    
    protected void sapling( Block block ) {
        ModelFile model = models().withExistingParent( name( block ), ResourceLocation.withDefaultNamespace( "block/cross" ) )
                .renderType( "cutout" )
                .texture( "cross", blockTexture( block ) );
        
        getVariantBuilder( block ).partialState().setModels( new ConfiguredModel( model ) );
    }
    
    protected void door( DoorBlock doorBlock, boolean cutout ) {
        ResourceLocation bottom = blockTextureWith( doorBlock, "bottom" );
        ResourceLocation top = blockTextureWith( doorBlock, "top" );
        
        if( cutout ) {
            doorBlockWithRenderType( doorBlock, bottom, top, "cutout" );
        }
        else {
            doorBlock( doorBlock, bottom, top );
        }
    }
    
    protected void trapDoor( TrapDoorBlock trapDoorBlock, boolean orientable, boolean cutout ) {
        ResourceLocation texture = blockTexture( trapDoorBlock );
        
        if( cutout ) {
            trapdoorBlockWithRenderType( trapDoorBlock, texture, orientable, "cutout" );
        }
        else {
            trapdoorBlock( trapDoorBlock, texture, orientable );
        }
    }
    
    protected void doubleCrop( BaseDoubleCropBlock block ) {
        ResourceLocation crossModel = mcLoc( "block/cross" );
        
        getVariantBuilder( block ).forAllStates( ( state ) -> {
            int age = state.getValue( block.getAgeProperty() );
            boolean top = state.getValue( BaseDoubleCropBlock.IS_TOP );
            String modelFileName = name( block ) + "_stage_" + age + (top ? "_top" : "");
            
            return ConfiguredModel.builder()
                    .modelFile( models().withExistingParent( modelFileName, crossModel )
                            .renderType( "cutout" )
                            .texture( "cross", texture( modelFileName ) ) )
                    .build();
        } );
    }
    
    protected void cuttableVine( CuttableVinesBlock vineBlock ) {
        getVariantBuilder( vineBlock ).forAllStatesExcept( ( state ) -> {
            Direction face = state.getValue( CuttableVinesBlock.FACING );
            boolean cut = state.getValue( CuttableVinesBlock.CUT );
            int yRot = (int) face.getOpposite().toYRot();
            
            String textureName = name( vineBlock ) + (cut ? "_cut" : "");
            ResourceLocation modelName = rl( "block/vine_var_1" + (cut ? "_cut" : "") );
            
            return ConfiguredModel.builder()
                    .modelFile( models().withExistingParent( name( vineBlock ) + (cut ? "_cut" : ""), modelName )
                            .texture( "vine", texture( textureName ) ) )
                    .rotationY( yRot )
                    .build();
        }, CuttableVinesBlock.CAN_GROW );
        generatedItem( vineBlock );
    }
    
    protected void spearTrap( SpearTrapBlock trapBlock ) {
        getVariantBuilder( trapBlock ).forAllStatesExcept( ( state ) -> {
            boolean extended = state.getValue( SpearTrapBlock.EXTENDED );
            ResourceLocation parentModel = rl( "block/spear_trap" );
            
            return ConfiguredModel.builder()
                    .modelFile( models().withExistingParent( extended ? name( trapBlock ) + "_extended" : name( trapBlock ), parentModel )
                            .texture( "texture", extended ? texture( "spear_trap_base" ) : texture( name( trapBlock ) ) ) )
                    .build();
        }, SpearTrapBlock.WATERLOGGED );
        generateItemBlockTexture( trapBlock );
    }
    
    protected void woodPillar( AztecWoodPillarBlock woodPillarBlock ) {
        final String[] extendedModels = new String[] {
                "_extended",
                "_connect_x_extended",
                "_connect_z_extended",
                "_connect_xz_extended"
        };
        final String normal = "";
        final String x = "_x";
        final String z = "_z";
        final String connectX = "_connect_x";
        final String connectZ = "_connect_z";
        final String connectXZ = "_connect_xz";
        
        getVariantBuilder( woodPillarBlock ).forAllStatesExcept( ( state ) -> {
            boolean extended = state.getValue( AztecWoodPillarBlock.EXTENDED );
            Direction.Axis axis = state.getValue( AztecWoodPillarBlock.AXIS );
            boolean connectedX = state.getValue( AztecWoodPillarBlock.CONNECTED_X );
            boolean connectedZ = state.getValue( AztecWoodPillarBlock.CONNECTED_Z );
            
            String modelName = normal;
            
            if( extended ) {
                if( connectedX && connectedZ )
                    modelName = extendedModels[3];
                else if( connectedZ )
                    modelName = extendedModels[2];
                else if( connectedX )
                    modelName = extendedModels[1];
                else {
                    switch( axis ) {
                        case X -> modelName = x;
                        case Z -> modelName = z;
                        case Y -> modelName = extendedModels[0];
                    }
                }
            }
            else {
                if( connectedX && connectedZ )
                    modelName = connectXZ;
                else if( connectedZ )
                    modelName = connectZ;
                else if( connectedX )
                    modelName = connectX;
                else {
                    switch( axis ) {
                        case X -> modelName = x;
                        case Z -> modelName = z;
                        case Y -> {
                            // Do nothing; model already initialized as the normal variant
                        }
                    }
                }
            }
            
            return ConfiguredModel.builder()
                    .modelFile( models().withExistingParent( name( woodPillarBlock ) + modelName, rl( "block/wood_pillar" + modelName ) )
                            .texture( "texture", texture( name( woodPillarBlock ) ) ) )
                    .build();
        }, AztecWoodPillarBlock.WATERLOGGED );
        simpleBlockItem( woodPillarBlock, models().withExistingParent( name( woodPillarBlock ), rl( "block/wood_pillar" ) ) );
    }
    
    protected void skullGoblet( SkullGobletBlock skullGoblet ) {
        final ResourceLocation filledModel = rl( "block/template/skull_goblet_full" );
        final ResourceLocation filledDiagModel = rl( "block/template/skull_goblet_full_diagonal" );
        final ResourceLocation emptyModel = rl( "block/template/skull_goblet_empty" );
        final ResourceLocation emptyDiagModel = rl( "block/template/skull_goblet_empty_diagonal" );
        
        getVariantBuilder( skullGoblet ).forAllStates( ( state ) -> {
            final boolean filled = state.getValue( AQStateProperties.FILLED );
            final int rotation = state.getValue( SkullGobletBlock.ROTATION );
            final boolean isDiagonal = rotation % 2 == 0;
            
            
            if( filled ) {
                return ConfiguredModel.builder()
                        .modelFile( models().withExistingParent( name( skullGoblet ) + (isDiagonal ? "_full" : "_full_diagonal"),
                                        isDiagonal ? filledModel : filledDiagModel )
                                .texture( "base", texture( name( skullGoblet ) + "_base" ) )
                                .texture( "skull", texture( name( skullGoblet ) + "_skull" ) )
                                .texture( "fluid", texture( name( skullGoblet ) + "_fluid" ) )
                        )
                        .rotationY( (int) Math.floor( (double) rotation / 2 ) * 90 )
                        .build();
            }
            else {
                return ConfiguredModel.builder()
                        .modelFile( models().withExistingParent( name( skullGoblet ) + (isDiagonal ? "_empty" : "_empty_diagonal"),
                                        isDiagonal ? emptyModel : emptyDiagModel )
                                .texture( "base", texture( name( skullGoblet ) + "_base" ) )
                                .texture( "skull", texture( name( skullGoblet ) + "_skull" ) )
                        )
                        .rotationY( (int) Math.floor( (double) rotation / 2 ) * 90 )
                        .build();
            }
        } );
        simpleBlockItem( skullGoblet, models().withExistingParent( name( skullGoblet ) + "_empty", emptyModel ) );
    }
    
    private void generatedItem( Block block ) {
        itemModels().withExistingParent( name( block ), mcLoc( "item/generated" ) )
                .texture( "layer0", itemTexture( name( block ) ) );
    }
    
    private void generateItemBlockTexture( Block block ) {
        itemModels().withExistingParent( name( block ), mcLoc( "item/generated" ) )
                .texture( "layer0", texture( name( block ) ) );
    }
    
    public static ResourceLocation rl( String path ) {
        return ArchaicQuest.rl( path );
    }
    
    public static ResourceLocation texture( String textureName ) {
        return rl( "block/" + textureName );
    }
    
    public static ResourceLocation itemTexture( String textureName ) {
        return rl( "item/" + textureName );
    }
    
    public static ResourceLocation blockTextureWith( Block block, String suffix ) {
        ResourceLocation name = regKey( block );
        return ResourceLocation.fromNamespaceAndPath( name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/" + name.getPath() + "_" + suffix );
    }
    
    public static ResourceLocation regKey( Block block ) {
        return ForgeRegistries.BLOCKS.getKey( block );
    }
}
