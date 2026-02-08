package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Archer - ranged attack troop
 */
public class Archer extends Troop {
    public Archer(float x, float y) {
        super(TroopType.ARCHER, x, y,
              Constants.ARCHER_HP,
              Constants.ARCHER_DAMAGE,
              Constants.ARCHER_SPEED,
              Constants.ARCHER_RANGE,
              Constants.COLOR_ARCHER);
    }
}
