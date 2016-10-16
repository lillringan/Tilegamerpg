package com.ringbert.tilegame.entities.statics;

import java.awt.Graphics;
import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.gfx.Assets;
import com.ringbert.tilegame.items.Item;
import com.ringbert.tilegame.tile.Tile;

public class Tree extends StaticEntity {
	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT * 2);
		
		bounds.x = 10;
		bounds.y = (int) (height / 1.15f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.1f);
	}

	@Override
	public void tick() {
		
	}
	
	@Override
	public void die(){
		for(int i = 1; i <= randInt(1, 4); i++){
			handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x + width / 2 + randInt(-20, 20),(int) y + height / 2 + randInt(-20, 20)));
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree,(int) (x - handler.getGameCamera().getxOffset()),
				(int) (y- handler.getGameCamera().getyOffset()), width, height, null);
	}
	
}
