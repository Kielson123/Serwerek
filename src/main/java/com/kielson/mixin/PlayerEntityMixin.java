package com.kielson.mixin;

import com.kielson.item.ModItems;
import com.kielson.item.custom.EnderSword;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity{
    @Unique PlayerEntity player = (PlayerEntity) (Object) this;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = {"addExperience"}, at = {@At("HEAD")})
    private void addAdditionalExperience(int experience, CallbackInfo ci) {
        if(player.getMainHandStack().isOf(ModItems.ENDER_SWORD)) experience = (int) (experience * (EnderSword.EXPERIENCE_GAIN * 0.01));
    }
}
