package com.datvt.utils;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Assets {

	public static ImageLoader loader;
	public static SpriteSheet ssRight, ssLeft, ssAttackRight;
	public static BufferedImage[] sMoveLeft, sMoveRight, sAttackNormalLeft,
			sAttackSkillRight, sAttackSkillLeft, sPowerHigh, sPowerLow,
			sPowerRight, sPowerLeft, sAttackNormalRight, sIdleRight, sIdleLeft,
			msLeft, msRight, msAttackRight, msAttackLeft, dLeft, dRight, dUp,
			dDown, gLeft, gRight, gAttackLeft, gAttackRight, kLeft, kRight,
			kAttackLeft, kAttackRight, bLeft, bRight, bUp, bDown, skill,
			defRight, defLeft, runRight, runLeft, sFireRight, sFireLeft,
			sFireUp, sFireDown, win, fall, fallRight, buffImage, ball,
			explosion, effect, collision;
	public static ArrayList<BufferedImage> sFall, sFallRight;

	public static void init() {
		loader = new ImageLoader();
		//
		collision = new BufferedImage[3];
		for (int i = 0; i < collision.length; i++) {
			collision[i] = loader.loadImage("spriteSheet/effect" + (i+5) + ".png");
		}
		//
		effect = new BufferedImage[5];
		for (int i = 0; i < effect.length; i++) {
			effect[i] = loader.loadImage("spriteSheet/effect" + i + ".png");
		}
		//
		explosion = new BufferedImage[6];
		for (int i = 0; i < explosion.length; i++) {
			explosion[i] = loader.loadImage("spriteSheet/explosion" + i
					+ ".png");
		}
		//
		sFireRight = new BufferedImage[6];
		for (int i = 0; i < sFireRight.length; i++) {
			sFireRight[i] = loader.loadImage("spriteSheet/fire" + i + ".png");
		}
		//
		sFireLeft = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			sFireLeft[i] = loader.loadImage("spriteSheet/fire" + (i + 6)
					+ ".png");
		}
		//
		sFireUp = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			sFireUp[i] = loader.loadImage("spriteSheet/fire" + (i + 12)
					+ ".png");
		}
		//
		sFireDown = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			sFireDown[i] = loader.loadImage("spriteSheet/fire" + (i + 18)
					+ ".png");
		}
		//
		buffImage = new BufferedImage[3];
		buffImage[0] = loader.loadImage("spriteSheet/walkRight.png");
		buffImage[1] = loader.loadImage("spriteSheet/walkLeft.png");
		buffImage[2] = loader.loadImage("spriteSheet/attackSkill.png");
		/* Animation Attack Skill Left */
		sAttackSkillLeft = new BufferedImage[7];
		for (int i = 0; i < 7; i++) {
			sAttackSkillLeft[i] = loader.loadImage("spriteSheet/skill_left_"
					+ i + ".png");
		}
		/* Animation Power High */
		sPowerHigh = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			sPowerHigh[i] = loader.loadImage("spriteSheet/power" + i + ".png");
		}
		/* Animation Power Low */
		sPowerLow = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			sPowerLow[i] = loader.loadImage("spriteSheet/power" + (i + 3)
					+ ".png");
		}
		/* Animation Power Run Right */
		sPowerRight = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			sPowerRight[i] = loader.loadImage("spriteSheet/power" + (i + 6)
					+ ".png");
		}
		/* Animation Power Run Left */
		sPowerLeft = new BufferedImage[3];
		for (int i = 0; i < 3; i++) {
			sPowerLeft[i] = loader.loadImage("spriteSheet/power" + (i + 9)
					+ ".png");
		}
		/* Animation Attack Normal Right */
		sAttackNormalRight = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			sAttackNormalRight[i] = loader.loadImage("spriteSheet/attack_right"
					+ i + ".png");
		}
		/* Animation Attack Normal Left */
		sAttackNormalLeft = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			sAttackNormalLeft[i] = loader.loadImage("spriteSheet/attack_left"
					+ i + ".png");
		}
		/* Animation Idle Right */
		sIdleRight = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			sIdleRight[i] = loader.loadImage("spriteSheet/stand" + i + ".png");
		}
		/* Animation Idle Left */
		sIdleLeft = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			sIdleLeft[i] = loader.loadImage("spriteSheet/stand" + (i + 4)
					+ ".png");
		}
		// Monster
		// Animation Move Left
		msLeft = new BufferedImage[5];
		for (int i = 0; i < 5; i++) {
			msLeft[i] = loader.loadImage("spriteSheet/mons_left" + i + ".png");
		}
		// Animation Move Right
		msRight = new BufferedImage[5];
		for (int i = 0; i < 5; i++) {
			msRight[i] = loader
					.loadImage("spriteSheet/mons_right" + i + ".png");
		}
		// Animation Attack Right
		msAttackRight = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			msAttackRight[i] = loader.loadImage("spriteSheet/mons_attack_right"
					+ i + ".png");
		}
		// Animation Attack Left
		msAttackLeft = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			msAttackLeft[i] = loader.loadImage("spriteSheet/mons_attack_left"
					+ i + ".png");
		}
		// DRAGON
		// Animation Move Left
		dLeft = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			dLeft[i] = loader.loadImage("spriteSheet/left" + i + ".png");
		}
		// Animation Move Right
		dRight = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			dRight[i] = loader.loadImage("spriteSheet/right" + i + ".png");
		}
		// Animation Move Down
		dDown = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			dDown[i] = loader.loadImage("spriteSheet/mDown" + i + ".png");
		}
		// Animation Move Up
		dUp = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			dUp[i] = loader.loadImage("spriteSheet/mUp" + i + ".png");
		}
		// Giant
		// Animation Idle Left
		gLeft = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			gLeft[i] = loader.loadImage("spriteSheet/idle" + (i + 6) + ".png");
		}
		// Animation Idle Right
		gRight = new BufferedImage[6];
		for (int i = 0; i < 6; i++) {
			gRight[i] = loader.loadImage("spriteSheet/idle" + i + ".png");
		}
		// Animation Attack Left
		gAttackLeft = new BufferedImage[11];
		for (int i = 0; i < 11; i++) {
			gAttackLeft[i] = loader.loadImage("spriteSheet/idleAttack" + i
					+ ".png");
		}
		// Animation Attack Right
		gAttackRight = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			gAttackRight[i] = loader.loadImage("spriteSheet/monAttack" + i
					+ ".png");
		}
		// Kuma
		// Animation Move Right
		kRight = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			kRight[i] = loader.loadImage("spriteSheet/kuma" + i + ".png");
		}
		// Animation Move Left
		kLeft = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			kLeft[i] = loader.loadImage("spriteSheet/kuma" + (i + 8) + ".png");
		}
		// Animation Attack Right
		kAttackRight = new BufferedImage[17];
		for (int i = 0; i < 17; i++) {
			kAttackRight[i] = loader.loadImage("spriteSheet/kuma" + (i + 16)
					+ ".png");
		}
		// Animation Attack Left
		kAttackLeft = new BufferedImage[17];
		for (int i = 0; i < 17; i++) {
			kAttackLeft[i] = loader.loadImage("spriteSheet/k" + i + ".png");
		}
		// Bird
		// Animation Move Up
		bUp = new BufferedImage[3];
		bUp[0] = loader.loadImage("spriteSheet/up0.png");
		bUp[1] = loader.loadImage("spriteSheet/up1.png");
		bUp[2] = loader.loadImage("spriteSheet/up2.png");
		// Animation Move Down
		bDown = new BufferedImage[3];
		bDown[0] = loader.loadImage("spriteSheet/down0.png");
		bDown[1] = loader.loadImage("spriteSheet/down1.png");
		bDown[2] = loader.loadImage("spriteSheet/down2.png");
		// Animation Move Left
		bLeft = new BufferedImage[3];
		bLeft[0] = loader.loadImage("spriteSheet/mons0.png");
		bLeft[1] = loader.loadImage("spriteSheet/mons1.png");
		bLeft[2] = loader.loadImage("spriteSheet/mons2.png");
		// Animation Move Right
		bRight = new BufferedImage[3];
		bRight[0] = loader.loadImage("spriteSheet/mons5.png");
		bRight[1] = loader.loadImage("spriteSheet/mons4.png");
		bRight[2] = loader.loadImage("spriteSheet/mons3.png");
		/* Animation Move Left */
		ssLeft = new SpriteSheet(buffImage[1]);
		sMoveLeft = new BufferedImage[8];
		sMoveLeft[0] = ssLeft.crop(48, 0, 52, 75);
		sMoveLeft[1] = ssLeft.crop(106, 0, 54, 75);
		sMoveLeft[2] = ssLeft.crop(164, 0, 57, 75);
		sMoveLeft[3] = ssLeft.crop(224, 0, 56, 75);
		sMoveLeft[4] = ssLeft.crop(284, 0, 58, 75);
		sMoveLeft[5] = ssLeft.crop(345, 0, 60, 75);
		sMoveLeft[6] = ssLeft.crop(410, 0, 62, 75);
		sMoveLeft[7] = ssLeft.crop(474, 0, 58, 75);
		/* Animation Move Right */
		ssRight = new SpriteSheet(buffImage[0]);
		sMoveRight = new BufferedImage[8];
		sMoveRight[0] = ssRight.crop(50, 0, 52, 77);
		sMoveRight[1] = ssRight.crop(106, 0, 55, 77);
		sMoveRight[2] = ssRight.crop(165, 0, 55, 77);
		sMoveRight[3] = ssRight.crop(227, 0, 55, 77);
		sMoveRight[4] = ssRight.crop(287, 0, 55, 77);
		sMoveRight[5] = ssRight.crop(348, 0, 60, 77);
		sMoveRight[6] = ssRight.crop(410, 0, 62, 77);
		sMoveRight[7] = ssRight.crop(479, 0, 54, 77);
		/* Animation Attack Skill Right */
		ssAttackRight = new SpriteSheet(buffImage[2]);
		sAttackSkillRight = new BufferedImage[8];
		sAttackSkillRight[0] = ssAttackRight.crop(0, 12, 52, 76);
		sAttackSkillRight[1] = ssAttackRight.crop(104, 12, 52, 76);
		sAttackSkillRight[2] = ssAttackRight.crop(160, 12, 57, 76);
		sAttackSkillRight[3] = ssAttackRight.crop(215, 12, 75, 76);
		sAttackSkillRight[4] = ssAttackRight.crop(290, 12, 76, 76);
		sAttackSkillRight[5] = ssAttackRight.crop(365, 12, 75, 76);
		sAttackSkillRight[6] = ssAttackRight.crop(438, 12, 76, 76);
		sAttackSkillRight[7] = ssAttackRight.crop(514, 12, 57, 76);
		//
		fall = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			fall[i] = loader.loadImage("spriteSheet/" + i + ".png");
		}
		fallRight = new BufferedImage[10];
		for (int i = 0; i < 10; i++) {
			fallRight[i] = loader.loadImage("spriteSheet/r" + i + ".png");
		}
		/* Animation Fall Left */
		sFall = new ArrayList<BufferedImage>();
		for (int i = 0; i < 10; i++) {
			sFall.add(fall[i]);
		}
		/* Animation Fall Right */
		sFallRight = new ArrayList<BufferedImage>();
		for (int i = 0; i < 10; i++) {
			sFallRight.add(fallRight[i]);
		}
		// Skill
		skill = new BufferedImage[4];
		for (int i = 0; i < 4; i++) {
			skill[i] = loader.loadImage("spriteSheet/skill" + i + ".png");
		}
		//
		ball = new BufferedImage[5];
		for (int i = 0; i < 5; i++) {
			ball[i] = loader.loadImage("spriteSheet/ball" + i + ".png");
		}
		// Defense
		defLeft = new BufferedImage[1];
		defLeft[0] = loader.loadImage("spriteSheet/defense0.png");
		defRight = new BufferedImage[1];
		defRight[0] = loader.loadImage("spriteSheet/defense1.png");
		// Run
		runLeft = new BufferedImage[1];
		runLeft[0] = loader.loadImage("spriteSheet/run1.png");
		runRight = new BufferedImage[1];
		runRight[0] = loader.loadImage("spriteSheet/run0.png");
		// Windown
		win = new BufferedImage[7];
		for (int i = 0; i < 7; i++) {
			win[i] = loader.loadImage("spriteSheet/port" + i + ".png");
		}
	}
}
