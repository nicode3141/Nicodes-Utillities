package de.nicode3141.nicodesutils.intergration;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.recipe.ElectrolysisRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;

public class ElectrolysisRecipeCategory implements IRecipeCategory<ElectrolysisRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(NicodesUtils.MOD_ID, "electrolysis");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(NicodesUtils.MOD_ID, "textures/gui/machines/electrolysis_chamber_gui");

    private final IDrawable background;
    private final IDrawable icon;

    public ElectrolysisRecipeCategory(IGuiHelper helper) {

        this.background = helper.createDrawable(TEXTURE, 0, 0, 175,85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.ELECTROLYSIS_CHAMBER.get()));
    }

    @Override
    public RecipeType<ElectrolysisRecipe> getRecipeType() {
        return JeInicodesutilsPlugin.ELECTROLYSIS_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Electrolysis Chamber");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, ElectrolysisRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 86, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 55, 15)
                .addIngredients(ForgeTypes.FLUID_STACK, List.of(recipe.getFluid()))
                .setFluidRenderer(64000, false, 16, 61);

        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 60).addItemStack(recipe.getResultItem());
    }
}
