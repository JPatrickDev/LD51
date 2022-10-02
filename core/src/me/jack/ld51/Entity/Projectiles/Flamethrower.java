package me.jack.ld51.Entity.Projectiles;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.FireParticle;
import me.jack.ld51.Entity.Particles.Weapons.FlameJetParticle;
import me.jack.ld51.level.Level;

public class Flamethrower extends RangedWeapon {
    //TODO Map for common textures for all entities to use

    public Flamethrower(Mob owner) {
        super(new Texture("projectiles/bullet.png"), owner);
        icon = new Texture("gun.png");
    }

    @Override
    public void use(Level parent, int tx, int ty) {


        for (int i = 0; i != 5; i++) {
            spawnFire(parent,tx + (new Random().nextInt(100) - 50),ty + (new Random().nextInt(100) - 50));
        }
    }

    private void spawnFire(Level parent,int tx,int ty){
        float xSpeed = (tx - owner.getX());
        float ySpeed = (ty - owner.getY());
        float factor = (float) (25 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
        xSpeed *= factor;
        ySpeed *= factor;
        parent.spawnEntity(new FlameJetParticle(owner.getX() + owner.getW()/2, owner.getY() + owner.getH()/2, 3, 3, (int) xSpeed, (int) ySpeed,owner));
    }
    @Override
    public long fireRate() {
        return 1;
    }

    @Override
    public long lifespan() {
        return 2000;
    }

    @Override
    public float damage() {
        return 20;
    }

    @Override
    public float range() {
        return 200;
    }

}
