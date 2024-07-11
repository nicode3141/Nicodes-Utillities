package de.nicode3141.nicodesutils.events;


import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.commands.CommandExplode;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = NicodesUtils.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void onCommandsRegister(RegisterCommandsEvent event) {
        new CommandExplode(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }

}
