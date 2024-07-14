package de.nicode3141.nicodesutils.block.custom.extremeTNT;

import de.nicode3141.nicodesutils.util.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.dispenser.IPosition;
import net.minecraft.entity.Entity;
import net.minecraft.particles.ParticleTypes;
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
    public static DamageSource explosionDamage = new DamageSource("damage.explosion").setExplosion().setDamageBypassesArmor();

    public ExplosionHandler(BlockPos originPosition, ServerWorld world, int radius){
        this.originPosition = originPosition;
        this.world = world;
        this.radius = radius;
    }

    public void explode(){

        world.playSound(null, originPosition.getX(), originPosition.getY(),originPosition.getZ(), ModSoundEvents.MEDIUM_EXPLOSION.get(), SoundCategory.BLOCKS, 10.0F, 0.9F);
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

        world.addParticle(ParticleTypes.EXPLOSION_EMITTER, originPosition.getX(), originPosition.getY(),originPosition.getZ(), 10.0D, 0.0D, 0.0D);
        world.addOptionalParticle(ParticleTypes.EXPLOSION, originPosition.getX(), originPosition.getY(),originPosition.getZ(), 10.0D, 0.0D, 0.0D);

        AxisAlignedBB axis = new AxisAlignedBB(BlockPos.unpackX(
                originPosition.toLong()) - radius, BlockPos.unpackY(originPosition.toLong()) - radius, BlockPos.unpackZ(originPosition.toLong()) -Math.min(radius, 100),
                BlockPos.unpackX(originPosition.toLong()) + radius, BlockPos.unpackY(originPosition.toLong()) + radius, BlockPos.unpackZ(originPosition.toLong()) + radius);
        List<Entity> entities = world.getEntitiesWithinAABB(Entity.class, axis);
        for (Entity entity : entities) {
            System.out.println(entity.getEntityId());
            double distance = originPosition.distanceSq( entity.prevPosX, entity.prevPosY, entity.prevPosZ, true);
            float damage = (float) ((radius - Math.sqrt(distance)) * 1000);
            entity.attackEntityFrom(explosionDamage, damage);
            //entity.remove();
        }

    }
}
