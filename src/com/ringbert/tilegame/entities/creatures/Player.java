package com.ringbert.tilegame.entities.creatures;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.entities.Entity;
import com.ringbert.tilegame.entities.statics.Gate;
import com.ringbert.tilegame.gfx.Animation;
import com.ringbert.tilegame.gfx.Assets;
import com.ringbert.tilegame.inventory.Inventory;

public class Player extends Creature {

	// Animations
	private Animation animDown, animUp, animLeft, animRight;
	private Animation animAttackDown, animAttackUp, animAttackLeft, animAttackRight;
	private int animSpeed;

	// Attack timer
	private long lastAttackTimer, attackCooldown = 500, attackTimer = attackCooldown;

	private Inventory inventory;

	private float sightRange;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		bounds.x = 22;
		bounds.y = 44;
		bounds.width = 19;
		bounds.height = 19;

		health = 20;
		// Animations
		animSpeed = 500;
		animDown = new Animation(animSpeed, Assets.player_down);
		animUp = new Animation(animSpeed, Assets.player_up);
		animLeft = new Animation(animSpeed, Assets.player_left);
		animRight = new Animation(animSpeed, Assets.player_right);
		animAttackDown = new Animation(animSpeed, Assets.player_attack_down);
		animAttackUp = new Animation(animSpeed, Assets.player_attack_up);
		animAttackLeft = new Animation(animSpeed, Assets.player_attack_left);
		animAttackRight = new Animation(animSpeed, Assets.player_attack_right);

		inventory = new Inventory(handler);

		sightRange = 128;

	}

	@Override
	public void tick() {
		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();
		animAttackDown.tick();
		animAttackUp.tick();
		animAttackLeft.tick();
		animAttackRight.tick();
		// Movement
		getInput();
		move();
		handler.getGameCamera().centerOnEntity(this);
		// Attack
		checkAttacks();
		// Inventory
		inventory.tick();
	}

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown)
			return;

		Rectangle cb = getCollosionBounds(0, 0);
		Rectangle ar = new Rectangle();
		int arSize = 40;
		ar.width = arSize;
		ar.height = arSize;

		if (handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}

		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollosionBounds(0, 0).intersects(ar)) {
				e.hurt(randInt(1, 5));
				return;
			}
		}
	}

	@Override
	public void die() {
		System.out.println("You lose");
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (handler.getKeyManager().up) {
			yMove = -speed;
		}
		if (handler.getKeyManager().down) {
			yMove = speed;
		}
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = speed;
		}
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
			changeGate();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffset()),
				(int) (y - handler.getGameCamera().getyOffset()), width, height, null);
		inventory.render(g);
	}

	public void changeGate(Gate gate) {
		gate.changeGate();
	}

	public void changeGate() {
		ArrayList<Entity> sphereCheck = sphereCollide(x, y, sightRange);
		for (Entity e : sphereCheck) {
			if (e.getClass().equals(Gate.class)) {
				changeGate((Gate) e);
			}
		}
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
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
		} else if (handler.getKeyManager().aDown) {
			return animAttackDown.getCurrentFrame();
		} else if (handler.getKeyManager().aUp) {
			return animAttackUp.getCurrentFrame();
		} else if (handler.getKeyManager().aLeft) {
			return animAttackLeft.getCurrentFrame();
		} else if (handler.getKeyManager().aRight) {
			return animAttackRight.getCurrentFrame();
		} else {
			return Assets.player;
		}
	}

}
