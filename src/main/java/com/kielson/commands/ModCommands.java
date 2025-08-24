package com.kielson.commands;

import com.kielson.cardinal_components.ModComponents;
import com.kielson.item.ModItems;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class ModCommands {
    public static void initialize(){
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("artifact")
                    .then(CommandManager.literal("list")
                            .executes(ModCommands::listAllArtifacts))

                    .then(CommandManager.literal("info")
                            .then(CommandManager.argument("item", StringArgumentType.word())
                                    .suggests(new ModItemSuggestionProvider())
                                    .executes(ModCommands::displayItemInfo)))
            );
        });
    }
    private static int listAllArtifacts(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        World world = context.getSource().getWorld();
        Map<String, String> itemsCrafted = ModComponents.ITEMS_CRAFTED.get(world.getScoreboard()).itemsCrafted;

        if(itemsCrafted.isEmpty()){
            source.sendFeedback(() -> Text.literal("Brak stworzonych artefaktów"), false);
        }
        else{
            source.sendFeedback(() -> Text.translatable("advancement.serwerek.root").append(Text.literal(":")), false);

            for (Map.Entry<String, String> entry : itemsCrafted.entrySet()) {
                String itemId = entry.getKey();
                UUID uuid = UUID.fromString(entry.getValue());

                ItemStack stack = new ItemStack(Registries.ITEM.get(Identifier.tryParse(itemId)));
                Text itemText = itemHoverText(stack, Formatting.UNDERLINE, Formatting.DARK_PURPLE);

                Text playerText = Text.literal(getPlayerName(source, uuid)).formatted(Formatting.ITALIC);

                Text line = Text.literal(" ")
                        .append(itemText)
                        .append(Text.literal(" -> "))
                        .append(playerText);

                source.sendFeedback(() -> line, false);
            }
        }
        return 1;
    }

    private static int displayItemInfo(CommandContext<ServerCommandSource> context){
        ServerCommandSource source = context.getSource();
        String item = StringArgumentType.getString(context, "item");

        switch (item){
            case "amethyst_axe": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.AMETHYST_AXE), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(AMETHYST_SHARD), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(WITHER_BONE), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(AMETHYST_CLUSTER), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(AMETHYST_CLUSTER), false);
                break;
            }
            case "cursed_bone_crown": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.CURSED_BONE_CROWN), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 4 x ").append(BONE_BLOCK), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(WITHER_ROSE), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(EMERALD_BLOCK), false);
                break;
            }
            case "echo_scythe": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.ECHO_SCYTHE), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(STICK), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(ECHO_SHARD), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(WITHER_SKULL), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(WARDEN_SOUL), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(ECHO_SHARD), false);
                break;
            }
            case "emerald_fishing_rod": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.EMERALD_FISHING_ROD), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 3 x ").append(EMERALD_BLOCK), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(HONEYCOMB), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(BLAZE_POWDER), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(EMERALD_BLOCK), false);
                break;
            }
            case "ender_sword": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.ENDER_SWORD), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(ENDER_EYE), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(BREEZE_ROD), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(ENDER_EYE), false);
                break;
            }
            case "heart_of_the_nether": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.HEART_OF_THE_NETHER), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(NETHER_STAR), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(WITHER_SKULL), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(NETHER_STAR), false);
                break;
            }
            case "obsidian_bow": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.OBSIDIAN_BOW), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 3 x ").append(WITHER_BONE), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(OBSIDIAN), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(CRYING_OBSIDIAN), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(WITHER_BONE), false);
                break;
            }
            case "resin_pickaxe": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.RESIN_PICKAXE), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(RESIN_BRICK), false);
                source.sendFeedback(() -> Text.literal("   - 2 x ").append(STICK), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(NETHERITE_SCRAP), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(RESIN_BRICK), false);
                break;
            }
            case "shattered_mirror": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.SHATTERED_MIRROR), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 4 x ").append(GOLD_BLOCK), false);
                source.sendFeedback(() -> Text.literal("   - 4 x ").append(NETHERITE_SCRAP), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(TINTED_GLASS), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.repair")), false);
                source.sendFeedback(() -> Text.literal("   - ").append(TINTED_GLASS), false);
                break;
            }
            case "totem_of_shadows": {
                source.sendFeedback(() -> Text.empty().append(itemHoverText(new ItemStack(ModItems.TOTEM_OF_SHADOWS), Formatting.UNDERLINE, Formatting.DARK_PURPLE)).append(Text.literal(":")), false);
                source.sendFeedback(() -> Text.literal(" ").append(Text.translatable("item.crafting")), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(AMETHYST_SHARD), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(WARDEN_SOUL), false);
                source.sendFeedback(() -> Text.literal("   - 1 x ").append(DEEPSLATE), false);
                break;
            }
            default: {
                source.sendFeedback(() -> Text.literal("No such item was found"), false);
                break;
            }
        }

        return 1;
    }

    private static Text itemHoverText(ItemStack stack, Formatting... formatting){
        return Text.translatable(stack.getItem().getTranslationKey()).styled(style -> style.withHoverEvent(new HoverEvent.ShowItem(stack))).formatted(formatting);
    }

    private static String getPlayerName(ServerCommandSource source, UUID uuid){
        String playerName = "brak";
        try {
            var player = source.getServer().getPlayerManager().getPlayer(uuid);
            if (player != null) {
                playerName = player.getName().getString();
            } else {
                GameProfile profile = Objects.requireNonNull(source.getServer().getUserCache()).getByUuid(uuid).orElse(null);
                if (profile != null) {
                    playerName = profile.getName();
                }
            }
        } catch (IllegalArgumentException ignored) {}

        return playerName;
    }

    private static final Text AMETHYST_SHARD = itemHoverText(new ItemStack(Items.AMETHYST_SHARD), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text AMETHYST_CLUSTER = itemHoverText(new ItemStack(Items.AMETHYST_CLUSTER), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text BONE_BLOCK = itemHoverText(new ItemStack(Items.BONE_BLOCK), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text WITHER_BONE = itemHoverText(new ItemStack(ModItems.WITHER_BONE), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text WITHER_ROSE = itemHoverText(new ItemStack(Items.WITHER_ROSE), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text EMERALD_BLOCK = itemHoverText(new ItemStack(Items.EMERALD_BLOCK), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text STICK = itemHoverText(new ItemStack(Items.STICK), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text ECHO_SHARD = itemHoverText(new ItemStack(Items.ECHO_SHARD), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text WITHER_SKULL = itemHoverText(new ItemStack(Items.WITHER_SKELETON_SKULL), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text WARDEN_SOUL = itemHoverText(new ItemStack(ModItems.WARDEN_SOUL), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text BLAZE_POWDER = itemHoverText(new ItemStack(Items.BLAZE_POWDER), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text HONEYCOMB = itemHoverText(new ItemStack(Items.HONEYCOMB), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text BREEZE_ROD = itemHoverText(new ItemStack(Items.BREEZE_ROD), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text ENDER_EYE = itemHoverText(new ItemStack(Items.ENDER_EYE), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text NETHER_STAR = itemHoverText(new ItemStack(Items.NETHER_STAR), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text OBSIDIAN = itemHoverText(new ItemStack(Items.OBSIDIAN), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text CRYING_OBSIDIAN = itemHoverText(new ItemStack(Items.CRYING_OBSIDIAN), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text RESIN_BRICK = itemHoverText(new ItemStack(Items.RESIN_BRICK), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text NETHERITE_SCRAP = itemHoverText(new ItemStack(Items.NETHERITE_SCRAP), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text GOLD_BLOCK = itemHoverText(new ItemStack(Items.GOLD_BLOCK), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text TINTED_GLASS = itemHoverText(new ItemStack(Items.TINTED_GLASS), Formatting.UNDERLINE, Formatting.GRAY);
    private static final Text DEEPSLATE = itemHoverText(new ItemStack(Items.DEEPSLATE), Formatting.UNDERLINE, Formatting.GRAY);

}
