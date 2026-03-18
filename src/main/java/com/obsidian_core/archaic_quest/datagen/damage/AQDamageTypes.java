package com.obsidian_core.archaic_quest.datagen.damage;

import com.obsidian_core.archaic_quest.common.core.ArchaicQuest;
import com.obsidian_core.archaic_quest.common.entity.projectile.BaseDartEntity;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class AQDamageTypes {
    
    public static final ResourceKey<DamageType> SPEAR_TRAP = create( "spear_trap" );
    public static final ResourceKey<DamageType> SPIKE_TRAP = create( "spike_trap" );
    public static final ResourceKey<DamageType> DART = create( "dart" );
    
    
    //
    // --------------------------- Convenience ---------------------------
    //
    public static DamageSource of( Level level, ResourceKey<DamageType> key ) {
        return new DamageSource( level.registryAccess().registryOrThrow( Registries.DAMAGE_TYPE ).getHolderOrThrow( key ) );
    }
    
    public static DamageSource dart( Level level, BaseDartEntity dart, @Nullable Entity shooter ) {
        Holder<DamageType> type = level.registryAccess().registryOrThrow( Registries.DAMAGE_TYPE ).getHolderOrThrow( DART );
        return new DamageSource( type, dart, shooter );
    }
    
    
    //
    // ---------------------------- Data gen ----------------------------
    //
    public static void bootstrap( BootstapContext<DamageType> context ) {
        register( context, SPEAR_TRAP, new DamageType( msg( "spear_trap" ), 0.0F ) );
        register( context, SPIKE_TRAP, new DamageType( msg( "spike_trap" ), 0.0F ) );
        register( context, DART, new DamageType( msg( "dart" ), 0.0F ) );
    }
    
    protected static void register( BootstapContext<DamageType> context, ResourceKey<DamageType> damageTypeKey, DamageType damageType ) {
        context.register( damageTypeKey, damageType );
    }
    
    public static String msg( String name ) {
        return ArchaicQuest.MODID + "." + name;
    }
    
    /** @return A damage type key with the given name. */
    private static ResourceKey<DamageType> create( String name ) {
        return ResourceKey.create( Registries.DAMAGE_TYPE, ArchaicQuest.rl( name ) );
    }
}
