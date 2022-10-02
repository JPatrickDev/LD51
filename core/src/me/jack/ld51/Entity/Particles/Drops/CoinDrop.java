package me.jack.ld51.Entity.Particles.Drops;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public class CoinDrop extends DropParticle{

    public static final Texture t = new Texture("coin.png");
    @Override
    public void apply(Level parent, Mob pickedUpBy) {
        pickedUpBy.addCoins(1);
    }

    public CoinDrop(int x, int y) {
        super(x, y, 16, 16, null,null);
        lifespan = 100000;
    }

    @Override
    public void renderTextures(SpriteBatch batch)
    {
        batch.draw(t, getX(), getY(),16,16);
    }
    @Override
    public void renderShapes(ShapeRenderer renderer) {}
}
