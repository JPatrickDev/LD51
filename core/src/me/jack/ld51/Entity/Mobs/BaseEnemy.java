package me.jack.ld51.Entity.Mobs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import org.xguzm.pathfinding.grid.GridCell;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import me.jack.ld51.Entity.Projectiles.Weapons.Knife;
import me.jack.ld51.LD51Game;
import me.jack.ld51.Screen.InGameScreen;
import me.jack.ld51.level.Level;
import me.jack.ld51.tile.FloorTile;
import me.jack.ld51.tile.Tile;

public abstract class BaseEnemy extends Mob {


    int weaponRange = 32;
    static Texture texture = new Texture("grunt.png");

    public BaseEnemy(Texture texture, int x, int y) {
        super(texture, x, y);
        currentWeapon = new Knife(this);
        //     health = 10;
    }

    List<GridCell> path;
    GridCell currentTarget = null;
    long targetAge = 0;
    long pathAge = 0;
    int i = 0;

    //Mobs in general should aim to get inside the range of their current weapon of the player, and then attempt to use the weapon
    //Ranged mobs should kinda randomly walk a bit as they fire
    @Override
    public void update(Level parent) {
        super.update(parent);
        Player player = parent.getPlayer();
        if (player == null) {
            if (System.currentTimeMillis() - pathAge > 5000) {
                List<GridCell> p = parent.finder.findPath(getX() / Tile.TILE_SIZE, getY() / Tile.TILE_SIZE, LD51Game.rand(parent.w), LD51Game.rand(parent.h), parent.pathfindingGrid);
                if (p != null && !p.isEmpty()) {
                    path = new ArrayList<>(p);
                    pathAge = System.currentTimeMillis();
                    currentTarget = path.get(0);
                    targetAge = System.currentTimeMillis();
                }
            }
        } else {
            if (System.currentTimeMillis() - pathAge > 5000) {
                List<GridCell> p = parent.finder.findPath(getX() / Tile.TILE_SIZE, getY() / Tile.TILE_SIZE, player.getX() / Tile.TILE_SIZE, player.getY() / Tile.TILE_SIZE, parent.pathfindingGrid);
                if (p != null && !p.isEmpty()) {
                    path = new ArrayList<>(p);
                    pathAge = System.currentTimeMillis();
                    currentTarget = path.get(0);
                    targetAge = System.currentTimeMillis();
                }
            }
        }
        if (currentTarget != null) {
            angle = (float) -(Math.atan2(this.x - currentTarget.getX() * Tile.TILE_SIZE, this.y - currentTarget.getY() * Tile.TILE_SIZE) * 180 / Math.PI) - 180;
            float xSpeed = (currentTarget.getX() * Tile.TILE_SIZE - getX());
            float ySpeed = (currentTarget.getY() * Tile.TILE_SIZE - getY());
            if (currentWeapon != null && player != null) {
                if (Level.dist(this, player) < currentWeapon.range()) {
                    dX = 0;
                    dY = 0;
                    currentWeapon.use(parent, parent.getPlayer());
                }
            }else if(player == null){
                Mob r = parent.findMobInRange(this,currentWeapon.range());
                if(r != null){
                    currentWeapon.use(parent,r);
                }
            }
            float factor = (float) (2 / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed));
            xSpeed *= factor;
            ySpeed *= factor;
            dX = xSpeed;
            dY = ySpeed;

            if (Level.dist(this, (Tile) currentTarget) < 16) {
                i++;
                if (i < path.size()) {
                    currentTarget = path.get(i);
                    targetAge = System.currentTimeMillis();
                } else {
                    i = 0;
                    currentTarget = null;
                    path = null;
                    pathAge = 0;
                    targetAge = 0;
                }

            }
        }
    }

    @Override
    public void renderShapes(ShapeRenderer renderer) {
        super.renderShapes(renderer);
        if (health != maxHealth) {
            renderer.setColor(Color.BLACK);
            renderer.rect(getX(), getY() + getH(), getW(), 2);
            renderer.setColor(Color.RED);
            renderer.rect(getX(), getY() + getH(), getW() * (health / maxHealth), 2);
        }

        if (path != null) {
            for (GridCell c : path) {
                //    renderer.rect(c.x * Tile.TILE_SIZE, c.y * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
            }
        }
    }
}
