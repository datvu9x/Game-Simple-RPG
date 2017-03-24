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

public class MonsterDragon extends Actor implements Activities {

	private long tick;
	private boolean mThrow;
	private boolean checkWalk;

	private Animation moveLeft, moveRight, moveDown, moveUp;

	public MonsterDragon(float x, float y, int orient, boolean alive,
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
		moveLeft = new Animation(300, Assets.dLeft);
		moveRight = new Animation(300, Assets.dRight);
		moveUp = new Animation(300, Assets.dUp);
		moveDown = new Animation(300, Assets.dDown);
		width = 94;
		height = 91;
		health = healthMax;
		speedX = speedY = 4f;
		mThrow = false;
		checkWalk = true;
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
		moveLeft.tick();
		moveRight.tick();
		moveUp.tick();
		moveDown.tick();
		if (positionX + speedX <= 300 && positionX + speedX >= 90
				&& positionY + speedY >= 50 && positionY + speedY <= 210
				&& InterfaceGame.checkMap0) {
			if (orient == 0) {
				orient = 1;
				positionX += Monster.SPEED;
			} else if (orient == 1) {
				orient = 0;
				positionX -= Monster.SPEED;
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
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
			} else if (orient == 2) {
				orient = 3;
				positionY += Monster.SPEED;
			} else if (orient == 3) {
				orient = 2;
				positionY -= Monster.SPEED;
			}
		} else {
			switch (orient) {
			case Monster.LEFT:
				positionX -= Monster.SPEED;
				break;
			case Monster.RIGHT:
				positionX += Monster.SPEED;
				break;
			case Monster.UP:
				positionY -= Monster.SPEED;
				break;
			case Monster.DOWN:
				positionY += Monster.SPEED;
				break;
			case 5:
				positionX += 0;
				break;
			case 6:
				positionX += 0;
				break;
			}
		}

		image = getCurrentAnimationFrame();
		if (System.currentTimeMillis() - tick >= 2000) {
			tick = System.currentTimeMillis();
			mThrow = true;
		}
	}
	
	public void findFigure(Figure figure) {
		if (checkWalk) {
			if (orient == 0 || orient == 1) {
				if (positionX > figure.positionX) {
					orient = 0;
				} else if (positionX < figure.positionX) {
					orient = 1;
				} else {
					if (positionY > figure.positionY) {
						orient = 2;
					} else {
						orient = 3;
					}
				}
			} else {
				if (positionY > figure.positionY) {
					orient = 2;
				} else if (positionY > figure.positionY) {
					orient = 3;
				} else {
					if (positionX > figure.positionX) {
						orient = 0;
					} else {
						orient = 1;
					}
				}
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
		g2.drawImage(image, x, y, 50, 60, null);
		g2.setColor(Color.YELLOW);
		if (health <= 50)
			g2.setColor(Color.RED);
		g2.fillRect(x + 5, y - 10, (int) (37 * (health * 1.0 / healthMax)), 3);
		g2.setColor(Color.BLACK);
		g2.drawRect(x + 5, y - 10, 37 , 3);
		g2.setColor(Color.RED);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", x + 45, y - 5);
		g2.setColor(Color.WHITE);
		g2.drawString("Igneel", positionX - 30, y - 17);
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
						&& y >= positionY - height / 2
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

}
