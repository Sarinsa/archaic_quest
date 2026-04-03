package com.obsidian_core.archaic_quest.common.block.base;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;

public enum ThroneType {
    
    THRONE( "throne" ),
    MOSSY_THRONE( "mossy_throne" );
    
    
    ThroneType( String name ) {
        this.name = name;
        this.texture = ArchaicQuest.rl( "textures/tile/throne/" + name + ".png" );
    }
    
    private final String name;
    private final ResourceLocation texture;
    
    public ResourceLocation getTextureLocation() {
        return texture;
    }
    
    public String getName() {
        return name;
    }
    
    @Nullable
    public static ThroneType getFromName( @Nullable String name ) {
        if( name == null )
            return null;
        
        for( ThroneType type : ThroneType.values() ) {
            if( type.getName().equals( name ) )
                return type;
        }
        return null;
    }
}
