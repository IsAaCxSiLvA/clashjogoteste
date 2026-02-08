package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Barracks - trains troops
 */
public class Barracks extends Building {
    public Barracks(int gridX, int gridY) {
        super(BuildingType.BARRACKS, gridX, gridY, 3, 3, Constants.COLOR_BARRACKS);
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 500 + (level * 200);
        this.hp = maxHp;
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.BARRACKS_COST + (level * 200);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 20f;
    }
}
