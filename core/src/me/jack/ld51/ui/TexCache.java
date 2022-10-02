package me.jack.ld51.ui;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TexCache {

    public static final HashMap<String, Texture> cache = new HashMap<>();

    public static Texture get(String name){
        if(cache.containsKey(name)){
            return cache.get(name);
        }else{
            Texture t = new Texture(name);
            cache.put(name,t);
            return t;
        }
    }
}
