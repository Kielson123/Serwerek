package com.kielson.mixin;

import com.kielson.item.ModItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootWorldContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.List;

@Mixin(FishingBobberEntity.class)
public class FishingBobberEntityMixin {

    @Redirect(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 0))
    private boolean addEmeraldFishingRodMainHand(ItemStack instance, Item item){
        return instance.isOf(ModItems.EMERALD_FISHING_ROD) || instance.isOf(Items.FISHING_ROD);
    }

    @Redirect(method = "removeIfInvalid", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z", ordinal = 1))
    private boolean addEmeraldFishingRodOffHand(ItemStack instance, Item item){
        return instance.isOf(ModItems.EMERALD_FISHING_ROD) || instance.isOf(Items.FISHING_ROD);
    }

    @Redirect(method = "use", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private Iterator<ItemStack> addMultipleLoots(List<ItemStack> list, @Local(argsOnly = true) ItemStack stack, @Local LootWorldContext lootWorldContext, @Local LootTable lootTable){
        if(stack.isOf(ModItems.EMERALD_FISHING_ROD)) {
            list.addAll(lootTable.generateLoot(lootWorldContext));
            list.addAll(lootTable.generateLoot(lootWorldContext));
            list.addAll(lootTable.generateLoot(lootWorldContext));
            list.addAll(lootTable.generateLoot(lootWorldContext));
        }
        return list.iterator();
    }
}
