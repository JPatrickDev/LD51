package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.ExplosionParticle;
import me.jack.ld51.Entity.Particles.Weapons.FireParticle;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.Entity.Projectiles.SeekingProjectile;
import me.jack.ld51.LD51Game;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.TexCache;

public class SeekerRocket extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    public SeekerRocket(Mob owner) {
        super(TexCache.get("seekerrocket.png"),owner);
        icon  = TexCache.get("seekerrocket.png");
        this.name = "Seeking Missile";
        this.description = "Locks on to a nearby target.";
        this.upgrades = new String[]{"DAMAGE:1:1:Increased blast radius:radius.png",
                "FIRERATE:1:1:Increased rate of fire:rapidfire.png"};
        unlockedAt = 1;
        regenRate = 0.02f;
        usageRate = 0.4f;
    }

    @Override
    public void use(Level parent, Mob target) {

        System.out.println(lastUse);
        if (System.currentTimeMillis() - lastUse > fireRate() && usage > 0.2) {
            parent.spawnEntity(new SeekingProjectile(this.owner, this,target));
            lastUse = System.currentTimeMillis();
            usage -= usageRate;
        }
    }

    @Override
    public long fireRate(){
        if(appliedUpgrades.contains("FIRERATE")){
            return 100;
        }else {
            return 250;
        }
    }

    @Override
    public long lifespan() {
        return 10000;
    }

    @Override
    public float damage() {
        return 20;
    }

    @Override
    public float range() {
        return 400;
    }


    @Override
    public void onRemove(Projectile source, Level parent){
        int k = 50;
        if(appliedUpgrades.contains("DAMAGE")){
            k = 100;
        }
        for(int i= 0; i != k; i++){
            if(LD51Game.rand(5) == 0){
                parent.spawnEntity(new FireParticle(source.getX(),source.getY(),3,3, ((Mob)source.getOwner())));
            }else{
                parent.spawnEntity(new ExplosionParticle(source.getX() + (LD51Game.rand(50)-25),source.getY()+ (LD51Game.rand(50)-25),3,3, ((Mob)source.getOwner())));
            }

        }
    }
}
