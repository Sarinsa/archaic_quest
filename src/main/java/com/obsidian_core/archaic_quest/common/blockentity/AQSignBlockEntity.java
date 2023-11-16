package com.obsidian_core.archaic_quest.common.blockentity;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.obsidian_core.archaic_quest.common.core.register.AQBlockEntities;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.*;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;
import java.util.function.Function;

public class AQSignBlockEntity extends SignBlockEntity {

    /*
    public static final int LINES = 4;
    private static final String[] RAW_TEXT_FIELD_NAMES = new String[]{"Text1", "Text2", "Text3", "Text4"};
    private static final String[] FILTERED_TEXT_FIELD_NAMES = new String[]{"FilteredText1", "FilteredText2", "FilteredText3", "FilteredText4"};

    private final Component[] messages = new Component[]{CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY};
    private final Component[] filteredMessages = new Component[]{CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY, CommonComponents.EMPTY};
    private boolean isEditable = true;
    @Nullable
    private UUID playerWhoMayEdit;
    @Nullable
    private FormattedCharSequence[] renderMessages;
    private boolean renderMessagedFiltered;
    private DyeColor color = DyeColor.BLACK;
    private boolean hasGlowingText;


     */
    public AQSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return AQBlockEntities.AQ_SIGN.get();
    }

    /*
    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);

        for (int lines = 0; lines < 4; ++lines) {
            Component message = messages[lines];
            String s = Component.Serializer.toJson(message);
            compoundTag.putString(RAW_TEXT_FIELD_NAMES[lines], s);
            Component filteredMessage = filteredMessages[lines];

            if (!filteredMessage.equals(message)) {
                compoundTag.putString(FILTERED_TEXT_FIELD_NAMES[lines], Component.Serializer.toJson(filteredMessage));
            }
        }

        compoundTag.putString("Color", color.getName());
        compoundTag.putBoolean("GlowingText", hasGlowingText);
    }

    @Override
    public void load(CompoundTag compoundTag) {
        isEditable = false;
        super.load(compoundTag);
        color = DyeColor.byName(compoundTag.getString("Color"), DyeColor.BLACK);

        for(int lines = 0; lines < 4; ++lines) {
            String s = compoundTag.getString(RAW_TEXT_FIELD_NAMES[lines]);
            Component message = loadLine(s);
            messages[lines] = message;
            String s1 = FILTERED_TEXT_FIELD_NAMES[lines];

            if (compoundTag.contains(s1, 8)) {
                filteredMessages[lines] = loadLine(compoundTag.getString(s1));
            }
            else {
                filteredMessages[lines] = message;
            }
        }

        renderMessages = null;
        hasGlowingText = compoundTag.getBoolean("GlowingText");
    }

    private Component loadLine(String s) {
        Component component = deserializeTextSafe(s);

        if (level instanceof ServerLevel) {
            try {
                return ComponentUtils.updateForEntity(createCommandSourceStack(null), component, null, 0);
            }
            catch (CommandSyntaxException ignored) { }
        }

        return component;
    }

    private Component deserializeTextSafe(String s) {
        try {
            Component component = Component.Serializer.fromJson(s);

            if (component != null) {
                return component;
            }
        }
        catch (Exception ignored) { }

        return CommonComponents.EMPTY;
    }

    public Component getMessage(int line, boolean filtered) {
        return getMessages(filtered)[line];
    }

    public void setMessage(int line, Component message) {
        setMessage(line, message, message);
    }

    public void setMessage(int line, Component message, Component filteredMessage) {
        messages[line] = message;
        filteredMessages[line] = filteredMessage;
        renderMessages = null;
    }

    public FormattedCharSequence[] getRenderMessages(boolean renderFiltered, Function<Component, FormattedCharSequence> func) {
        if (renderMessages == null || renderMessagedFiltered != renderFiltered) {
            renderMessagedFiltered = renderFiltered;
            renderMessages = new FormattedCharSequence[4];

            for(int line = 0; line < 4; ++line) {
                renderMessages[line] = func.apply(getMessage(line, renderFiltered));
            }
        }

        return renderMessages;
    }

    private Component[] getMessages(boolean filtered) {
        return filtered ? filteredMessages : messages;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public boolean onlyOpCanSetNbt() {
        return true;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean editable) {
        isEditable = editable;

        if (!editable) {
            playerWhoMayEdit = null;
        }
    }

    public void setAllowedPlayerEditor(UUID uuid) {
        playerWhoMayEdit = uuid;
    }

    @Nullable
    public UUID getPlayerWhoMayEdit() {
        return playerWhoMayEdit;
    }

    public boolean executeClickCommands(ServerPlayer serverPlayer) {
        for(Component component : getMessages(serverPlayer.isTextFilteringEnabled())) {
            Style style = component.getStyle();
            ClickEvent clickEvent = style.getClickEvent();

            if (clickEvent != null && clickEvent.getAction() == ClickEvent.Action.RUN_COMMAND) {
                serverPlayer.getServer().getCommands().performPrefixedCommand(this.createCommandSourceStack(serverPlayer), clickEvent.getValue());
            }
        }
        return true;
    }

    public CommandSourceStack createCommandSourceStack(@Nullable ServerPlayer serverPlayer) {
        String s = serverPlayer == null ? "Sign" : serverPlayer.getName().getString();
        Component component = serverPlayer == null ? Component.literal("Sign") : serverPlayer.getDisplayName();
        return new CommandSourceStack(CommandSource.NULL, Vec3.atCenterOf(this.worldPosition), Vec2.ZERO, (ServerLevel)level, 2, s, component, level.getServer(), serverPlayer);
    }

    public DyeColor getColor() {
        return color;
    }

    public boolean setColor(DyeColor dyeColor) {
        if (dyeColor != getColor()) {
            color = dyeColor;
            markUpdated();
            return true;
        }
        else {
            return false;
        }
    }

    public boolean hasGlowingText() {
        return hasGlowingText;
    }

    public boolean setHasGlowingText(boolean glowingText) {
        if (hasGlowingText != glowingText) {
            hasGlowingText = glowingText;
            markUpdated();
            return true;
        }
        else {
            return false;
        }
    }

    private void markUpdated() {
        setChanged();
        level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
    }

     */
}
