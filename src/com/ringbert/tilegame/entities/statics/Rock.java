package com.ringbert.tilegame.entities.statics;

import java.awt.Graphics;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.gfx.Assets;
import com.ringbert.tilegame.items.Item;
import com.ringbert.tilegame.tile.Tile;

public class Rock extends StaticEntity {

	public Rock(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 3;
		bounds.y = (int) (height / 2f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 2f);
		
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		for(int i = 1; i <= randInt(1, 4); i++){
			handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x + randInt(-20, 20),(int) y + randInt(-20, 20)));
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.rock, (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

}
