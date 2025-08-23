package com.kielson;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import static com.kielson.Serwerek.MOD_ID;

public class ModTags {
    public static final TagKey<Item> BONES = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "bones"));
    public static final TagKey<Item> AMETHYST = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "amethyst"));
    public static final TagKey<Item> RESIN = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "resin"));
    public static final TagKey<Item> ENDER_EYE = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "ender_eye"));
    public static final TagKey<Item> ECHO_SHARD = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "echo_shard"));
    public static final TagKey<Item> MOD_ITEMS = TagKey.of(RegistryKeys.ITEM, Identifier.of(MOD_ID, "mod_items"));


    public static final TagKey<Enchantment> CURSE = TagKey.of(RegistryKeys.ENCHANTMENT, Identifier.of(MOD_ID, "curse"));

    public static final TagKey<StatusEffect> POSITIVE_EFFECTS = TagKey.of(RegistryKeys.STATUS_EFFECT, Identifier.of(MOD_ID, "positive_effects"));
    public static final TagKey<StatusEffect> NEGATIVE_EFFECTS = TagKey.of(RegistryKeys.STATUS_EFFECT, Identifier.of(MOD_ID, "negative_effects"));

    public static void initialize(){}
}
