package com.clashgame.systems;

import com.badlogic.gdx.utils.Array;
import com.clashgame.entities.*;
import com.clashgame.utils.Constants;

/**
 * Manages combat and troop behavior
 */
public class CombatSystem {
    private Array<Troop> troops;
    private BuildingSystem buildingSystem;
    
    public CombatSystem(BuildingSystem buildingSystem) {
        this.troops = new Array<>();
        this.buildingSystem = buildingSystem;
    }
    
    public void update(float delta) {
        Array<Troop> deadTroops = new Array<>();
        
        for (Troop troop : troops) {
            if (!troop.isAlive()) {
                deadTroops.add(troop);
                continue;
            }
            
            troop.update(delta);
            
            // Find target if none
            if (troop.getTarget() == null || troop.getTarget().isDestroyed()) {
                Building nearestBuilding = findNearestBuilding(troop);
                troop.setTarget(nearestBuilding);
            }
            
            // Move towards target or attack
            Building target = troop.getTarget();
            if (target != null) {
                if (troop.isInRange(target)) {
                    troop.attack(target);
                    if (target.isDestroyed()) {
                        buildingSystem.removeBuilding(target);
                        troop.setTarget(null);
                    }
                } else {
                    troop.moveTowards(target, delta);
                }
            }
        }
        
        // Remove dead troops
        for (Troop troop : deadTroops) {
            troops.removeValue(troop, true);
        }
    }
    
    private Building findNearestBuilding(Troop troop) {
        Building nearest = null;
        float minDistance = Float.MAX_VALUE;
        
        for (Building building : buildingSystem.getBuildings()) {
            if (!building.isDestroyed()) {
                float distance = troop.getPosition().dst(
                    building.getGridX() + building.getWidth() / 2f,
                    building.getGridY() + building.getHeight() / 2f
                );
                
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = building;
                }
            }
        }
        
        return nearest;
    }
    
    public void addTroop(Troop troop) {
        troops.add(troop);
    }
    
    public Array<Troop> getTroops() {
        return troops;
    }
    
    public void clearTroops() {
        troops.clear();
    }
    
    public boolean checkVictory() {
        Building townHall = buildingSystem.getTownHall();
        if (townHall != null && townHall.isDestroyed()) {
            return true;
        }
        
        // Check 50% destruction
        int totalBuildings = buildingSystem.getBuildings().size;
        int destroyedCount = 0;
        for (Building building : buildingSystem.getBuildings()) {
            if (building.isDestroyed()) {
                destroyedCount++;
            }
        }
        
        return totalBuildings > 0 && (destroyedCount * 100 / totalBuildings) >= 50;
    }
    
    public int getDestructionPercentage() {
        int totalBuildings = buildingSystem.getBuildings().size;
        if (totalBuildings == 0) return 0;
        
        int destroyedCount = 0;
        for (Building building : buildingSystem.getBuildings()) {
            if (building.isDestroyed()) {
                destroyedCount++;
            }
        }
        
        return (destroyedCount * 100) / totalBuildings;
    }
}
