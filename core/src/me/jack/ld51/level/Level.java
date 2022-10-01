package me.jack.ld51.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Projectiles.Bullet;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.tile.FloorTile;
import me.jack.ld51.tile.Tile;
import me.jack.ld51.tile.WallTile;

public class Level {

    private ArrayList<Entity> entities = new ArrayList<Entity>();


    private int w, h;

    Tile[][] map;

    public Level() {
        w = 20;
        h = 15;
        map = new Tile[w][h];
        for (int x = 0; x != w; x++) {
            for (int y = 0; y != h; y++) {
                if (x == 0 || x + 1 == w || y == 0 || y + 1 == h) {
                    map[x][y] = new WallTile(x, y);
                } else {
                    map[x][y] = new FloorTile(x, y);
                }
            }
        }
        entities.add(new Player(300,300));
    }

    public void render(ShapeRenderer renderer, SpriteBatch batch) {
        for (int x = 0; x != w; x++) {
            for (int y = 0; y != h; y++) {
                map[x][y].render(renderer,batch);
            }
        }
        for (Entity e : entities) {
            e.render(renderer, batch);
        }
    }

    public void update(){
        for(Entity e : entities){
            e.update(this);
        }

        for(Entity e : toSpawn){
            entities.add(e);
        }
        for(Entity e : toRemove){
            entities.remove(e);
        }
        toSpawn.clear();
        toRemove.clear();
    }

    public boolean canMove(Entity target, float dx,float dy){
        float newX = target.getX() + dx;
        float newY = target.getY() + dy;
        if(newX < 0 || newY < 0 || newX > 640 || newY > 480 ){
            return false;
        }
        return true;
    }

    //Attempt to apply the Entity's current velocity to its position
    //If we collide with the outer walls, bounce off them
    //If collide with another Entity,
    public void doMove(Entity target){
        float newX = target.getX() + target.getdX();
        float newY = target.getY() + target.getdY();

        if(newX < Tile.TILE_SIZE || newX > 640 - Tile.TILE_SIZE - target.getW()){
            collideWithWall(target,new Vector2(1,0));
        }
        if(newY < Tile.TILE_SIZE || newY > 480 - Tile.TILE_SIZE - target.getH()) {
            collideWithWall(target, new Vector2(0, 1));
        }
        target.move();
    }

    public void collideWithWall(Entity target, Vector2 wall){
        Vector2 v = new Vector2(target.getdX(),target.getdY());
        float dot = wall.dot(v);
        Vector2 newVelocity = new Vector2(v.x - 2 * dot * wall.x, v.y - 2 * dot * wall.y);

        float factor = 2.5f;
        if(target instanceof Projectile){
            factor = 1;
        }
        target.setdX(newVelocity.x / factor);
        target.setdY(newVelocity.y / factor);
        target.bounces++;
    }

    public ArrayList<Entity> toSpawn = new ArrayList<>();
    public void spawnEntity(Entity entity) {
        toSpawn.add(entity);
    }
    public ArrayList<Entity> toRemove = new ArrayList<>();
    public void removeEntity(Entity entity) {
        toRemove.add(entity);
    }
}
