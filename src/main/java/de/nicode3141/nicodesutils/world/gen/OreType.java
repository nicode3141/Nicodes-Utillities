package de.nicode3141.nicodesutils.world.gen;

import de.nicode3141.nicodesutils.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

    NICKEL(Lazy.of(ModBlocks.NICKEL_ORE),8,25,50,2),
    LANATHANUM(Lazy.of(ModBlocks.LANATHANUM_ORE),2,5,20,1),
    COBALT(Lazy.of(ModBlocks.COBALT_ORE),6,12,33,1);


    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;

    private final int veinsPerChunk;


    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight,int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public Lazy<Block> getBlock(){
        return block;
    }

    public static OreType get(Block block){
        for (OreType ore : values()){
            if(block == ore.block){
                return ore;
            }
        }
        return null;
    }
}
