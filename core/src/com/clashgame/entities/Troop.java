package com.clashgame.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * Base class for all troops in the game
 */
public abstract class Troop {
    public enum TroopType {
        BARBARIAN, ARCHER, GIANT
    }
    
    protected TroopType type;
    protected Vector2 position;
    protected int hp;
    protected int maxHp;
    protected int damage;
    protected float speed;
    protected float range;
    protected Color color;
    protected Building target;
    protected float attackTimer;
    protected float attackCooldown;
    protected boolean isAlive;
    
    public Troop(TroopType type, float x, float y, int maxHp, int damage, float speed, float range, Color color) {
        this.type = type;
        this.position = new Vector2(x, y);
        this.maxHp = maxHp;
        this.hp = maxHp;
        this.damage = damage;
        this.speed = speed;
        this.range = range;
        this.color = color;
        this.attackTimer = 0;
        this.attackCooldown = 1f;
        this.isAlive = true;
    }
    
    public void update(float delta) {
        if (attackTimer > 0) {
            attackTimer -= delta;
        }
    }
    
    public void moveTo(float x, float y, float delta) {
        Vector2 targetPos = new Vector2(x, y);
        Vector2 direction = targetPos.sub(position).nor();
        position.add(direction.scl(speed * delta));
    }
    
    public void moveTowards(Building building, float delta) {
        if (building != null) {
            float targetX = building.getGridX() + building.getWidth() / 2f;
            float targetY = building.getGridY() + building.getHeight() / 2f;
            moveTo(targetX, targetY, delta);
        }
    }
    
    public boolean isInRange(Building building) {
        if (building == null) return false;
        float targetX = building.getGridX() + building.getWidth() / 2f;
        float targetY = building.getGridY() + building.getHeight() / 2f;
        return position.dst(targetX, targetY) <= range;
    }
    
    public boolean canAttack() {
        return attackTimer <= 0 && isAlive;
    }
    
    public void attack(Building building) {
        if (canAttack() && isInRange(building)) {
            building.takeDamage(damage);
            attackTimer = attackCooldown;
        }
    }
    
    public void takeDamage(int damage) {
        hp = Math.max(0, hp - damage);
        if (hp <= 0) {
            isAlive = false;
        }
    }
    
    // Getters and setters
    public TroopType getType() { return type; }
    public Vector2 getPosition() { return position; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public int getDamage() { return damage; }
    public float getSpeed() { return speed; }
    public float getRange() { return range; }
    public Color getColor() { return color; }
    public boolean isAlive() { return isAlive; }
    public Building getTarget() { return target; }
    public void setTarget(Building target) { this.target = target; }
}
