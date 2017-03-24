package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class MonsterSky extends Actor implements Activities {

	private boolean mThrow;
	private long tick;

	private Animation moveUp, moveDown, moveLeft, moveRight;

	public MonsterSky(float x, float y, int orient, boolean alive, int attack,
			int defense, int healthMax) {
		positionX = x;
		positionY = y;
		this.orient = orient;
		this.attack = attack;
		this.defense = defense;
		this.healthMax = healthMax;
		this.alive = alive;
		init();
	}

	@Override
	public void init() {
		moveLeft = new Animation(280, Assets.bLeft);
		moveRight = new Animation(280, Assets.bRight);
		moveUp = new Animation(280, Assets.bUp);
		moveDown = new Animation(280, Assets.bDown);
		mThrow = false;
		health = healthMax;
		tick = System.currentTimeMillis();
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (orient == Monster.LEFT) {
			return moveLeft.getCurrentFrame();
		} else if (orient == Monster.RIGHT) {
			return moveRight.getCurrentFrame();
		} else if (orient == Monster.UP) {
			return moveUp.getCurrentFrame();
		} else {
			return moveDown.getCurrentFrame();
		}
	}

	@Override
	protected void move() {
		moveDown.tick();
		moveLeft.tick();
		moveRight.tick();
		moveUp.tick();
		switch (orient) {
		case Monster.LEFT:
			width = 70;
			height = 76;
			positionX -= Monster.SPEED;
			break;
		case Monster.RIGHT:
			width = 70;
			height = 76;
			positionX += Monster.SPEED;
			break;
		case Monster.UP:
			width = 95;
			height = 83;
			positionY -= Monster.SPEED;
			break;
		case Monster.DOWN:
			width = 93;
			height = 50;
			positionY += Monster.SPEED;
			break;
		}
		image = getCurrentAnimationFrame();
		if (System.currentTimeMillis() - tick >= 3000) {
			tick = System.currentTimeMillis();
			mThrow = true;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y, null);
		g2.setColor(Color.YELLOW);
		if (health <= 50)
			g2.setColor(Color.RED);
		g2.fillRect(x + 30, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 30, y - 10, 37 , 3);
		g2.setColor(Color.RED);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", x + 71, y - 5);
		g2.setColor(Color.WHITE);
		g2.drawString("Happy", positionX - 12, y - 17);
	}

	protected void attack(Actor actor) {
		int result = attack - actor.defense;
		if (result <= 0) {
			result = 1;
		}
		actor.takeDamage(result);
	}

	protected void takeDamage(int damage) {
		health -= damage;
		if (health < 0) {
			health = 0;
		}
	}

	public boolean collisionSkillFigure(ArrayList<SkillFigure> skills) {
		for (SkillFigure skill : skills) {
			if (skill.alive) {
				float x = skill.positionX;
				float y = skill.positionY;
				if (x >= positionX - width / 2 - 30
						&& x <= positionX + width / 2
						&& y >= positionY - height / 2 - 30
						&& y <= positionY + height / 2) {
					skill.alive = false;
					return true;
				}
			}
		}
		return false;
	}

	public boolean isThrow() {
		return mThrow;
	}

	public void setThrow(boolean mThrow) {
		this.mThrow = mThrow;
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}

}
