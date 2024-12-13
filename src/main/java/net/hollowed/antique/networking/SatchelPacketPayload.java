package net.hollowed.antique.networking;

import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.hollowed.antique.Antiquities;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;

public record SatchelPacketPayload(boolean bool) implements CustomPayload {

    public static final CustomPayload.Id<SatchelPacketPayload> ID = new CustomPayload.Id<>(Antiquities.id("satchel_packet"));

    public static final PacketCodec<RegistryByteBuf, SatchelPacketPayload> CODEC = PacketCodec.of(SatchelPacketPayload::write, SatchelPacketPayload::new);

    public SatchelPacketPayload(RegistryByteBuf buf) {
        this(buf.readBoolean());
    }

    public void write(RegistryByteBuf buf) {
        buf.writeBoolean(bool);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }

}
