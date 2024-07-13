package de.nicode3141.nicodesutils.entity.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import de.nicode3141.nicodesutils.entity.custom.ExtremeTNTEntity;
import de.nicode3141.nicodesutils.entity.model.RGBSheepModel;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.TNTMinecartRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.StringTextComponent;

import javax.annotation.Nonnull;

public class ExtremeTNTRenderer extends EntityRenderer<ExtremeTNTEntity> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(NicodesUtils.MOD_ID, "textures/block/extreme_tnt/side.png");

    public ExtremeTNTRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn);
        this.shadowSize = 0.5F;

    }

    @Override
    public void render(ExtremeTNTEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {

        //change size
        matrixStackIn.push();
        matrixStackIn.translate(0.0D, 0.5D, 0.0D);
        /*if(entityIn.getFuse() < 50 && entityIn.getFuse() > 15) {
            float blendFactor = (entityIn.getFuse() - 10.0F) / 40.0F;
            float interpolatedScale = 0.4F + blendFactor * 0.3F + (1.0F - blendFactor) * 0.4F;
            //float f1 = 1.0F + f * 0.3F;
            matrixStackIn.scale(interpolatedScale, interpolatedScale, interpolatedScale);
        }*/

        //wenn nur noch 10 ticks bis zur Zündung:
        if ((float)entityIn.getFuse() - partialTicks + 1.0F < 10.0F) {
            float f = 1.0F - ((float)entityIn.getFuse() - partialTicks + 1.0F) / 10.0F;
            f = MathHelper.clamp(f, 0.0F, 1.0F);
            f = f * f;
            f = f * f;
            //float f1 = 1.0F + f * 0.3F;
            float f1 = 1.0F + f * 10F;
            matrixStackIn.scale(f1, f1, f1);
        }


        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(-90.0F));
        matrixStackIn.translate(-0.5D, -0.5D, 0.5D);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(90.0F));
        renderTntFlash(ModBlocks.EXTREME_TNT.get().getDefaultState(), matrixStackIn, bufferIn, packedLightIn, entityIn.getFuse() / 5 % 2 == 0, entityIn.getFuse());
        matrixStackIn.pop();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    public static void renderTntFlash(BlockState blockStateIn, MatrixStack matrixStackIn, IRenderTypeBuffer renderTypeBuffer, int combinedLight, boolean doFullBright, int fuse) {
        int i;
        if (doFullBright) {
            if(fuse > 60){
                i = OverlayTexture.getPackedUV(OverlayTexture.getU(1.0F), 10);
            } else{
                i = OverlayTexture.getPackedUV(OverlayTexture.getU(1.0F), 5);
            }
        } else {
            i = OverlayTexture.NO_OVERLAY;
        }

        //WHAT THE HECK HAPPENS HERE??
        //first: getU() =>  RETURNS float * 15
        //getPackedUV(A, B) => Creates C : B Shifted 15 times in binary form to the left THEN => takes the highest int: A OR C

        /*i = OverlayTexture.getPackedUV(OverlayTexture.getU(1.0F), 100);

        System.out.println( "Combined Col: " + i);*/

        Minecraft.getInstance().getBlockRendererDispatcher().renderBlock(blockStateIn, matrixStackIn, renderTypeBuffer, combinedLight, i);
    }

    @Override
    public ResourceLocation getEntityTexture(ExtremeTNTEntity entity) {

        return TEXTURE;
    }

}
