package com.kielson.datagen;

import com.kielson.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup registryLookup, RecipeExporter exporter) {
        return new RecipeGenerator(registryLookup, exporter) {
            @Override
            public void generate() {
                RegistryWrapper.Impl<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

                createShaped(RecipeCategory.COMBAT, ModItems.ENDER_SWORD)
                        .pattern("b")
                        .pattern("b")
                        .pattern("h")
                        .input('b', Items.ENDER_EYE)
                        .input('h', Items.BREEZE_ROD)
                        .criterion(hasItem(Items.ENDER_EYE), conditionsFromItem(Items.ENDER_EYE))
                        .criterion(hasItem(Items.BREEZE_ROD), conditionsFromItem(Items.BREEZE_ROD))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.RESIN_PICKAXE)
                        .pattern("bdb")
                        .pattern(" h ")
                        .pattern(" h ")
                        .input('b', Items.RESIN_BRICK)
                        .input('d', Items.NETHERITE_SCRAP)
                        .input('h', Items.STICK)
                        .criterion(hasItem(Items.RESIN_BRICK), conditionsFromItem(Items.RESIN_BRICK))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.ECHO_SCYTHE)
                        .pattern("esw")
                        .pattern(" h ")
                        .pattern("h  ")
                        .input('e', Items.ECHO_SHARD)
                        .input('w', Items.WITHER_SKELETON_SKULL)
                        .input('h', Items.STICK)
                        .input('s', ModItems.WARDEN_SOUL)
                        .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                        .criterion(hasItem(ModItems.WARDEN_SOUL), conditionsFromItem(ModItems.WARDEN_SOUL))
                        .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                        .criterion(hasItem(Items.STICK), conditionsFromItem(Items.STICK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.CURSED_BONE_CROWN)
                        .pattern("bwb")
                        .pattern("beb")
                        .input('b', Items.BONE_BLOCK)
                        .input('w', Items.WITHER_ROSE)
                        .input('e', Items.EMERALD_BLOCK)
                        .criterion(hasItem(Items.BONE_BLOCK), conditionsFromItem(Items.BONE_BLOCK))
                        .criterion(hasItem(Items.WITHER_ROSE), conditionsFromItem(Items.WITHER_ROSE))
                        .criterion(hasItem(Items.EMERALD_BLOCK), conditionsFromItem(Items.EMERALD_BLOCK))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.SHATTERED_MIRROR)
                        .pattern("bgb")
                        .pattern("gag")
                        .pattern("bgb")
                        .input('g', Items.GOLD_BLOCK)
                        .input('b', Items.GILDED_BLACKSTONE)
                        .input('a', Items.TINTED_GLASS)
                        .criterion(hasItem(Items.GOLD_BLOCK), conditionsFromItem(Items.GOLD_BLOCK))
                        .criterion(hasItem(Items.GILDED_BLACKSTONE), conditionsFromItem(Items.GILDED_BLACKSTONE))
                        .criterion(hasItem(Items.TINTED_GLASS), conditionsFromItem(Items.TINTED_GLASS))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.AMETHYST_AXE)
                        .pattern("ca")
                        .pattern("aw")
                        .pattern(" w")
                        .input('c', Items.AMETHYST_CLUSTER)
                        .input('a', Items.AMETHYST_SHARD)
                        .input('w', ModItems.WITHER_BONE)
                        .criterion(hasItem(Items.AMETHYST_CLUSTER), conditionsFromItem(Items.AMETHYST_CLUSTER))
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .criterion(hasItem(ModItems.WITHER_BONE), conditionsFromItem(ModItems.WITHER_BONE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.TOOLS, ModItems.EMERALD_FISHING_ROD)
                        .pattern("  e")
                        .pattern(" eb")
                        .pattern("e h")
                        .input('e', Items.EMERALD_BLOCK)
                        .input('b', Items.BLAZE_POWDER)
                        .input('h', Items.HONEYCOMB)
                        .criterion(hasItem(Items.EMERALD_BLOCK), conditionsFromItem(Items.EMERALD_BLOCK))
                        .criterion(hasItem(Items.BLAZE_POWDER), conditionsFromItem(Items.BLAZE_POWDER))
                        .criterion(hasItem(Items.HONEYCOMB), conditionsFromItem(Items.HONEYCOMB))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.OBSIDIAN_BOW)
                        .pattern(" ow")
                        .pattern("c w")
                        .pattern(" ow")
                        .input('c', Items.CRYING_OBSIDIAN)
                        .input('o', Items.OBSIDIAN)
                        .input('w', ModItems.WITHER_BONE)
                        .criterion(hasItem(Items.CRYING_OBSIDIAN), conditionsFromItem(Items.CRYING_OBSIDIAN))
                        .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                        .criterion(hasItem(ModItems.WITHER_BONE), conditionsFromItem(ModItems.WITHER_BONE))
                        .offerTo(exporter);

                createShaped(RecipeCategory.COMBAT, ModItems.TOTEM_OF_SHADOWS)
                        .pattern(" a ")
                        .pattern(" s ")
                        .pattern(" d ")
                        .input('a', Items.AMETHYST_SHARD)
                        .input('d', Items.DEEPSLATE)
                        .input('s', ModItems.WARDEN_SOUL)
                        .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                        .criterion(hasItem(Items.DEEPSLATE), conditionsFromItem(Items.DEEPSLATE))
                        .criterion(hasItem(ModItems.WARDEN_SOUL), conditionsFromItem(ModItems.WARDEN_SOUL))
                        .offerTo(exporter);

                createShaped(RecipeCategory.MISC, ModItems.HEART_OF_THE_NETHER)
                        .pattern("sw")
                        .pattern("ws")
                        .input('s', Items.NETHER_STAR)
                        .input('w', Items.WITHER_SKELETON_SKULL)
                        .criterion(hasItem(Items.NETHER_STAR), conditionsFromItem(Items.NETHER_STAR))
                        .criterion(hasItem(Items.WITHER_SKELETON_SKULL), conditionsFromItem(Items.WITHER_SKELETON_SKULL))
                        .offerTo(exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "";
    }
}
