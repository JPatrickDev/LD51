package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Particles.Weapons.ExplosionParticle;
import me.jack.ld51.Entity.Particles.Weapons.FireParticle;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.Entity.Projectiles.SeekingProjectile;
import me.jack.ld51.level.Level;

public class SeekerRocket extends RangedWeapon{
    //TODO Map for common textures for all entities to use
    public SeekerRocket(Mob owner) {
        super(new Texture("seekerrocket.png"),owner);
        icon  = new Texture("seekerrocket.png");
        this.name = "Seeking Missile";
        this.description = "Locks on to a nearby target.";
        this.upgrades = new String[]{"DAMAGE:20:1000:Increased blast radius:radius.png",
                "MIRV:25:200:Split in to three sub rockets after launch:mirv.png",
                "FIRERATE:15:500:Increased rate of fire:rapidfire.png"};
        unlockedAt = 10;
    }

    @Override
    public void use(Level parent, Mob target) {

        System.out.println(lastUse);
        if (System.currentTimeMillis() - lastUse > fireRate()) {
            parent.spawnEntity(new SeekingProjectile(this.owner, this,target));
            lastUse = System.currentTimeMillis();
        }
    }

    @Override
    public long fireRate(){
        return 50;
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
