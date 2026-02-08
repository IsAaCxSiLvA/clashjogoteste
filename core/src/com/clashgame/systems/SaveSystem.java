package com.clashgame.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;
import com.clashgame.entities.*;

/**
 * Handles saving and loading game data
 */
public class SaveSystem {
    private static final String PREFS_NAME = "ClashGameSave";
    private Preferences prefs;
    
    public SaveSystem() {
        prefs = Gdx.app.getPreferences(PREFS_NAME);
    }
    
    public void saveGame(ResourceSystem resourceSystem, BuildingSystem buildingSystem) {
        // Save resources
        prefs.putInteger("gold", resourceSystem.getGold().getAmount());
        prefs.putInteger("elixir", resourceSystem.getElixir().getAmount());
        prefs.putInteger("gems", resourceSystem.getGems().getAmount());
        prefs.putInteger("goldCapacity", resourceSystem.getGold().getMaxCapacity());
        prefs.putInteger("elixirCapacity", resourceSystem.getElixir().getMaxCapacity());
        
        // Save buildings
        Array<Building> buildings = buildingSystem.getBuildings();
        prefs.putInteger("buildingCount", buildings.size);
        
        for (int i = 0; i < buildings.size; i++) {
            Building b = buildings.get(i);
            String prefix = "building_" + i + "_";
            
            prefs.putString(prefix + "type", b.getType().name());
            prefs.putInteger(prefix + "x", b.getGridX());
            prefs.putInteger(prefix + "y", b.getGridY());
            prefs.putInteger(prefix + "level", b.getLevel());
            prefs.putInteger(prefix + "hp", b.getHp());
            prefs.putBoolean(prefix + "constructing", b.isConstructing());
            prefs.putFloat(prefix + "constructionTime", b.getConstructionTimeLeft());
        }
        
        prefs.flush();
    }
    
    public void loadGame(ResourceSystem resourceSystem, BuildingSystem buildingSystem) {
        // Load resources
        resourceSystem.getGold().setAmount(prefs.getInteger("gold", 1000));
        resourceSystem.getElixir().setAmount(prefs.getInteger("elixir", 1000));
        resourceSystem.getGems().setAmount(prefs.getInteger("gems", 50));
        
        int goldCap = prefs.getInteger("goldCapacity", 10000);
        int elixirCap = prefs.getInteger("elixirCapacity", 10000);
        resourceSystem.getGold().setMaxCapacity(goldCap);
        resourceSystem.getElixir().setMaxCapacity(elixirCap);
        
        // Load buildings
        int buildingCount = prefs.getInteger("buildingCount", 0);
        
        for (int i = 0; i < buildingCount; i++) {
            String prefix = "building_" + i + "_";
            
            String typeStr = prefs.getString(prefix + "type");
            int x = prefs.getInteger(prefix + "x");
            int y = prefs.getInteger(prefix + "y");
            int level = prefs.getInteger(prefix + "level");
            int hp = prefs.getInteger(prefix + "hp");
            
            Building building = createBuilding(typeStr, x, y);
            if (building != null) {
                // Set level by upgrading
                for (int l = 1; l < level; l++) {
                    building.startUpgrade();
                }
                // Restore HP
                building.takeDamage(building.getMaxHp() - hp);
                
                buildingSystem.addBuilding(building);
            }
        }
    }
    
    private Building createBuilding(String type, int x, int y) {
        Building.BuildingType buildingType = Building.BuildingType.valueOf(type);
        
        switch (buildingType) {
            case TOWN_HALL:
                return new TownHall(x, y);
            case GOLD_MINE:
                return new GoldMine(x, y);
            case ELIXIR_COLLECTOR:
                return new ElixirCollector(x, y);
            case STORAGE:
                return new Storage(x, y);
            case BARRACKS:
                return new Barracks(x, y);
            case WALL:
                return new Wall(x, y);
            case ARCHER_TOWER:
                return new ArcherTower(x, y);
            default:
                return null;
        }
    }
    
    public boolean hasSaveData() {
        return prefs.contains("buildingCount");
    }
    
    public void clearSave() {
        prefs.clear();
        prefs.flush();
    }
}
