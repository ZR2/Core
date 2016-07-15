package me.gamerzking.core.block;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by GamerzKing on 7/14/2016.
 */
public class CustomBlockManager {

    private static CustomBlockManager instance = new CustomBlockManager();

    public static CustomBlockManager getInstance() {
        return instance;
    }

    private List<CustomBlock> customBlocks = new ArrayList<>();

    public List<CustomBlock> getCustomBlocks() {
        return customBlocks;
    }

    public void addCustomBlock(CustomBlock cb) {
        customBlocks.add(cb);
    }

    public void removeCustomBlock(CustomBlock cb) {
        customBlocks.remove(cb);
    }
}