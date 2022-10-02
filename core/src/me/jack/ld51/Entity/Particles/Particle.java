package me.jack.ld51.Entity.Particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import javax.sound.midi.SysexMessage;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.level.Level;

public class Particle extends Entity {
    protected Color start;
    protected Color end;

    public long starttime = 0;

    protected long lifespan = 1000;

    public Particle(int x, int y, int w, int h, Color c1, Color c2) {
        super(x, y, w, h);
        this.start = c1;
        this.end = c2;
    }

    @Override
    public void update(Level parent) {
        super.update(parent);

        if(Math.abs(dX) == 0 || Math.abs(dY) == 0){
            if(starttime == 0)
               starttime = System.currentTimeMillis();
            if(System.currentTimeMillis() - starttime > lifespan && new Random().nextInt(5) == 0){
                parent.removeEntity(this);
            }
        }

    }

    @Override
    public void renderTextures(SpriteBatch batch) {

    }

    @Override
    public void renderShapes(ShapeRenderer renderer) {
        renderer.rect(x, y, w, h, start, start, end, end);
    }
}
