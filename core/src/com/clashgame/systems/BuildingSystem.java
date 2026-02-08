package com.clashgame.systems;

import com.badlogic.gdx.utils.Array;
import com.clashgame.entities.*;
import com.clashgame.utils.Constants;

/**
 * Manages all buildings in the game
 */
public class BuildingSystem {
    private Array<Building> buildings;
    private boolean[][] grid;
    private int gridWidth;
    private int gridHeight;
    
    public BuildingSystem() {
        buildings = new Array<>();
        gridWidth = Constants.GRID_WIDTH;
        gridHeight = Constants.GRID_HEIGHT;
        grid = new boolean[gridWidth][gridHeight];
    }
    
    public void update(float delta) {
        for (Building building : buildings) {
            building.update(delta);
        }
    }
    
    public boolean canPlaceBuilding(int gridX, int gridY, int width, int height) {
        if (gridX < 0 || gridY < 0 || gridX + width > gridWidth || gridY + height > gridHeight) {
            return false;
        }
        
        for (int x = gridX; x < gridX + width; x++) {
            for (int y = gridY; y < gridY + height; y++) {
                if (grid[x][y]) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private void occupyGrid(Building building) {
        int x = building.getGridX();
        int y = building.getGridY();
        int w = building.getWidth();
        int h = building.getHeight();
        
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                grid[i][j] = true;
            }
        }
    }
    
    private void freeGrid(Building building) {
        int x = building.getGridX();
        int y = building.getGridY();
        int w = building.getWidth();
        int h = building.getHeight();
        
        for (int i = x; i < x + w; i++) {
            for (int j = y; j < y + h; j++) {
                grid[i][j] = false;
            }
        }
    }
    
    public boolean addBuilding(Building building) {
        if (canPlaceBuilding(building.getGridX(), building.getGridY(), 
                            building.getWidth(), building.getHeight())) {
            buildings.add(building);
            occupyGrid(building);
            return true;
        }
        return false;
    }
    
    public boolean moveBuilding(Building building, int newX, int newY) {
        freeGrid(building);
        if (canPlaceBuilding(newX, newY, building.getWidth(), building.getHeight())) {
            building.setGridPosition(newX, newY);
            occupyGrid(building);
            return true;
        } else {
            occupyGrid(building);
            return false;
        }
    }
    
    public void removeBuilding(Building building) {
        freeGrid(building);
        buildings.removeValue(building, true);
    }
    
    public Array<Building> getBuildings() {
        return buildings;
    }
    
    public Building getBuildingAt(int gridX, int gridY) {
        for (Building building : buildings) {
            if (gridX >= building.getGridX() && gridX < building.getGridX() + building.getWidth() &&
                gridY >= building.getGridY() && gridY < building.getGridY() + building.getHeight()) {
                return building;
            }
        }
        return null;
    }
    
    public int collectFromMines(ResourceSystem resourceSystem) {
        int totalGold = 0;
        for (Building building : buildings) {
            if (building instanceof GoldMine) {
                int gold = ((GoldMine) building).collectGold();
                resourceSystem.addGold(gold);
                totalGold += gold;
            }
        }
        return totalGold;
    }
    
    public int collectFromCollectors(ResourceSystem resourceSystem) {
        int totalElixir = 0;
        for (Building building : buildings) {
            if (building instanceof ElixirCollector) {
                int elixir = ((ElixirCollector) building).collectElixir();
                resourceSystem.addElixir(elixir);
                totalElixir += elixir;
            }
        }
        return totalElixir;
    }
    
    public Building getTownHall() {
        for (Building building : buildings) {
            if (building instanceof TownHall) {
                return building;
            }
        }
        return null;
    }
}
