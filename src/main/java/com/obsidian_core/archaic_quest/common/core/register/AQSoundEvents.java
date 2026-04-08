package com.obsidian_core.archaic_quest.common.core.register;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AQSoundEvents {
    
    public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create( ForgeRegistries.SOUND_EVENTS, ArchaicQuest.MODID );
    
    public static final RegistryObject<SoundEvent> DEATH_WHISTLE_SHRIEK = registerSound( "death_whistle_shriek" );
    public static final RegistryObject<SoundEvent> AZTEC_DOOR_OPENING = registerSound( "aztec_door_opening" );
    public static final RegistryObject<SoundEvent> AZTEC_DOOR_CLOSING = registerSound( "aztec_door_closing" );
    public static final RegistryObject<SoundEvent> AZTEC_DUNGEON_CHEST_OPEN = registerSound( "aztec_dungeon_chest_open" );
    public static final RegistryObject<SoundEvent> AZTEC_DUNGEON_CHEST_CLOSE = registerSound( "aztec_dungeon_chest_close" );
    public static final RegistryObject<SoundEvent> VASE_BREAK = registerSound( "vase_break" );
    public static final RegistryObject<SoundEvent> POISON_TRAP_ACTIVATE = registerSound( "poison_trap_activate" );
    
    /** The vanilla default sound range. */
    private static final float DEFAULT_RANGE = 16.0F;
    
    
    private static RegistryObject<SoundEvent> registerSound( String name ) {
        return REGISTRY.register( name, () -> SoundEvent.createFixedRangeEvent( ArchaicQuest.rl( name ), DEFAULT_RANGE ) );
    }
}
