package com.kielson.mixin;

import com.kielson.cardinal_components.ModComponents;
import com.kielson.ModTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;
import java.util.Map;

@Mixin(Entity.class)
public class EntityMixin {
    @Unique private Entity entity = (Entity) (Object) this;

    @Inject(method = "kill", at = @At("HEAD"))
    private void onKill(ServerWorld world, CallbackInfo ci){
        if(entity instanceof ItemEntity itemEntity){
            ItemStack stack = itemEntity.getStack();
            if(stack.isIn(ModTags.MOD_ITEMS)){
                String itemKey = stack.getItem().toString().toLowerCase();
                Scoreboard scoreboard = world.getScoreboard();

                var component = ModComponents.ITEMS_CRAFTED.getNullable(scoreboard);
                assert component != null;
                Map<String, String> newMap = new HashMap<>(component.itemsCrafted);
                newMap.remove(itemKey);
                component.itemsCrafted = newMap;
            }
        }
    }

    @Inject(method = "discard", at = @At("HEAD"))
    private void onDiscard(CallbackInfo ci){
        if(entity instanceof ItemEntity itemEntity){
            ItemStack stack = itemEntity.getStack();
            if(stack.isIn(ModTags.MOD_ITEMS)){
                String itemKey = stack.getItem().toString().toLowerCase();
                Scoreboard scoreboard = entity.getWorld().getScoreboard();

                var component = ModComponents.ITEMS_CRAFTED.getNullable(scoreboard);
                assert component != null;
                Map<String, String> newMap = new HashMap<>(component.itemsCrafted);
                newMap.remove(itemKey);
                component.itemsCrafted = newMap;
            }
        }
    }

    @Inject(method = "remove", at = @At("HEAD"))
    private void onRemove(CallbackInfo ci){
        if(entity instanceof ItemEntity itemEntity){
            ItemStack stack = itemEntity.getStack();
            if(stack.isIn(ModTags.MOD_ITEMS)){
                String itemKey = stack.getItem().toString().toLowerCase();
                Scoreboard scoreboard = entity.getWorld().getScoreboard();

                var component = ModComponents.ITEMS_CRAFTED.getNullable(scoreboard);
                assert component != null;
                Map<String, String> newMap = new HashMap<>(component.itemsCrafted);
                newMap.remove(itemKey);
                component.itemsCrafted = newMap;
            }
        }
    }
}
