package me.jack.ld51.Entity.Particles.Decorative;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import me.jack.ld51.LD51Game;

public class BloodParticle extends DecorativeParticle {
    public BloodParticle(int x, int y, int w, int h) {
        super(x, y, w, h, Color.RED, Color.FIREBRICK);
        dX = LD51Game.rand(30) - 15;
        dY = LD51Game.rand(30) - 15;
    }
}
