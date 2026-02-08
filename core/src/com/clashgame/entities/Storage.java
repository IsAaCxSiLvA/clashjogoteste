package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Storage - increases resource capacity
 */
public class Storage extends Building {
    private int storageCapacity;
    
    public Storage(int gridX, int gridY) {
        super(BuildingType.STORAGE, gridX, gridY, 2, 2, Constants.COLOR_STORAGE);
        this.storageCapacity = Constants.STORAGE_CAPACITY;
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 400 + (level * 150);
        this.hp = maxHp;
        this.storageCapacity = Constants.STORAGE_CAPACITY + (level * 1000);
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.STORAGE_COST + (level * 150);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 10f;
    }
    
    public int getStorageCapacity() {
        return storageCapacity;
    }
}
