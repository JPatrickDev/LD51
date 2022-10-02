package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.LD51Game;

public class ExplosionParticle extends WeaponParticle{
    public ExplosionParticle(int x, int y, int w, int h, Mob owner) {
        super(x, y, w, h, Color.GRAY, Color.BLACK,owner);
        dX = LD51Game.rand(50) - 25;
        dY = LD51Game.rand(50) - 25;
        lifespan=0;
    }

}
