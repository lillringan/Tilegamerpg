package com.ringbert.tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

import javax.net.ssl.HandshakeCompletedListener;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private Slot slot;

	// Inventory measures
	private int inventoryWidth, inventoryHeight, inventoryOffset, slotSize, margin;

	public Inventory(Handler handler) {
		this.handler = handler;
		slot = new Slot(handler, handler.getWidth() - 350, handler.getHeight() - 350);
		inventoryItems = new ArrayList<>();
		inventoryOffset = 324;
		inventoryWidth = 320;
		inventoryHeight = 320;
		slotSize = 32;
		margin = 4;
	}

	public void tick() {
		if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_I)) {
			active = !active;
		}
		if (!active) {
			return;
		}

	}

	public void render(Graphics g) {
		if (!active) {
			return;
		} else {
			slot.render(g);
			showItem(g);
		}
	}

	// Inventory methods

	private void showItem(Graphics g) {
		int j = -1;
		for (int i = 0; i < inventoryItems.size(); i++) {
			if (i % 10 == 0) {
				j++;
			}
			if (inventoryItems.get(i) != null) {
				g.drawImage(inventoryItems.get(i).getTexture(), handler.getWidth() - inventoryOffset + slotSize * i,
						handler.getHeight() - inventoryOffset + slotSize * j, slotSize, slotSize, null);
			}
		}
	}

	public void addItem(Item item) {
		for (Item i : inventoryItems) {
			if (i.getId() == item.getId()) {
				i.setCount(i.getCount() + item.getCount());
				return;
			}
		}
		inventoryItems.add(item);
	}

	public void removeItem() {
		for(int i = 0; i < inventoryItems.size(); i++){
			inventoryItems.remove(i);
		}
	}

	// GETTERS SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public class Slot {

		private Handler handler;

		private int x;
		private int y;

		private final int width = 32;
		private final int height = 32;

		private Rectangle bounds;

		private boolean empty = true;
		private Item item;

		public Slot(Handler handler, int x, int y) {
			this.handler = handler;
			this.x = x;
			this.y = y;
			bounds = new Rectangle(x, y, width, height);
			item = null;
		}

		public void render(Graphics g) {

			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					g.setColor(Color.GRAY);
					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j,
							handler.getHeight() - inventoryOffset + slotSize * i, slotSize, slotSize);

					g.setColor(Color.BLACK);
					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j,
							handler.getHeight() - inventoryOffset + slotSize * i, margin, slotSize);

					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j,
							handler.getHeight() - inventoryOffset + slotSize * i, slotSize, margin);

				}
			}
			g.fillRect(handler.getWidth() - inventoryOffset, handler.getHeight() - inventoryOffset + inventoryWidth,
					inventoryHeight, margin);
			g.fillRect(handler.getWidth() - inventoryOffset + inventoryWidth, handler.getHeight() - inventoryOffset,
					margin, inventoryHeight);

		}

		public Handler getHandler() {
			return handler;
		}

		public void setHandler(Handler handler) {
			this.handler = handler;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}

	}

}
