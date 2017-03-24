package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

import com.datvt.images.ImageLibrary;
import com.datvt.main.GameMain;
import com.datvt.utils.Activities;

public class MonsterWater extends Actor implements Activities {

	public MonsterWater(float x, float y, int orient, boolean alive,
			int attack, int defense, int healthMax) {
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
		width = 119;
		height = 56;
		health = healthMax;
	}

	@Override
	protected void move() {
		switch (orient) {
		case Monster.LEFT:
			positionX -= Monster.SPEED;
			image = GameMain.images.get(ImageLibrary.MONSTER_WATER + 1);
			break;
		case Monster.RIGHT:
			positionX += Monster.SPEED;
			image = GameMain.images.get(ImageLibrary.MONSTER_WATER + 0);
			break;
		case Monster.UP:
			positionY -= Monster.SPEED;
			break;
		case Monster.DOWN:
			positionY += Monster.SPEED;
			break;
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
		g2.fillRect(x + 20, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 20, y - 10, 37 , 3);
		g2.setColor(Color.RED);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", x + 60, y - 5);
		g2.setColor(Color.WHITE);
		g2.drawString("Acnologia", positionX - 12, y - 17);
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
				if (x >= positionX - width / 2 && x <= positionX + width / 2
						&& y >= positionY - height / 2
						&& y <= positionY + height / 2) {
					skill.alive = false;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}
}
