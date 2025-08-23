package com.kielson.client;

import com.kielson.event.ModItemTooltips;
import net.fabricmc.api.ClientModInitializer;

public class SerwerekClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModItemTooltips.addItemTooltips();
    }
}
