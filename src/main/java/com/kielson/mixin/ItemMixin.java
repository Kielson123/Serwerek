package com.kielson.mixin;

import com.kielson.cardinal_components.ModComponents;
import com.kielson.item.ModItems;
import com.kielson.ModTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(Item.class)
public abstract class ItemMixin {

    @Inject(method = "onCraftByPlayer", at = @At("HEAD"))
    private void enableOneRecipe(ItemStack stack, PlayerEntity player, CallbackInfo ci){
        String itemKey = stack.getItem().toString().toLowerCase();
        Scoreboard scoreboard = player.getWorld().getScoreboard();
        var component = ModComponents.ITEMS_CRAFTED.getNullable(scoreboard);
        assert component != null;
        Map<String, String> newMap = new HashMap<>(component.itemsCrafted);
        if(stack.isIn(ModTags.MOD_ITEMS)) newMap.put(itemKey, player.getUuidAsString());
        component.itemsCrafted = newMap;

        if(stack.isOf(ModItems.CURSED_BONE_CROWN)){
            RegistryEntry<Enchantment> enchantment = player.getWorld().getRegistryManager()
                    .getOrThrow(RegistryKeys.ENCHANTMENT)
                    .getOrThrow(Enchantments.BINDING_CURSE);

            stack.addEnchantment(enchantment, 1);
        }
    }

    @Inject(method = "onCraft", at = @At("HEAD"), cancellable = true)
    private void disableAutomaticRecipes(ItemStack stack, World world, CallbackInfo ci){
        if(stack.isIn(ModTags.MOD_ITEMS)) ci.cancel();
    }

    @Inject(method = "inventoryTick", at = @At("HEAD"))
    private void tick(ItemStack stack, ServerWorld world, Entity entity, EquipmentSlot slot, CallbackInfo ci){
        if(!EnchantmentHelper.hasAnyEnchantmentsIn(stack, ModTags.CURSE) && stack.isOf(ModItems.CURSED_BONE_CROWN)){
            RegistryEntry<Enchantment> enchantment = world.getRegistryManager()
                    .getOrThrow(RegistryKeys.ENCHANTMENT)
                    .getOrThrow(Enchantments.BINDING_CURSE);

            stack.addEnchantment(enchantment, 1);
        }
    }
}
