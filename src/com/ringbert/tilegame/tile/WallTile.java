package com.ringbert.tilegame.tile;

import com.ringbert.tilegame.gfx.Assets;

public class WallTile extends Tile {

	public WallTile(int id) {
		super(Assets.wall, id);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isSolid(){
		return true;
	}

}
