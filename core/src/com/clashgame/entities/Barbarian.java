package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Barbarian - basic melee troop
 */
public class Barbarian extends Troop {
    public Barbarian(float x, float y) {
        super(TroopType.BARBARIAN, x, y, 
              Constants.BARBARIAN_HP,
              Constants.BARBARIAN_DAMAGE,
              Constants.BARBARIAN_SPEED,
              Constants.BARBARIAN_RANGE,
              Constants.COLOR_BARBARIAN);
    }
}
