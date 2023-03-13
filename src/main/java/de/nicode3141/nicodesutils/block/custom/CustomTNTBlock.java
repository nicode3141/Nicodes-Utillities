package de.nicode3141.nicodesutils.block.custom;

import de.nicode3141.nicodesutils.entity.custom.EntityCustomTNT;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.TntBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CustomTNTBlock extends TntBlock {
    public CustomTNTBlock(Properties pProperties) {
        super(pProperties);
    }



    @Override
    public int getFlammability(BlockState state, BlockGetter world, BlockPos pos, Direction face) {
        //300 is 100% chance fire will spread to this block, 100 is default for TNT
        // Given we are "obsidian" make ours slightly more stable against fire being spread than vanilla TNT
        return 75;
    }

    @Override
    public void onCaughtFire(@NotNull BlockState state, Level world, @NotNull BlockPos pos, @Nullable Direction side, @Nullable LivingEntity igniter) {
        if (!world.isClientSide && createAndAddEntity(world, pos, igniter)) {
            world.gameEvent(igniter, GameEvent.PRIME_FUSE, pos);
        }
    }

    @Override
    public void wasExploded(Level world, @NotNull BlockPos pos, @NotNull Explosion explosion) {
        if (!world.isClientSide) {
            PrimedTnt tnt = EntityCustomTNT.create(world, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, explosion.getSourceMob());
            if (tnt != null) {
                tnt.setFuse((short) (world.random.nextInt(tnt.getFuse() / 4) + tnt.getFuse() / 8));
                world.addFreshEntity(tnt);
            }
        }
    }

    @Override
    @Deprecated
    public boolean isPathfindable(@NotNull BlockState state, @NotNull BlockGetter world, @NotNull BlockPos pos, @NotNull PathComputationType type) {
        return false;
    }



    public static boolean createAndAddEntity(@NotNull Level world, @NotNull BlockPos pos, @Nullable LivingEntity igniter) {
        PrimedTnt tnt = EntityCustomTNT.create(world, pos.getX() + 0.5F, pos.getY(), pos.getZ() + 0.5F, igniter);
        if (tnt != null) {
            world.addFreshEntity(tnt);
            world.playSound(null, tnt.getX(), tnt.getY(), tnt.getZ(), SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        }
        return false;
    }
}
