package me.jack.ld51.Entity.Particles.Weapons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.LD51Game;

public class FireParticle extends WeaponParticle {
    public FireParticle(int x, int y, int w, int h, Mob owner) {
        super(x, y, w,h, Color.RED, Color.ORANGE,owner);
        dX = LD51Game.rand(50) - 25;
        dY = LD51Game.rand(50) - 25;
        lifespan = 2500;
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
                currentI = LD51Game.rand(5) + 2;
            }
            j =0;

        }
        for(int k = 0; k != currentI;k++) {
            renderer.rect(x + (LD51Game.rand(10) -5), y + (LD51Game.rand(10) -5), w, h, start, start, end, end);
        }
    }
}
