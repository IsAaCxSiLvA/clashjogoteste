package com.clashgame.systems;

import com.clashgame.entities.Resource;
import com.clashgame.utils.Constants;

/**
 * Manages game resources (Gold, Elixir, Gems)
 */
public class ResourceSystem {
    private Resource gold;
    private Resource elixir;
    private Resource gems;
    
    public ResourceSystem() {
        gold = new Resource(Resource.Type.GOLD, 
                           Constants.STARTING_GOLD, 
                           Constants.MAX_GOLD_STORAGE);
        elixir = new Resource(Resource.Type.ELIXIR, 
                             Constants.STARTING_ELIXIR, 
                             Constants.MAX_ELIXIR_STORAGE);
        gems = new Resource(Resource.Type.GEMS, 
                           Constants.STARTING_GEMS, 
                           10000);
    }
    
    public boolean spendGold(int amount) {
        return gold.subtract(amount);
    }
    
    public boolean spendElixir(int amount) {
        return elixir.subtract(amount);
    }
    
    public boolean spendGems(int amount) {
        return gems.subtract(amount);
    }
    
    public void addGold(int amount) {
        gold.add(amount);
    }
    
    public void addElixir(int amount) {
        elixir.add(amount);
    }
    
    public void addGems(int amount) {
        gems.add(amount);
    }
    
    public boolean canAffordGold(int cost) {
        return gold.canAfford(cost);
    }
    
    public boolean canAffordElixir(int cost) {
        return elixir.canAfford(cost);
    }
    
    public Resource getGold() {
        return gold;
    }
    
    public Resource getElixir() {
        return elixir;
    }
    
    public Resource getGems() {
        return gems;
    }
    
    public void increaseStorageCapacity(int amount) {
        gold.setMaxCapacity(gold.getMaxCapacity() + amount);
        elixir.setMaxCapacity(elixir.getMaxCapacity() + amount);
    }
}
