package de.nicode3141.nicodesutils.block.custom.extremeTNT;

import de.nicode3141.nicodesutils.entity.custom.ExtremeTNTEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TNTBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ExtremeTNTBlock extends TNTBlock {
    public ExtremeTNTBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter) {


        /*TNTEntity tntentity = new TNTEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
        world.addEntity(tntentity);
        world.playSound((PlayerEntity)null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        */
            TNTEntity tntentity = ExtremeTNTEntity.create(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
            world.addEntity(tntentity);
            world.playSound((PlayerEntity)null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);

    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {

        ItemStack itemstack = player.getHeldItem(handIn);
        Item item = itemstack.getItem();
        if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) {
            return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
        } else {
            catchFire(state, worldIn, pos, hit.getFace(), player);
            worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
            if (!player.isCreative()) {
                if (item == Items.FLINT_AND_STEEL) {
                    itemstack.damageItem(1, player, (player1) -> {
                        player1.sendBreakAnimation(handIn);
                    });
                } else {
                    itemstack.shrink(1);
                }
            }

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }

    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    public static void explode(World world, BlockPos worldIn) {
        explode(world, worldIn, (LivingEntity)null);
    }

    @Deprecated //Forge: Prefer using IForgeBlock#catchFire
    private static void explode(World worldIn, BlockPos pos, @Nullable LivingEntity entityIn) {
        if (!worldIn.isRemote) {
            TNTEntity tntentity = new TNTEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, entityIn);
            worldIn.addEntity(tntentity);
            worldIn.playSound((PlayerEntity)null, tntentity.getPosX(), tntentity.getPosY(), tntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
        }
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {


        if (!oldState.matchesBlock(state.getBlock())) {
            if (worldIn.isBlockPowered(pos)) {
                catchFire(state, worldIn, pos, null, null);
                worldIn.removeBlock(pos, false);
            }

        }
    }

    public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn) {
        if (!worldIn.isRemote) {
            TNTEntity tntentity = new TNTEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getExplosivePlacedBy());
            tntentity.setFuse((short)(worldIn.rand.nextInt(tntentity.getFuse() / 4) + tntentity.getFuse() / 8));
            worldIn.addEntity(tntentity);
        }
    }

    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.isBlockPowered(pos)) {
            catchFire(state, worldIn, pos, null, null);
            worldIn.removeBlock(pos, false);
        }

    }

    /**
     * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
     * this block
     */
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!worldIn.isRemote() && !player.isCreative() && state.get(UNSTABLE)) {
            catchFire(state, worldIn, pos, null, null);
        }

        super.onBlockHarvested(worldIn, pos, state, player);
    }
}
