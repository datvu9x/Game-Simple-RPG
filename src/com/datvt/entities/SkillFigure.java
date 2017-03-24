package com.datvt.entities;

import java.awt.Graphics2D;
import java.util.ArrayList;

import com.datvt.main.GameMain;
import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class SkillFigure extends Actor implements Activities {

	private static final float SPEED = 4f;

	private Animation skill;

	public SkillFigure(float x, float y, int orient) {
		this.positionX = x;
		this.positionY = y;
		this.orient = orient;
		init();
	}

	@Override
	public void init() {
		skill = new Animation(100, Assets.skill);
		alive = true;
	}

	@Override
	protected void move() {
		skill.tick();
		if (orient == 0) {
			positionX += SPEED;
			if (positionX >= GameMain.WIDTH) {
				alive = false;
			}

		} else {
			positionX -= SPEED;
			if (positionX <= 0) {
				alive = false;
			}
		}
		image = skill.getCurrentFrame();
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x - 40, y, null);
	}

	public boolean collisionSkillMonster(SkillFigure skill,
			ArrayList<SkillMonster> monster) {
		for (SkillMonster mons : monster) {
			if (mons.alive) {
				float x = mons.positionX;
				float y = mons.positionY;
				if (x >= positionX - width / 2 - 20
						&& x <= positionX + width / 2 + 20
						&& y >= positionY - height / 2 - 50
						&& y <= positionY + height / 2 + 50) {
					mons.alive = false;
					return true;
				}
			}
		}
		return false;
	}

	@Override
	protected void attack(Actor actor) {
	}

	@Override
	protected void takeDamage(int damage) {
	}

	@Override
	protected boolean collision(Actor actor) {
		return false;
	}
}
