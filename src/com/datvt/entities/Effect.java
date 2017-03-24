package com.datvt.entities;

import java.awt.Graphics2D;

import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class Effect extends Actor {

	private Animation animEffect, animCollision;
	private int id;

	public Effect(float x, float y, int id) {
		positionX = x;
		positionY = y;
		this.id = id;
		init();
	}

	public void init() {
		animEffect = new Animation(100, Assets.effect);
		animCollision = new Animation(200, Assets.collision);
		alive = true;
	}

	protected void move() {
		if (alive) {
			if (id == 0) {
				animEffect.tick();
				image = animEffect.getCurrentFrame();
			} else {
				animCollision.tick();
				image = animCollision.getCurrentFrame();
			}
		}
		if ((animEffect.getIndex() == Assets.effect.length - 1)
				|| (animCollision.getIndex() == Assets.collision.length - 1)) {
			alive = false;
		}

	}

	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x - 20, y - 40, null);
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
