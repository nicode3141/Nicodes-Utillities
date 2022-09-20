package de.nicode3141.nicodesutils.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;

public class ItemShredderTile extends TileEntity {

    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public ItemShredderTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public ItemShredderTile() {
        this(ModTileEntities.ITEM_SHREDDER_TILE.get());
    }

    //saves the items in the tyileentity when world saved
    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.read(state, nbt);
    }

    //loads the items in the tyileentity when world loaded
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());

        return super.write(compound);
    }

    private ItemStackHandler createHandler(){
        //sets the possible Stacksize of Slots
        return new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {
               markDirty();
            }

            //sets the slot limit for the items in the tileentity
            @Override
            public int getSlotLimit(int slot) {
                return 64;
            }

            /*
            //replace §ONLYITEM§ with the item, the tileentity should accept.
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch(slot) {
                    case 0: return stack. getItem() == Items.§ONLYITEM§;
                    case 1: return stack. getItem() == ModItems.§ONLYITEM§.get() ||
                            stack.getItem() == ModItems.§ONLYITEM§.get();
                    default:
                        return false;
                }

                return super.isItemValid(slot, stack);
            }*/



            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                //gives the item back if its not Valid
                /*
                if(!isItemValid(slot, stack)){
                    return stack;
                }*/

                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }

        return super.getCapability(cap, side);
    }



}
