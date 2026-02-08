package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Gold Mine - generates gold over time
 */
public class GoldMine extends Building {
    private float productionTimer;
    private int productionRate;
    
    public GoldMine(int gridX, int gridY) {
        super(BuildingType.GOLD_MINE, gridX, gridY, 2, 2, Constants.COLOR_GOLD_MINE);
        this.productionTimer = 0;
        this.productionRate = Constants.GOLD_MINE_PRODUCTION;
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 300 + (level * 100);
        this.hp = maxHp;
        this.productionRate = Constants.GOLD_MINE_PRODUCTION + (level * 5);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        if (!isConstructing) {
            productionTimer += delta;
        }
    }
    
    public int collectGold() {
        int gold = (int)(productionTimer * productionRate);
        productionTimer = 0;
        return gold;
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.GOLD_MINE_COST + (level * 100);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 15f;
    }
    
    public int getProductionRate() {
        return productionRate;
    }
}
