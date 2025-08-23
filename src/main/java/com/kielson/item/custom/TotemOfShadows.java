package com.kielson.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.consume.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.List;

import static com.kielson.Serwerek.MOD_ID;

public class TotemOfShadows extends Item {
    private static final int EFFECT_DURATION = 60;
    private static final int ABILITY_COOLDOWN = 300;

    public TotemOfShadows(Settings settings) {
        super(settings
                .component(DataComponentTypes.DEATH_PROTECTION,
                        new DeathProtectionComponent(List.of(
                                new ClearAllEffectsConsumeEffect(),
                                new ApplyEffectsConsumeEffect(List.of(
                                        new StatusEffectInstance(StatusEffects.INVISIBILITY, EFFECT_DURATION * 20, 0, false, false, true),
                                        new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 1, 1, false, false, true),
                                        new StatusEffectInstance(StatusEffects.NIGHT_VISION, EFFECT_DURATION * 20, 0, false, false, true),
                                        new StatusEffectInstance(StatusEffects.SATURATION, 1, 1, false, false, true),
                                        new StatusEffectInstance(StatusEffects.SPEED, EFFECT_DURATION * 20, 1, false, false, true)
                                )),
                                new PlaySoundConsumeEffect(RegistryEntry.of(SoundEvents.ITEM_TOTEM_USE)),
                                new TeleportRandomlyConsumeEffect(24.0F)
                        )))
                .component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.builder()
                        .add(EntityAttributes.SNEAKING_SPEED, new EntityAttributeModifier(Identifier.of(MOD_ID, "totem_of_shadows"), 2.0, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE), AttributeModifierSlot.HAND)
                        .build())
                .maxCount(1)
                .useCooldown(ABILITY_COOLDOWN)
                .rarity(Rarity.EPIC));
    }
}
