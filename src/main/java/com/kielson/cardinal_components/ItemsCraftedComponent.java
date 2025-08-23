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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemsCraftedComponent implements ComponentV3, AutoSyncedComponent {
    public List<String> itemsCrafted = new ArrayList<>();;
    private static final String KEY = "items_crafted";
    public static Scoreboard provider;

    private static final Codec<List<String>> CODEC = Codec.list(Codec.STRING).fieldOf(KEY).codec();

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
        Optional<List<String>> optional = readView.read(KEY, CODEC);
        assert optional.isPresent();
        itemsCrafted = new ArrayList<>(optional.get());
    }

    @Override
    public void writeData(WriteView writeView) {
        writeView.put(KEY, CODEC, itemsCrafted);
    }
}