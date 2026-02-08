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
import com.clashgame.systems.CombatSystem;
import com.clashgame.utils.Constants;

/**
 * Main game screen where player builds and manages their base
 */
public class GameScreen implements Screen {
    private ClashGame game;
    private Building selectedBuilding;
    private boolean buildMode;
    private Building.BuildingType selectedBuildingType;
    private Rectangle buildMenuButton;
    private Rectangle attackButton;
    private Rectangle saveButton;
    private Rectangle barracksButton;
    private Array<Rectangle> buildingButtons;
    private boolean showBuildMenu;
    private float autoSaveTimer;
    
    public GameScreen(ClashGame game) {
        this.game = game;
        this.buildMode = false;
        this.showBuildMenu = false;
        this.autoSaveTimer = 0;
        
        // Initialize buttons
        buildMenuButton = new Rectangle(10, Constants.SCREEN_HEIGHT - 60, 150, 50);
        attackButton = new Rectangle(170, Constants.SCREEN_HEIGHT - 60, 150, 50);
        saveButton = new Rectangle(330, Constants.SCREEN_HEIGHT - 60, 120, 50);
        barracksButton = new Rectangle(460, Constants.SCREEN_HEIGHT - 60, 150, 50);
        
        // Initialize building menu buttons
        buildingButtons = new Array<>();
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 120, 140, 40)); // Town Hall
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 170, 140, 40)); // Gold Mine
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 220, 140, 40)); // Elixir Collector
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 270, 140, 40)); // Storage
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 320, 140, 40)); // Barracks
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 370, 140, 40)); // Wall
        buildingButtons.add(new Rectangle(10, Constants.SCREEN_HEIGHT - 420, 140, 40)); // Archer Tower
        
        // Initialize with a Town Hall if starting fresh
        if (game.buildingSystem.getBuildings().size == 0) {
            game.buildingSystem.addBuilding(new TownHall(5, 5));
            game.buildingSystem.addBuilding(new GoldMine(10, 5));
            game.buildingSystem.addBuilding(new ElixirCollector(10, 8));
        }
    }
    
    @Override
    public void show() {
    }
    
    @Override
    public void render(float delta) {
        // Update
        game.buildingSystem.update(delta);
        
        // Auto-save every 30 seconds
        autoSaveTimer += delta;
        if (autoSaveTimer >= 30f) {
            game.saveSystem.saveGame(game.resourceSystem, game.buildingSystem);
            autoSaveTimer = 0;
        }
        
        // Auto-collect resources
        if ((int)autoSaveTimer % 10 == 0) {
            game.buildingSystem.collectFromMines(game.resourceSystem);
            game.buildingSystem.collectFromCollectors(game.resourceSystem);
        }
        
        // Clear screen
        Gdx.gl.glClearColor(0.1f, 0.4f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Draw grid
        drawGrid();
        
        // Draw buildings
        drawBuildings();
        
        // Draw UI
        drawUI();
        
        // Handle input
        handleInput();
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
        
        for (Building building : game.buildingSystem.getBuildings()) {
            if (building.isDestroyed()) continue;
            
            game.shapeRenderer.setColor(building.getColor());
            game.shapeRenderer.rect(
                building.getGridX() * Constants.GRID_SIZE + 2,
                building.getGridY() * Constants.GRID_SIZE + 2,
                building.getWidth() * Constants.GRID_SIZE - 4,
                building.getHeight() * Constants.GRID_SIZE - 4
            );
            
            // Draw HP bar
            if (building.getHp() < building.getMaxHp()) {
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
        }
        
        game.shapeRenderer.end();
        
        // Draw building info
        game.batch.begin();
        game.font.setColor(Color.WHITE);
        for (Building building : game.buildingSystem.getBuildings()) {
            if (building.isDestroyed()) continue;
            
            float x = building.getGridX() * Constants.GRID_SIZE + 5;
            float y = (building.getGridY() + building.getHeight()) * Constants.GRID_SIZE - 5;
            game.font.getData().setScale(0.8f);
            game.font.draw(game.batch, "Lv" + building.getLevel(), x, y);
            game.font.getData().setScale(1.5f);
        }
        game.batch.end();
    }
    
    private void drawUI() {
        game.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        
        // Top bar background
        game.shapeRenderer.setColor(0, 0, 0, 0.7f);
        game.shapeRenderer.rect(0, Constants.SCREEN_HEIGHT - 70, Constants.SCREEN_WIDTH, 70);
        
        // Buttons
        game.shapeRenderer.setColor(Color.ORANGE);
        game.shapeRenderer.rect(buildMenuButton.x, buildMenuButton.y, buildMenuButton.width, buildMenuButton.height);
        
        game.shapeRenderer.setColor(Color.RED);
        game.shapeRenderer.rect(attackButton.x, attackButton.y, attackButton.width, attackButton.height);
        
        game.shapeRenderer.setColor(Color.CYAN);
        game.shapeRenderer.rect(saveButton.x, saveButton.y, saveButton.width, saveButton.height);
        
        game.shapeRenderer.setColor(Color.YELLOW);
        game.shapeRenderer.rect(barracksButton.x, barracksButton.y, barracksButton.width, barracksButton.height);
        
        // Build menu
        if (showBuildMenu) {
            game.shapeRenderer.setColor(0, 0, 0, 0.8f);
            game.shapeRenderer.rect(0, Constants.SCREEN_HEIGHT - 440, 160, 370);
            
            String[] buildingNames = {"Town Hall", "Gold Mine", "Elixir Col", "Storage", "Barracks", "Wall", "Tower"};
            for (int i = 0; i < buildingButtons.size; i++) {
                Rectangle btn = buildingButtons.get(i);
                game.shapeRenderer.setColor(Color.GOLD);
                game.shapeRenderer.rect(btn.x, btn.y, btn.width, btn.height);
            }
        }
        
        game.shapeRenderer.end();
        
        // Draw text
        game.batch.begin();
        game.font.setColor(Color.WHITE);
        
        // Resource display
        game.font.draw(game.batch, "Gold: " + game.resourceSystem.getGold().getAmount(), 
                      Constants.SCREEN_WIDTH - 350, Constants.SCREEN_HEIGHT - 20);
        game.font.draw(game.batch, "Elixir: " + game.resourceSystem.getElixir().getAmount(), 
                      Constants.SCREEN_WIDTH - 350, Constants.SCREEN_HEIGHT - 45);
        game.font.draw(game.batch, "Gems: " + game.resourceSystem.getGems().getAmount(), 
                      Constants.SCREEN_WIDTH - 180, Constants.SCREEN_HEIGHT - 20);
        
        // Button labels
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, "Build", buildMenuButton.x + 40, buildMenuButton.y + 33);
        game.font.draw(game.batch, "Attack", attackButton.x + 40, attackButton.y + 33);
        game.font.draw(game.batch, "Save", saveButton.x + 30, saveButton.y + 33);
        game.font.draw(game.batch, "Barracks", barracksButton.x + 30, barracksButton.y + 33);
        
        // Build menu labels
        if (showBuildMenu) {
            game.font.setColor(Color.BLACK);
            String[] buildingNames = {"Town Hall", "Gold Mine", "Elixir Col", "Storage", "Barracks", "Wall", "Tower"};
            int[] costs = {Constants.TOWN_HALL_COST, Constants.GOLD_MINE_COST, Constants.ELIXIR_COLLECTOR_COST,
                          Constants.STORAGE_COST, Constants.BARRACKS_COST, Constants.WALL_COST, Constants.ARCHER_TOWER_COST};
            
            for (int i = 0; i < buildingButtons.size; i++) {
                Rectangle btn = buildingButtons.get(i);
                game.font.getData().setScale(1f);
                game.font.draw(game.batch, buildingNames[i], btn.x + 5, btn.y + 26);
                game.font.getData().setScale(0.7f);
                game.font.draw(game.batch, costs[i] + "g", btn.x + 5, btn.y + 12);
                game.font.getData().setScale(1.5f);
            }
        }
        
        game.batch.end();
    }
    
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Constants.SCREEN_HEIGHT - Gdx.input.getY();
            
            // Check UI buttons
            if (buildMenuButton.contains(touchX, touchY)) {
                showBuildMenu = !showBuildMenu;
                buildMode = false;
                return;
            }
            
            if (attackButton.contains(touchX, touchY)) {
                game.setScreen(new BattleScreen(game));
                return;
            }
            
            if (saveButton.contains(touchX, touchY)) {
                game.saveSystem.saveGame(game.resourceSystem, game.buildingSystem);
                return;
            }
            
            if (barracksButton.contains(touchX, touchY)) {
                game.setScreen(new BarracksScreen(game));
                return;
            }
            
            // Check build menu
            if (showBuildMenu) {
                Building.BuildingType[] types = {
                    Building.BuildingType.TOWN_HALL,
                    Building.BuildingType.GOLD_MINE,
                    Building.BuildingType.ELIXIR_COLLECTOR,
                    Building.BuildingType.STORAGE,
                    Building.BuildingType.BARRACKS,
                    Building.BuildingType.WALL,
                    Building.BuildingType.ARCHER_TOWER
                };
                
                for (int i = 0; i < buildingButtons.size; i++) {
                    if (buildingButtons.get(i).contains(touchX, touchY)) {
                        selectedBuildingType = types[i];
                        buildMode = true;
                        showBuildMenu = false;
                        return;
                    }
                }
            }
            
            // Place building in build mode
            if (buildMode) {
                int gridX = (int)(touchX / Constants.GRID_SIZE);
                int gridY = (int)(touchY / Constants.GRID_SIZE);
                
                Building newBuilding = createBuilding(selectedBuildingType, gridX, gridY);
                if (newBuilding != null) {
                    int cost = getBuildingCost(selectedBuildingType);
                    if (game.resourceSystem.canAffordGold(cost)) {
                        if (game.buildingSystem.addBuilding(newBuilding)) {
                            game.resourceSystem.spendGold(cost);
                            buildMode = false;
                        }
                    }
                }
            }
        }
    }
    
    private Building createBuilding(Building.BuildingType type, int x, int y) {
        switch (type) {
            case TOWN_HALL: return new TownHall(x, y);
            case GOLD_MINE: return new GoldMine(x, y);
            case ELIXIR_COLLECTOR: return new ElixirCollector(x, y);
            case STORAGE: return new Storage(x, y);
            case BARRACKS: return new Barracks(x, y);
            case WALL: return new Wall(x, y);
            case ARCHER_TOWER: return new ArcherTower(x, y);
            default: return null;
        }
    }
    
    private int getBuildingCost(Building.BuildingType type) {
        switch (type) {
            case TOWN_HALL: return Constants.TOWN_HALL_COST;
            case GOLD_MINE: return Constants.GOLD_MINE_COST;
            case ELIXIR_COLLECTOR: return Constants.ELIXIR_COLLECTOR_COST;
            case STORAGE: return Constants.STORAGE_COST;
            case BARRACKS: return Constants.BARRACKS_COST;
            case WALL: return Constants.WALL_COST;
            case ARCHER_TOWER: return Constants.ARCHER_TOWER_COST;
            default: return 0;
        }
    }
    
    @Override
    public void resize(int width, int height) {
    }
    
    @Override
    public void pause() {
        game.saveSystem.saveGame(game.resourceSystem, game.buildingSystem);
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
