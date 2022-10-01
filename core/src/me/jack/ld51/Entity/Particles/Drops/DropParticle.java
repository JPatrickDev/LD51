package me.jack.ld51.Entity.Particles.Drops;

import com.badlogic.gdx.graphics.Color;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Particle;
import me.jack.ld51.level.Level;

public abstract class DropParticle extends Particle {
    public DropParticle(int x, int y, int w, int h, Color c1, Color c2) {
        super(x, y, w, h, c1, c2);
        lifespan = 10000;
    }


    public abstract void apply(Level parent, Mob pickedUpBy);
}
