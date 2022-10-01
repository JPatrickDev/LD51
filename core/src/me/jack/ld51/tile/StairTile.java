package me.jack.ld51.tile;

import java.util.ArrayDeque;
import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public abstract class StairTile extends Tile{

    public StairTile(int tX, int tY) {
        super(tX, tY);
    }

    public ArrayDeque<Mob> toSpawn = new ArrayDeque<Mob>();
    long spawnRate = 1000;
    long lastSpawn = 0;

    @Override
    public void update(Level parent) {
        super.update(parent);
        if(!toSpawn.isEmpty() && System.currentTimeMillis() - lastSpawn > spawnRate && new Random().nextInt(5) == 0) {
            parent.spawnEntity(toSpawn.pop());
            lastSpawn = System.currentTimeMillis();
        }
    }
}
