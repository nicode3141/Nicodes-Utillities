package de.nicode3141.nicodesutils.block.custom.extremeTNT;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;

import java.util.HashSet;
import java.util.List;

public class ExplosionHandler {
    private final BlockPos originPosition;
    private final ServerWorld world;
    private final int radius;
    public static DamageSource explosionDamage = new DamageSource("explosion").setExplosion().setDamageBypassesArmor();

    public ExplosionHandler(BlockPos originPosition, ServerWorld world, int radius){
        this.originPosition = originPosition;
        this.world = world;
        this.radius = radius;
    }

    public void explode(){

        world.playSound(null, originPosition.getX(), originPosition.getY(),originPosition.getZ(), SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 10.0F, 0.9F);
        HashSet<BlockPos> blockToDestroy = new HashSet<>();

        //adds Blocks in radius to HashSet
        //now, loop thru every Block in the given radius (relative)
        for(int x = -radius; x <= radius; x++){
            for(int y = -radius; y <= radius; y++){
                for(int z = -Math.min(radius, 100); z <= radius; z++){
                    BlockPos pos = BlockPos.fromLong(originPosition.offset(originPosition.toLong(), x, y, z)); //get absolute coords
                    double distance = originPosition.distanceSq(pos.getX(), pos.getY(), pos.getZ(), true);
                    if(distance <= radius * radius){
                        blockToDestroy.add(pos);
                    }
                }
            }
        }

        for(BlockPos pos : blockToDestroy){
            BlockState state = world.getBlockState(pos);
            Block block = state.getBlock();
            if(!block.isAir(state, world, pos) && !block.matchesBlock(Blocks.BEDROCK)){
                //remove Blocks
                world.removeBlock(pos, false);
                world.getLightManager().checkBlock(pos);
            }
        }

        /*List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(originPosition));
        for (Entity entity : entities) {
            double distance = originPosition.distanceSq((IPosition) entity.getPosition(), true);
            float damage = (float) ((radius - Math.sqrt(distance)) * 10);
            entity.attackEntityFrom(DamageSource.causeBedExplosionDamage(), damage);
            entity.remove();
        }*/

    }
}
