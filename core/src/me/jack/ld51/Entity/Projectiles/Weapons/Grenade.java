package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.ExplosionParticle;
import me.jack.ld51.Entity.Particles.Weapons.FireParticle;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.level.Level;

public class Grenade extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    long firerate = 100;
    public Grenade(Mob owner, long firerate) {
        super(new Texture("grenade.png"),owner);
        icon  = new Texture("grenade.png");
        this.firerate = firerate;

        this.name = "Grenade";
        this.description = "Explodes after a few seconds.";
        this.upgrades = new String[]{"RADIUS:10:300:Increased blast radius:radius.png",
                "FIRESPLASH:18:600:Releases fire on detonation:firesplash.png"};
        unlockedAt = 3;
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
        System.out.println("Grenade removed");
        for(int i= 0; i != 100; i++){
            if(new Random().nextInt(5) == 0){
                parent.spawnEntity(new FireParticle(source.getX(),source.getY(),3,3, ((Mob)source.getOwner())));
            }else{
                parent.spawnEntity(new ExplosionParticle(source.getX(),source.getY(),3,3, ((Mob)source.getOwner())));
            }

        }
    }

}
