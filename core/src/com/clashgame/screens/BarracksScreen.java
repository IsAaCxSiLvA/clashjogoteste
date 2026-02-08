package com.clashgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.clashgame.ClashGame;
import com.clashgame.entities.Troop;
import com.clashgame.utils.Constants;

/**
 * Barracks screen for training troops
 */
public class BarracksScreen implements Screen {
    private ClashGame game;
    private Array<Troop> trainedTroops;
    private Rectangle barbarianButton;
    private Rectangle archerButton;
    private Rectangle giantButton;
    private Rectangle backButton;
    private float trainTimer;
    private Troop.TroopType trainingType;
    private boolean isTraining;
    
    public BarracksScreen(ClashGame game) {
        this.game = game;
        this.trainedTroops = new Array<>();
        this.trainTimer = 0;
        this.isTraining = false;
        
        float centerX = Constants.SCREEN_WIDTH / 2f;
        barbarianButton = new Rectangle(centerX - 350, 400, 200, 100);
        archerButton = new Rectangle(centerX - 100, 400, 200, 100);
        giantButton = new Rectangle(centerX + 150, 400, 200, 100);
        backButton = new Rectangle(50, 50, 150, 50);
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        // Update training
        if (isTraining) {
            trainTimer -= delta;
            if (trainTimer <= 0) {
                isTraining = false;
                trainTimer = 0;
                // Training complete (troops added in handleInput)
            }
        }
        
        // Clear screen
        Gdx.gl.glClearColor(0.3f, 0.2f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Draw UI
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        // Troop buttons
        game.shapeRenderer.setColor(Constants.COLOR_BARBARIAN);
        game.shapeRenderer.rect(barbarianButton.x, barbarianButton.y, barbarianButton.width, barbarianButton.height);
        
        game.shapeRenderer.setColor(Constants.COLOR_ARCHER);
        game.shapeRenderer.rect(archerButton.x, archerButton.y, archerButton.width, archerButton.height);
        
        game.shapeRenderer.setColor(Constants.COLOR_GIANT);
        game.shapeRenderer.rect(giantButton.x, giantButton.y, giantButton.width, giantButton.height);
        
        // Back button
        game.shapeRenderer.setColor(Color.GRAY);
        game.shapeRenderer.rect(backButton.x, backButton.y, backButton.width, backButton.height);
        
        // Training progress bar
        if (isTraining) {
            game.shapeRenderer.setColor(Color.DARK_GRAY);
            game.shapeRenderer.rect(Constants.SCREEN_WIDTH / 2f - 200, 250, 400, 30);
            game.shapeRenderer.setColor(Color.GREEN);
            float progress = 1f - (trainTimer / Constants.TROOP_TRAIN_TIME);
            game.shapeRenderer.rect(Constants.SCREEN_WIDTH / 2f - 200, 250, 400 * progress, 30);
        }
        
        game.shapeRenderer.end();
        
        // Draw text
        game.batch.begin();
        game.font.setColor(Color.WHITE);
        
        // Title
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "BARRACKS - Train Troops", Constants.SCREEN_WIDTH / 2f - 200, 600);
        game.font.getData().setScale(1.5f);
        
        // Resource display
        game.font.draw(game.batch, "Elixir: " + game.resourceSystem.getElixir().getAmount(), 
                      Constants.SCREEN_WIDTH - 250, Constants.SCREEN_HEIGHT - 20);
        
        // Troop info
        game.font.setColor(Color.BLACK);
        game.font.getData().setScale(1.2f);
        
        // Barbarian
        game.font.draw(game.batch, "BARBARIAN", barbarianButton.x + 30, barbarianButton.y + 80);
        game.font.getData().setScale(0.9f);
        game.font.draw(game.batch, "Cost: " + Constants.BARBARIAN_COST, barbarianButton.x + 10, barbarianButton.y + 55);
        game.font.draw(game.batch, "HP: " + Constants.BARBARIAN_HP, barbarianButton.x + 10, barbarianButton.y + 40);
        game.font.draw(game.batch, "DMG: " + Constants.BARBARIAN_DAMAGE, barbarianButton.x + 10, barbarianButton.y + 25);
        
        // Archer
        game.font.getData().setScale(1.2f);
        game.font.draw(game.batch, "ARCHER", archerButton.x + 50, archerButton.y + 80);
        game.font.getData().setScale(0.9f);
        game.font.draw(game.batch, "Cost: " + Constants.ARCHER_COST, archerButton.x + 10, archerButton.y + 55);
        game.font.draw(game.batch, "HP: " + Constants.ARCHER_HP, archerButton.x + 10, archerButton.y + 40);
        game.font.draw(game.batch, "DMG: " + Constants.ARCHER_DAMAGE, archerButton.x + 10, archerButton.y + 25);
        
        // Giant
        game.font.getData().setScale(1.2f);
        game.font.draw(game.batch, "GIANT", giantButton.x + 60, giantButton.y + 80);
        game.font.getData().setScale(0.9f);
        game.font.draw(game.batch, "Cost: " + Constants.GIANT_COST, giantButton.x + 10, giantButton.y + 55);
        game.font.draw(game.batch, "HP: " + Constants.GIANT_HP, giantButton.x + 10, giantButton.y + 40);
        game.font.draw(game.batch, "DMG: " + Constants.GIANT_DAMAGE, giantButton.x + 10, giantButton.y + 25);
        
        // Back button
        game.font.getData().setScale(1.5f);
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Back", backButton.x + 40, backButton.y + 33);
        
        // Training status
        if (isTraining) {
            game.font.setColor(Color.YELLOW);
            game.font.draw(game.batch, "Training... " + (int)trainTimer + "s", 
                          Constants.SCREEN_WIDTH / 2f - 80, 295);
        }
        
        // Troop capacity
        game.font.setColor(Color.WHITE);
        game.font.draw(game.batch, "Troops: " + trainedTroops.size + " / " + Constants.MAX_TROOP_CAPACITY, 
                      50, Constants.SCREEN_HEIGHT - 20);
        
        game.batch.end();
        
        // Handle input
        handleInput();
    }
    
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Constants.SCREEN_HEIGHT - Gdx.input.getY();
            
            if (backButton.contains(touchX, touchY)) {
                game.setScreen(new GameScreen(game));
                return;
            }
            
            if (!isTraining && trainedTroops.size < Constants.MAX_TROOP_CAPACITY) {
                if (barbarianButton.contains(touchX, touchY)) {
                    if (game.resourceSystem.canAffordElixir(Constants.BARBARIAN_COST)) {
                        game.resourceSystem.spendElixir(Constants.BARBARIAN_COST);
                        trainingType = Troop.TroopType.BARBARIAN;
                        isTraining = true;
                        trainTimer = Constants.TROOP_TRAIN_TIME;
                    }
                } else if (archerButton.contains(touchX, touchY)) {
                    if (game.resourceSystem.canAffordElixir(Constants.ARCHER_COST)) {
                        game.resourceSystem.spendElixir(Constants.ARCHER_COST);
                        trainingType = Troop.TroopType.ARCHER;
                        isTraining = true;
                        trainTimer = Constants.TROOP_TRAIN_TIME;
                    }
                } else if (giantButton.contains(touchX, touchY)) {
                    if (game.resourceSystem.canAffordElixir(Constants.GIANT_COST)) {
                        game.resourceSystem.spendElixir(Constants.GIANT_COST);
                        trainingType = Troop.TroopType.GIANT;
                        isTraining = true;
                        trainTimer = Constants.TROOP_TRAIN_TIME;
                    }
                }
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
