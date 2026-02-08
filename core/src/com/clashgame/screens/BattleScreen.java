package com.clashgame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.clashgame.ClashGame;
import com.clashgame.entities.*;
import com.clashgame.systems.BuildingSystem;
import com.clashgame.systems.CombatSystem;
import com.clashgame.utils.Constants;

/**
 * Battle screen for attacking enemy bases
 */
public class BattleScreen implements Screen {
    private ClashGame game;
    private BuildingSystem enemyBuildings;
    private CombatSystem combatSystem;
    private Array<Troop> deployedTroops;
    private Rectangle barbarianDeployButton;
    private Rectangle archerDeployButton;
    private Rectangle giantDeployButton;
    private Rectangle backButton;
    private boolean battleEnded;
    private boolean victory;
    private int troopsAvailable;
    
    public BattleScreen(ClashGame game) {
        this.game = game;
        this.deployedTroops = new Array<>();
        this.battleEnded = false;
        this.victory = false;
        this.troopsAvailable = 10; // Simple troop count for battle
        
        // Create enemy base
        enemyBuildings = new BuildingSystem();
        setupEnemyBase();
        
        combatSystem = new CombatSystem(enemyBuildings);
        
        // Initialize buttons
        barbarianDeployButton = new Rectangle(50, 50, 120, 50);
        archerDeployButton = new Rectangle(180, 50, 120, 50);
        giantDeployButton = new Rectangle(310, 50, 120, 50);
        backButton = new Rectangle(Constants.SCREEN_WIDTH - 170, 50, 150, 50);
    }
    
    private void setupEnemyBase() {
        // Create a simple enemy base
        enemyBuildings.addBuilding(new TownHall(18, 10));
        enemyBuildings.addBuilding(new GoldMine(12, 8));
        enemyBuildings.addBuilding(new ElixirCollector(24, 8));
        enemyBuildings.addBuilding(new ArcherTower(15, 13));
        enemyBuildings.addBuilding(new ArcherTower(21, 13));
        enemyBuildings.addBuilding(new Wall(16, 9));
        enemyBuildings.addBuilding(new Wall(17, 9));
        enemyBuildings.addBuilding(new Wall(19, 9));
        enemyBuildings.addBuilding(new Wall(20, 9));
        enemyBuildings.addBuilding(new Wall(16, 13));
        enemyBuildings.addBuilding(new Wall(20, 13));
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        if (!battleEnded) {
            // Update combat
            combatSystem.update(delta);
            enemyBuildings.update(delta);
            
            // Check victory/defeat conditions
            if (combatSystem.checkVictory()) {
                battleEnded = true;
                victory = true;
                // Award resources
                game.resourceSystem.addGold(500);
                game.resourceSystem.addElixir(300);
            } else if (combatSystem.getTroops().size == 0 && troopsAvailable == 0) {
                battleEnded = true;
                victory = false;
            }
        }
        
        // Clear screen
        Gdx.gl.glClearColor(0.4f, 0.3f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Draw grid
        drawGrid();
        
        // Draw buildings
        drawBuildings();
        
        // Draw troops
        drawTroops();
        
        // Draw UI
        drawUI();
        
        // Handle input
        if (!battleEnded) {
            handleInput();
        }
    }
    
    private void drawGrid() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.DARK_GRAY);
        
        for (int x = 0; x <= Constants.GRID_WIDTH; x++) {
            game.shapeRenderer.line(x * Constants.GRID_SIZE, 0, 
                                   x * Constants.GRID_SIZE, Constants.GRID_HEIGHT * Constants.GRID_SIZE);
        }
        
        for (int y = 0; y <= Constants.GRID_HEIGHT; y++) {
            game.shapeRenderer.line(0, y * Constants.GRID_SIZE, 
                                   Constants.GRID_WIDTH * Constants.GRID_SIZE, y * Constants.GRID_SIZE);
        }
        
