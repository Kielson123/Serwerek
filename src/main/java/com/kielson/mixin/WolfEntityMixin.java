package com.kielson.mixin;

import com.kielson.item.ModItems;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {

    @Redirect(method = "interactMob", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean addWitherBone(ItemStack instance, Item item){
        return instance.isOf(Items.BONE) || instance.isOf(ModItems.WITHER_BONE);
    }
}
