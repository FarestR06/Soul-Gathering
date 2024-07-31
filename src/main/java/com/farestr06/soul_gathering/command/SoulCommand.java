package com.farestr06.soul_gathering.command;

import com.farestr06.soul_gathering.component.ModComponents;
import com.farestr06.soul_gathering.component.SoulComponentHelper;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

import static net.minecraft.server.command.CommandManager.*;

public class `SoulCommand {
    static final SimpleCommandExceptionType TOO_MANY_SOULS_ADD = new SimpleCommandExceptionType(Text.translatable("command.soul_gathering.too_many_souls.add"));
    static final SimpleCommandExceptionType TOO_MANY_SOULS_REMOVE = new SimpleCommandExceptionType(Text.translatable("command.soul_gathering.too_many_souls.remove"));

    public static void registerCommand() {

        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("soul")
                .requires(source -> source.hasPermissionLevel(2))
                .then(literal("query")
                        .then(literal("percent")
                        .then(argument("player", EntityArgumentType.player())
                            .executes(context -> {
                                final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                int value = SoulComponentHelper.MathHelper.mappedSoulCount(player);
                                context.getSource().sendFeedback(() -> Text.literal("%s's soul count is currently at %s percent".formatted(player.getName().getLiteralString(), value)), true);
                                return value;
                            })))
                        .then(literal("total")
                        .then(argument("player", EntityArgumentType.player())
                            .executes(context -> {
                                final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                int value = ModComponents.SOUL_COMPONENT.get(player).getSoulCount() ;
                                context.getSource().sendFeedback(() -> Text.literal("%s currently has %s souls".formatted(player.getName().getLiteralString(), value)), true);
                                return value;
                            }))))
                .then(literal("set")
                        .then(argument("player", EntityArgumentType.player())
                                .then(argument("amount", IntegerArgumentType.integer(0, 8192))
                                        .executes(context -> {
                                            final int amount = IntegerArgumentType.getInteger(context, "amount");
                                            final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                            ModComponents.SOUL_COMPONENT.get(player).setSoulCount(amount);
                                            context.getSource().sendFeedback(() -> Text.literal("Set %s's soul count to %s".formatted(player.getName().getLiteralString(), amount)), true);
                                            return Command.SINGLE_SUCCESS;
                                        }))))
                .then(literal("add")
                        .then(argument("player", EntityArgumentType.player())
                                .then(argument("amount", IntegerArgumentType.integer(1, 8192))
                                .executes(context -> {
                                    final int amount = IntegerArgumentType.getInteger(context, "amount");
                                    final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                    if (ModComponents.SOUL_COMPONENT.get(player).getSoulCount() + amount > 8192) {
                                        throw TOO_MANY_SOULS_ADD.create();
                                    } else {
                                        ModComponents.SOUL_COMPONENT.get(player).addSouls(amount);
                                    }
                                    context.getSource().sendFeedback(() -> Text.literal("Added %s souls to %s".formatted(amount, player.getName().toString())), true);
                                    return Command.SINGLE_SUCCESS;
                                }))))
                .then(literal("remove")
                        .then(argument("player", EntityArgumentType.player())
                                .then(argument("amount", IntegerArgumentType.integer(1, 8192))
                                .executes(context -> {
                                    final int amount = IntegerArgumentType.getInteger(context, "amount");
                                    final PlayerEntity player = EntityArgumentType.getPlayer(context, "player");
                                    if (ModComponents.SOUL_COMPONENT.get(player).getSoulCount() - amount < 0) {
                                        throw TOO_MANY_SOULS_REMOVE.create();
                                    } else {
                                        ModComponents.SOUL_COMPONENT.get(player).removeSouls(amount);
                                    }
                                    context.getSource().sendFeedback(() -> Text.literal("Removed %s souls from %s".formatted(amount, player.getName().toString())), true);
                                    return Command.SINGLE_SUCCESS;
                }))))));
    }
}
