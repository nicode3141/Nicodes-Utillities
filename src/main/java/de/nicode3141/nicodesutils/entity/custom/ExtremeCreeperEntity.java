package de.nicode3141.nicodesutils.entity.custom;

import de.nicode3141.nicodesutils.block.custom.extremeTNT.ExplosionHandler;
import de.nicode3141.nicodesutils.util.ModSoundEvents;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class ExtremeCreeperEntity extends CreeperEntity {
    public ExtremeCreeperEntity(EntityType<? extends CreeperEntity> type, World worldIn) {
        super(type, worldIn);
    }


    private boolean isSoundPlaying = false;
    private boolean exploded = false;
    private int lastActiveTime;
    private int timeSinceIgnited;
    private int timeSinceIgnited2;
    private int fuseTime = 700;
    private int explosionRadius = 3;

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.9D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 13.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 50.0D);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp(ZombifiedPiglinEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillagerEntity.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolemEntity.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, TurtleEntity.class, 10, true, false, TurtleEntity.TARGET_DRY_BABY));
        this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 20.0F));
    }

    @Override
    public int getMaxFallHeight() {
        return super.getMaxFallHeight();
    }

    @Override
    public boolean onLivingFall(float distance, float damageMultiplier) {
        System.out.println("on Living Fall!");
        return super.onLivingFall(distance, damageMultiplier);
    }

    @Override
    protected void registerData() {
        super.registerData();

    }

    @Override
    public void writeAdditional(CompoundNBT compound) {
        super.writeAdditional(compound);
    }

    @Override
    public void readAdditional(CompoundNBT compound) {
        super.readAdditional(compound);
    }

    @Override
    public void tick() {
        if (this.isAlive()) {
            this.lastActiveTime = this.timeSinceIgnited;
            if (this.hasIgnited()) {
                this.setCreeperState(1);
            }

            int i = this.getCreeperState();
            if (i > 0 && this.timeSinceIgnited == 0) {
                this.playSound(SoundEvents.ENTITY_CREEPER_PRIMED, 1.0F, 0.5F);
            }

            //this.timeSinceIgnited += i;
            if (this.timeSinceIgnited < 0) {
                this.timeSinceIgnited = 0;
            }

            if (this.timeSinceIgnited >= this.fuseTime) {
                this.timeSinceIgnited = this.fuseTime;
                this.explodeFake();
            }

            if (!this.world.isRemote) {
                List<PlayerEntity> players = this.world.getEntitiesWithinAABB(PlayerEntity.class, this.getBoundingBox().grow(20.0D));

                if(!players.isEmpty()){

                    this.timeSinceIgnited2++;

                    if (this.timeSinceIgnited2 >= this.fuseTime) {
                        this.timeSinceIgnited2 = this.fuseTime;
                        this.explode();
                    }

                    if(!isSoundPlaying && !exploded){
                        for(PlayerEntity player : players){
                            player.playSound(ModSoundEvents.DONT_WORRY.get(), SoundCategory.HOSTILE, 1.0F, 1.0F);
                            isSoundPlaying = true;
                        }
                    }
                } else {

                }
            }
        } else{
            isSoundPlaying = false;
        }
        super.tick();
    }

    private void explode() {
        if (!this.world.isRemote) {
            Explosion.Mode explosion$mode = net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.world, this) ? Explosion.Mode.DESTROY : Explosion.Mode.NONE;
            float f = this.isCharged() ? 2.0F : 1.0F;
            this.dead = true;
            ExplosionHandler explosion = new ExplosionHandler(getPosition(), (ServerWorld) this.world, 40);
            explosion.explode();
            isSoundPlaying = false;
            exploded = true;
            this.remove();

        }

    }
    private void explodeFake() {
        if (!this.world.isRemote) {
            this.world.addParticle(ParticleTypes.BUBBLE, this.getPosX(), this.getPosY(), this.getPosZ(), 0.1F, 0.1F, 0.1F);
        }

    }



    @Override
    public boolean isCharged() {
        return super.isCharged();
    }

    @Override
    public float getCreeperFlashIntensity(float partialTicks) {
        return super.getCreeperFlashIntensity(partialTicks);
    }

    @Override
    public boolean hasIgnited() {
        return super.hasIgnited();
    }

    @Override
    public void ignite() {
        super.ignite();
    }


}
