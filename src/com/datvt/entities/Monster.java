package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;

public class Monster extends Actor implements Activities {

	public static final float SPEED = 0.5f;
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int UP = 2;
	public static final int DOWN = 3;

	private Animation moveLeft, moveRight, attackRight, attackLeft;
	private boolean checkWalk;

	public Monster(float x, float y, int orient, boolean alive, int attack,
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
		moveLeft = new Animation(300, Assets.msLeft);
		moveRight = new Animation(300, Assets.msRight);
		attackLeft = new Animation(300, Assets.msAttackLeft);
		attackRight = new Animation(300, Assets.msAttackRight);
		width = 71;
		height = 56;
		checkWalk = true;
		health = healthMax;
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (orient == LEFT) {
			return moveLeft.getCurrentFrame();
		} else if (orient == RIGHT) {
			return moveRight.getCurrentFrame();
		} else if (orient == 5) {
			return attackRight.getCurrentFrame();
		} else {
			return attackLeft.getCurrentFrame();
		}
	}

	@Override
	protected void move() {
		moveLeft.tick();
		moveRight.tick();
		attackLeft.tick();
		attackRight.tick();
		if (positionX + speedX <= 300 && positionX + speedX >= 90
				&& positionY + speedY >= 50 && positionY + speedY <= 210
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 400 && positionX + speedX >= 150
				&& positionY + speedY >= 390 && positionY + speedY <= 520
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 880 && positionX + speedX >= 610
				&& positionY + speedY >= 60 && positionY + speedY <= 200
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}

		} else if (positionX + speedX <= 880 && positionX + speedX >= 620
				&& positionY + speedY >= 380 && positionY + speedY <= 520
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 1024 && positionX + speedX >= 0
				&& positionY + speedY >= 650 && positionY + speedY <= 700
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 700 && positionX + speedX >= 120
				&& positionY + speedY >= 350 && positionY + speedY <= 700
				&& InterfaceGame.checkMap2) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 850 && positionX + speedX >= 750
				&& positionY + speedY >= 550 && positionY + speedY <= 620
				&& InterfaceGame.checkMap2) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 90 && positionX + speedX >= 10
				&& positionY + speedY >= 600 && positionY + speedY <= 700
				&& InterfaceGame.checkMap2) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 400 && positionX + speedX >= 300
				&& positionY + speedY >= 100 && positionY + speedY <= 165
				&& InterfaceGame.checkMap2) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 1024 && positionX + speedX >= 830
				&& positionY + speedY >= 0 && positionY + speedY <= 295
				&& InterfaceGame.checkMap1) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 830 && positionX + speedX >= 530
				&& positionY + speedY >= 0 && positionY + speedY <= 195
				&& InterfaceGame.checkMap1) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else if (positionX + speedX <= 530 && positionX + speedX >= 420
				&& positionY + speedY >= 0 && positionY + speedY <= 130
				&& InterfaceGame.checkMap1) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			}
		} else {
			switch (orient) {
			case LEFT:
				positionX -= SPEED;
				break;
			case RIGHT:
				positionX += SPEED;
				break;
			}
		}
		image = getCurrentAnimationFrame();
	}

	public void findFigure(Figure figure) {
		if (checkWalk) {
			if (positionX > figure.positionX) {
				orient = 0;
			} else if (positionX < figure.positionX) {
				orient = 1;
			}
		}

		if (this.getBounds().intersects(figure.getBounds())) {
			checkWalk = false;
		} else {
			checkWalk = true;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle();
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		g2.drawImage(image, x, y, 60, 40, null);
		g2.setColor(Color.YELLOW);
		if (health <= 50)
			g2.setColor(Color.RED);
		g2.fillRect(x + 15, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 15, y - 10, 37 , 3);
		g2.setColor(Color.RED);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", x + 56, y - 5);
		g2.setColor(Color.WHITE);
		g2.drawString("Spider", positionX - 12, y - 17);
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
		if (health <= 0) {
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
