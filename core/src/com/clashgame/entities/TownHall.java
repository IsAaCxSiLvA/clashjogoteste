package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Town Hall - main building
 */
public class TownHall extends Building {
    public TownHall(int gridX, int gridY) {
        super(BuildingType.TOWN_HALL, gridX, gridY, 3, 3, Constants.COLOR_TOWN_HALL);
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 1000 + (level * 500);
        this.hp = maxHp;
    }
    
    @Override
    public int getUpgradeCost() {
        return level * 500;
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 30f;
    }
}
