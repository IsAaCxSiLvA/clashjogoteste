package com.clashgame.entities;

import com.clashgame.utils.Constants;

/**
 * Giant - tank troop with high HP
 */
public class Giant extends Troop {
    public Giant(float x, float y) {
        super(TroopType.GIANT, x, y,
              Constants.GIANT_HP,
              Constants.GIANT_DAMAGE,
              Constants.GIANT_SPEED,
              Constants.GIANT_RANGE,
              Constants.COLOR_GIANT);
    }
}
