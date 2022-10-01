package me.jack.ld51.Entity.Particles;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.level.Level;

public class Particle extends Entity {
    private Color start, end;

    long starttime = 0;
    public Particle(int x, int y, int w, int h, Color c1, Color c2) {
        super(x, y, w, h);
        this.start = c1;
        this.end = c2;
        starttime = System.currentTimeMillis();
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if(System.currentTimeMillis() - starttime > 1000 && new Random().nextInt(5) == 0){
            parent.removeEntity(this);
        }
    }

    @Override
    public void renderTextures(SpriteBatch batch) {

    }

    @Override
    public void renderShapes(ShapeRenderer renderer) {
        System.out.println("Drawing particle");
        renderer.rect(x, y, w, h, start, start, end, end);
    }
}
