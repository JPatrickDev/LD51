package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;

public class ExplosionParticle extends WeaponParticle{
    public ExplosionParticle(int x, int y, int w, int h, Mob owner) {
        super(x, y, w, h, Color.GRAY, Color.BLACK,owner);
        dX = new Random().nextInt(100) - 50;
        dY = new Random().nextInt(100) - 50;
        lifespan=0;
    }

}
