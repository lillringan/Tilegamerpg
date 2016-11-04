package com.ringbert.tilegame.entities.statics;

import java.awt.Graphics;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.gfx.Assets;
import com.ringbert.tilegame.tile.Tile;

public class Gate extends StaticEntity{

	private boolean open;
	
	public Gate(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);
		bounds.x = 3;
		bounds.y = (int) (height / 1.1f);
		bounds.width = width - 6;
		bounds.height = (int) (height - height / 1.05f);
		
		health = 100000;
		open = false;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		if(isOpen()){
			g.drawImage(Assets.gate, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}else{
			g.drawImage(Assets.fence, (int) (x - handler.getGameCamera().getxOffset()),
					(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		}
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}
	
	public void changeGate(){
		if(!isOpen()){
			bounds.x = 0;
			bounds.y = 0;
			bounds.width = 0;
			bounds.height = 0;
			setOpen(open = !open);
		}else{
			bounds.x = 3;
			bounds.y = (int) (height / 1.1f);
			bounds.width = width - 6;
			bounds.height = (int) (height - height / 1.05f);
			setOpen(open = !open);
		}
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	
	

}
