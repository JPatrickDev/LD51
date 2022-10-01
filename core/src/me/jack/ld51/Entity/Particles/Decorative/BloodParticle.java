package me.jack.ld51.Entity.Particles.Decorative;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

public class BloodParticle extends DecorativeParticle {
    public BloodParticle(int x, int y, int w, int h) {
        super(x, y, w, h, Color.RED, Color.FIREBRICK);
        dX = new Random().nextInt(30) - 15;
        dY = new Random().nextInt(30) - 15;
    }
}
