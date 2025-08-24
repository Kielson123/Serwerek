package com.kielson.datagen;

import com.kielson.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.client.data.Models;
import net.minecraft.item.Item;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        for (Item item : ModItems.MOD_ITEMS_HANDHELD) {
            itemModelGenerator.register(item, Models.HANDHELD);
        }
        for (Item item : ModItems.MOD_ITEMS_GENERIC) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
        for (Item item : ModItems.GENERIC) {
            itemModelGenerator.register(item, Models.GENERATED);
        }
    }
}
