package com.ringbert.tilegame.entities.statics;

import java.awt.Graphics;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.gfx.Assets;
import com.ringbert.tilegame.tile.Tile;

public class Fence extends StaticEntity{

	public Fence(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 3;
		bounds.y = (int) (height / 1.1f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 1.05f);
		
		health = 100000;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.fence, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

}
