package com.obsidian_core.archaic_quest.api;

/** Represents the lit state of an Adventurer's Torch item. */
public enum TorchLitType {
    UNLIT,
    NORMAL,
    SOULFIRE;
    
    /**
     * @return The TorchLitType corresponding to the specified ordinal,
     * or {@link TorchLitType#UNLIT} if the ordinal value is invalid.
     */
    public static TorchLitType fromOrdinal( int ordinal ) {
        return ordinal < 0 || ordinal > (values().length - 1) ? UNLIT : values()[ordinal];
    }
}
