package com.kielson.mixin;

import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Unique private ItemStack itemStack = (ItemStack) (Object) this;

}
