package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.LD51Game;

public class FlameJetParticle extends WeaponParticle {
    public FlameJetParticle(int x, int y, int w, int h, int xVel, int yVel, Mob owner) {
        super(x, y, w * 4, h * 4, Color.RED, Color.ORANGE,owner);
        dX = xVel;// + (new Random().nextInt(15) - 2);
        dY = yVel;// + (new Random().nextInt(15) - 2);
        lifespan = 200;
    }

    @Override
    public void renderTextures(SpriteBatch batch) {
        super.renderTextures(batch);
    }

    int currentI = 5;
    int j = 0;
    @Override
    public void renderShapes(ShapeRenderer renderer) {
        j++;
        if(j%500 == 0){
            if(LD51Game.randBool()){
                currentI =LD51Game.rand(5) + 2;
            }
            j =0;

        }
        for(int k = 0; k != currentI;k++) {
            renderer.rect(x + (LD51Game.rand(10) -5), y + (LD51Game.rand(10)-5), w/4, h/4, start, start, end, end);
        }
    }
}
