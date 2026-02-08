package com.clashgame;

public class GameManager {
    private int gold;
    private int elixir;
    private int gems;
    private int level;
    private int townHallLevel;
    private int barracksCount;
    private int cannonsCount;
    private int troopsCount;
    private int lastLoot;
    
    public GameManager() {
        gold = 1000;
        elixir = 500;
        gems = 50;
        level = 1;
        townHallLevel = 1;
        barracksCount = 0;
        cannonsCount = 0;
        troopsCount = 0;
        lastLoot = 0;
    }
    
    public boolean upgradeTownHall() {
        int cost = townHallLevel * 1000;
        if (gold >= cost) {
            gold -= cost;
            townHallLevel++;
            level++;
            return true;
        }
        return false;
    }
    
    public boolean buildBarracks() {
        if (barracksCount >= townHallLevel * 2) {
            return false;
        }
        int cost = 500;
        if (gold >= cost) {
            gold -= cost;
            barracksCount++;
            return true;
        }
        return false;
    }
    
    public boolean buildCannon() {
        if (cannonsCount >= townHallLevel * 3) {
            return false;
        }
        int cost = 800;
        if (gold >= cost) {
            gold -= cost;
            cannonsCount++;
            return true;
        }
        return false;
    }
    
    public void collectGold() {
        int amount = 100 + (townHallLevel * 50);
        gold += amount;
    }
    
    public boolean trainTroops() {
        if (barracksCount == 0) {
            return false;
        }
        int cost = 50;
        if (elixir >= cost) {
            elixir -= cost;
            troopsCount += 10 * barracksCount;
            return true;
        }
        return false;
    }
    
    public boolean attack() {
        if (troopsCount < 10) {
            return false;
        }
        troopsCount -= 10;
        lastLoot = 200 + (level * 100);
        gold += lastLoot;
        elixir += 50;
        return true;
    }
    
    // Getters
    public int getGold() { return gold; }
    public int getElixir() { return elixir; }
    public int getGems() { return gems; }
    public int getLevel() { return level; }
    public int getTownHallLevel() { return townHallLevel; }
    public int getBarracksCount() { return barracksCount; }
    public int getCannonsCount() { return cannonsCount; }
    public int getTroopsCount() { return troopsCount; }
    public int getLastLoot() { return lastLoot; }
}
