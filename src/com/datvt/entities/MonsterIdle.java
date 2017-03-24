package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class MonsterIdle extends Actor implements Activities {

	private Animation idleLeft, idleRight, attackLeft, attackRight;

	public MonsterIdle(float x, float y, int orient, boolean alive, int attack,
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
		idleLeft = new Animation(300, Assets.gLeft);
		idleRight = new Animation(300, Assets.gRight);
		attackLeft = new Animation(150, Assets.gAttackLeft);
		attackRight = new Animation(150, Assets.gAttackRight);
		width = 63;
		height = 94;
		health = healthMax;
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (orient == Monster.LEFT) {
			return idleLeft.getCurrentFrame();
		} else if (orient == Monster.RIGHT) {
			return idleRight.getCurrentFrame();
		} else if (orient == 6) {
			return attackRight.getCurrentFrame();
		} else {
			return attackLeft.getCurrentFrame();
		}
	}

	@Override
	protected void move() {
		idleLeft.tick();
		idleRight.tick();
		attackLeft.tick();
		attackRight.tick();
		switch (orient) {
		case 5:
			width = 97;
			height = 96;
			break;
		case 6:
			width = 56;
			height = 61;
			break;
		}
		image = getCurrentAnimationFrame();
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y, null);
		g2.setColor(Color.YELLOW);
		if (health <= 50)
			g2.setColor(Color.RED);
		g2.fillRect(x + 15, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 15, y - 10, 37 , 3);
		g2.setColor(Color.RED);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", x + 55 , y - 5);
		g2.setColor(Color.WHITE);
		g2.drawString("Huk", positionX - 12, y - 17);
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

	protected boolean collision(Actor actor) {
		if (actor.alive) {
			float x = actor.positionX;
			float y = actor.positionY;
			if (x >= positionX - width / 2 - 30 && x <= positionX + width / 2
					&& y >= positionY - height / 2 - 30
					&& y <= positionY + height / 2) {
				return true;
			}
		}
		return false;
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
}
