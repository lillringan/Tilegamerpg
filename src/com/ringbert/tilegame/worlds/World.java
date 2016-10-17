package com.ringbert.tilegame.worlds;

import java.awt.Graphics;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.entities.EntityManager;
import com.ringbert.tilegame.entities.creatures.Player;
import com.ringbert.tilegame.entities.creatures.monster.Zombie;
import com.ringbert.tilegame.entities.statics.Fence;
import com.ringbert.tilegame.entities.statics.Gate;
import com.ringbert.tilegame.entities.statics.Rock;
import com.ringbert.tilegame.entities.statics.Tree;
import com.ringbert.tilegame.items.ItemManager;
import com.ringbert.tilegame.tile.Tile;
import com.ringbert.tilegame.utils.Utils;

/**
 * @author Christian
 *
 */
public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	//Entities
	private EntityManager entityManager;
	//Item
	private ItemManager itemManager;
	
	private int[][] tiles;
	
	public World(Handler handler, String path){
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		entityManager.addEntity(new Tree(handler, 400, 30));
		entityManager.addEntity(new Tree(handler, 350, 30));
		entityManager.addEntity(new Tree(handler, 300, 30));
		entityManager.addEntity(new Tree(handler, 250, 30));
		entityManager.addEntity(new Tree(handler, 400, 100));
		entityManager.addEntity(new Tree(handler, 350, 100));
		entityManager.addEntity(new Tree(handler, 300, 100));
		entityManager.addEntity(new Tree(handler, 250, 100));
		entityManager.addEntity(new Tree(handler, 100, 100));
		entityManager.addEntity(new Rock(handler, 100, 450));
		entityManager.addEntity(new Zombie(handler, 200, 400));
		for(int i = 128; i < 1024; i+= 64){
			entityManager.addEntity(new Fence(handler, i, 320));
		}
		entityManager.addEntity(new Gate(handler, 1024, 250));
		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick(){
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILEHEIGHT +1);
		
		for(int y = yStart; y < yEnd; y++){
			for(int x = xStart; x < xEnd; x++){
				getTile(x, y).render(g,(int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffset())
						,(int) (y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		//Items
		itemManager.render(g);
		//Entities
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y){
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.grassTile;
		
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null)
			return Tile.dirtTile;
		return t;
	}
	
	private void loadWorld(String path){
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		tiles = new int[width][height];
		
		for(int y = 0; y < height; y++){
			for(int x = 0; x < width; x++){
				tiles[x][y] = Utils.parseInt(tokens[(x + y * width + 4)]);
			}
		}
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

}