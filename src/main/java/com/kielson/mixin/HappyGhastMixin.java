package com.kielson.mixin;

import net.minecraft.entity.passive.HappyGhastEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HappyGhastEntity.class)
public class HappyGhastMixin {

    @ModifyArg(method = "createHappyGhastAttributes", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;add(Lnet/minecraft/registry/entry/RegistryEntry;D)Lnet/minecraft/entity/attribute/DefaultAttributeContainer$Builder;"), index = 1)
    private static double addSpeedBoost(double baseValue){
        if(baseValue == 0.05) return 0.075;
        return baseValue;
    }
}
