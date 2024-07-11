package de.nicode3141.nicodesutils.entity.custom;

import de.nicode3141.nicodesutils.block.custom.extremeTNT.ExplosionHandler;
import de.nicode3141.nicodesutils.entity.ModEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SSpawnObjectPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ExtremeTNTEntity extends TNTEntity {
    public ExtremeTNTEntity(EntityType<? extends TNTEntity> type, World worldIn) {
        super(type, worldIn);
        setFuse(200);
    }

    @Nullable
    private LivingEntity igniter;

    public static ExtremeTNTEntity create(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        ExtremeTNTEntity entity = ModEntityTypes.EXTREME_TNT.get().create(worldIn);
        igniter.sendMessage(new StringTextComponent("This should create a entity!"), igniter.getUniqueID());

        entity.setPosition(x, y, z);

        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        entity.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);

        entity.setFuse(200);

        entity.prevPosX = x;
        entity.prevPosY = y;
        entity.prevPosZ = z;
        entity.igniter = igniter;

        return entity;
    }


    @Override
    public void tick() {

        if (!this.hasNoGravity()) {
            this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
        }

        this.move(MoverType.SELF, this.getMotion());
        this.setMotion(this.getMotion().scale(0.98D));
        if (this.onGround) {
            this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
        }

        setFuse(getFuse()-1);
        if (this.getFuse() <= 0) {
            this.remove();
            if (!this.world.isRemote) {
                this.explode();
            }
        } else {
            this.func_233566_aG_();
            if (this.world.isRemote) {
                this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    protected void explode() {
        igniter.sendMessage(new StringTextComponent("Exploded!"), igniter.getUniqueID());
        ExplosionHandler explosion = new ExplosionHandler(getPosition(), (ServerWorld) world, 70);
        explosion.explode();
    }

    @Nonnull
    @Override
    public EntityType<?> getType() {
        return ModEntityTypes.EXTREME_TNT.get();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return new SSpawnObjectPacket(this);
    }
}
