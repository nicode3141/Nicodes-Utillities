package de.nicode3141.nicodesutils.custom.chamber;

import de.nicode3141.nicodesutils.block.ItemShredderContainer;
import de.nicode3141.nicodesutils.tileentity.ItemShredderTile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class ElectrolysisChamberBlock extends Block {
    public ElectrolysisChamberBlock(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        while (!worldIn.isRemote()) {
            TileEntity tileEntity = worldIn.getTileEntity(pos);

            if (!player.isCrouching()) {
                if (tileEntity instanceof ItemShredderTile) {
                    INamedContainerProvider containerProvider = createContainerProvider(worldIn, pos);

                    NetworkHooks.openGui(((ServerPlayerEntity) player), containerProvider, tileEntity.getPos());
                } else {
                    throw new IllegalStateException("Our Container Provider is missing");
                }
            } else {
                if (tileEntity instanceof ItemShredderTile) {
                    //do somethings when player is right click this block while crouching
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private INamedContainerProvider createContainerProvider(World worldIn, BlockPos pos) {
        return new INamedContainerProvider() {
            @Override
            public ITextComponent getDisplayName() {
                return new TranslationTextComponent("screen.nicodesutils.item_shredder");
            }

            @Nullable
            @Override
            public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                return new ItemShredderContainer(i, worldIn, pos, playerInventory, playerEntity);
            }
        };
    }
}
