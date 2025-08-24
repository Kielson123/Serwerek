package com.kielson.mixin;

import com.kielson.cardinal_components.ModComponents;
import com.kielson.ModTags;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.recipe.input.RecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Map;

@Mixin(CraftingScreenHandler.class)
public class CraftingScreenHandlerMixin {

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Lnet/minecraft/recipe/CraftingRecipe;craft(Lnet/minecraft/recipe/input/RecipeInput;Lnet/minecraft/registry/RegistryWrapper$WrapperLookup;)Lnet/minecraft/item/ItemStack;"))
    private static ItemStack customItem(CraftingRecipe instance, RecipeInput recipeInput, RegistryWrapper.WrapperLookup wrapperLookup, @Local CraftingRecipeInput craftingRecipeInput, @Local(argsOnly = true) ServerWorld world){
        ItemStack itemStack = instance.craft(craftingRecipeInput, world.getRegistryManager());
        String itemKey = itemStack.getItem().toString().toLowerCase();
        Scoreboard scoreboard = world.getScoreboard();
        Map<String, String> itemsCrafted = ModComponents.ITEMS_CRAFTED.get(scoreboard).itemsCrafted;

        if(itemStack.isIn(ModTags.MOD_ITEMS) && !itemsCrafted.getOrDefault(itemKey, "").isBlank() && !world.isClient){
            return ItemStack.EMPTY;
        }
        return itemStack;
    }
}
