package me.jack.ld51.level;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import me.jack.ld51.Entity.Entity;
import me.jack.ld51.Entity.Mobs.BaseEnemy;
import me.jack.ld51.Entity.Mobs.GruntEnemy;
import me.jack.ld51.Entity.Mobs.Mob;
import me.jack.ld51.Entity.Mobs.Player;
import me.jack.ld51.Entity.Mobs.RangedEnemy;
import me.jack.ld51.Entity.Particles.Decorative.BloodParticle;
import me.jack.ld51.Entity.Particles.Decorative.DecorativeParticle;
import me.jack.ld51.Entity.Particles.Drops.CoinDrop;
import me.jack.ld51.Entity.Particles.Drops.DropParticle;
import me.jack.ld51.Entity.Particles.Drops.HealthDrop;
import me.jack.ld51.Entity.Particles.Particle;
import me.jack.ld51.Entity.Particles.Weapons.WeaponParticle;
import me.jack.ld51.Entity.Projectiles.Projectile;
import me.jack.ld51.LD51Game;
import me.jack.ld51.tile.FloorTile;
import me.jack.ld51.tile.LeftStairTile;
import me.jack.ld51.tile.RightStairTile;
import me.jack.ld51.tile.StairTile;
import me.jack.ld51.tile.Tile;
import me.jack.ld51.tile.WallTile;

public class Level {

    public ArrayList<Entity> entities = new ArrayList<Entity>();


    private int w, h;

    Tile[][] map;

    Player player;

    public long roundTimer = System.currentTimeMillis();
    public int currentRound = 0;

    List<StairTile> stairs = new ArrayList<>();

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

