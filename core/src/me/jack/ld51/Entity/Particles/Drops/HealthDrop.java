package me.jack.ld51.Entity.Particles.Drops;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class HealthDrop extends DropParticle{

    public static final Texture t = new Texture("healthdrop.png");
    @Override
    public void apply(Level parent, Mob pickedUpBy) {
        pickedUpBy.addHealth(10f);
    }

    public HealthDrop(int x, int y) {
        super(x, y, 16, 16, null,null);
    }

    @Override
    public void renderTextures(SpriteBatch batch)
    {
        batch.draw(t, getX(), getY());
    }
    @Override
    public void renderShapes(ShapeRenderer renderer) {}
}