        game.shapeRenderer.end();
    }
    
    private void drawBuildings() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        for (Building building : enemyBuildings.getBuildings()) {
            if (building.isDestroyed()) continue;
            
            game.shapeRenderer.setColor(building.getColor());
            game.shapeRenderer.rect(
                building.getGridX() * Constants.GRID_SIZE + 2,
                building.getGridY() * Constants.GRID_SIZE + 2,
                building.getWidth() * Constants.GRID_SIZE - 4,
                building.getHeight() * Constants.GRID_SIZE - 4
            );
            
            // Draw HP bar
            float hpPercent = (float)building.getHp() / building.getMaxHp();
            game.shapeRenderer.setColor(Color.RED);
            game.shapeRenderer.rect(
                building.getGridX() * Constants.GRID_SIZE,
                (building.getGridY() + building.getHeight()) * Constants.GRID_SIZE + 2,
                building.getWidth() * Constants.GRID_SIZE,
                4
            );
            game.shapeRenderer.setColor(Color.GREEN);
            game.shapeRenderer.rect(
                building.getGridX() * Constants.GRID_SIZE,
                (building.getGridY() + building.getHeight()) * Constants.GRID_SIZE + 2,
                building.getWidth() * Constants.GRID_SIZE * hpPercent,
                4
            );
        }
        
        game.shapeRenderer.end();
    }
    
    private void drawTroops() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        for (Troop troop : combatSystem.getTroops()) {
            if (!troop.isAlive()) continue;
            
            game.shapeRenderer.setColor(troop.getColor());
            float x = troop.getPosition().x * Constants.GRID_SIZE;
            float y = troop.getPosition().y * Constants.GRID_SIZE;
            game.shapeRenderer.circle(x, y, 8);
            
            // Draw HP bar
            float hpPercent = (float)troop.getHp() / troop.getMaxHp();
            game.shapeRenderer.setColor(Color.RED);
            game.shapeRenderer.rect(x - 10, y + 12, 20, 3);
            game.shapeRenderer.setColor(Color.GREEN);
            game.shapeRenderer.rect(x - 10, y + 12, 20 * hpPercent, 3);
        }
        
        game.shapeRenderer.end();
    }
    
    private void drawUI() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        // Bottom bar
        game.shapeRenderer.setColor(0, 0, 0, 0.7f);
        game.shapeRenderer.rect(0, 0, Constants.SCREEN_WIDTH, 120);
        
        // Deploy buttons
        if (!battleEnded && troopsAvailable > 0) {
            game.shapeRenderer.setColor(Constants.COLOR_BARBARIAN);
            game.shapeRenderer.rect(barbarianDeployButton.x, barbarianDeployButton.y, 
                                   barbarianDeployButton.width, barbarianDeployButton.height);
            
            game.shapeRenderer.setColor(Constants.COLOR_ARCHER);
            game.shapeRenderer.rect(archerDeployButton.x, archerDeployButton.y, 
                                   archerDeployButton.width, archerDeployButton.height);
            
            game.shapeRenderer.setColor(Constants.COLOR_GIANT);
            game.shapeRenderer.rect(giantDeployButton.x, giantDeployButton.y, 
                                   giantDeployButton.width, giantDeployButton.height);
        }
        
        // Back button (or end battle button)
        game.shapeRenderer.setColor(battleEnded ? Color.GREEN : Color.GRAY);
        game.shapeRenderer.rect(backButton.x, backButton.y, backButton.width, backButton.height);
        
        game.shapeRenderer.end();
        
        // Draw text
        game.batch.begin();
        game.font.setColor(Color.WHITE);
        
        // Battle stats
        game.font.draw(game.batch, "Troops: " + troopsAvailable, 50, Constants.SCREEN_HEIGHT - 20);
        game.font.draw(game.batch, "Destruction: " + combatSystem.getDestructionPercentage() + "%", 
                      250, Constants.SCREEN_HEIGHT - 20);
        
        // Button labels
        if (!battleEnded && troopsAvailable > 0) {
            game.font.setColor(Color.BLACK);
            game.font.getData().setScale(1f);
            game.font.draw(game.batch, "Barbarian", barbarianDeployButton.x + 10, barbarianDeployButton.y + 30);
            game.font.draw(game.batch, "Archer", archerDeployButton.x + 25, archerDeployButton.y + 30);
            game.font.draw(game.batch, "Giant", giantDeployButton.x + 30, giantDeployButton.y + 30);
            game.font.getData().setScale(1.5f);
        }
        
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, battleEnded ? "End Battle" : "Retreat", 
                      backButton.x + 20, backButton.y + 33);
        
        // Battle result
        if (battleEnded) {
            game.font.getData().setScale(3f);
            if (victory) {
                game.font.setColor(Color.GREEN);
                game.font.draw(game.batch, "VICTORY!", Constants.SCREEN_WIDTH / 2f - 100, Constants.SCREEN_HEIGHT / 2f);
                game.font.getData().setScale(1.5f);
                game.font.draw(game.batch, "+500 Gold, +300 Elixir", 
                              Constants.SCREEN_WIDTH / 2f - 130, Constants.SCREEN_HEIGHT / 2f - 50);
            } else {
                game.font.setColor(Color.RED);
                game.font.draw(game.batch, "DEFEAT", Constants.SCREEN_WIDTH / 2f - 80, Constants.SCREEN_HEIGHT / 2f);
            }
            game.font.getData().setScale(1.5f);
        }
        
        game.batch.end();
    }
    
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Constants.SCREEN_HEIGHT - Gdx.input.getY();
            
            if (backButton.contains(touchX, touchY)) {
                game.setScreen(new GameScreen(game));
                return;
            }
            
            // Deploy troops
            if (troopsAvailable > 0) {
                Troop newTroop = null;
                
                if (barbarianDeployButton.contains(touchX, touchY)) {
                    newTroop = new Barbarian(2, 2);
                } else if (archerDeployButton.contains(touchX, touchY)) {
                    newTroop = new Archer(2, 2);
                } else if (giantDeployButton.contains(touchX, touchY)) {
                    newTroop = new Giant(2, 2);
                } else if (touchY < Constants.GRID_HEIGHT * Constants.GRID_SIZE) {
                    // Deploy by clicking on map (default barbarian)
                    int gridX = (int)(touchX / Constants.GRID_SIZE);
                    int gridY = (int)(touchY / Constants.GRID_SIZE);
                    newTroop = new Barbarian(gridX, gridY);
                }
                
                if (newTroop != null) {
                    combatSystem.addTroop(newTroop);
                    troopsAvailable--;
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
