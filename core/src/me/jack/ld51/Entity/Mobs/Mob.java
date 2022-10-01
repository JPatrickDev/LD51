package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.level.Level;

public class Mob extends Entity {
    public Mob(Texture t, int x, int y) {
        super(t, x, y);
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
    }
}
