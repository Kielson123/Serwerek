package com.kielson.item;

import com.kielson.item.custom.*;
import com.kielson.util.ItemHelper;
import net.minecraft.item.Item;
import net.minecraft.util.Rarity;

import static com.kielson.Serwerek.MOD_ID;

public class ModItems {

    public static final Item WITHER_BONE = ItemHelper.registerItem(MOD_ID, "wither_bone", Item::new, new Item.Settings());
    public static final Item WARDEN_SOUL = ItemHelper.registerItem(MOD_ID, "warden_soul", Item::new, new Item.Settings().rarity(Rarity.RARE));


    public static final Item ENDER_SWORD = ItemHelper.registerItem(MOD_ID, "ender_sword", EnderSword::new, new Item.Settings());
    public static final Item RESIN_PICKAXE = ItemHelper.registerItem(MOD_ID, "resin_pickaxe", ResinPickaxe::new, new Item.Settings());
    public static final Item ECHO_SCYTHE = ItemHelper.registerItem(MOD_ID, "echo_scythe", EchoScythe::new, new Item.Settings());
    public static final Item CURSED_BONE_CROWN = ItemHelper.registerItem(MOD_ID, "cursed_bone_crown", CursedBoneCrown::new, new Item.Settings());
    public static final Item SHATTERED_MIRROR = ItemHelper.registerItem(MOD_ID, "shattered_mirror", ShatteredMirror::new, new Item.Settings());
    public static final Item AMETHYST_AXE = ItemHelper.registerItem(MOD_ID, "amethyst_axe", AmethystAxe::new, new Item.Settings());
    public static final Item EMERALD_FISHING_ROD = ItemHelper.registerItem(MOD_ID, "emerald_fishing_rod", EmeraldFishingRod::new, new Item.Settings());
    public static final Item OBSIDIAN_BOW = ItemHelper.registerItem(MOD_ID, "obsidian_bow", ObsidianBow::new, new Item.Settings());
    public static final Item TOTEM_OF_SHADOWS = ItemHelper.registerItem(MOD_ID, "totem_of_shadows", TotemOfShadows::new, new Item.Settings());
    public static final Item HEART_OF_THE_NETHER = ItemHelper.registerItem(MOD_ID, "heart_of_the_nether", HeartOfNether::new, new Item.Settings());

    //shield - thorns
    //trident - fire
    //goat horn - sonic boom
    //crossbow - load multiple arrows, and fire them all at the same time
    //chestplate - when low HP - gives blindness, weakness, and slowness to nearby entities - long cooldown

    public static final Item[] MOD_ITEMS_HANDHELD = new Item[]{
            ENDER_SWORD, RESIN_PICKAXE, ECHO_SCYTHE, AMETHYST_AXE
    };
    public static final Item[] MOD_ITEMS_CUSTOM = new Item[]{
            CURSED_BONE_CROWN, EMERALD_FISHING_ROD, OBSIDIAN_BOW, HEART_OF_THE_NETHER
    };
    public static final Item[] MOD_ITEMS_GENERIC = new Item[]{
            SHATTERED_MIRROR, TOTEM_OF_SHADOWS
    };
    public static final Item[] GENERIC = new Item[]{
            WITHER_BONE, WARDEN_SOUL
    };

    public static void initialize(){}
}
