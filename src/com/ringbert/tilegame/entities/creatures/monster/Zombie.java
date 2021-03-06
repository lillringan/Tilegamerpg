package com.ringbert.tilegame.entities.creatures.monster;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.entities.creatures.Creature;
import com.ringbert.tilegame.gfx.Animation;
import com.ringbert.tilegame.gfx.Assets;

public class Zombie extends Monster {

	private Animation animDown, animUp, animLeft, animRight;

	

	public Zombie(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;

		animDown = new Animation(500, Assets.goblin_down);
		animUp = new Animation(500, Assets.goblin_up);
		animLeft = new Animation(500, Assets.goblin_left);
		animRight = new Animation(500, Assets.goblin_right);
		
		minDmg = 1;
		maxDmg = 3;
	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();

		if (target == null && !isChasing()) {
			look();
		}else {
			chase();
			attack();
			stopChase();
		}
		if(target == null && !isChasing() && x != startX && y != startY){
			returnToStartPos();
		}

	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
	}

	@Override
	public void die() {

	}

	private BufferedImage getCurrentAnimationFrame() {

		if (xMove < 0) {
			return animLeft.getCurrentFrame();
		} else if (xMove > 0) {
			return animRight.getCurrentFrame();
		} else if (yMove < 0) {
			return animUp.getCurrentFrame();
		} else if (yMove > 0) {
			return animDown.getCurrentFrame();
		} else {
			return Assets.goblin;
		}

	}

}
