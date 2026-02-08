package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Elixir Collector - generates elixir over time
 */
public class ElixirCollector extends Building {
    private float productionTimer;
    private int productionRate;
    
    public ElixirCollector(int gridX, int gridY) {
        super(BuildingType.ELIXIR_COLLECTOR, gridX, gridY, 2, 2, Constants.COLOR_ELIXIR_COLLECTOR);
        this.productionTimer = 0;
        this.productionRate = Constants.ELIXIR_COLLECTOR_PRODUCTION;
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 300 + (level * 100);
        this.hp = maxHp;
        this.productionRate = Constants.ELIXIR_COLLECTOR_PRODUCTION + (level * 5);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        if (!isConstructing) {
            productionTimer += delta;
        }
    }
    
    public int collectElixir() {
        int elixir = (int)(productionTimer * productionRate);
        productionTimer = 0;
        return elixir;
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.ELIXIR_COLLECTOR_COST + (level * 100);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 15f;
    }
    
    public int getProductionRate() {
        return productionRate;
    }
}
