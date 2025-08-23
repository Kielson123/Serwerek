package com.kielson.mixin;

import com.kielson.item.ModItems;
import com.kielson.item.custom.CursedBoneCrown;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
abstract class VillagerEntityMixin {
    @Unique private VillagerEntity villager = (VillagerEntity) (Object) this;

    @Inject(method = "prepareOffersFor", at = @At(value = "TAIL"))
    private void applyDiscount(PlayerEntity player, CallbackInfo ci){
        if (player.getEquippedStack(EquipmentSlot.HEAD).isOf(ModItems.CURSED_BONE_CROWN)) {
            for (TradeOffer tradeOffer3 : villager.getOffers()) {
                int offerPrice = (int) Math.floor((CursedBoneCrown.VILLAGER_DISCOUNT * 0.01) * (double)tradeOffer3.getOriginalFirstBuyItem().getCount());
                tradeOffer3.increaseSpecialPrice(-Math.max(offerPrice, 1));
            }
        }
    }
}
