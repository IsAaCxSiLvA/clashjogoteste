package com.clashgame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

/**
 * Base class for all buildings in the game
 */
public abstract class Building {
    public enum BuildingType {
        TOWN_HALL, GOLD_MINE, ELIXIR_COLLECTOR, STORAGE, 
        BARRACKS, WALL, ARCHER_TOWER
    }
    
    protected BuildingType type;
    protected int gridX, gridY;
    protected int width, height; // in grid cells
    protected int level;
    protected int maxLevel;
    protected int hp;
    protected int maxHp;
    protected boolean isConstructing;
    protected float constructionTimeLeft;
    protected Color color;
    
    public Building(BuildingType type, int gridX, int gridY, int width, int height, Color color) {
        this.type = type;
        this.gridX = gridX;
        this.gridY = gridY;
        this.width = width;
        this.height = height;
        this.level = 1;
        this.maxLevel = 5;
        this.color = color;
        this.isConstructing = false;
        this.constructionTimeLeft = 0;
        initializeStats();
    }
    
    protected abstract void initializeStats();
    
    public abstract int getUpgradeCost();
    
    public abstract float getUpgradeTime();
    
    public void update(float delta) {
        if (isConstructing) {
            constructionTimeLeft -= delta;
            if (constructionTimeLeft <= 0) {
                isConstructing = false;
                constructionTimeLeft = 0;
            }
        }
    }
    
    public void startUpgrade() {
        if (level < maxLevel) {
            isConstructing = true;
            constructionTimeLeft = getUpgradeTime();
            level++;
            initializeStats();
        }
    }
    
    public boolean canUpgrade() {
        return level < maxLevel && !isConstructing;
    }
    
    public Rectangle getBounds() {
        return new Rectangle(gridX, gridY, width, height);
    }
    
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
    }
    
    public boolean isDestroyed() {
        return hp <= 0;
    }
    
    // Getters and setters
    public BuildingType getType() { return type; }
    public int getGridX() { return gridX; }
    public int getGridY() { return gridY; }
    public void setGridPosition(int x, int y) { this.gridX = x; this.gridY = y; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getLevel() { return level; }
    public int getMaxLevel() { return maxLevel; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean isConstructing() { return isConstructing; }
    public float getConstructionTimeLeft() { return constructionTimeLeft; }
    public Color getColor() { return color; }
}
