package me.jack.ld51.tile;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class LeftStairTile extends StairTile{

    public LeftStairTile(int tX, int tY) {
        super(tX, tY);
    }

    @Override
    public String getTextureName() {
        return "leftstair.png";
    }

}
