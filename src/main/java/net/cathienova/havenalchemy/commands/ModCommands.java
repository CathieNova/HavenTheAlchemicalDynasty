package net.cathienova.havenalchemy.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.cathienova.havenalchemy.util.EMCSystem;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ModCommands
{
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
    dispatcher.register(Commands.literal("havenalchemy")
            .then(Commands.literal("emc")
                    .then(Commands.literal("reload")
                            .executes(ModCommands::reloadConfig))
                    .then(Commands.literal("addemcrecipes")
                            .executes(ModCommands::addEMCRecipes))
                    .then(Commands.literal("add")
                            .then(Commands.argument("value", IntegerArgumentType.integer())
                                    .executes(context -> addEMC(context, IntegerArgumentType.getInteger(context, "value")))))
                    .then(Commands.literal("remove")
                            .executes(ModCommands::removeEMC))));
    }

    private static int addEMCRecipes(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (!player.hasPermissions(2) || !player.isCreative())
        {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §4You do not have permission to use this command."));
            return 0;
        }
        EMCSystem.setEmcFromRecipes();
        context.getSource().sendSuccess(() -> Component.literal("§6[§5Haven §2Alchemy§6]§r §2EMC values added from recipes."), true);
        return 1;
    }

    private static int reloadConfig(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (!player.hasPermissions(2) || !player.isCreative())
        {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §4You do not have permission to use this command."));
            return 0;
        }
        EMCSystem.loadEmcValues();
        context.getSource().sendSuccess(() -> Component.literal("§6[§5Haven §2Alchemy§6]§r §2EMC config reloaded with §5" + EMCSystem.getEMCListSize() + " §2entries."), true);
        return 1;
    }

    private static int addEMC(CommandContext<CommandSourceStack> context, int value) throws CommandSyntaxException
    {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (!player.hasPermissions(2) || !player.isCreative())
        {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §4You do not have permission to use this command."));
            return 0;
        }
        ItemStack itemInHand = player.getMainHandItem();

        if (!itemInHand.isEmpty()) {
            EMCSystem.AddEMCToConfig(itemInHand.getItem(), value);
            EMCSystem.loadEmcValues();
            context.getSource().sendSuccess(() -> Component.literal("§6[§5Haven §2Alchemy§6]§r §5" + itemInHand.getDisplayName().getString() + " §2added to EMC list with value §5" + value + " §2and config reloaded."), true);
        } else {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §cNo item in hand to add to EMC list."));
        }
        return 1;
    }

    private static int removeEMC(CommandContext<CommandSourceStack> context) throws CommandSyntaxException
    {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (!player.hasPermissions(2) || !player.isCreative())
        {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §4You do not have permission to use this command."));
            return 0;
        }
        ItemStack itemInHand = player.getMainHandItem();

        if (!itemInHand.isEmpty()) {
            EMCSystem.RemoveEMCFromConfig(itemInHand.getItem());
            EMCSystem.loadEmcValues();
            context.getSource().sendSuccess(() -> Component.literal("§6[§5Haven §2Alchemy§6]§r §5" + itemInHand.getDisplayName().getString() + " §2removed from EMC list and config reloaded."), true);
        } else {
            context.getSource().sendFailure(Component.literal("§6[§5Haven §2Alchemy§6]§r §cNo item in hand to remove from EMC list."));
        }
        return 1;
    }
}