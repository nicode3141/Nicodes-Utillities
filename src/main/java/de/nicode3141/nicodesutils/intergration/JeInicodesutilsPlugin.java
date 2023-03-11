package de.nicode3141.nicodesutils.intergration;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.recipe.ElectrolysisRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JeInicodesutilsPlugin implements IModPlugin {
    public static RecipeType<ElectrolysisRecipe> ELECTROLYSIS_TYPE =
            new RecipeType<>(ElectrolysisRecipeCategory.UID,ElectrolysisRecipe.class);

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(NicodesUtils.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                ElectrolysisRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();

        List<ElectrolysisRecipe> recipesElectrolysis = rm.getAllRecipesFor(ElectrolysisRecipe.Type.INSTANCE);
        registration.addRecipes(ELECTROLYSIS_TYPE, recipesElectrolysis);
    }
}
