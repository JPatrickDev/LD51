package me.jack.ld51.Entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import me.jack.ld51.Entity.Particles.Particle;
import me.jack.ld51.level.Level;

public abstract class Entity {

    private Texture texture;

    protected int x, y;
    protected int w;
    protected int h;


    //How many times the Entity has bounced off a wall
    public int bounces;

    protected float dX, dY;

    public Entity(Texture t, int x, int y) {
        this(x, y, t.getWidth(), t.getHeight());
        this.texture = t;
    }

    public Entity(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void renderTextures(SpriteBatch batch) {
        batch.draw(texture, getX(), getY());
    }

    public void renderShapes(ShapeRenderer renderer) {

    }

    public void update(Level parent) {
        parent.doMove(this);
    }

    //Slightly tweak the current velocity, so bouncing between two walls isn't deterministic
    public void randVel() {
        float xOff = new Random().nextFloat();
        float yOff = new Random().nextFloat();
        if (dX > 0) {
            dX += xOff;
        } else {
            dX -= xOff;
        }

        if (dY > 0) {
            dY += yOff;
        } else {
            dY -= yOff;
        }
    }

    public void onDeath(Level level) {
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getW() {
        return w;
    }


    public int getH() {
        return h;
    }

    //Should only be called from Level, doesn't perform any checks
    public void move() {
        x += dX;
        y += dY;
        if(!(this instanceof Particle)){
        dX /= 1.1;
        dY /= 1.1;
        }else{
            dX /= 1.5;
            dY /= 1.5;
        }

        if(this instanceof Particle && (Math.abs(dX) <= 0.5 || Math.abs(dY) <= 0.5)){
            dX = 0;
            dY = 0;
        }
    }


    public float getdX() {
        return dX;
    }


    public float getdY() {
        return dY;
    }

    public void setdX(float dX) {
        this.dX = dX;
    }

    public void setdY(float dY) {
        this.dY = dY;
    }
}
