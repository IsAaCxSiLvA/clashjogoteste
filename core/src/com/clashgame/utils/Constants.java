package com.clashgame.utils;

import com.badlogic.gdx.graphics.Color;

/**
 * Constants and configuration values for the game
 */
public class Constants {
    // Game configuration
    public static final int SCREEN_WIDTH = 1280;
    public static final int SCREEN_HEIGHT = 720;
    public static final int GRID_SIZE = 32;
    public static final int GRID_WIDTH = 40;
    public static final int GRID_HEIGHT = 22;
    
    // Resource constants
    public static final int STARTING_GOLD = 1000;
    public static final int STARTING_ELIXIR = 1000;
    public static final int STARTING_GEMS = 50;
    public static final int MAX_GOLD_STORAGE = 10000;
    public static final int MAX_ELIXIR_STORAGE = 10000;
    
    // Building costs
    public static final int TOWN_HALL_COST = 0; // Free for first one
    public static final int GOLD_MINE_COST = 200;
    public static final int ELIXIR_COLLECTOR_COST = 200;
    public static final int STORAGE_COST = 300;
    public static final int BARRACKS_COST = 500;
    public static final int WALL_COST = 50;
    public static final int ARCHER_TOWER_COST = 400;
    
    // Building production/storage
    public static final int GOLD_MINE_PRODUCTION = 10; // per second
    public static final int ELIXIR_COLLECTOR_PRODUCTION = 10; // per second
    public static final int STORAGE_CAPACITY = 5000;
    
    // Troop costs and stats
    public static final int BARBARIAN_COST = 50;
    public static final int BARBARIAN_HP = 100;
    public static final int BARBARIAN_DAMAGE = 15;
    public static final float BARBARIAN_SPEED = 40f;
    public static final float BARBARIAN_RANGE = 1f;
    
    public static final int ARCHER_COST = 80;
    public static final int ARCHER_HP = 60;
    public static final int ARCHER_DAMAGE = 12;
    public static final float ARCHER_SPEED = 35f;
    public static final float ARCHER_RANGE = 5f;
    
    public static final int GIANT_COST = 200;
    public static final int GIANT_HP = 500;
    public static final int GIANT_DAMAGE = 25;
    public static final float GIANT_SPEED = 20f;
    public static final float GIANT_RANGE = 1f;
    
    // Combat
    public static final int MAX_TROOP_CAPACITY = 20;
    public static final float ATTACK_COOLDOWN = 1f; // seconds
    public static final float TROOP_TRAIN_TIME = 5f; // seconds
    
    // Colors
    public static final Color COLOR_TOWN_HALL = Color.GOLD;
    public static final Color COLOR_GOLD_MINE = Color.YELLOW;
    public static final Color COLOR_ELIXIR_COLLECTOR = Color.MAGENTA;
    public static final Color COLOR_STORAGE = Color.GRAY;
    public static final Color COLOR_BARRACKS = Color.ORANGE;
    public static final Color COLOR_WALL = Color.BROWN;
    public static final Color COLOR_ARCHER_TOWER = Color.RED;
    
    public static final Color COLOR_BARBARIAN = Color.FIREBRICK;
    public static final Color COLOR_ARCHER = Color.LIME;
    public static final Color COLOR_GIANT = Color.BLUE;
    
    // UI
    public static final int BUTTON_WIDTH = 200;
    public static final int BUTTON_HEIGHT = 50;
    public static final int PADDING = 10;
}
