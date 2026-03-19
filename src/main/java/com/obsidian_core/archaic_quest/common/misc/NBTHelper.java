package com.obsidian_core.archaic_quest.common.misc;

import com.google.common.collect.ImmutableMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Map;
import java.util.Objects;

public class NBTHelper {
    
    /**
     * Modified copy-paste of {@link net.minecraft.nbt.NbtUtils#writeBlockState(BlockState)}.
     * Uses the Forge block registry for lookup and does not require level access.
     */
    public static CompoundTag writeBlockState( BlockState state ) {
        CompoundTag blockTag = new CompoundTag();
        blockTag.putString( "Name", Objects.requireNonNull( ForgeRegistries.BLOCKS.getKey( state.getBlock() ) ).toString() );
        ImmutableMap<Property<?>, Comparable<?>> properties = state.getValues();
        
        if( !properties.isEmpty() ) {
            CompoundTag propertyTag = new CompoundTag();
            
            for( Map.Entry<Property<?>, Comparable<?>> entry : properties.entrySet() ) {
                Property<?> property = entry.getKey();
                propertyTag.putString( property.getName(), getName( property, entry.getValue() ) );
            }
            blockTag.put( "Properties", propertyTag );
        }
        return blockTag;
    }
    
    /** Helper method for getting around some annoying type parameter shenanigans. */
    @SuppressWarnings( "unchecked" )
    private static <T extends Comparable<T>> String getName( Property<T> property, Comparable<?> comparable ) {
        return property.getName( (T) comparable );
    }
    
    /**
     * Fetches and returns the compound tag in the parent tag with the
     * specified name, if it exists. Otherwise, a new compound tag is created,
     * put in the parent tag and returned.
     */
    public static CompoundTag getOrCreate( CompoundTag parentTag, String tagName ) {
        if( !parentTag.contains( tagName, Tag.TAG_COMPOUND ) ) {
            CompoundTag tag = new CompoundTag();
            parentTag.put( tagName, tag );
            return tag;
        }
        else {
            return parentTag.getCompound( tagName );
        }
    }
}
