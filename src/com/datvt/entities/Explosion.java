package com.datvt.entities;

import java.awt.Graphics2D;

import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class Explosion extends Actor {

	private Animation animExplosion;

	public Explosion(float x, float y) {
		positionX = x;
		positionY = y;
		init();
	}

	public void init() {
		animExplosion = new Animation(100, Assets.explosion);
		alive = true;
	}

	protected void move() {
		if (alive) {
			animExplosion.tick();
			image = animExplosion.getCurrentFrame();
		}
		if (animExplosion.getIndex() == Assets.explosion.length - 1) {
			alive = false;
		}
	}

	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y - 15, null);
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
