package com.ringbert.tilegame.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import com.ringbert.tilegame.Handler;

public abstract class Entity {

	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected Rectangle bounds;
	protected int health;
	public static final int DEFAULT_HEALTH = 5;
	protected boolean active = true;

	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;

		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract void die();

	public void hurt(int amt) {
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
		}
	}

	public boolean checkEntityCollision(float xOffset, float yOffset) {
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollosionBounds(0f, 0f).intersects(getCollosionBounds(xOffset, yOffset)))
				return true;
		}
		return false;
	}

	public Rectangle getCollosionBounds(float xOffset, float yOffset) {
		return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width,
				bounds.height);
	}

	public ArrayList<Entity> sphereCollide(float x, float y, float radius) {

		ArrayList<Entity> res = new ArrayList<Entity>();

		for (Entity entity : handler.getWorld().getEntityManager().getEntities()) {
			if (dist(entity.getX(), entity.getY(), x, y) < radius)
				res.add(entity);
		}

		return res;

	}

	public float dist(float x1, float y1, float x2, float y2) {

		double x = x2 - x1;
		double y = y2 - y1;

		return (float) Math.sqrt((x * x) + (y * y));
	}
	
	public int randInt(int bound1, int bound2) {
        int min = Math.min(bound1, bound2);
        int max = Math.max(bound1, bound2);
        return (int) (min + (Math.random() * (max - min)));
    }

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}