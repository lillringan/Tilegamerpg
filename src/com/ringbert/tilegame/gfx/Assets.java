package com.ringbert.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 32, height = 32;
	
	public static BufferedImage dirt, grass, wall, tree, rock, wood, fence, gate, player, goblin, goldbar;
	public static BufferedImage[] player_down, player_up, player_left, player_right;
	public static BufferedImage[] player_attack_down, player_attack_up, player_attack_left, player_attack_right;
	public static BufferedImage[] goblin_down, goblin_up, goblin_left, goblin_right;
	public static BufferedImage[] btn_start;
	
	public static void init(){
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/textures/sheet.png"));
		
		btn_start = new BufferedImage[2];
		btn_start[0] = sheet.crop(width * 6, height * 4, width * 2, height);
		btn_start[1] = sheet.crop(width * 6, height * 5, width * 2, height);
		//Player move
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		//Player attack
		player_attack_down = new BufferedImage[2];
		player_attack_up = new BufferedImage[2];
		player_attack_left = new BufferedImage[2];
		player_attack_right = new BufferedImage[2];
		
		player_down[0] = sheet.crop(width * 4, 0, width, height);
		player_down[1] = sheet.crop(width * 5, 0, width, height);
		
		player_up[0] = sheet.crop(width * 6, 0, width, height);
		player_up[1] = sheet.crop(width * 7, 0, width, height);
		
		player_left[0] = sheet.crop(width * 6, height, width, height);
		player_left[1] = sheet.crop(width * 7, height, width, height);
		
		player_right[0] = sheet.crop(width * 4, height, width, height);
		player_right[1] = sheet.crop(width * 5, height, width, height);
		
		player = sheet.crop(width * 4, 0, width, height);
		
		player_attack_down[0] = sheet.crop(0, height * 3, width, height);
		player_attack_down[1] = sheet.crop(width, height * 3, width, height);
		
		player_attack_right[0] = sheet.crop(width * 2, height * 3, width, height);
		player_attack_right[1] = sheet.crop(width * 3, height * 3, width, height);
		
		player_attack_up[0] = sheet.crop(0, height * 4, width, height);
		player_attack_up[1] = sheet.crop(width, height * 4, width, height);
		
		player_attack_left[0] = sheet.crop(width * 2, height * 4, width, height);
		player_attack_left[1] = sheet.crop(width * 3, height * 4, width, height);
		
		goblin_down = new BufferedImage[2];
		goblin_up = new BufferedImage[2];
		goblin_left = new BufferedImage[2];
		goblin_right = new BufferedImage[2];
		
		goblin_down[0] = sheet.crop(width * 4, height * 2, width, height);
		goblin_down[1] = sheet.crop(width * 5, height * 2, width, height);
		
		goblin_up[0] = sheet.crop(width * 6, height * 2, width, height);
		goblin_up[1] = sheet.crop(width * 7, height * 2, width, height);
		
		goblin_left[0] = sheet.crop(width * 6, height * 3, width, height);
		goblin_left[1] = sheet.crop(width * 7, height * 3, width, height);
		
		goblin_right[0] = sheet.crop(width * 4, height * 3, width, height);
		goblin_right[1] = sheet.crop(width * 5, height * 3, width, height);
		
		goblin = sheet.crop(width * 4, height * 2, width, height);
		
		tree = sheet.crop(0, 0, width, height * 2);
		dirt = sheet.crop(width, 0, width, height);
		grass = sheet.crop(width * 2, 0, width, height);
		wall = sheet.crop(width * 3, 0, width, height);
		rock = sheet.crop(0, height * 2, width, height);
		wood = sheet.crop(width, height, width, height);
		fence = sheet.crop(width * 2, height, width, height);
		gate = sheet.crop(width * 3, height, width, height);
		goldbar = sheet.crop(width, height * 2, width, height);
		
		
		
		
		
		
	}

}
