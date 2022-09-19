package de.nicode3141.nicodesutils.container;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ItemShredderContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static DeferredRegister<ContainerType<?>> CONTAINERS
            = DeferredRegister.create(ForgeRegistries.CONTAINERS, NicodesUtils.MOD_ID);

    public static final RegistryObject<ContainerType<ItemShredderContainer>> ITEM_SHREDDER_CONTAINER
            = CONTAINERS.register("item_shredder_container",
            ()-> IForgeContainerType.create(((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getEntityWorld();
                return new ItemShredderContainer(windowId, world, pos, inv, inv.player);
            })));

    public static void register(IEventBus eventbus){
        CONTAINERS.register(eventbus);
    }
}
