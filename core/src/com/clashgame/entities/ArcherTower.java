package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Archer Tower - defensive structure that attacks
 */
public class ArcherTower extends Building {
    private int damage;
    private float range;
    private float attackCooldown;
    private float attackTimer;
    
    public ArcherTower(int gridX, int gridY) {
        super(BuildingType.ARCHER_TOWER, gridX, gridY, 2, 2, Constants.COLOR_ARCHER_TOWER);
        this.attackCooldown = Constants.ATTACK_COOLDOWN;
        this.attackTimer = 0;
    }
    
    @Override
    protected void initializeStats() {
        this.maxHp = 400 + (level * 150);
        this.hp = maxHp;
        this.damage = 10 + (level * 5);
        this.range = 5f + (level * 0.5f);
    }
    
    @Override
    public void update(float delta) {
        super.update(delta);
        if (attackTimer > 0) {
            attackTimer -= delta;
        }
    }
    
    public boolean canAttack() {
        return attackTimer <= 0 && !isConstructing;
    }
    
    public void attack() {
        attackTimer = attackCooldown;
    }
    
    @Override
    public int getUpgradeCost() {
        return Constants.ARCHER_TOWER_COST + (level * 200);
    }
    
    @Override
    public float getUpgradeTime() {
        return level * 20f;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public float getRange() {
        return range;
    }
}
