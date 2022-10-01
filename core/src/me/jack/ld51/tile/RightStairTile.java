package me.jack.ld51.tile;

import java.util.ArrayDeque;
import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class RightStairTile extends StairTile{

    public RightStairTile(int tX, int tY) {
        super(tX, tY);
    }

    @Override
    public String getTextureName() {
        return "rightstair.png";
    }

}
