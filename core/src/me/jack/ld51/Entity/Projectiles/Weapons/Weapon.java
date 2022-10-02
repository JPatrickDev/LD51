package me.jack.ld51.Entity.Projectiles.Weapons;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.level.Level;

public abstract class Weapon {


    public String name,description;

    public float usageRate = 0.2f;
    public float usage = 1f;
    public float regenRate = 0.05f;

    public int unlockedAt;


    public String[] upgrades = new String[0];
    public List<String> appliedUpgrades = new ArrayList<String>();

    protected Mob owner;
    protected long lastUse;
    public Texture icon;
    public Weapon(Mob owner){
        this.owner = owner;
    }
    public abstract long fireRate();

    public abstract float damage();
    public abstract float range();

    public abstract void use(Level parent, Mob target);
}
