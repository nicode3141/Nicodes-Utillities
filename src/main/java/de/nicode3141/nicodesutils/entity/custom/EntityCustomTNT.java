package de.nicode3141.nicodesutils.entity.custom;

import de.nicode3141.nicodesutils.block.ModBlocks;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib3.geo.render.built.GeoModel;

import javax.annotation.Nullable;

public class EntityCustomTNT extends PrimedTnt {

    public EntityCustomTNT(EntityType<? extends PrimedTnt> entityType, Level level) {
        super(entityType, level);
        setFuse(100);
    }

    @Nullable
    public static EntityCustomTNT create(Level level, double x, double y, double z, @Nullable LivingEntity igniter) {
        EntityCustomTNT tnt = ModEntityTypes.CUSTOM_TNT.get().create(level);
        if(tnt == null) {
            return null;
        }

        tnt.setPos(x,y,z);
        double d0 = level.random.nextDouble() * (double) ((float) Math.PI * 2F);

        tnt.setDeltaMovement(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        tnt.xo = x;
        tnt.yo = y;
        tnt.zo = z;


        tnt.setFuse(100);
        return tnt;
    }

    @Override
    public boolean isPushable() {
        return true;
    }

    @Override
    public void tick() {
        super.tick();

        if(isAlive() && getFuse() > 0) {
            level.addParticle(ParticleTypes.SMOKE, getX(), getY() + 0.5, getZ(), 0, 0, 0);
        }
    }

    @Override
    protected void explode() {
        level.explode(this, getX(), getY() + (double) (getBbHeight() / 5.0F), getZ(), 99, Explosion.BlockInteraction.BREAK);


    }

    @NotNull
    @Override
    public EntityType<?> getType() {
        return ModEntityTypes.CUSTOM_TNT.get();
    }

    @NotNull
    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

}
