package com.kielson.cardinal_components;

import net.minecraft.util.Identifier;
import org.ladysnake.cca.api.v3.component.ComponentKey;
import org.ladysnake.cca.api.v3.component.ComponentRegistry;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry;
import org.ladysnake.cca.api.v3.scoreboard.ScoreboardComponentInitializer;

import static com.kielson.Serwerek.MOD_ID;

public final class ModComponents implements ScoreboardComponentInitializer {
    public static final ComponentKey<ItemsCraftedComponent> ITEMS_CRAFTED =
            ComponentRegistry.getOrCreate(Identifier.of(MOD_ID, "items_crafted"), ItemsCraftedComponent.class);

    @Override
    public void registerScoreboardComponentFactories(ScoreboardComponentFactoryRegistry registry) {
        registry.registerScoreboardComponent(ITEMS_CRAFTED, ItemsCraftedComponent::new);
    }
}
