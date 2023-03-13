package de.nicode3141.nicodesutils.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.entity.custom.EntityCustomTNT;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.TntMinecartRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RenderCustomTNTPrimed extends EntityRenderer<EntityCustomTNT> {

    private final BlockRenderDispatcher blockRenderer;

    public RenderCustomTNTPrimed(EntityRendererProvider.Context pContext) {
        super(pContext);
        shadowRadius = 0.5f;
        this.blockRenderer = pContext.getBlockRenderDispatcher();
    }



    @Override
    public void render(EntityCustomTNT tnt, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource renderer, int light) {
        pPoseStack.pushPose();
        pPoseStack.translate(0, 0.5, 0);
        if (tnt.getFuse() - pPartialTick + 1.0F < 10.0F) {
            float f = 1.0F - (tnt.getFuse() - pPartialTick + 1.0F) / 10.0F;
            f = Mth.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            float f1 = 1.0F + f * 0.3F;
            pPoseStack.scale(f1, f1, f1);
        }

        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(-90.0F));
        pPoseStack.translate(-0.5, -0.5, 0.5);
        pPoseStack.mulPose(Vector3f.YP.rotationDegrees(90.0F));
        TntMinecartRenderer.renderWhiteSolidBlock(blockRenderer, ModBlocks.CUSTOM_TNT.get().defaultBlockState(), pPoseStack, renderer, light,
                tnt.getFuse() / 5 % 2 == 0);
        pPoseStack.popPose();
        super.render(tnt, pEntityYaw, pPartialTick, pPoseStack, renderer, light);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCustomTNT pEntity) {
        return TextureAtlas.LOCATION_BLOCKS;
    }
}
