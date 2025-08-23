package com.kielson.mixin;

import com.kielson.item.ModItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(LivingEntity.class)
public abstract class LivinEntityMixin {
    @Unique LivingEntity livingEntity = (LivingEntity) (Object) this;

    @ModifyArg(method = "tryUseDeathProtector", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    private int addTotemOfShadows(int amount, @Local(ordinal = 1) ItemStack stack){
        if(stack.isOf(ModItems.TOTEM_OF_SHADOWS)) return 0;
        return amount;
    }
}
