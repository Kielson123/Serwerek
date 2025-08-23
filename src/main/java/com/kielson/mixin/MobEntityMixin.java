package com.kielson.mixin;

import com.kielson.item.ModItems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.tag.EntityTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MobEntity.class)
public class MobEntityMixin {
    @Unique private LivingEntity livingEntity = (LivingEntity) (Object) this;

    @Inject(method = "setTarget", at = @At("HEAD"), cancellable = true)
    private void makeUndeadNeutral(LivingEntity target, CallbackInfo ci){
        if(target instanceof PlayerEntity player){
            if(livingEntity.getType().isIn(EntityTypeTags.UNDEAD) && player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.CURSED_BONE_CROWN)){
                ci.cancel();
            }
        }
    }
}
