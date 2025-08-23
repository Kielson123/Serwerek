package com.kielson.datagen;

import com.kielson.item.ModItems;
import com.kielson.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.MOD_ITEMS)
                .add(ModItems.MOD_ITEMS_CUSTOM)
                .add(ModItems.MOD_ITEMS_HANDHELD)
                .add(ModItems.MOD_ITEMS_GENERIC);

        valueLookupBuilder(ModTags.ECHO_SHARD).add(Items.ECHO_SHARD);
        valueLookupBuilder(ModTags.RESIN).add(Items.RESIN_BRICK);
        valueLookupBuilder(ModTags.ENDER_EYE).add(Items.ENDER_EYE);
        valueLookupBuilder(ModTags.AMETHYST).add(Items.AMETHYST_CLUSTER);
        valueLookupBuilder(ModTags.BONES).add(ModItems.WITHER_BONE).add(Items.BONE);

        addEnchantabilityTags();
    }

    private void addEnchantabilityTags(){
        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.SHATTERED_MIRROR)
                .add(ModItems.ECHO_SCYTHE)
                .add(ModItems.EMERALD_FISHING_ROD)
                .add(ModItems.OBSIDIAN_BOW)
                .setReplace(false);

        valueLookupBuilder(ItemTags.WEAPON_ENCHANTABLE)
                .add(ModItems.ECHO_SCYTHE)
                .setReplace(false);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.ENDER_SWORD)
                .setReplace(false);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.AMETHYST_AXE)
                .setReplace(false);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.RESIN_PICKAXE)
                .setReplace(false);

        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.OBSIDIAN_BOW)
                .setReplace(false);
    }
}
