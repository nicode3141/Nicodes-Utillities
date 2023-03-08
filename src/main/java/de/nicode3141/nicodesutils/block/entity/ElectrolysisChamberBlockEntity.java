package de.nicode3141.nicodesutils.block.entity;

import de.nicode3141.nicodesutils.block.custom.ElectrolysisChamberBlock;
import de.nicode3141.nicodesutils.item.ModItems;
import de.nicode3141.nicodesutils.screen.ElectrolysisChamberMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ElectrolysisChamberBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHanler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;


    public ElectrolysisChamberBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECTROLYSIS_CHAMBER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index){
                    case 0 -> ElectrolysisChamberBlockEntity.this.progress;
                    case 1 -> ElectrolysisChamberBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {switch (index){
                  case 0 -> ElectrolysisChamberBlockEntity.this.progress = value;
                  case 1 -> ElectrolysisChamberBlockEntity.this.maxProgress = value;
                };

            }

            @Override
            public int getCount() {
                return 2; //MUST match the same count in the Menu Class!!!
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Electrolysis Chamber");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ElectrolysisChamberMenu(id, inventory,this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return lazyItemHanler.cast();
        }

        return super.getCapability(cap);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHanler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHanler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());

        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    }

    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++){
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition, inventory);
    }

    public static void tick(Level level, BlockPos pos, BlockState state, ElectrolysisChamberBlockEntity pEntity) {
        if(level.isClientSide()){
            return;
        }

        if(hasRecipe(pEntity)){
            pEntity.progress++;
            setChanged(level,pos,state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level,pos,state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static void craftItem(ElectrolysisChamberBlockEntity pEntity) {
        if(hasRecipe(pEntity)){
            pEntity.itemHandler.extractItem(1,1,false);
            pEntity.itemHandler.setStackInSlot(2,new ItemStack(ModItems.HYDROGEN_BUCKET.get(),
                    pEntity.itemHandler.getStackInSlot(2).getCount() + 1));

            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(ElectrolysisChamberBlockEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasWaterInFirstSlot = entity.itemHandler.getStackInSlot(1).getItem() == Items.WATER_BUCKET;

        return hasWaterInFirstSlot && canInsertAmountIntoOutputSlot(inventory) &&
                canInsertItemIntoOutputSlot(inventory, new ItemStack(ModItems.HYDROGEN_BUCKET.get(), 1));

    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(2).getItem() == stack.getItem() || inventory.getItem(2).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleContainer inventory) {
        return inventory.getItem(2).getMaxStackSize() > inventory.getItem(2).getCount();
    }
}
