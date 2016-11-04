package com.ringbert.tilegame.inventory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.net.ssl.HandshakeCompletedListener;

import com.ringbert.tilegame.Handler;
import com.ringbert.tilegame.items.Item;

public class Inventory {

	private Handler handler;
	private boolean active = false;
	private ArrayList<Item> inventoryItems;
	private Rectangle inventory;
	private Slot slot;
	
	//Inventory measures
	private int inventoryWidth, inventoryHeight, inventoryOffset, slotSize, margin;

	public Inventory(Handler handler) {
		this.handler = handler;
		inventoryItems = new ArrayList<>();
		inventory = new Rectangle(0, 0, 50, 50);
		slot = null;
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
			for (int i = 0; i < 10; i++) {
				for (int j = 0; j < 10; j++) {
					g.setColor(Color.GRAY);
					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j, 
							handler.getHeight() - inventoryOffset + slotSize * i,
							slotSize, slotSize);
					
					g.setColor(Color.BLACK);
					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j,
							handler.getHeight() - inventoryOffset + slotSize * i, margin, slotSize);
					
					g.fillRect(handler.getWidth() - inventoryOffset + slotSize * j,
							handler.getHeight() - inventoryOffset + slotSize * i, slotSize, margin);
					showItem(g);
				}
			}
			g.fillRect(handler.getWidth() - inventoryOffset, handler.getHeight() - inventoryOffset + inventoryWidth, inventoryHeight, margin);
			g.fillRect(handler.getWidth() - inventoryOffset + inventoryWidth, handler.getHeight() - inventoryOffset, margin, inventoryHeight);
		}
	}

	// Inventory methods

	private void showItem(Graphics g) {
		int j = -1;
		for (int i = 0; i < inventoryItems.size(); i++){
			if(i % 10 == 0)
				j++;
			g.drawImage(inventoryItems.get(i).getTexture(), handler.getWidth() - inventoryOffset + slotSize * i,
					(handler.getHeight() - inventoryOffset) + slotSize * j, slotSize, slotSize, null);
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

	// GETTERS SETTERS
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	protected class Slot {

		private Handler handler;

		private int x;
		private int y;

		private final int ITEMS_WIDTH = 32;
		private final int ITEMS_HEIGHT = 32;

		private Rectangle bounds;
		private Line2D verticleLine;
		private Line2D horizontalLine;

		private boolean empty = true;
		private Item item;

		protected Slot(Handler handler) {
			this.handler = handler;
			bounds = new Rectangle(x, y, ITEMS_WIDTH, ITEMS_HEIGHT);
			verticleLine.setLine(ITEMS_WIDTH, 0, 3, ITEMS_HEIGHT);
			horizontalLine.setLine(0, ITEMS_HEIGHT, ITEMS_WIDTH + 3, 3);

		}

		public Item createNew(Item item, int x, int y) {
			item = new Item(item.getTexture(), item.getName(), item.getId());
			item.setPosition(x, y);
			return item;
		}

		public void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
			bounds.x = x;
			bounds.y = y;
		}

		public Handler getHandler() {
			return handler;
		}

		public void setHandler(Handler handler) {
			this.handler = handler;
		}

		public int getITEMS_WIDTH() {
			return ITEMS_WIDTH;
		}

		public int getITEMS_HEIGHT() {
			return ITEMS_HEIGHT;
		}

	}

}
