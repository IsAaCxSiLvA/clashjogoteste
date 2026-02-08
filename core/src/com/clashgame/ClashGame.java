package com.clashgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.clashgame.screens.MainMenuScreen;
import com.clashgame.systems.BuildingSystem;
import com.clashgame.systems.ResourceSystem;
import com.clashgame.systems.SaveSystem;

/**
 * Main game class - Entry point for the Clash Game
 */
public class ClashGame extends Game {
    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;
    public BitmapFont font;
    
    // Game systems
    public ResourceSystem resourceSystem;
    public BuildingSystem buildingSystem;
    public SaveSystem saveSystem;
    
    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        
        // Initialize game systems
        resourceSystem = new ResourceSystem();
        buildingSystem = new BuildingSystem();
        saveSystem = new SaveSystem();
        
        // Start with main menu
        setScreen(new MainMenuScreen(this));
    }
    
    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
        if (screen != null) {
            screen.dispose();
        }
    }
}
