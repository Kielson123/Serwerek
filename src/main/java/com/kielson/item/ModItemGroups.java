package com.kielson.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.kielson.Serwerek.MOD_ID;

public class ModItemGroups {

    public static final ItemGroup Serwer = Registry.register(Registries.ITEM_GROUP, Identifier.of(MOD_ID, MOD_ID),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup." + MOD_ID)).icon(() -> new ItemStack(ModItems.ENDER_SWORD)).entries((displayContext, entries) -> {
                for (Item item : ModItems.MOD_ITEMS_HANDHELD) {
                    entries.add(item);
                }
                for (Item item : ModItems.MOD_ITEMS_CUSTOM) {
                    entries.add(item);
                }
                for (Item item : ModItems.MOD_ITEMS_GENERIC) {
                    entries.add(item);
                }
            }).build());

    public static void initialize() {}
}
