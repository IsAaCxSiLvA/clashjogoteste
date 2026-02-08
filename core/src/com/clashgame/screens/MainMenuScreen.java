package com.clashgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.clashgame.ClashGame;
import com.clashgame.utils.Constants;

/**
 * Main menu screen with New Game and Continue options
 */
public class MainMenuScreen implements Screen {
    private ClashGame game;
    private Rectangle newGameButton;
    private Rectangle continueButton;
    private Rectangle exitButton;
    
    public MainMenuScreen(ClashGame game) {
        this.game = game;
        
        float centerX = Constants.SCREEN_WIDTH / 2f - Constants.BUTTON_WIDTH / 2f;
        newGameButton = new Rectangle(centerX, 400, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        continueButton = new Rectangle(centerX, 320, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
        exitButton = new Rectangle(centerX, 240, Constants.BUTTON_WIDTH, Constants.BUTTON_HEIGHT);
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.2f, 0.3f, 0.4f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Draw UI
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        // New Game button
        game.shapeRenderer.setColor(Color.GREEN);
        game.shapeRenderer.rect(newGameButton.x, newGameButton.y, newGameButton.width, newGameButton.height);
        
        // Continue button
        if (game.saveSystem.hasSaveData()) {
            game.shapeRenderer.setColor(Color.BLUE);
        } else {
            game.shapeRenderer.setColor(Color.GRAY);
        }
        game.shapeRenderer.rect(continueButton.x, continueButton.y, continueButton.width, continueButton.height);
        
        // Exit button
        game.shapeRenderer.setColor(Color.RED);
        game.shapeRenderer.rect(exitButton.x, exitButton.y, exitButton.width, exitButton.height);
        
        game.shapeRenderer.end();
        
        // Draw text
        game.batch.begin();
        game.font.setColor(Color.BLACK);
        
        // Title
        String title = "CLASH GAME";
        game.font.getData().setScale(3f);
        game.font.draw(game.batch, title, Constants.SCREEN_WIDTH / 2f - 120, 550);
        game.font.getData().setScale(1.5f);
        
        // Button labels
        game.font.draw(game.batch, "New Game", newGameButton.x + 50, newGameButton.y + 33);
        game.font.draw(game.batch, "Continue", continueButton.x + 55, continueButton.y + 33);
        game.font.draw(game.batch, "Exit", exitButton.x + 75, exitButton.y + 33);
        
        game.batch.end();
        
        // Handle input
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Constants.SCREEN_HEIGHT - Gdx.input.getY();
            
            if (newGameButton.contains(touchX, touchY)) {
                // Start new game
                game.saveSystem.clearSave();
                game.resourceSystem = new ResourceSystem();
                game.buildingSystem = new BuildingSystem();
                game.setScreen(new GameScreen(game));
            } else if (continueButton.contains(touchX, touchY) && game.saveSystem.hasSaveData()) {
                // Continue game
                game.resourceSystem = new ResourceSystem();
                game.buildingSystem = new BuildingSystem();
                game.saveSystem.loadGame(game.resourceSystem, game.buildingSystem);
                game.setScreen(new GameScreen(game));
            } else if (exitButton.contains(touchX, touchY)) {
                Gdx.app.exit();
            }
        }
    }
    
    @Override
    public void resize(int width, int height) {
    }
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
    
    @Override
    public void hide() {
    }
    
    @Override
    public void dispose() {
    }
}
