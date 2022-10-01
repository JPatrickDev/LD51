package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import me.jack.ld51.Entity.Projectiles.Bullet;
import me.jack.ld51.Entity.Projectiles.Knife;
import me.jack.ld51.level.Level;

public class GruntMob extends Mob {


    int weaponRange = 32;
    static Texture texture = new Texture("grunt.png");
    public GruntMob(int x, int y) {
        super(texture, x, y);
        currentWeapon = new Bullet(this, 2000);
        //     health = 10;
    }

    //Mobs in general should aim to get inside the range of their current weapon of the player, and then attempt to use the weapon
    //Ranged mobs should kinda randomly walk a bit as they fire
    @Override
    public void update(Level parent) {
        super.update(parent);
        Player player = parent.getPlayer();
        float xSpeed = (player.getX() - x);
        float ySpeed = (player.getY() - y);
        if (currentWeapon != null) {
            if (Level.dist(this, player) < currentWeapon.range()) {
                dX = 0;
                dY = 0;
                currentWeapon.use(parent, parent.getPlayer());
                return;
            }
        }
        float factor = (float) (2 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        dX = xSpeed;
        dY = ySpeed;
    }

    @Override
    public void renderShapes(ShapeRenderer renderer) {
        super.renderShapes(renderer);
        if (health != maxHealth) {
            renderer.setColor(Color.BLACK);
            renderer.rect(getX(), getY() + getH(), getW(), 2);
            renderer.setColor(Color.RED);
            renderer.rect(getX(), getY() + getH(), getW() * (health / maxHealth), 2);
        }
    }
}