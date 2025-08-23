package com.kielson.mixin;

import com.kielson.item.ModItems;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMixin {

    @Redirect(method = "updateResult", at = @At(value = "INVOKE", target = "Ljava/lang/Math;min(II)I"))
    private int makeHeartOfNetherRepairable(int a, int b, @Local(ordinal = 1) ItemStack itemStack2){
        if(itemStack2.isOf(ModItems.HEART_OF_THE_NETHER)) return Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage());
        return Math.min(itemStack2.getDamage(), itemStack2.getMaxDamage() / 4);
    }

}
