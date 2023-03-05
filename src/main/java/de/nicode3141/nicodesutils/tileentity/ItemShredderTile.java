package de.nicode3141.nicodesutils.tileentity;

import de.nicode3141.nicodesutils.util.ModEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.util.Map;
import java.util.logging.Level;

public class ItemShredderTile extends TileEntity implements ITickableTileEntity {

    private final ItemStackHandler itemHandler = createHandler();

    protected World level;

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private int progress = 0;
    private int maxProgress = 10;



    //private final Map<Direction, LazyOptional<WrappedHandler>> directionWrappedHandlerMap =
          /*
                    Direction.NORTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.SOUTH, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 2, (i, s) -> false)),
                    Direction.EAST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (i) -> i == 1,
                            (index, stack) -> itemHandler.isItemValid(1, stack))),
                    Direction.WEST, LazyOptional.of(() -> new WrappedHandler(itemHandler, (index) -> index == 0 || index == 1,
                            (index, stack) -> itemHandler.isItemValid(0, stack) || itemHandler.isItemValid(1, stack))));
*/
    private final ModEnergyStorage ENERGY_STORAGE = new ModEnergyStorage(60000,256) {
        @Override
        public void onEnergyChanged() {
            markDirty();
        }



    };
    private final int ENERGY_REQ = 32;

    /*public BlockState getStateforPlacement(BlockItemUseContext context){
        return null;
    }*/


    public ItemShredderTile(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public void onLoad() {
        lazyEnergyHandler.invalidate();
        super.onLoad();
    }

    @Override
    protected void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    public ItemShredderTile() {
        this(ModTileEntities.ITEM_SHREDDER_TILE.get());
    }

    //saves the items in the tyileentity when world saved
    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        ENERGY_STORAGE.setEnergy(nbt.getInt("itemshreddertile.energy"));
        super.read(state, nbt);
    }

    //loads the items in the tyileentity when world loaded
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        compound.putInt("itemshreddertile.energy", ENERGY_STORAGE.getEnergyStored());

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


            //replace §ONLYITEM§ with the item, the tileentity should accept.
            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch(slot) {
                    case 0: return true;
                    case 1: return false;
                    default:
                        return false;
                }
            }



            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                //gives the item back if its not Valid

                if(!isItemValid(slot, stack)){
                    return stack;
                }

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

        if(cap == CapabilityEnergy.ENERGY){
            return lazyEnergyHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    private static boolean hasEnoughEnergy(ItemShredderTile itemShredderTile){
        return itemShredderTile.ENERGY_STORAGE.getEnergyStored() >= itemShredderTile.ENERGY_REQ * itemShredderTile.maxProgress;
    }

    private static void shredderItem(ItemShredderTile itemShredderTile){
        itemShredderTile.itemHandler.getStackInSlot(0).shrink(1);
    }


    public void resetProgress() {
        this.progress = 0;
    }

/*
    public static void tick(World world, BlockPos pos, BlockState state, ItemShredderTile pTile) {
        if(!world.isRemote()){
            return;
        }

        if(hasRecipe(pTile)){

        }

    }*/

    @Override
    public void tick() {

        if(!world.isRemote()){
            return;
        }

        if(itemHandler.getStackInSlot(0).getCount() > 0){
            ENERGY_STORAGE.receiveEnergy(64,false);
        }

        if(ENERGY_STORAGE.getEnergyStored() >= ENERGY_REQ * maxProgress) {
            progress++;
            ENERGY_STORAGE.extractEnergy(ENERGY_REQ,false);
            markDirty();

            if(progress < maxProgress) {
                //maybe mistake ???
                shredderItem(this);
            }
        }else {
            resetProgress();
            markDirty();
        }



    }

    @Nullable

    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return null;
    }
}
