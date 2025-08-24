package com.kielson.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.CompletableFuture;

public class ModItemSuggestionProvider implements SuggestionProvider<ServerCommandSource> {
    private static final String[] SUGGESTIONS = new String[]{
            "amethyst_axe", "cursed_bone_crown", "echo_scythe", "emerald_fishing_rod", "ender_sword",
            "heart_of_the_nether", "obsidian_bow", "resin_pickaxe", "shattered_mirror", "totem_of_shadows"
    };

    @Override
    public CompletableFuture<Suggestions> getSuggestions(CommandContext<ServerCommandSource> context, SuggestionsBuilder builder) throws CommandSyntaxException {
        for (String suggestion : SUGGESTIONS) {
            builder.suggest(suggestion);
        }

        return builder.buildFuture();
    }
}
