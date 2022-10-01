package me.jack.ld51;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import me.jack.ld51.level.Level;

public class LD51Game extends ApplicationAdapter {
	SpriteBatch batch;

	Level level;
	@Override
	public void create () {
		batch = new SpriteBatch();
		level = new Level();
	}

	int i =0;
	@Override
	public void render () {
		if(i % 2 == 0) {
			level.update();
			i = 0;
		}
		i++;
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		level.render(null,batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