                if ((x - 2 == 0 && y - 2 == 0) || (x + 3 == w && y + 3 == h)) {
                    map[x][y] = new RightStairTile(x, y);
                    stairs.add((StairTile) map[x][y]);
                }
                if ((x - 2 == 0 && y + 3 == h) || (x + 3 == w && y - 2 == 0)) {
                    map[x][y] = new LeftStairTile(x, y);
                    stairs.add((StairTile) map[x][y]);
                }
            }
        }
        player = new Player(300, 300);
        entities.add(player);

        //  newRoundSpawnMobs();
    }

    public void renderTextures(SpriteBatch batch) {
        for (int x = 0; x != w; x++) {
            for (int y = 0; y != h; y++) {
                map[x][y].update(this);
                map[x][y].render(batch);
            }
        }
        for (Entity e : entities) {
            if (e instanceof Player)
                continue;
            e.renderTextures(batch);
        }

        // player.renderTextures(batch);
    }

    public void renderShapes(ShapeRenderer renderer) {
        for (Entity e : entities) {
            if (e instanceof Player)
                continue;
            e.renderShapes(renderer);
        }
        //  player.renderShapes(renderer);
    }


    public void newRoundStart() {
        newRoundSpawnMobs();

        newRoundChangeWalls();
    }


    public void newRoundSpawnMobs() {
        for (StairTile t : stairs) {
            for (int i = 0; i <= LD51Game.rand(5) + 1; i++) {
                t.toSpawn.add(new GruntEnemy(t.tX * Tile.TILE_SIZE, t.tY * Tile.TILE_SIZE));
                if (LD51Game.rand(5) == 0) {
                    t.toSpawn.add(new RangedEnemy(t.tX * Tile.TILE_SIZE, t.tY * Tile.TILE_SIZE));
                }
            }
        }
    }


    HashMap<Rectangle, Tile> walls = new HashMap<>();

    public void newRoundChangeWalls() {
        for (Rectangle r : walls.keySet()) {
            int tx = (int) (r.getX() / Tile.TILE_SIZE);
            int ty = (int) (r.getY() / Tile.TILE_SIZE);
            map[tx][ty] = new FloorTile(tx, ty);
        }
        walls.clear();
        int v = LD51Game.rand(5) + 2;
        for (int i = 0; i != v; i++) {
            int s = LD51Game.rand(5) + 1;
            int x = LD51Game.rand(w);
            int y = LD51Game.rand(h);
            int dX = 1, dY = 0;
            if (LD51Game.randBool()) {
                dX = 0;
                dY = 1;
            }
            if (LD51Game.randBool()) {
                dX *= -1;
                dY *= -1;
            }
            for (int k = 0; k != s; k++) {
                if (inBounds(x + (k * dX), y + (k * dY)) && map[x + (k * dX)][y + (k * dY)] instanceof FloorTile && canSpawn(new Rectangle((x + (dX * k)) * Tile.TILE_SIZE, (y + (dY * k)) * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE))) {
                    Tile t = new WallTile(x + (k * dX), y + (k * dY));
                    map[x + (k * dX)][y + (k * dY)] = t;
                    walls.put(new Rectangle((x + (dX * k)) * Tile.TILE_SIZE, (y + (dY * k)) * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE), t);
                }
            }
        }
    }

    public boolean inBounds(int x, int y) {
        return x > 0 && x < w && y > 0 && y < h;
    }

    public void update() {
        System.out.println("Level updating");
        if (currentRound == 0) {
            newRoundChangeWalls();
            currentRound = 1;
        }
        if (System.currentTimeMillis() - roundTimer > 10000) {
            roundTimer = System.currentTimeMillis();
            newRoundStart();
            currentRound++;
        }

        for (Entity e : entities) {
            e.update(this);
        }


        for (Entity e : toRemove) {
            e.onRemove(this);
            entities.remove(e);
            if (e instanceof Mob) { //TODO should be moved inside Mob and Grunt
                for (int i = 0; i != 50; i++) {
                    if (LD51Game.randBool())
                        toSpawn.add(new BloodParticle(e.getX(), e.getY(), 4, 4));
                    else
                        toSpawn.add(new BloodParticle(e.getX(), e.getY(), 2, 2));
                }
                if (LD51Game.rand(5) == 0) {
                    toSpawn.add(new HealthDrop(e.getX(), e.getY()));
                }
                if (LD51Game.rand(5) == 0) {
                    toSpawn.add(new CoinDrop(e.getX(), e.getY()));
                }
            }
        }
        Iterator<Entity> iterator = toSpawn.iterator();
        while (iterator.hasNext()) {
            Entity e = iterator.next();
            if (canSpawn(new Rectangle(e.getX(), e.getY(), e.getW(), e.getH())) || !(e instanceof Mob)) {
                entities.add(e);
                iterator.remove();
            }

        }

        toRemove.clear();
    }


    //Attempt to apply the Entity's current velocity to its position
    //If we collide with the outer walls, bounce off them
    public void doMove(Entity target) {


        float newX = target.getX() + target.getdX();
        float newY = target.getY() + target.getdY();

        if (newX < Tile.TILE_SIZE || newX > 640 - Tile.TILE_SIZE - target.getW()) {
            collideWithWall(target, new Vector2(1, 0));
        }
        if (newY < Tile.TILE_SIZE || newY > 480 - Tile.TILE_SIZE - target.getH()) {
            collideWithWall(target, new Vector2(0, 1));
        }


        if (target instanceof Player) {
            //   target.move(); return;
        }
        Rectangle r = new Rectangle((int) newX, (int) newY, target.getW(), target.getH());

        for (Rectangle wall : walls.keySet()) {
            if (wall.intersects(r)) {
                if (target.getX() > wall.getX() && target.getX() < wall.getX() + wall.getWidth()) {
                    collideWithWall(target, new Vector2(0, 1));
                } else {
                    collideWithWall(target, new Vector2(1, 0));
                }

            }
        }

        for (Entity e : entities) {
            if (e == target || e instanceof Projectile || e instanceof DecorativeParticle)
                continue;
            Rectangle r2 = new Rectangle((int) e.getX(), (int) e.getY(), e.getW(), e.getH());
            if (e instanceof BaseEnemy && target instanceof BaseEnemy) {
                r2 = new Rectangle((int) e.getX() + e.getW() / 2, (int) e.getY() + e.getH() / 2, e.getW() / 2, e.getH() / 2);
            }
            if (r2.intersects(r)) {
                if (e instanceof Mob && target instanceof Projectile && ((Projectile) target).getOwner() != e) {
                    if (!(((Projectile) target).getOwner() instanceof BaseEnemy && e instanceof BaseEnemy)) {
                        ((Mob) e).takeDamage(((Projectile) target).toFire.damage());
                        removeEntity(target);
                    }
                } else {
                    if ((!(target instanceof Projectile) || ((Projectile) target).getOwner() != e) && !(e instanceof Particle)) {
                        target.setdX(0);
                        target.setdY(0);
                    }
                }
                if (target instanceof Player && e instanceof DropParticle) {
                    ((DropParticle) e).apply(this, (Mob) target);
                    removeEntity(e);
                }
                if (target instanceof BaseEnemy && e instanceof WeaponParticle) {
                    ((Mob) target).takeDamage(((WeaponParticle) e).getDamage());
                    removeEntity(e);
                }
                if (target instanceof WeaponParticle) {
                    if (e instanceof Mob) {
                        if (((WeaponParticle) target).owner != e) {
                            ((Mob) e).takeDamage(((WeaponParticle) target).getDamage());
                            removeEntity(target);
                        }
                    }
                }
            }
        }
        target.move();
    }

    public boolean canSpawn(Rectangle entity) {
        for (Entity e : entities) {
            if (!(e instanceof Mob))
                continue;
            if (new Rectangle(e.getX(), e.getY(), e.getW(), e.getH()).intersects(entity))
                return false;
        }
        return true;
    }

    public void collideWithWall(Entity target, Vector2 wall) {
        Vector2 v = new Vector2(target.getdX(), target.getdY());
        float dot = wall.dot(v);
        Vector2 newVelocity = new Vector2(v.x - 2 * dot * wall.x, v.y - 2 * dot * wall.y);

        float factor = 2.5f;
        if (target instanceof Projectile) {
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
        if (entity instanceof Player) {
            LD51Game.gameover();
            return;
        }
        //    if (!(entity instanceof Particle) )
        toRemove.add(entity);
    }

    public Player getPlayer() {
        return player;
    }


    public static int dist(Entity o, Entity t) {
        return (int) Point2D.distance(o.getX(), o.getY(), t.getX(), t.getY());
    }

    public Mob findMobInRange(Player player, float range) {
        List<Mob> choices = new ArrayList<Mob>();
        for (Entity e : entities) {
            if (!(e instanceof Mob))
                continue;
            if (e == player)
                continue;
            if (dist(player, e) <= range)
                choices.add((Mob) e);
        }
        if (!choices.isEmpty())
            return choices.get(LD51Game.rand(choices.size()));
        return null;
    }
}
