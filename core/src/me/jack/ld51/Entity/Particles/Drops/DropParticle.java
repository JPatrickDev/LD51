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

    @Override
    public void update(Level parent) {
        super.update(parent);
        if(Level.dist(parent.getPlayer(),this) < 64){
            float xSpeed = (parent.getPlayer().getX() - x);
            float ySpeed = (parent.getPlayer().getY() - y);

            float factor = (float) (2 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
            xSpeed *= factor;
            ySpeed *= factor;
            dX = xSpeed;
            dY = ySpeed;
        }
    }

    public abstract void apply(Level parent, Mob pickedUpBy);
}
