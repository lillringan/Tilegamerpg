package com.ringbert.tilegame.entities.statics;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.entities.Entity;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
	}

}
