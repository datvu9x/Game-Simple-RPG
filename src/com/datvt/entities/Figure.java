package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.datvt.audios.AudioLibrary;
import com.datvt.main.GameMain;
import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Animator;
import com.datvt.utils.Assets;

public class Figure extends Actor implements Activities, KeyListener {

	private static final float SPEED = 2.5f;

	private Image imagePower;
	private int checkPower, count, mana, manaMax, level;
	private long mEXP, maxEXP, tick;
	private boolean onPower, checkRight, checkLeft, checkFall, checkFallRight,
			atLeft, atRight, skiLeft, skiRight, temp, temp1, runLeft, runRight,
			defLeft, defRight;

	private Animation moveLeft, moveRight, attackNormalLeft, attackSkillRight,
			attackSkillLeft, powerHigh, powerLow, powerRunRight, powerRunLeft,
			attackNormalRight, idleRight, idleLeft, defesLeft, defesRight,
			rLeft, rRight;
	private Animator fall, fallRight;

	public Figure(float x, float y, int health, int mana) {
		this.positionX = x;
		this.positionY = y;
		this.health = health;
		this.mana = mana;
		init();
	}

	public void init() {
		// Animation
		idleRight = new Animation(310, Assets.sIdleRight);
		idleLeft = new Animation(310, Assets.sIdleLeft);
		moveLeft = new Animation(70, Assets.sMoveLeft);
		moveRight = new Animation(70, Assets.sMoveRight);
		attackNormalLeft = new Animation(60, Assets.sAttackNormalLeft);
		attackNormalRight = new Animation(60, Assets.sAttackNormalRight);
		attackSkillLeft = new Animation(60, Assets.sAttackSkillLeft);
		attackSkillRight = new Animation(60, Assets.sAttackSkillRight);
		powerHigh = new Animation(100, Assets.sPowerHigh);
		powerLow = new Animation(100, Assets.sPowerLow);
		powerRunLeft = new Animation(100, Assets.sPowerLeft);
		powerRunRight = new Animation(100, Assets.sPowerRight);
		defesLeft = new Animation(400, Assets.defLeft);
		defesRight = new Animation(400, Assets.defRight);
		rLeft = new Animation(400, Assets.runLeft);
		rRight = new Animation(400, Assets.runRight);
		fall = new Animator(Assets.sFall);
		fall.setSpeed(200);
		fall.start();
		fallRight = new Animator(Assets.sFallRight);
		fallRight.setSpeed(200);
		fallRight.start();
		width = 39;
		height = 65;
		attack = 300;
		defense = 300;
		healthMax = 300;
		manaMax = 500;
		maxEXP = 10000;
		orient = -1;
		alive = true;
		count = 0;
		checkRight = true;
		onPower = temp1 = runLeft = runRight = defLeft = defRight = temp = checkLeft = atLeft = atRight = skiLeft = skiRight = checkFall = checkFallRight = false;
		image = getCurrentAnimationFrame();
	}

	private BufferedImage getCurrentAnimationFrame() {
		// Idle
		if (checkRight) {
			return idleRight.getCurrentFrame();
		} else if (checkLeft) {
			return idleLeft.getCurrentFrame();
		}
		// Attack
		else if (atLeft) {
			return attackNormalLeft.getCurrentFrame();
		} else if (atRight) {
			return attackNormalRight.getCurrentFrame();
		} else if (skiLeft) {
			return attackSkillLeft.getCurrentFrame();
		} else if (skiRight) {
			return attackSkillRight.getCurrentFrame();
		}
		// Run
		else if (runLeft) {
			return rLeft.getCurrentFrame();
		} else if (runRight) {
			return rRight.getCurrentFrame();
		}
		// Defense
		else if (defLeft) {
			return defesLeft.getCurrentFrame();
		} else if (defRight) {
			return defesRight.getCurrentFrame();
		}
		// Movement
		else if (orient == 1) {
			return moveLeft.getCurrentFrame();
		} else {
			return moveRight.getCurrentFrame();
		}
	}

