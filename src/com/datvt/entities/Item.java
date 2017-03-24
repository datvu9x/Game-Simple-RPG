package com.datvt.entities;

import java.awt.Graphics2D;

import com.datvt.images.ImageLibrary;
import com.datvt.main.GameMain;
import com.datvt.utils.Activities;

public class Item extends Actor implements Activities {

	private int hp, mp;
	private int id;

	public Item(float x, float y, int id, int hp, int mp) {
		this.positionX = x;
		this.positionY = y;
		this.id = id;
		this.hp = hp;
		this.mp = mp;
		init();
	}

	public void init() {
		image = GameMain.images.get(ImageLibrary.ITEM + id);
		alive = true;
	}

	protected void move() {
	}

	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y, 16, 20, null);
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected void attack(Actor actor) {
	}

	protected void takeDamage(int damage) {
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}
}
