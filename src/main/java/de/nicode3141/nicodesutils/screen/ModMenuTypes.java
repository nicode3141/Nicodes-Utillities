package de.nicode3141.nicodesutils.screen;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.entity.ElectrolysisChamberBlockEntity;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, NicodesUtils.MOD_ID);

    public static final RegistryObject<MenuType<ElectrolysisChamberMenu>> ELECTROLYSIS_CHAMBER_MENU =
            registerMenuType(ElectrolysisChamberMenu::new, "electrolysis_chamber_menu");


    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                  String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus){
        MENUS.register(eventBus);
    }
}
