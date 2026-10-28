package de.nicode3141.nicodesutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.LiteralMessage;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.FormattedText;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;

public class CommandExplode {
    public CommandExplode(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(
                Commands.literal("explode")
                        .requires(cs -> cs.hasPermission(3))
                        .then(Commands.literal("return").executes((command) -> {
                            return executeCommand(command.getSource());
                        })));
    }

    private int executeCommand(CommandSourceStack source) {
        source.send
        return 1;
    }

}