	@Override
	protected void move() {
		moveLeft.tick();
		moveRight.tick();
		idleLeft.tick();
		idleRight.tick();
		attackNormalLeft.tick();
		attackNormalRight.tick();
		attackSkillLeft.tick();
		attackSkillRight.tick();
		powerHigh.tick();
		powerLow.tick();
		powerRunLeft.tick();
		powerRunRight.tick();
		// Collision Tile
		if (positionX + speedX >= GameMain.WIDTH || positionX + speedX <= 0) {
			speedX = 0;
		} else if (positionY + speedY >= GameMain.HEIGHT
				|| positionY + speedY <= 0) {
			speedY = 0;
		} else if (positionX + speedX <= 300 && positionX + speedX >= 90
				&& positionY + speedY >= 50 && positionY + speedY <= 210
				&& InterfaceGame.checkMap0) {
			speedX = 0;
		} else if (positionX + speedX <= 400 && positionX + speedX >= 150
				&& positionY + speedY >= 390 && positionY + speedY <= 520
				&& InterfaceGame.checkMap0) {
			speedX = 0;
		} else if (positionX + speedX <= 880 && positionX + speedX >= 610
				&& positionY + speedY >= 60 && positionY + speedY <= 200
				&& InterfaceGame.checkMap0) {
			speedX = 0;
		} else if (positionX + speedX <= 880 && positionX + speedX >= 620
				&& positionY + speedY >= 380 && positionY + speedY <= 520
				&& InterfaceGame.checkMap0) {
			speedX = 0;
		} else if (positionX + speedX <= 1024 && positionX + speedX >= 0
				&& positionY + speedY >= 650 && positionY + speedY <= 700
				&& InterfaceGame.checkMap0) {
			speedX = 0;
		} else if (positionX + speedX <= 700 && positionX + speedX >= 120
				&& positionY + speedY >= 350 && positionY + speedY <= 700
				&& InterfaceGame.checkMap2) {
			speedX = 0;
		} else if (positionX + speedX <= 850 && positionX + speedX >= 750
				&& positionY + speedY >= 550 && positionY + speedY <= 620
				&& InterfaceGame.checkMap2) {
			speedX = 0;
		} else if (positionX + speedX <= 90 && positionX + speedX >= 10
				&& positionY + speedY >= 600 && positionY + speedY <= 700
				&& InterfaceGame.checkMap2) {
			speedX = 0;
		} else if (positionX + speedX <= 400 && positionX + speedX >= 300
				&& positionY + speedY >= 100 && positionY + speedY <= 165
				&& InterfaceGame.checkMap2) {
			speedX = 0;
		} else if (positionX + speedX <= 1024 && positionX + speedX >= 830
				&& positionY + speedY >= 0 && positionY + speedY <= 295
				&& InterfaceGame.checkMap1) {
			speedX = 0;
		} else if (positionX + speedX <= 830 && positionX + speedX >= 530
				&& positionY + speedY >= 0 && positionY + speedY <= 195
				&& InterfaceGame.checkMap1) {
			speedX = 0;
		} else if (positionX + speedX <= 530 && positionX + speedX >= 420
				&& positionY + speedY >= 0 && positionY + speedY <= 130
				&& InterfaceGame.checkMap1) {
			speedX = 0;
		} else {
			positionX += speedX;
			positionY += speedY;
		}
		// check Power
		if (onPower) {
			if (checkPower == 0) {
				imagePower = powerHigh.getCurrentFrame();
			} else if (checkPower == 1) {
				imagePower = powerLow.getCurrentFrame();
			} else if (checkPower == 2) {
				imagePower = powerRunRight.getCurrentFrame();
			} else {
				imagePower = powerRunLeft.getCurrentFrame();
			}
			count++;
		}
		if (count > 600) {
			onPower = false;
			attack -= 100;
			defense -= 100;
			count = 0;
		}
		//
		if (health <= 0) {
			alive = false;
		} else {
			alive = true;
		}
		//
		image = getCurrentAnimationFrame();
		//
		if (checkFall) {
			if (fall != null)
				fall.update(System.currentTimeMillis());
			image = fall.sprite;
			speedX = -SPEED;
			if (System.currentTimeMillis() - tick > 1900) {
				checkFall = false;
				speedX = 0;
			}
		} else if (checkFallRight) {
			if (fallRight != null)
				fallRight.update(System.currentTimeMillis());
			image = fallRight.sprite;
			speedX = SPEED;
			if (System.currentTimeMillis() - tick > 1900) {
				checkFallRight = false;
				speedX = 0;
			}
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		int x = Math.round(positionX - width / 2);
		int y = Math.round(positionY - height / 2);
		if (temp) {
			x = Math.round(positionX - 100);
			y = Math.round(positionY - 50);
		}
		if (temp1) {
			x = Math.round(positionX - width / 2);
			y = Math.round(positionY - height / 2 - 15);
		}
		g2.drawImage(image, x, y, null);
		if (onPower) {
			if (!temp && !temp1) {
				if (checkPower == 0) {
					x -= 22;
					y -= 50;
				} else if (checkPower == 1) {
					x -= 43;
					y -= 15;
				} else if (checkPower == 2) {
					x -= 20;
					y -= 20;
				} else {
					x -= 10;
					y -= 20;
				}
			} else if (temp) {
				x += 20;
				y -= 5;
			} else if (temp1) {
				x -= 40;
				y -= 5;
			}
			g2.drawImage(imagePower, x, y, null);
		}
		g2.setColor(Color.BLUE);
		g2.setFont(new Font("MV Boli", Font.BOLD, 11));
		g2.drawString(health + "", positionX + 33, y - 8);
		g2.setColor(Color.WHITE);
		g2.drawString("Zoro", positionX - 10, y - 8);
	}

	protected void attack(Actor actor) {
		int result = (attack - actor.defense) / 4;
		if (result <= 0) {
			result = 15;
		}
		actor.takeDamage(result);
	}

	protected void takeDamage(int damage) {
		if (defLeft || defRight) {
			damage = 0;
		}
		if (damage >= 50) {
			if (orient == 0) {
				tick = System.currentTimeMillis();
				checkFall = true;
				GameMain.audios.get(AudioLibrary.DIE).play();
			} else if (orient == 1) {
				tick = System.currentTimeMillis();
				checkFallRight = true;
				GameMain.audios.get(AudioLibrary.DIE).play();
			}
		}
		health -= damage;
		if (health <= 0) {
			health = 0;
		}
	}

	public void getItem(Item item) {
		health += item.getHp();
		mana += item.getMp();
		if (health > healthMax) {
			health = healthMax;
		}
		if (mana > manaMax) {
			mana = manaMax;
		}
		if (item.getId() == 3) {
			onPower = true;
			attack += 100;
			defense += 100;
		}
	}

	public void checkDamage(int dama) {
		if (defLeft || defRight) {
			dama = 0;
		}
		if (orient == 0) {
			tick = System.currentTimeMillis();
			checkFall = true;
			orient = -1;
			GameMain.audios.get(AudioLibrary.DIE).play();
		} else if (orient == 1) {
			tick = System.currentTimeMillis();
			checkFallRight = true;
			orient = -1;
			GameMain.audios.get(AudioLibrary.DIE).play();
		}
		health -= dama;
		if (health <= 0) {
			health = 0;
		}
	}

	public boolean checkItem(ArrayList<Item> items) {
		for (Item item : items) {
			if (item.alive) {
				float x = item.positionX;
				float y = item.positionY;
				if (x >= positionX - width / 2 && x <= positionX + width / 2
						&& y >= positionY - height / 2
						&& y <= positionY + height / 2) {
					item.alive = false;
					getItem(item);
					return true;
				}
			}
		}
		return false;
	}

	protected boolean collision(Actor actor) {
		if (actor.alive) {
			float x = actor.positionX;
			float y = actor.positionY;
			if ((x >= positionX - width / 2 - 50
					&& x <= positionX + width / 2 + 50
					&& y >= positionY - height / 2 - 50
					&& y <= positionY + height / 2 + 50 && temp)
					|| (x >= positionX - width / 2 - 50
							&& x <= positionX + width / 2 + 50
							&& y >= positionY - height / 2 - 50
							&& y <= positionY + height / 2 + 50 && temp1)) {
				attack(actor);
				return true;
			}
		}
		return false;
	}

	public boolean collisionSkillMonster(ArrayList<SkillMonster> skills) {
		for (SkillMonster skill : skills) {
			if (skill.alive && alive) {
				float x = skill.positionX;
				float y = skill.positionY;
				if (x >= positionX - width / 2 - 50
						&& x <= positionX + width / 2 + 50
						&& y >= positionY - height / 2 - 50
						&& y <= positionY + height / 2 + 50) {
					skill.alive = false;
					return true;
				}
			}
		}
		return false;
	}

	public Rectangle getBounds() {
		return new Rectangle();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			checkPower = 1;
			orient = 1;
			checkRight = false;
			checkLeft = false;
			speedX = -SPEED;
			break;
		case KeyEvent.VK_RIGHT:
			checkPower = 1;
			orient = 0;
			checkRight = false;
			checkLeft = false;
			speedX = SPEED;
			break;
		case KeyEvent.VK_UP:
			checkPower = 1;
			orient = 1;
			checkRight = false;
			checkLeft = false;
			speedY = -SPEED;
			break;
		case KeyEvent.VK_DOWN:
			checkPower = 1;
			orient = 0;
			checkRight = false;
			checkLeft = false;
			speedY = SPEED;
			break;
		case KeyEvent.VK_A:
			checkPower = 1;
			atLeft = true;
			temp = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_S:
			checkPower = 1;
			atRight = true;
			temp1 = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_X:
			checkPower = 1;
			skiRight = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_Z:
			checkPower = 1;
			skiLeft = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_D:
			checkPower = 1;
			defLeft = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_F:
			checkPower = 1;
			defRight = true;
			checkRight = false;
			checkLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_R:
			checkPower = 2;
			runRight = true;
			checkRight = false;
			checkLeft = false;
			orient = -1;
			speedX = SPEED * 3.5f;
			break;
		case KeyEvent.VK_E:
			checkPower = 3;
			runLeft = true;
			checkLeft = false;
			orient = -1;
			speedX = -SPEED * 3.5f;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			checkPower = 0;
			checkLeft = true;
			checkRight = false;
			orient = -1;
			speedX = 0;
			break;
		case KeyEvent.VK_RIGHT:
			checkPower = 0;
			checkLeft = false;
			checkRight = true;
			orient = -1;
			speedX = 0;
			break;
		case KeyEvent.VK_UP:
			checkPower = 0;
			checkRight = true;
			checkLeft = false;
			orient = -1;
			speedY = 0;
			break;
		case KeyEvent.VK_DOWN:
			checkPower = 0;
			checkLeft = true;
			checkRight = false;
			orient = -1;
			speedY = 0;
			break;
		case KeyEvent.VK_A:
			checkPower = 0;
			checkLeft = true;
			checkRight = false;
			orient = -1;
			atLeft = false;
			temp = false;
			speedX = 0;
			break;
		case KeyEvent.VK_S:
			checkPower = 0;
			temp1 = false;
			atRight = false;
			checkLeft = false;
			checkRight = true;
			orient = -1;
			speedX = 0;
			break;
		case KeyEvent.VK_X:
			checkPower = 0;
			checkLeft = false;
			checkRight = true;
			orient = -1;
			skiRight = false;
			speedX = 0;
			break;
		case KeyEvent.VK_Z:
			checkPower = 0;
			checkLeft = true;
			checkRight = false;
			orient = -1;
			skiLeft = false;
			speedX = 0;
			break;
		case KeyEvent.VK_D:
			checkPower = 0;
			checkLeft = true;
			checkRight = false;
			defLeft = false;
			orient = -1;
			speedX = 0;
			break;
		case KeyEvent.VK_F:
			checkPower = 0;
			checkRight = true;
			checkLeft = false;
			defRight = false;
			orient = -1;
			speedX = 0;
			break;
		case KeyEvent.VK_R:
			checkPower = 0;
			runRight = false;
			checkRight = true;
			checkLeft = false;
			orient = 0;
			speedX = 0;
			break;
		case KeyEvent.VK_E:
			checkPower = 0;
			checkLeft = true;
			runLeft = false;
			checkRight = false;
			orient = 1;
			speedX = 0;
			break;
		default:
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public long getEXP() {
		return mEXP;
	}

	public void setEXP(long mEXP) {
		this.mEXP = mEXP;
	}

	public long getMaxEXP() {
		return maxEXP;
	}

	public void setMaxEXP(long maxEXP) {
		this.maxEXP = maxEXP;
	}

	public int getManaMax() {
		return manaMax;
	}

	public void setManaMax(int manaMax) {
		this.manaMax = manaMax;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
