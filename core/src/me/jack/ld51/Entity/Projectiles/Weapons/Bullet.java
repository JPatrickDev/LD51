package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import me.jack.ld51.Entity.Mobs.BaseEnemy;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.level.Level;

public class Bullet extends RangedWeapon {
    //TODO Map for common textures for all entities to use
    long firerate = 100;

    public Bullet(Mob owner, long firerate) {
        super(new Texture("projectiles/bullet.png"), owner);
        icon = new Texture("gun.png");
        this.firerate = firerate;
        this.name = "Handgun";
        this.description = "A basic handgun.";
        this.upgrades = new String[]{"FIRERATE:5:100:Increase the rate of fire:rapidfire.png",
                "DAMAGE:10:200:Deal more damage with bigger bullets:largerbullet.png",
                "MULTISHOT:15:500:Shoot three bullets at once:tripleshot.png"};
        unlockedAt = 0;
    }


    @Override
    public long fireRate() {
        if(owner instanceof BaseEnemy){
            return 2000;
        }
        if (appliedUpgrades.contains("FIRERATE")) {
            return 150;
        } else {
            return 250;
        }
    }

    @Override
    public void use(Level parent, int tx, int ty) {
        if (appliedUpgrades.contains("MULTISHOT")) {
            System.out.println(lastUse);
            if (System.currentTimeMillis() - lastUse > fireRate()) {
                parent.spawnEntity(new Projectile(this.owner, this, tx + 15, ty));
                parent.spawnEntity(new Projectile(this.owner, this, tx, ty));
                parent.spawnEntity(new Projectile(this.owner, this, tx - 15, ty));
                lastUse = System.currentTimeMillis();
            }
        } else {
            super.use(parent, tx, ty);
        }


    }

    @Override
    public long lifespan() {
        return 2000;
    }

    @Override
    public float damage() {
        if (appliedUpgrades.contains("DAMAGE")) {
            return 55;
        } else {
            return 20;
        }
    }

    @Override
    public float range() {
        return 200;
    }

}
