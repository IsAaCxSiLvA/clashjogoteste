package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Wall - defensive structure
 */
public class Wall extends Building {
    public Wall(int gridX, int gridY) {
        super(BuildingType.WALL, gridX, gridY, 1, 1, Constants.COLOR_WALL);
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 100 + (level * 50);
        this.hp = maxHp;
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.WALL_COST + (level * 25);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 5f;
    }
}
