package de.nicode3141.nicodesutils.entity.render;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.entity.custom.RGBSheepEntity;
import de.nicode3141.nicodesutils.entity.model.RGBSheepModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RGBSheepRenderer extends MobRenderer<RGBSheepEntity, RGBSheepModel<RGBSheepEntity>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(NicodesUtils.MOD_ID, "textures/entity/buff_zombie.png");

    public RGBSheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RGBSheepModel(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(RGBSheepEntity entity) {
        return TEXTURE;
    }
}
