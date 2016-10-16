package com.ringbert.tilegame.entities.creatures.monster;

import java.util.ArrayList;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.entities.Entity;
import com.ringbert.tilegame.entities.creatures.Creature;
import com.ringbert.tilegame.entities.creatures.Player;

public abstract class Monster extends Creature {

	protected Entity target;
	protected float sightRange;
	protected float attackRange;
	protected int minDmg;
	protected int maxDmg;
	protected float maxSpeed = 2f;
	protected boolean chasing;
	protected float startX;
	protected float startY;

	// Timer variables for stopChase()
	private long last;
	private long timer;
	private long now;
	private boolean lastTime;

	// Attack timer
	private long lastAttackTimer, attackCooldown = 1000, attackTimer = attackCooldown;

	public Monster(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);
		target = null;
		sightRange = 128;
		chasing = false;

		startX = 400;
		startY = 200;

		lastTime = true;
		timer = 0;
		last = 0;
		now = 0;

		bounds.x = (int) x;
		bounds.y = (int) y;
		bounds.width = width;
		bounds.height = height;
	}

	protected void look() {

		ArrayList<Entity> objects = sphereCollide(x, y, sightRange);

		for (Entity entity : objects)
			if (entity.equals(handler.getWorld().getEntityManager().getPlayer()))
				setTarget((Entity) entity);

	}

	protected void chase() {

		xMove = (getTarget().getX() - x);
		yMove = (getTarget().getY() - y);

		float dist = Math.abs((x - getTarget().getX()) + (y - getTarget().getY()));
		if (dist > 40) {

			if (xMove > maxSpeed)
				xMove = maxSpeed;
			if (xMove < -maxSpeed)
				xMove = -maxSpeed;

			if (yMove > maxSpeed)
				yMove = maxSpeed;
			if (yMove < -maxSpeed)
				yMove = -maxSpeed;

			if (!checkEntityCollision(xMove, 0)) {
				moveX();
			}
			if (!checkEntityCollision(0, yMove)) {
				moveY();
			}
			setChasing(true);
		}
	}

	protected void attack() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();
		if (attackTimer < attackCooldown) {
			return;
		}

		attackTimer = 0;

		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this)) {
				continue;
			}
			if (e.getClass().equals(Player.class) && e.dist(x, y, target.getX(), target.getY()) < 40) {
				e.hurt(randInt(minDmg, maxDmg));
				System.out.println("damaged the player, player hp: " + e.getHealth());
				return;
			}
		}
	}

	protected void stopChase() {
		if (target.dist(x, y, target.getX(), target.getY()) > sightRange && isChasing()) {
			if (lastTime) {
				last = System.nanoTime();
				lastTime = false;
			}
			now = System.nanoTime();
			timer += now - last;
			last = now;

			if (timer >= 3000000000L) {
				setChasing(false);
				target = null;
				timer = 0;
				lastTime = true;
			}

		} else {
			now = 0;
			last = 0;
			timer = 0;
			lastTime = true;
		}
	}

	protected void returnToStartPos() {
		xMove = (startX - x);
		yMove = (startY - y);

		float dist = Math.abs((x - startX) + (y - startY));
		if (dist > 0 && !isChasing()) {

			if (xMove > maxSpeed)
				xMove = maxSpeed;
			if (xMove < -maxSpeed)
				xMove = -maxSpeed;

			if (yMove > maxSpeed)
				yMove = maxSpeed;
			if (yMove < -maxSpeed)
				yMove = -maxSpeed;
			
			if (!checkEntityCollision(xMove, 0)) {
				moveX();
			}
			if (!checkEntityCollision(0, yMove)) {
				moveY();
			}

		} else if (dist == 0) {
			target = null;
		}
	}

	protected void setTarget(Entity entity) {
		target = entity;
	}

	protected Player getTarget() {
		return handler.getWorld().getEntityManager().getPlayer();
	}

	public void setSightRange(float sightRange) {
		this.sightRange = sightRange;
	}

	public boolean isChasing() {
		return chasing;
	}

	public void setChasing(boolean chasing) {
		this.chasing = chasing;
	}

}
