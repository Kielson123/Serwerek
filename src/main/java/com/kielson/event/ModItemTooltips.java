package com.kielson.event;

import com.kielson.item.ModItems;
import com.kielson.ModTags;
import com.kielson.util.ModComponents;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;

public class ModItemTooltips {
    public static void addItemTooltips(){
        ItemTooltipCallback.EVENT.register((stack, tooltipContext, tooltipType, list) -> {
            if (!stack.isIn(ModTags.MOD_ITEMS)) return;

            if(stack.isOf(ModItems.CURSED_BONE_CROWN)){
                list.addLast(Text.translatable("item.serwerek.cursed_bone_crown.ability1"));
                list.addLast(Text.translatable("item.serwerek.cursed_bone_crown.ability2"));
            }
            if(stack.isOf(ModItems.ENDER_SWORD)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.ender_sword.ability1"));
                list.addLast(Text.translatable("item.serwerek.ender_sword.ability2"));
            }
            if(stack.isOf(ModItems.RESIN_PICKAXE)){
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.resin_pickaxe.ability"));
            }
            if(stack.isOf(ModItems.ECHO_SCYTHE)){
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.after_hit"));
                list.addLast(Text.translatable("item.serwerek.echo_scythe.ability"));
            }
            if(stack.isOf(ModItems.SHATTERED_MIRROR)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.shattered_mirror.ability1.line1"));
                list.addLast(Text.translatable("item.serwerek.shattered_mirror.ability1.line2"));
                list.addLast(Text.translatable("item.serwerek.shattered_mirror.ability2.line1"));
                list.addLast(Text.translatable("item.serwerek.shattered_mirror.ability2.line2"));
            }
            if(stack.isOf(ModItems.AMETHYST_AXE)){
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.after_mine_log"));
                list.addLast(Text.translatable("item.serwerek.amethyst_axe.ability"));

                boolean active = Boolean.TRUE.equals(stack.get(ModComponents.ACTIVE));
                Text activeLine = Text.translatable("item.serwerek.amethyst_axe.active");
                int activeLineIndex = -1;
                for(int i = 0; i < list.size(); i++){
                    if(list.get(i).contains(activeLine)){
                        activeLineIndex = i;
                        break;
                    }
                }
                if(active && activeLineIndex == -1){
                    list.add(1, activeLine);
                }
                else if(!active && activeLineIndex >= 0) {
                    list.remove(activeLineIndex);
                }
            }
            if(stack.isOf(ModItems.EMERALD_FISHING_ROD)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.emerald_fishing_rod.ability"));
            }
            if(stack.isOf(ModItems.TOTEM_OF_SHADOWS)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.totem_of_shadows.ability"));
            }
            if(stack.isOf(ModItems.OBSIDIAN_BOW)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.obsidian_bow.ability"));
            }
            if(stack.isOf(ModItems.HEART_OF_THE_NETHER)) {
                list.addLast(Text.empty());
                list.addLast(Text.translatable("item.when_used"));
                list.addLast(Text.translatable("item.serwerek.heart_of_the_nether.ability"));
            }
        });
    }

}
