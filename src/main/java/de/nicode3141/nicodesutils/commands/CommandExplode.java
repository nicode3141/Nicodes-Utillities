package de.nicode3141.nicodesutils.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.nicode3141.nicodesutils.block.custom.extremeTNT.ExplosionHandler;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;

public class CommandExplode {


    public CommandExplode(CommandDispatcher<CommandSource> dispatcher){
        dispatcher.register(
                Commands.literal("explode")
                        .requires(pl -> pl.hasPermissionLevel(3))
                        .then(Commands.argument("radius", IntegerArgumentType.integer(10, 50000))
                        .executes((command) -> {
                            return handleExplosion(command.getSource(), IntegerArgumentType.getInteger(command, "radius"));
                        })
        ));
    }

    private int handleExplosion(CommandSource source, int radius) throws CommandSyntaxException {
        source.sendFeedback(new StringTextComponent("Starting explosion! Radius = " + radius), true);
        ServerPlayerEntity player = source.asPlayer();
        BlockPos playerPosition = player.getPosition();

        ExplosionHandler explosion = new ExplosionHandler(playerPosition, source.getWorld(), radius);
        explosion.explode();
        return 1;
    }
}
