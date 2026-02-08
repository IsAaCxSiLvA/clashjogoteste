package com.clashgame.entities;

/**
 * Represents game resources (Gold, Elixir, Gems)
 */
public class Resource {
    public enum Type {
        GOLD, ELIXIR, GEMS
    }
    
    private Type type;
    private int amount;
    private int maxCapacity;
    
    public Resource(Type type, int startingAmount, int maxCapacity) {
        this.type = type;
        this.amount = startingAmount;
        this.maxCapacity = maxCapacity;
    }
    
    public boolean add(int amount) {
        if (this.amount + amount > maxCapacity) {
            this.amount = maxCapacity;
            return false;
        }
        this.amount += amount;
        return true;
    }
    
    public boolean subtract(int amount) {
        if (this.amount < amount) {
            return false;
        }
        this.amount -= amount;
        return true;
    }
    
    public boolean canAfford(int cost) {
        return amount >= cost;
    }
    
    public int getAmount() {
        return amount;
    }
    
    public void setAmount(int amount) {
        this.amount = Math.min(amount, maxCapacity);
    }
    
    public int getMaxCapacity() {
        return maxCapacity;
    }
    
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
    public Type getType() {
        return type;
    }
}
