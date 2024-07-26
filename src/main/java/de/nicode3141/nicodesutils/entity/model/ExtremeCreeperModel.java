package de.nicode3141.nicodesutils.entity.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import de.nicode3141.nicodesutils.entity.custom.ExtremeCreeperEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ExtremeCreeperModel <T extends ExtremeCreeperEntity> extends EntityModel<T> {

    private final ModelRenderer sheep;
    private final ModelRenderer body;
    private final ModelRenderer legs;
    private final ModelRenderer upper;
    private final ModelRenderer lower;
    private final ModelRenderer head;
    private final ModelRenderer face;
    private final ModelRenderer main;

    public ExtremeCreeperModel() {
        textureWidth = 16;
        textureHeight = 16;

        sheep = new ModelRenderer(this);
        sheep.setRotationPoint(0.0F, 24.0F, 0.0F);


        body = new ModelRenderer(this);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        sheep.addChild(body);
        body.setTextureOffset(0, 0).addBox(-8.0F, -28.0F, -11.0F, 16.0F, 13.0F, 23.0F, 0.0F, false);

        legs = new ModelRenderer(this);
        legs.setRotationPoint(0.0F, 0.0F, 0.0F);
        sheep.addChild(legs);


        upper = new ModelRenderer(this);
        upper.setRotationPoint(0.0F, 0.0F, 0.0F);
        legs.addChild(upper);
        upper.setTextureOffset(0, 0).addBox(1.0F, -15.0F, -8.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
        upper.setTextureOffset(0, 0).addBox(-7.0F, -15.0F, -8.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
        upper.setTextureOffset(0, 0).addBox(-7.0F, -15.0F, 5.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);
        upper.setTextureOffset(0, 0).addBox(1.0F, -15.0F, 5.0F, 6.0F, 8.0F, 6.0F, 0.0F, false);

        lower = new ModelRenderer(this);
        lower.setRotationPoint(0.0F, 0.0F, 0.0F);
        legs.addChild(lower);
        lower.setTextureOffset(0, 0).addBox(2.0F, -8.0F, -7.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        lower.setTextureOffset(0, 0).addBox(-6.0F, -8.0F, -7.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        lower.setTextureOffset(0, 0).addBox(-6.0F, -8.0F, 6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);
        lower.setTextureOffset(0, 0).addBox(2.0F, -8.0F, 6.0F, 4.0F, 8.0F, 4.0F, 0.0F, false);

        head = new ModelRenderer(this);
        head.setRotationPoint(0.0F, 0.0F, 0.0F);
        sheep.addChild(head);


        face = new ModelRenderer(this);
        face.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(face);
        face.setTextureOffset(0, 0).addBox(-5.0F, -32.0F, -18.0F, 10.0F, 9.0F, 2.0F, 0.0F, false);

        main = new ModelRenderer(this);
        main.setRotationPoint(0.0F, 0.0F, 0.0F);
        head.addChild(main);
        main.setTextureOffset(0, 0).addBox(-6.0F, -33.0F, -16.0F, 12.0F, 11.0F, 12.0F, 0.0F, false);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        sheep.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
    }

    @Override
    public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.head.rotateAngleY = headPitch * ((float) Math.PI / 180F);
        this.legs.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F * (float) Math.PI) *1.4F * limbSwingAmount;
    }
}
