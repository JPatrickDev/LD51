package me.jack.ld51.Entity.Projectiles;

import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Projectiles.Weapons.RangedWeapon;
import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;

public class Projectile extends Entity {

    Entity owner;
    public RangedWeapon toFire;
    long start;

    public Projectile(Entity owner, RangedWeapon toFire, int tx, int ty) {
        super(toFire.projectileTexture, owner.getX(), owner.getY());
        this.owner = owner;
        this.toFire = toFire;
        start = System.currentTimeMillis();
        float xSpeed = (fuzz(tx) - x);
        float ySpeed = (fuzz(ty) - y);

        float factor = (float) (15 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        dX = xSpeed;
        dY = ySpeed;
    }

    public int fuzz(int input) {
        int i = LD51Game.rand(15);
        if (LD51Game.randBool())
            i *= -1;
        return input + i;
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        if (bounces > 1) {
            parent.removeEntity(this);
        }
        if (System.currentTimeMillis() - start > 2000) {
            parent.removeEntity(this);
        }
    }

    //Should only be called from Level, doesn't perform any checks
    //Projectiles don't slow down over time
    @Override
    public void move() {
        x += dX;
        y += dY;
    }

    public Entity getOwner() {
        return this.owner;
    }

    @Override
    public void onRemove(Level parent) {
        super.onRemove(parent);
        this.toFire.onRemove(this,parent);
    }
}
