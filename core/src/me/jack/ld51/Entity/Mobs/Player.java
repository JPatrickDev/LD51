package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;

import java.util.Random;

import me.jack.ld51.Entity.Projectiles.Bullet;
import me.jack.ld51.Entity.Projectiles.Flamethrower;
import me.jack.ld51.Entity.Projectiles.Grenade;
import me.jack.ld51.Entity.Projectiles.Knife;
import me.jack.ld51.Entity.Projectiles.PlayerKnife;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.Entity.Projectiles.RangedWeapon;
import me.jack.ld51.Entity.Projectiles.Weapon;
import me.jack.ld51.level.Level;

public class Player extends Mob {

    public Weapon[] weaponWheel = new Weapon[4];

    public Player(int x, int y) {
        super(new Texture("player.png"), x, y);

        weaponWheel[0] = new PlayerKnife(this);
        weaponWheel[1] = new Bullet(this,100);
        weaponWheel[2] = new Grenade(this,1000);
        weaponWheel[3] = new Flamethrower(this);
        currentWeapon = weaponWheel[0];
        setStartHealth(150f);
    }

    @Override
    public void update(Level parent) {
        super.update(parent);
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
            if (this.currentWeapon instanceof RangedWeapon) {
                ((RangedWeapon) this.currentWeapon).use(parent, Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());
            } else {
                Mob m = parent.findMobInRange(this, this.currentWeapon.range());
                if (m != null)
                    this.currentWeapon.use(parent, m);
            }
            // parent.spawnEntity(new Projectile(this,this.currentWeapon,getX(),getY()));

        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            currentWeapon = weaponWheel[0];
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            currentWeapon = weaponWheel[1];
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            currentWeapon = weaponWheel[2];
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_4)){
            currentWeapon = weaponWheel[3];
        }

        if (new Random().nextInt(5) == 0) {
            //    takeDamage(10f);
        }
    }

}
