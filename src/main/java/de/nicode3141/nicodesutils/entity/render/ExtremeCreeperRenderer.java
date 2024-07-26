package de.nicode3141.nicodesutils.entity.render;

import de.nicode3141.nicodesutils.NicodesUtils;
import de.nicode3141.nicodesutils.entity.custom.ExtremeCreeperEntity;
import de.nicode3141.nicodesutils.entity.model.ExtremeCreeperModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class ExtremeCreeperRenderer extends MobRenderer<ExtremeCreeperEntity, ExtremeCreeperModel<ExtremeCreeperEntity>> {

    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(NicodesUtils.MOD_ID, "textures/entity/extreme_creeper/extreme_creeper.png");

    public ExtremeCreeperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new ExtremeCreeperModel(), 1.0F);
    }

    @Override
    public ResourceLocation getEntityTexture(ExtremeCreeperEntity entity) {
        return TEXTURE;
    }
}
