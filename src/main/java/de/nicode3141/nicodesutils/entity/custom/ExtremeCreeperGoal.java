package de.nicode3141.nicodesutils.entity.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.monster.CreeperEntity;

import java.util.EnumSet;

public class ExtremeCreeperGoal extends Goal {

    private final ExtremeCreeperEntity swellingCreeper;
    private LivingEntity creeperAttackTarget;

    public ExtremeCreeperGoal(ExtremeCreeperEntity entitycreeperIn) {
        this.swellingCreeper = entitycreeperIn;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
    }

    @Override
    public boolean shouldExecute() {
        LivingEntity livingentity = this.swellingCreeper.getAttackTarget();
        return livingentity != null && this.swellingCreeper.getDistanceSq(livingentity) < 20.0D;
    }

    public void startExecuting() {
        this.swellingCreeper.getNavigator().clearPath();
        this.creeperAttackTarget = this.swellingCreeper.getAttackTarget();
    }
}
