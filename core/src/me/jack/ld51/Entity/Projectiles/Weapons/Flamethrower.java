package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.FlameJetParticle;
import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.TexCache;

public class Flamethrower extends RangedWeapon {
    //TODO Map for common textures for all entities to use

    public Flamethrower(Mob owner) {
        super(TexCache.get("projectiles/bullet.png"), owner);
        icon = TexCache.get("flamethrower.png");
        this.name = "Flamethower";
        this.description = "Throws flames.";
        this.upgrades = new String[]{"COOLER:20:1000:Increased cooling for longer bursts:cooler.png",
                "SPREAD:25:200:Shoot in a wider arc:arc.png",
                "NAPALM:15:500:Flames hang around for longer:napalm.png"};
        unlockedAt = 5;
    }

    @Override
    public void use(Level parent, int tx, int ty) {


        for (int i = 0; i != 5; i++) {
            spawnFire(parent,tx + (LD51Game.rand(100) - 50),ty + (LD51Game.rand(100)- 50));
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
