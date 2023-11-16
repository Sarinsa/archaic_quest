package com.obsidian_core.archaic_quest.common.item;

import com.obsidian_core.archaic_quest.common.entity.AQBoat;
import com.obsidian_core.archaic_quest.common.entity.AQChestBoat;
import com.obsidian_core.archaic_quest.common.entity.IModBoat;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class AQBoatItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    private final AQBoat.BoatType type;
    private final boolean hasChest;

    public AQBoatItem(boolean hasChest, AQBoat.BoatType type, Item.Properties properties) {
        super(properties);
        this.hasChest = hasChest;
        this.type = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        HitResult hitResult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.ANY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(heldItem);
        }
        else {
            Vec3 lookVec = player.getViewVector(1.0F);
            List<Entity> nearbyEntities = level.getEntities(player, player.getBoundingBox().expandTowards(lookVec.scale(5.0D)).inflate(1.0D), ENTITY_PREDICATE);

            if (!nearbyEntities.isEmpty()) {
                Vec3 eyePos = player.getEyePosition();

                for(Entity entity : nearbyEntities) {
                    AABB aabb = entity.getBoundingBox().inflate(entity.getPickRadius());
                    if (aabb.contains(eyePos)) {
                        return InteractionResultHolder.pass(heldItem);
                    }
                }
            }

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                Boat boat = getBoat(level, hitResult);
                ((IModBoat) boat).setBoatType(type);
                boat.setYRot(player.getYRot());

                if (!level.noCollision(boat, boat.getBoundingBox())) {
                    return InteractionResultHolder.fail(heldItem);
                }
                else {
                    if (!level.isClientSide) {
                        level.addFreshEntity(boat);
                        level.gameEvent(player, GameEvent.ENTITY_PLACE, hitResult.getLocation());

                        if (!player.getAbilities().instabuild) {
                            heldItem.shrink(1);
                        }
                    }
                    player.awardStat(Stats.ITEM_USED.get(this));
                    return InteractionResultHolder.sidedSuccess(heldItem, level.isClientSide());
                }
            }
            else {
                return InteractionResultHolder.pass(heldItem);
            }
        }
    }

    private Boat getBoat(Level level, HitResult hitResult) {
        return hasChest ? new AQChestBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z) : new AQBoat(level, hitResult.getLocation().x, hitResult.getLocation().y, hitResult.getLocation().z);
    }
}
