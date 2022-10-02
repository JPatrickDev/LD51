package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.LD51Game;

public class ExplosionParticle extends WeaponParticle {
    public ExplosionParticle(int x, int y, int w, int h, Mob owner) {
        this(x, y, w, h, owner, 50);
    }

    public ExplosionParticle(int x, int y, int w, int h, Mob owner, int vel) {
        super(x, y, w, h, Color.GRAY, Color.BLACK, owner);
        dX = LD51Game.rand(vel) - vel / 2;
        dY = LD51Game.rand(vel) - vel / 2;
        lifespan = 0;
    }
}
