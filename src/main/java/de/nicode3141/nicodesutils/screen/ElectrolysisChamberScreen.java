package de.nicode3141.nicodesutils.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import de.nicode3141.nicodesutils.NicodesUtils;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ElectrolysisChamberScreen extends AbstractContainerScreen<ElectrolysisChamberMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(NicodesUtils.MOD_ID,"textures/gui/machines/electrolysis_chamber_gui.png");

    public ElectrolysisChamberScreen(ElectrolysisChamberMenu electrolysisChamberMenu, Inventory inventory, Component component) {
        super(electrolysisChamberMenu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(pPoseStack, x, y);
    }

    private void renderProgressArrow(PoseStack pPoseStack, int x, int y) {
        if(menu.isCrafting()) {
            blit(pPoseStack, x + 105, y + 33, 176, 0, 8, menu.getScaledProgress()); //PROGRESS BAR POS
        }
    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
