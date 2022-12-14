package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.ExplosionParticle;
import me.jack.ld51.Entity.Particles.Weapons.FireParticle;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.TexCache;

public class Grenade extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    long firerate = 100;
    public Grenade(Mob owner, long firerate) {
        super(TexCache.get("grenade.png"),owner);
        icon  = TexCache.get("grenade.png");
        this.firerate = firerate;

        this.name = "Grenade";
        this.description = "Explodes after a few seconds.";
        this.upgrades = new String[]{"RADIUS:5:25:Increased blast radius:radius.png",
                "FIRESPLASH:10:50:Releases fire on detonation:firesplash.png"};
        unlockedAt = 3;

        regenRate = 0.02f;
        usageRate = 0.5f;
    }


    @Override
    public long fireRate(){
        return firerate;
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


    @Override
    public void onRemove(Projectile source, Level parent){
        for(int i= 0; i != 100; i++){
            if(LD51Game.rand(5) == 0 && appliedUpgrades.contains("FIRESPLASH")){
                parent.spawnEntity(new FireParticle(source.getX(),source.getY(),3,3, ((Mob)source.getOwner())));
            }else{
                int v = 50;
                if(appliedUpgrades.contains("RADIUS"))
                    v = 100;
                parent.spawnEntity(new ExplosionParticle(source.getX(),source.getY(),3,3, ((Mob)source.getOwner()),v));
            }

        }
    }

}
