package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Projectiles.Weapons.Bullet;
import me.jack.ld51.Entity.Projectiles.Weapons.Flamethrower;
import me.jack.ld51.Entity.Projectiles.Weapons.Grenade;
import me.jack.ld51.Entity.Projectiles.Weapons.RangedWeapon;
import me.jack.ld51.Entity.Projectiles.Weapons.SeekerRocket;
import me.jack.ld51.Entity.Projectiles.Weapons.Weapon;
import me.jack.ld51.LD51Game;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.level.Level;
import me.jack.ld51.ui.TexCache;

public class Player extends Mob {

    public Weapon[] weaponWheel = new Weapon[4];

    public int coins = 500;

    public boolean canBuy(int c) {
        return c <= coins;
    }

    public void buy(int c) {
        coins -= c;
    }

    public Player(int x, int y) {
        super(TexCache.get("player.png"), x, y);


        weaponWheel[0] = new Bullet(this, 100);
        weaponWheel[1] = new Grenade(this, 250);
        weaponWheel[2] = new Flamethrower(this);
        weaponWheel[3] = new SeekerRocket(this);
        currentWeapon = weaponWheel[0];
        setStartHealth(150f);
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
        angle = (float) -(Math.atan2(this.x - InGameScreen.getMX(), this.y - InGameScreen.getMY()) * 180 / Math.PI - 180);
        for (Weapon w : weaponWheel) {
            if (w != null) {
                w.usage += w.regenRate;
                if (w.usage > 1f) {
                    w.usage = 1f;
                }
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            dX += 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            dX -= 2;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            dY += 2;
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            dY -= 2;
        }

        if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
            if (this.currentWeapon instanceof RangedWeapon && !(this.currentWeapon instanceof SeekerRocket)) {
                ((RangedWeapon) this.currentWeapon).use(parent, InGameScreen.getMX(), InGameScreen.getMY());
            } else {
                Mob m = parent.findMobInRange(this, this.currentWeapon.range());
                if (m != null)
                    this.currentWeapon.use(parent, m);
            }
            // parent.spawnEntity(new Projectile(this,this.currentWeapon,getX(),getY()));

        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            if (weaponWheel[0] != null && weaponWheel[0].unlockedAt <= parent.currentRound)
                currentWeapon = weaponWheel[0];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            if (weaponWheel[1] != null && weaponWheel[1].unlockedAt <= parent.currentRound)
                currentWeapon = weaponWheel[1];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)) {
            if (weaponWheel[2] != null && weaponWheel[2].unlockedAt <= parent.currentRound)
                currentWeapon = weaponWheel[2];
        }
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_4)) {
            if (weaponWheel[3] != null && weaponWheel[3].unlockedAt <= parent.currentRound)
                currentWeapon = weaponWheel[3];
        }

        if (LD51Game.rand(5) == 0) {
            //    takeDamage(10f);
        }
    }

}
