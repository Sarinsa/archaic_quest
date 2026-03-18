package com.obsidian_core.archaic_quest.common.worldgen.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.obsidian_core.archaic_quest.common.core.register.AQStructureTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import javax.annotation.Nonnull;
import java.util.Optional;


// TODO - More configurable properties
public class GeneralStructureType extends Structure {
    
    public static final Codec<GeneralStructureType> CODEC = RecordCodecBuilder.<GeneralStructureType>mapCodec( instance ->
            instance.group( GeneralStructureType.settingsCodec( instance ),
                    StructureTemplatePool.CODEC.fieldOf( "start_pool" ).forGetter( structure -> structure.startPool ),
                    ResourceLocation.CODEC.optionalFieldOf( "start_jigsaw_name" ).forGetter( structure -> structure.startJigsawName ),
                    Codec.intRange( 0, 30 ).fieldOf( "size" ).forGetter( structure -> structure.size ),
                    HeightProvider.CODEC.fieldOf( "start_height" ).forGetter( structure -> structure.startHeight ),
                    Heightmap.Types.CODEC.optionalFieldOf( "project_start_to_heightmap" ).forGetter( structure -> structure.projectStartToHeightmap ),
                    Codec.intRange( 1, 128 ).fieldOf( "max_distance_from_center" ).forGetter( structure -> structure.maxDistanceFromCenter )
            ).apply( instance, GeneralStructureType::new ) ).codec();
    
    
    private final Holder<StructureTemplatePool> startPool;
    private final Optional<ResourceLocation> startJigsawName;
    private final int size;
    private final HeightProvider startHeight;
    private final Optional<Heightmap.Types> projectStartToHeightmap;
    private final int maxDistanceFromCenter;
    
    
    public GeneralStructureType( Structure.StructureSettings config, Holder<StructureTemplatePool> startPool, Optional<ResourceLocation> startJigsawName, int size, HeightProvider startHeight, Optional<Heightmap.Types> projectStartToHeightmap, int maxDistanceFromCenter ) {
        super( config );
        this.startPool = startPool;
        this.startJigsawName = startJigsawName;
        this.size = size;
        this.startHeight = startHeight;
        this.projectStartToHeightmap = projectStartToHeightmap;
        this.maxDistanceFromCenter = maxDistanceFromCenter;
    }
    
    @Override
    @Nonnull
    public Optional<Structure.GenerationStub> findGenerationPoint( Structure.GenerationContext context ) {
        int startY = 0;
        
        // Turns the chunk coordinates into actual coordinates we can use. (Gets corner of that chunk)
        ChunkPos chunkPos = context.chunkPos();
        BlockPos blockPos = new BlockPos( chunkPos.getMinBlockX(), startY, chunkPos.getMinBlockZ() );
        
        return JigsawPlacement.addPieces(
                context,
                startPool,
                startJigsawName,
                size,
                blockPos,
                false,
                projectStartToHeightmap,
                maxDistanceFromCenter );
    }
    
    @Override
    @Nonnull
    public StructureType<?> type() {
        return AQStructureTypes.GENERAL.get();
    }
}
