package de.nicode3141.nicodesutils.block.custom;

import de.nicode3141.nicodesutils.world.dimension.ModDimensions;
import de.nicode3141.nicodesutils.world.dimension.RgbTeleporter;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;

public class BrpTeleporter extends HorizontalBlock {
    public BrpTeleporter(Properties builder) {
        super(builder);
    }


    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HORIZONTAL_FACING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

   /* public BrpTeleporter(BlockPos pos, boolean b) {
        super();

    }*/

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            if (!player.isCrouching()) {
                MinecraftServer server = worldIn.getServer();

                if (server != null) {
                    if (worldIn.getDimensionKey() == ModDimensions.BRPDim) {
                        ServerWorld overWorld = server.getWorld(World.OVERWORLD);
                        if (overWorld != null) {
                           player.changeDimension(overWorld, (ITeleporter) new RgbTeleporter(pos, false));
                        }
                    } else {
                        ServerWorld brpdim = server.getWorld(ModDimensions.BRPDim);
                        if (brpdim != null) {
                          player.changeDimension(brpdim, (ITeleporter) new RgbTeleporter(pos, true));
                        }
                    }
                    return ActionResultType.SUCCESS;
                }
            }
        }


        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}
