package de.nicode3141.nicodesutils.recipe;

import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, NicodesUtils.MOD_ID);

    public static final RegistryObject<RecipeSerializer<ElectrolysisRecipe>> ELECTROLYSIS_SERILIZER =
            SERIALIZERS.register("electrolysis", () -> ElectrolysisRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
