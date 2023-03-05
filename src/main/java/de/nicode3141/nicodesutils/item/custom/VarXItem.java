package de.nicode3141.nicodesutils.item.custom;

import de.nicode3141.nicodesutils.entity.custom.RGBSheepEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class VarXItem extends Item {

    public VarXItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType itemInteractionForEntity(ItemStack stack, PlayerEntity playerIn, LivingEntity target, Hand hand) {
        if(!target.world.isRemote) {
            return ActionResultType.PASS;
        }

        if (target instanceof SheepEntity){


           // RGBSheepEntity rgbsheep = new RGBSheepEntity(target.world.getWorldProfiler(),playerIn);

            target.remove();

        }




        return super.itemInteractionForEntity(stack, playerIn, target, hand);
    }
}
