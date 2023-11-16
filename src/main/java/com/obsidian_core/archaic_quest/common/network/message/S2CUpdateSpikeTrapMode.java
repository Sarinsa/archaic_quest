package com.obsidian_core.archaic_quest.common.network.message;

import com.obsidian_core.archaic_quest.common.network.work.ClientWork;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class S2CUpdateSpikeTrapMode {

    public BlockPos pos;
    public int mode;

    public S2CUpdateSpikeTrapMode(BlockPos trapPos, int mode) {
        this.pos = trapPos;
        this.mode = mode;
    }

    public static void handle(S2CUpdateSpikeTrapMode message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();

        if (context.getDirection().getReceptionSide().isClient()) {
            context.enqueueWork(() -> ClientWork.handleUpdateSpikeTrap(message));
        }
        context.setPacketHandled(true);
    }

    public static void encode(S2CUpdateSpikeTrapMode message, FriendlyByteBuf byteBuf) {
        byteBuf.writeBlockPos(message.pos);
        byteBuf.writeInt(message.mode);
    }

    public static S2CUpdateSpikeTrapMode decode(FriendlyByteBuf byteBuf) {
        return new S2CUpdateSpikeTrapMode(byteBuf.readBlockPos(), byteBuf.readInt());
    }
}
