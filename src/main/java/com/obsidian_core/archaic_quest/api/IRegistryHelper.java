package com.obsidian_core.archaic_quest.api;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Predicate;

public interface IRegistryHelper {
    
    /**
     * Registers a block that can light the Adventurer's Torch,
     * BlockState sensitive.
     *
     * @param block     The block that is able to light the torch.
     * @param predicate The predicate to test if the block can light the torch.
     * @param type      The lit type to apply to the torch. Using {@link TorchLitType#UNLIT}
     *                  effectively makes this an extinguisher.
     */
    void registerTorchLightable( Block block, Predicate<BlockState> predicate, TorchLitType type );
    
    /**
     * Registers a block that can be interacted with using a lit Adventurer's Torch.
     *
     * @param block            The block that is able to light the torch.
     * @param torchInteraction The logic to use when the block is clicked with a lit adventurer's torch.
     */
    void registerTorchInteraction( Block block, TorchInteraction torchInteraction );
}
