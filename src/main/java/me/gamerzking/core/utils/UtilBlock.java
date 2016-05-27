package me.gamerzking.core.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by GamerzKing on 5/2/2016.
 */
public class UtilBlock {

    /**
     * Don't let anyone instantiate this class.
     */
    private UtilBlock() {}

    /**
     * Gets the nearby blocks to the {@link Block} provided, with the types of {@link Material} provided.
     *
     * @param block The block you're getting nearby blocks to.
     * @param allowedMaterials The list of materials that are allowed to be nearby.
     * @param blocks The set of blocks the code will refer to. Used to prevent duplicates.
     * @return The set of nearby blocks.
     */

    public static Set<Block> getNearbyBlocks(Block block, List<Material> allowedMaterials, Set<Block> blocks) {

        for (int x = -1; x < 2; x++) {
            for (int y = -1; y < 2; y++) {
                for (int z = -1; z < 2; z++) {

                    Block targetBlock = block.getLocation().add(x, y, z).getBlock();

                    if (targetBlock != null && !blocks.contains(targetBlock) && allowedMaterials.contains(targetBlock.getType())) {

                        blocks.add(targetBlock);
                        blocks.addAll(getNearbyBlocks(targetBlock, allowedMaterials, blocks));
                    }
                }
            }
        }
        return blocks;
    }

    /**
     * Sets the {@link org.bukkit.block.Block} at the specified {@link org.bukkit.Location} to the given {@link org.bukkit.Material}
     *
     * @param location The location the colored block will appear at.
     * @param material The material the colored block will appear in (wool, stained glass, etc.).
     * @param data The data being applied to the block.
     */

    public static void setColoredBlock(Location location, Material material, byte data) {

        ItemStack itemStack = new ItemStack(material, 1, data);
        Block block = location.getBlock();

        block.setType(material);

        BlockState blockState = block.getState();

        blockState.setData(itemStack.getData());
        blockState.update();
    }
}