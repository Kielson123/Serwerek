package com.kielson.cardinal_components;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import org.jetbrains.annotations.Nullable;
import org.ladysnake.cca.api.v3.component.ComponentV3;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;

import java.util.*;

public class ItemsCraftedComponent implements ComponentV3, AutoSyncedComponent {
    public Map<String, String> itemsCrafted = new HashMap<>();
    private static final String KEY = "items_crafted";
    public static Scoreboard provider;

    private static final Codec<Map<String, String>> CODEC = Codec.unboundedMap(Codec.STRING, Codec.STRING);

    public ItemsCraftedComponent(Scoreboard provider, @Nullable MinecraftServer server) {
        ItemsCraftedComponent.provider = provider;
    }

    @Override
    public void writeSyncPacket(RegistryByteBuf buf, ServerPlayerEntity recipient) {
        AutoSyncedComponent.super.writeSyncPacket(buf, recipient);
    }

    @Override
    public void applySyncPacket(RegistryByteBuf buf) {
        AutoSyncedComponent.super.applySyncPacket(buf);
    }

    @Override
    public void readData(ReadView readView) {
        Optional<Map<String, String>> optional = readView.read(KEY, CODEC);
        assert optional.isPresent();
        itemsCrafted = new HashMap<>(optional.get());
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.put(KEY, CODEC, itemsCrafted);
    }
}