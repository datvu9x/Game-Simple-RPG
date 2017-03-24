package com.datvt.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;

import com.datvt.audios.AudioLibrary;
import com.datvt.images.ImageLibrary;
import com.datvt.main.About;
import com.datvt.main.GameMain;
import com.datvt.main.Help;
import com.datvt.utils.Activities;
import com.datvt.utils.Animation;
import com.datvt.utils.Assets;
import com.datvt.utils.InfoFigure;
import com.datvt.utils.Map;
import com.datvt.utils.SaveGame;

public class InterfaceGame implements Activities, KeyListener {

	public static final int DURATION_ADD_DAMAGE = 1000;
	public static final int DURATION_MAP_UP = 2000;
	public static final int DURATION_LEVEL_UP = 3000;
	public static final float PERCENT = 5f;
	public static final float SPEED = 2f;
	public static final String FONT_NAME = "Cloudy";

	private Map map, map1, map2;
	private Figure figure;
	private Monster spider, spider1;
	private MonsterKuma kuma, kuma1;
	private MonsterSky bird, bird1;
	private MonsterWater dragonWater;
	private MonsterDragon dragonLand, dragonLand1;
	private MonsterIdle giant, giant1;
	private ArrayList<SkillFigure> skillFigure;
	private ArrayList<SkillMonster> skillMonster;
	private ArrayList<Explosion> explosionList;
	private ArrayList<Effect> effectList;
	private ArrayList<Item> mItem;
	private InfoFigure infoFigure;
	private SaveGame saveGame;
	private Animation animiGate;

	private Random random;
	private String[] options = { "Start", "Help", "About", "Quit" };
	private String[] over = { "Replay", "Quit" };
	private Image iMap, iTree, iTree1, iTree2, iTree3, iTree4, iTree5, iHouse,
			iHouse1, iHouse2, iWater, info, iGate, startGame, gameOver,
			optionMenu, iLevelUp;
	private int[][] maps, maps1, maps2;
	private int colMap, rowMap;
	private int currentSelection = 0, select = 0, temp, currentHP, previousHP,
			hp, mp, count, count1, count2;
	private float positionX, positionY;
	private long tick, EXP;
	private boolean mPlaying, mGameOver, damage, checkMap, checkLevel, checkUp,
			checkEnd;
	private boolean check, check1, check2, check3, check4, check5, check6,
			check7, check8, check9;
	public static boolean checkMap0, checkMap1, checkMap2;
	private boolean checkItem, checkItem1, checkItem2, checkItem3, checkItem4,
			checkItem5, checkItem6, checkItem7, checkItem8, checkItem9;

	public InterfaceGame() {
		init();
	}

	public void init() {
		Assets.init();
		animiGate = new Animation(150, Assets.win);
		//
		map = new Map("res/map.txt");
		map1 = new Map("res/map1.txt");
		map2 = new Map("res/map2.txt");
		maps = map.getMaps();
		maps1 = map1.getMaps();
		maps2 = map2.getMaps();
		colMap = map.getCol();
		rowMap = map.getRow();
		//
		startGame = GameMain.images.get(ImageLibrary.START_GAME);
		gameOver = GameMain.images.get(ImageLibrary.GAME_OVER);
		optionMenu = GameMain.images.get(ImageLibrary.OPTION_MENU);
		iMap = GameMain.images.get(ImageLibrary.MAP + 0);
		iHouse = GameMain.images.get(ImageLibrary.HOUSE + 0);
		iHouse1 = GameMain.images.get(ImageLibrary.HOUSE + 1);
		iHouse2 = GameMain.images.get(ImageLibrary.HOUSE + 2);
		iTree = GameMain.images.get(ImageLibrary.TREE + 0);
		iTree1 = GameMain.images.get(ImageLibrary.TREE + 1);
		iTree2 = GameMain.images.get(ImageLibrary.TREE + 2);
		iTree3 = GameMain.images.get(ImageLibrary.TREE + 5);
		iTree4 = GameMain.images.get(ImageLibrary.TREE + 6);
		iTree5 = GameMain.images.get(ImageLibrary.TREE + 4);
		iWater = GameMain.images.get(ImageLibrary.WATER);
		info = GameMain.images.get(ImageLibrary.INFO_FIGURE);
		iLevelUp = GameMain.images.get(ImageLibrary.LEVEL_UP);
		iGate = animiGate.getCurrentFrame();
		//
		figure = new Figure(500, 250, 200, 300);
		spider = new Monster(1000, 250, 1, true, 50, 100, 200);
		spider1 = new Monster(500, 540, 0, true, 50, 100, 200);
		kuma = new MonsterKuma(500, 90, 1, true, 350, 600, 1000);
		kuma1 = new MonsterKuma(400, 350, 0, true, 350, 600, 1000);
		dragonWater = new MonsterWater(200, 600, 1, true, 100, 150, 300);
		dragonLand = new MonsterDragon(200, 620, 1, true, 200, 150, 300);
		dragonLand1 = new MonsterDragon(900, 390, 0, true, 200, 150, 300);
		giant = new MonsterIdle(800, 200, 0, true, 300, 200, 500);
		giant1 = new MonsterIdle(100, 300, 1, true, 300, 200, 500);
		bird = new MonsterSky(200, 710, 2, true, 10, 200, 100);
		bird1 = new MonsterSky(800, -10, 3, true, 10, 200, 100);
		skillFigure = new ArrayList<SkillFigure>();
		skillMonster = new ArrayList<SkillMonster>();
		explosionList = new ArrayList<Explosion>();
		effectList = new ArrayList<Effect>();
		mItem = new ArrayList<Item>();
		saveGame = new SaveGame();
		// Load Game
		String[] info = saveGame.loadGame();
		if (info != null) {
			if (Integer.parseInt(info[0]) > 0) {
				figure.health = Integer.parseInt(info[0]);
			}
			figure.setMana(Integer.parseInt(info[1]));
			figure.healthMax = Integer.parseInt(info[2]);
			figure.setManaMax(Integer.parseInt(info[3]));
			figure.attack = Integer.parseInt(info[4]);
			figure.defense = Integer.parseInt(info[5]);
			figure.setEXP(Long.valueOf(info[6]));
			figure.setLevel(Integer.parseInt(info[7]));
		}
		//
		random = new Random();
		mPlaying = mGameOver = damage = checkMap = checkLevel = checkUp = checkEnd = false;
		// Check Collision Figure
		check = check1 = check3 = check2 = check4 = check5 = check6 = check7 = check8 = check9 = false;
		checkMap0 = true;
		checkMap1 = checkMap2 = false;
		temp = 0; // Check Map Next
		// Check Item
		checkItem = checkItem1 = checkItem2 = checkItem3 = checkItem4 = checkItem5 = checkItem6 = checkItem7 = checkItem8 = checkItem9 = true;
		EXP = 0;
		hp = mp = count = count1 = count2 = 0;
		// GameMain.audios.get(AudioLibrary.TITLE).play();
	}

	// Update State Game
	public void update() {
		if (mPlaying && !mGameOver) {
			animiGate.tick();
			iGate = animiGate.getCurrentFrame();
			count++;
			count1++;
			count2++;
			save();
			updateFigure();
			updateMonster();
			updateItem();
			updateMap();
			updateLevel();
			updateHPMP();
			updateDamage();
			updateExplosion();
			updateEffect();
		}
	}

	// Update Figure
	private void updateFigure() {
		if (figure.alive) {
			figure.move();
			if (figure.collisionSkillMonster(skillMonster)) {
				int damage = random.nextInt(50);
				figure.takeDamage(damage);
				explosionList.add(new Explosion(figure.positionX - 25,
						figure.positionY - 10));
			}
			// Check Skill Figure
			if (check) {
				check = false;
				if (figure.getMana() > 0) {
					GameMain.audios.get(AudioLibrary.SKILL).play();
					skillFigure.add(new SkillFigure(figure.positionX + 80,
							figure.positionY - 10, 0));
				}
				figure.setMana(figure.getMana() - 10);
				if (figure.getMana() <= 0) {
					figure.setMana(0);
				}
			}
			if (check1) {
				check1 = false;
				if (figure.getMana() > 0) {
					GameMain.audios.get(AudioLibrary.SKILL).play();
					skillFigure.add(new SkillFigure(figure.positionX - 120,
							figure.positionY - 10, 1));
				}
				figure.setMana(figure.getMana() - 10);
				if (figure.getMana() <= 0) {
					figure.setMana(0);
				}
			}
			// Up Level, EXP
			if (EXP > figure.getEXP()) {
				figure.setEXP(EXP);
			}
			if (figure.getEXP() >= figure.getMaxEXP()) {
				checkLevel = true;
				EXP = 0;
				GameMain.audios.get(AudioLibrary.UP_LEVEL).play();
				figure.setEXP(figure.getEXP() - figure.getMaxEXP());
				figure.setMaxEXP(figure.getMaxEXP() + 50000);
				figure.setLevel(figure.getLevel() + 1);
				figure.attack += 50;
				figure.defense += 50;
				figure.healthMax += 100;
				figure.setManaMax(figure.getManaMax() + 100);
				figure.health = figure.healthMax;
				figure.setMana(figure.getManaMax());
			}
			// Skill
			for (int index = 0; index < skillFigure.size(); index++) {
				SkillFigure skill = skillFigure.get(index);
				if (skill.collisionSkillMonster(skill, skillMonster)) {
					GameMain.audios.get(AudioLibrary.EXPLOSION).play();
					skill.alive = false;
					explosionList.add(new Explosion(skill.positionX,
							skill.positionY));
				}
				skill.move();
				if (!skill.alive) {
					skillFigure.remove(index);
				}
			}
		} else {
			if (figure.orient == 0) {
				figure.image = GameMain.images.get(ImageLibrary.DIE + 0);
			} else {
				figure.image = GameMain.images.get(ImageLibrary.DIE + 1);
			}
			checkEnd = true;
		}
	}

	// Update Monster
	private void updateMonster() {
		updateSpider();
		updateKuma();
		updateDragon();
		updateBird();
		updateGiant();
		updateMonsterWater();
		// Update Skill Monster
		for (int index = 0; index < skillMonster.size(); index++) {
			SkillMonster skill = skillMonster.get(index);
			skill.move();
			if (!skill.alive) {
				skillMonster.remove(index);
			}
		}
	}

	// Spider
	private void updateSpider() {
		// Monster Spider
		if (spider.alive) {
			if (spider.positionX <= 20) {
				spider.orient = 1;
			} else if (spider.positionX >= GameMain.WIDTH - 10) {
				spider.orient = 0;
			}
			//
			if (spider.collision(dragonLand) || spider.collision(giant)
					|| spider.collision(kuma) || spider.collision(dragonLand1)
					|| spider.collision(giant1) || spider.collision(kuma1)) {
				if (spider.orient == 0) {
					spider.orient = 1;
				} else if (spider.orient == 1) {
					spider.orient = 0;
				}
			}
			//
			if ((spider.positionX < figure.positionX - figure.width / 2)
					|| (spider.positionX > figure.positionX + figure.width / 2)) {
				if (spider.orient == 5) {
					spider.orient = 1;
				}
				if (spider.orient == 6) {
					spider.orient = 0;
				}
			}
			//
			if (spider.collisionSkillFigure(skillFigure) || check2) {
				effectList.add(new Effect(spider.positionX - 30,
						spider.positionY, 1));
				check2 = false;
				damage = true;
				previousHP = spider.health;
				figure.attack(spider);
				currentHP = spider.health;
				tick = System.currentTimeMillis();
				positionX = spider.positionX;
				positionY = spider.positionY;
			}
			//
			if (spider.collision(figure)) {
				if (spider.orient == 1) {
					spider.orient = 5;
				}
				if (spider.orient == 0) {
					spider.orient = 6;
				}
				spider.attack(figure);
			}
			//
			spider.move();
			//
			if (spider.health <= 0)
				spider.alive = false;
		} else {
			effectList.add(new Effect(spider.positionX, spider.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			spider = new Monster(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 50 + 50 * figure.getLevel(),
					100 + 50 * figure.getLevel(), 200 + 50 * figure.getLevel());
			EXP += (Math.abs(figure.attack - spider1.defense) / 4) * 10;
		}
		// Monster Spider1
		if (spider1.alive) {
			if (spider1.positionX <= 20) {
				spider1.orient = 1;
			} else if (spider1.positionX >= GameMain.WIDTH - 10) {
				spider1.orient = 0;
			}
			//
			if (spider1.collision(dragonLand) || spider1.collision(giant)
					|| spider1.collision(kuma)
					|| spider1.collision(dragonLand1)
					|| spider1.collision(giant1) || spider1.collision(kuma1)) {
				if (spider1.orient == 0) {
					spider1.orient = 1;
				} else if (spider1.orient == 1) {
					spider1.orient = 0;
				}
			}
			//
			if ((spider1.positionX < figure.positionX - figure.width / 2)
					|| (spider1.positionX > figure.positionX + figure.width / 2)) {
				if (spider1.orient == 5) {
					spider1.orient = 1;
				}
				if (spider1.orient == 6) {
					spider1.orient = 0;
				}
			}
			//
			if (spider1.collisionSkillFigure(skillFigure) || check8) {
				effectList.add(new Effect(spider1.positionX - 30,
						spider1.positionY, 1));
				check8 = false;
				damage = true;
				previousHP = spider1.health;
				figure.attack(spider1);
				currentHP = spider1.health;
				tick = System.currentTimeMillis();
				positionX = spider1.positionX;
				positionY = spider1.positionY;
			}
			//
			if (spider1.collision(figure)) {
				if (spider1.orient == 1) {
					spider1.orient = 5;
				}
				if (spider1.orient == 0) {
					spider1.orient = 6;
				}
				spider1.attack(figure);
			}
			spider1.move();
			//
			if (spider1.health <= 0)
				spider1.alive = false;
		} else {
			effectList.add(new Effect(spider1.positionX, spider1.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			spider1 = new Monster(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 50 + 50 * figure.getLevel(),
					100 + 50 * figure.getLevel(), 200 + 50 * figure.getLevel());
			EXP += (Math.abs(figure.attack - spider1.defense) / 4) * 10;
		}
	}

	// Kuma
	private void updateKuma() {
		// Monster Kuma
		if (kuma.alive) {
			if (kuma.positionX <= 20) {
				kuma.orient = 1;
			} else if (kuma.positionX >= GameMain.WIDTH - 10) {
				kuma.orient = 0;
			}
			//
			if (kuma.collision(dragonLand) || kuma.collision(giant)
					|| kuma.collision(spider) || kuma.collision(dragonLand1)
					|| kuma.collision(giant1) || kuma.collision(spider1)) {
				if (kuma.orient == 0) {
					kuma.orient = 1;
				} else if (kuma.orient == 1) {
					kuma.orient = 0;
				}
			}
			//
			if ((kuma.positionX < figure.positionX - figure.width / 2 - 100)
					|| (kuma.positionX > figure.positionX + figure.width / 2
							+ 100)) {
				if (kuma.orient == 5) {
					kuma.orient = 1;
				}
				if (kuma.orient == 6) {
					kuma.orient = 0;
				}
			}
			//
			if (kuma.collisionSkillFigure(skillFigure) || check6) {
				effectList.add(new Effect(kuma.positionX - 30, kuma.positionY,
						1));
				check6 = false;
				damage = true;
				previousHP = kuma.health;
				figure.attack(kuma);
				currentHP = kuma.health;
				tick = System.currentTimeMillis();
				positionX = kuma.positionX;
				positionY = kuma.positionY;
			}
			//
			if (kuma.collision(figure)) {
				if (kuma.orient == 1 || figure.orient == 1) {
					kuma.orient = 5;
				}
				if (kuma.orient == 0 || figure.orient == 0) {
					kuma.orient = 6;
				}
				int dama = (kuma.attack - figure.defense) / 4;
				if (dama <= 0)
					dama = 1;
				figure.checkDamage(dama);
			}
			kuma.move();
			//
			if (kuma.health <= 0)
				kuma.alive = false;
		} else {
			effectList.add(new Effect(kuma.positionX, kuma.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			kuma = new MonsterKuma(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 350 + figure.getLevel() * 50,
					600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - kuma.defense) / 2) * 20;
		}
		// Monster Kuma1
		if (kuma1.alive) {
			if (kuma1.positionX <= 20) {
				kuma1.orient = 1;
			} else if (kuma1.positionX >= GameMain.WIDTH - 10) {
				kuma1.orient = 0;
			}
			//
			if (kuma1.collision(dragonLand) || kuma1.collision(giant)
					|| kuma1.collision(spider) || kuma1.collision(dragonLand1)
					|| kuma1.collision(giant1) || kuma1.collision(spider1)) {
				if (kuma1.orient == 0) {
					kuma1.orient = 1;
				} else if (kuma1.orient == 1) {
					kuma1.orient = 0;
				}
			}
			//
			if ((kuma1.positionX < figure.positionX - figure.width / 2 - 100)
					|| (kuma1.positionX > figure.positionX + figure.width / 2
							+ 100)) {
				if (kuma1.orient == 5) {
					kuma1.orient = 1;
				}
				if (kuma1.orient == 6) {
					kuma1.orient = 0;
				}
			}
			//
			if (kuma1.collisionSkillFigure(skillFigure) || check9) {
				effectList.add(new Effect(kuma1.positionX - 30,
						kuma1.positionY, 1));
				check9 = false;
				damage = true;
				previousHP = kuma1.health;
				figure.attack(kuma1);
				currentHP = kuma1.health;
				tick = System.currentTimeMillis();
				positionX = kuma1.positionX;
				positionY = kuma1.positionY;
			}
			//
			if (kuma1.collision(figure)) {
				if (kuma1.orient == 1 || figure.orient == 1) {
					kuma1.orient = 5;
				}
				if (kuma1.orient == 0 || figure.orient == 0) {
					kuma1.orient = 6;
				}
				int dama = (kuma1.attack - figure.defense) / 4;
				if (dama <= 0)
					dama = 1;
				figure.checkDamage(dama);
			}
			kuma1.move();
			//
			if (kuma1.health <= 0)
				kuma1.alive = false;
		} else {
			effectList.add(new Effect(kuma1.positionX, kuma1.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			kuma1 = new MonsterKuma(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 350 + figure.getLevel() * 50,
					600 + figure.getLevel() * 50, 700 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - kuma1.defense) / 2) * 20;
		}
	}

	// Dragon
	private void updateDragon() {
		// Monster Dragon Land
		if (dragonLand.alive) {
			// Check Collision Edge
			if (dragonLand.positionX <= 20) {
				dragonLand.orient = 1;
			} else if (dragonLand.positionX >= GameMain.WIDTH) {
				dragonLand.orient = 0;
			} else if (dragonLand.positionY <= 20) {
				dragonLand.orient = 3;
			} else if (dragonLand.positionY >= GameMain.HEIGHT) {
				dragonLand.orient = 2;
			}
			//
			if (dragonLand.collision(kuma) || dragonLand.collision(giant)
					|| dragonLand.collision(spider)
					|| dragonLand.collision(kuma1)
					|| dragonLand.collision(giant1)
					|| dragonLand.collision(spider1)) {
				if (dragonLand.orient == 0) {
					dragonLand.orient = 1;
				} else if (dragonLand.orient == 1) {
					dragonLand.orient = 0;
				}
			}
			//
			if (count >= 400) {
				count = 0;
				dragonLand.orient = random.nextInt(4);
			}
			//
			if ((dragonLand.positionX < figure.positionX - figure.width / 2)
					|| (dragonLand.positionX > figure.positionX + figure.width
							/ 2)) {
				if (dragonLand.orient == 5) {
					dragonLand.orient = 0;
				}
				if (dragonLand.orient == 6) {
					dragonLand.orient = 1;
				}
			}
			// Check Shoot Skill
			if (dragonLand.isThrow()) {
				dragonLand.setThrow(false);
				int percent = random.nextInt(10);
				if (percent <= PERCENT) {
					if (dragonLand.orient == 0) {
						skillMonster.add(new SkillMonster(
								dragonLand.positionX - 90,
								dragonLand.positionY - 30, 1, 1));
					} else if (dragonLand.orient == 1) {
						skillMonster.add(new SkillMonster(
								dragonLand.positionX + 20,
								dragonLand.positionY - 35, 0, 1));
					} else if (dragonLand.orient == 2) {
						skillMonster.add(new SkillMonster(
								dragonLand.positionX - 30,
								dragonLand.positionY - 95, 3, 1));
					} else {
						skillMonster.add(new SkillMonster(
								dragonLand.positionX - 30,
								dragonLand.positionY, 4, 1));
					}
				}
			}
			// Check Collision Skill Figure
			if (dragonLand.collisionSkillFigure(skillFigure) || check3) {
				effectList.add(new Effect(dragonLand.positionX - 30,
						dragonLand.positionY, 1));
				check3 = false;
				damage = true;
				previousHP = dragonLand.health;
				figure.attack(dragonLand);
				currentHP = dragonLand.health;
				tick = System.currentTimeMillis();
				positionX = dragonLand.positionX;
				positionY = dragonLand.positionY;
			}

			if (dragonLand.collision(figure)) {
				if (dragonLand.orient == 1) {
					dragonLand.orient = 5;
				}
				if (dragonLand.orient == 0) {
					dragonLand.orient = 6;
				}
			}
			dragonLand.move();
			//
			if (dragonLand.health <= 0)
				dragonLand.alive = false;
		} else {
			effectList.add(new Effect(dragonLand.positionX,
					dragonLand.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			dragonLand = new MonsterDragon(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 200 + figure.getLevel() * 50,
					150 + figure.getLevel() * 50, 300 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - dragonLand.defense) / 2) * 10;
		}
		// Monster Dragon Land1
		if (dragonLand1.alive) {
			// Check Collision Edge
			if (dragonLand1.positionX <= 20) {
				dragonLand1.orient = 1;
			} else if (dragonLand1.positionX >= GameMain.WIDTH) {
				dragonLand1.orient = 0;
			} else if (dragonLand1.positionY <= 20) {
				dragonLand1.orient = 3;
			} else if (dragonLand1.positionY >= GameMain.HEIGHT) {
				dragonLand1.orient = 2;
			}
			//
			if (dragonLand1.collision(kuma) || dragonLand1.collision(giant)
					|| dragonLand1.collision(spider)
					|| dragonLand1.collision(kuma1)
					|| dragonLand1.collision(giant1)
					|| dragonLand1.collision(spider1)) {
				if (dragonLand1.orient == 0) {
					dragonLand1.orient = 1;
				} else if (dragonLand1.orient == 1) {
					dragonLand1.orient = 0;
				}
			}
			//
			if (count >= 400) {
				count = 0;
				dragonLand1.orient = random.nextInt(4);
			}
			//
			if ((dragonLand1.positionX < figure.positionX - figure.width / 2)
					|| (dragonLand1.positionX > figure.positionX + figure.width
							/ 2)) {
				if (dragonLand1.orient == 5) {
					dragonLand1.orient = 0;
				}
				if (dragonLand1.orient == 6) {
					dragonLand1.orient = 1;
				}
			}
			// Check Shoot Skill
			if (dragonLand1.isThrow()) {
				dragonLand1.setThrow(false);
				int percent = random.nextInt(10);
				if (percent <= PERCENT) {
					if (dragonLand1.orient == 0) {
						skillMonster.add(new SkillMonster(
								dragonLand1.positionX - 90,
								dragonLand1.positionY - 30, 1, 1));
					} else if (dragonLand1.orient == 1) {
						skillMonster.add(new SkillMonster(
								dragonLand1.positionX + 20,
								dragonLand1.positionY - 35, 0, 1));
					} else if (dragonLand1.orient == 2) {
						skillMonster.add(new SkillMonster(
								dragonLand1.positionX - 30,
								dragonLand1.positionY - 95, 3, 1));
					} else {
						skillMonster.add(new SkillMonster(
								dragonLand1.positionX - 30,
								dragonLand1.positionY, 4, 1));
					}
				}
			}
			// Check Collision Skill Figure
			if (dragonLand1.collisionSkillFigure(skillFigure) || check7) {
				effectList.add(new Effect(dragonLand1.positionX - 30,
						dragonLand1.positionY, 1));
				check7 = false;
				damage = true;
				previousHP = dragonLand1.health;
				figure.attack(dragonLand1);
				currentHP = dragonLand1.health;
				tick = System.currentTimeMillis();
				positionX = dragonLand1.positionX;
				positionY = dragonLand1.positionY;
			}

			if (dragonLand1.collision(figure)) {
				if (dragonLand1.orient == 1) {
					dragonLand1.orient = 5;
				}
				if (dragonLand1.orient == 0) {
					dragonLand1.orient = 6;
				}
			}
			dragonLand1.move();
			//
			if (dragonLand1.health <= 0)
				dragonLand1.alive = false;
		} else {
			effectList.add(new Effect(dragonLand1.positionX,
					dragonLand1.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			dragonLand1 = new MonsterDragon(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT - 50), random.nextInt(2),
					true, 200 + figure.getLevel() * 50,
					150 + figure.getLevel() * 50, 300 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - dragonLand1.defense) / 2) * 10;
		}
	}

	// Bird
	private void updateBird() {
		// Monster Sky
		if (bird.alive) {
			if (bird.positionX <= 20) {
				bird.orient = 1;
			} else if (bird.positionX >= GameMain.WIDTH) {
				bird.orient = 0;
			} else if (bird.positionY <= 20) {
				bird.orient = 3;
			} else if (bird.positionY >= GameMain.HEIGHT) {
				bird.orient = 2;
			}
			if (count1 >= 500) {
				count1 = 0;
				bird.orient = random.nextInt(4);
			}
			//
			if (bird.isThrow()) {
				bird.setThrow(false);
				int percent = random.nextInt(15);
				if (percent <= PERCENT) {
					skillMonster.add(new SkillMonster(bird.positionX - 10,
							bird.positionY + 10, 2, 0));
				}
			}
			if (bird.collisionSkillFigure(skillFigure)) {
				effectList.add(new Effect(bird.positionX - 30, bird.positionY,
						1));
				damage = true;
				previousHP = bird.health;
				figure.attack(bird);
				currentHP = bird.health;
				tick = System.currentTimeMillis();
				positionX = bird.positionX;
				positionY = bird.positionY;
			}
			bird.move();
			//
			if (bird.health <= 0)
				bird.alive = false;
		} else {
			effectList.add(new Effect(bird.positionX, bird.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			bird = new MonsterSky(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT), random.nextInt(4), true,
					10 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
					100 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - bird.defense) / 2) * 5;
		}
		//
		if (bird1.alive) {
			if (bird1.positionX <= 20) {
				bird1.orient = 1;
			} else if (bird1.positionX >= GameMain.WIDTH) {
				bird1.orient = 0;
			} else if (bird1.positionY <= 20) {
				bird1.orient = 3;
			} else if (bird1.positionY >= GameMain.HEIGHT) {
				bird1.orient = 2;
			}
			//
			if (count2 >= 600) {
				count2 = 0;
				bird1.orient = random.nextInt(4);
			}
			//
			if (bird1.isThrow()) {
				bird1.setThrow(false);
				int percent = random.nextInt(15);
				if (percent <= PERCENT) {
					skillMonster.add(new SkillMonster(bird1.positionX - 10,
							bird1.positionY + 10, 2, 0));
				}
			}
			if (bird1.collisionSkillFigure(skillFigure)) {
				effectList.add(new Effect(bird1.positionX - 30,
						bird1.positionY, 1));
				damage = true;
				previousHP = bird1.health;
				figure.attack(bird1);
				currentHP = bird1.health;
				tick = System.currentTimeMillis();
				positionX = bird1.positionX;
				positionY = bird1.positionY;
			}
			bird1.move();
			//
			if (bird1.health <= 0)
				bird1.alive = false;
		} else {
			effectList.add(new Effect(bird1.positionX, bird1.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			bird1 = new MonsterSky(random.nextInt(GameMain.WIDTH),
					random.nextInt(GameMain.HEIGHT), random.nextInt(4), true,
					10 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
					100 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - bird1.defense) / 2) * 5;
		}
	}

	// Giant
	private void updateGiant() {
		// Monster Giant
		if (giant.alive) {
			if ((giant.positionX < figure.positionX - figure.width / 2)
					|| (giant.positionX > figure.positionX + figure.width / 2)) {
				if (giant.orient == 5) {
					giant.orient = 0;
				}
			}
			//
			if (giant.collisionSkillFigure(skillFigure) || check4) {
				effectList.add(new Effect(giant.positionX - 30,
						giant.positionY, 1));
				check4 = false;
				damage = true;
				previousHP = giant.health;
				figure.attack(giant);
				currentHP = giant.health;
				tick = System.currentTimeMillis();
				positionX = giant.positionX;
				positionY = giant.positionY;
			}
			//
			if (giant.collision(figure)) {
				giant.orient = 5;
				giant.attack(figure);
			}
			giant.move();
			//
			if (giant.health <= 0)
				giant.alive = false;
		} else {
			effectList.add(new Effect(giant.positionX, giant.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			giant = new MonsterIdle(random.nextInt(GameMain.WIDTH) / 2 + 500,
					random.nextInt(GameMain.HEIGHT - 50), 0, true,
					300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
					500 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - giant.defense) / 2) * 5;
		}
		// Monster Giant1
		if (giant1.alive) {
			if ((giant1.positionX < figure.positionX - figure.width / 2)
					|| (giant1.positionX > figure.positionX + figure.width / 2)) {
				if (giant1.orient == 6) {
					giant1.orient = 1;
				}
			}
			//
			if (giant1.collisionSkillFigure(skillFigure) || check5) {
				effectList.add(new Effect(giant1.positionX - 30,
						giant1.positionY, 1));
				check5 = false;
				damage = true;
				previousHP = giant1.health;
				figure.attack(giant1);
				currentHP = giant1.health;
				tick = System.currentTimeMillis();
				positionX = giant1.positionX;
				positionY = giant1.positionY;
			}
			//
			if (giant1.collision(figure)) {
				giant1.orient = 6;
				giant1.attack(figure);
			}
			giant1.move();
			//
			if (giant1.health <= 0)
				giant1.alive = false;
		} else {
			effectList.add(new Effect(giant1.positionX, giant1.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			giant1 = new MonsterIdle(random.nextInt(GameMain.WIDTH) / 3 + 200,
					random.nextInt(GameMain.HEIGHT - 50), 1, true,
					300 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
					500 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - giant1.defense) / 2) * 5;
		}
	}

	// Monster Water
	private void updateMonsterWater() {
		// Monster Water
		if (dragonWater.alive) {
			if (dragonWater.positionX <= 160) {
				dragonWater.orient = 1;
			}
			if (dragonWater.positionX > 650) {
				dragonWater.orient = 0;
			}
			//
			if (dragonWater.collisionSkillFigure(skillFigure)) {
				effectList.add(new Effect(dragonWater.positionX - 30,
						dragonWater.positionY, 1));
				damage = true;
				previousHP = dragonWater.health;
				figure.attack(dragonWater);
				currentHP = dragonWater.health;
				tick = System.currentTimeMillis();
				positionX = dragonWater.positionX;
				positionY = dragonWater.positionY;
			}
			dragonWater.move();
			//
			if (dragonWater.health <= 0)
				dragonWater.alive = false;
		} else {
			effectList.add(new Effect(dragonWater.positionX,
					dragonWater.positionY, 0));
			GameMain.audios.get(AudioLibrary.EXPLOSION).play();
			dragonWater = new MonsterWater(200, 550, random.nextInt(2), true,
					10 + figure.getLevel() * 50, 200 + figure.getLevel() * 50,
					100 + figure.getLevel() * 50);
			EXP += (Math.abs(figure.attack - dragonWater.defense) / 2) * 5;
		}
	}

	// Update Item
	private void updateItem() {
		if (!spider.alive && checkItem) {
			mItem.add(new Item(spider.positionX, spider.positionY, 0, 50, 0));
			checkItem = false;
		}
		if (!spider1.alive && checkItem8) {
			mItem.add(new Item(spider1.positionX, spider1.positionY, 0, 50, 0));
			checkItem8 = false;
		}
		if (!bird.alive && checkItem1) {
			mItem.add(new Item(bird.positionX, bird.positionY, 3, 100, 100));
			checkItem1 = false;
		}
		if (!bird1.alive && checkItem2) {
			mItem.add(new Item(bird1.positionX, bird1.positionY, 1, 0, 50));
			checkItem2 = false;
		}
		if (!dragonLand.alive && checkItem3) {
			mItem.add(new Item(dragonLand.positionX, dragonLand.positionY, 2,
					70, 50));
			checkItem3 = false;
		}
		if (!dragonLand1.alive && checkItem7) {
			mItem.add(new Item(dragonLand1.positionX, dragonLand1.positionY, 2,
					70, 50));
			checkItem7 = false;
		}
		if (!giant.alive && checkItem4) {
			mItem.add(new Item(giant.positionX, giant.positionY, 1, 0, 150));
			checkItem4 = false;
		}
		if (!giant1.alive && checkItem5) {
			mItem.add(new Item(giant1.positionX, giant1.positionY, 2, 100, 150));
			checkItem5 = false;
		}
		if (!kuma.alive && checkItem6) {
			mItem.add(new Item(kuma.positionX, kuma.positionY, 2, 150, 150));
			checkItem6 = false;
		}
		if (!kuma1.alive && checkItem9) {
			mItem.add(new Item(kuma1.positionX, kuma1.positionY, 2, 150, 150));
			checkItem9 = false;
		}
		if (figure.checkItem(mItem)) {
			checkUp = true;
			tick = System.currentTimeMillis();
		}
		for (int i = 0; i < mItem.size(); i++) {
			Item item = mItem.get(i);
			if (!item.alive) {
				hp = item.getHp();
				mp = item.getMp();
				GameMain.audios.get(AudioLibrary.ITEM).play();
				mItem.remove(i);
			}
		}

	}

	// Update Explosion
	private void updateExplosion() {
		for (int index = 0; index < explosionList.size(); index++) {
			Explosion explosion = explosionList.get(index);
			explosion.move();
			if (!explosion.alive) {
				explosionList.remove(index);
			}
		}
	}

	// Update Effect
	private void updateEffect() {
		for (int index = 0; index < effectList.size(); index++) {
			Effect effect = effectList.get(index);
			effect.move();
			if (!effect.alive) {
				effectList.remove(index);
			}
		}
	}

	// Update Damage Of Monster
	private void updateDamage() {
		if (positionY >= 0) {
			positionY -= SPEED;
		}
		if (damage && System.currentTimeMillis() - DURATION_ADD_DAMAGE >= tick) {
			damage = false;
		}
	}

	// Update Map Next
	private void updateMap() {
		if (checkMap && System.currentTimeMillis() - DURATION_MAP_UP >= tick) {
			checkMap = false;
		}
	}

	// Update Level
	private void updateLevel() {
		if (checkLevel
				&& System.currentTimeMillis() - DURATION_LEVEL_UP >= tick) {
			checkLevel = false;
		}
	}

	// Update HP, MP
	private void updateHPMP() {
		if (checkUp && System.currentTimeMillis() - DURATION_MAP_UP >= tick) {
			checkUp = false;
		}
	}

	// Draw
	public void draw(Graphics2D g2) {
		if (!mPlaying && !mGameOver) {
			g2.drawImage(startGame, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
			g2.drawImage(optionMenu, 250, 0, null);
			for (int i = 0; i < options.length; i++) {
				if (i == currentSelection) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.RED);
				}
				g2.setFont(new Font(FONT_NAME, Font.PLAIN, 60));
				g2.drawString(options[i], GameMain.WIDTH / 2 - 70,
						165 + i * 110);
			}
		}
		//
		if (mPlaying && !mGameOver) {
			// Check Map
			if ((figure.positionX >= 990 && figure.positionY <= 50 && temp == 0)) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 2);
				figure.positionX = 990;
				figure.positionY = 70;
				temp = 1;
				checkMap2 = true;
				checkMap0 = false;
				checkMap1 = false;
				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).loop();
			}
			if ((temp == 1 && figure.positionX >= 990 && figure.positionY <= 50)) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 0);
				figure.positionX = 970;
				figure.positionY = 70;
				temp = 0;
				checkMap2 = false;
				checkMap0 = true;
				checkMap1 = false;
				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 2).loop();
			}
			if (figure.positionX >= 990 && figure.positionY >= 650 && temp == 1) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 1);
				figure.positionX = 950;
				figure.positionY = 670;
				temp = 2;
				checkMap2 = false;
				checkMap0 = false;
				checkMap1 = true;
				GameMain.audios.get(AudioLibrary.MAP + 1).stop();
				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).loop();
			}
			if (temp == 2 && figure.positionX >= 990 && figure.positionY >= 650) {
				checkMap = true;
				tick = System.currentTimeMillis();
				iMap = GameMain.images.get(ImageLibrary.MAP + 2);
				figure.positionX = 960;
				figure.positionY = 650;
				temp = 1;
				checkMap2 = true;
				checkMap0 = false;
				checkMap1 = false;
				GameMain.audios.get(AudioLibrary.MAP + 2).stop();
				GameMain.audios.get(AudioLibrary.MAP + 0).stop();
				GameMain.audios.get(AudioLibrary.MAP + 1).loop();
			}
			if (temp == 2 && figure.positionX >= 320 && figure.positionY <= 50
					&& figure.positionX <= 390) {
				mGameOver = true;
				mPlaying = false;
			}
			// Draw Map
			g2.drawImage(iMap, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
			//
			int w1 = Math.round(GameMain.WIDTH / colMap);
			int h1 = Math.round(GameMain.HEIGHT / rowMap);
			// Draw Map
			for (int i = 0; i < rowMap; i++) {
				for (int j = 0; j < colMap; j++) {
					// Map 0
					if (temp == 0) {
						if (maps[i][j] == 2) {
							g2.drawImage(iHouse, j * w1 - 20, i * h1 - 130,
									w1 + 150, h1 + 80, null);
						}
						if (maps[i][j] == 3) {
							g2.drawImage(iHouse1, j * w1 - 140, i * h1 - 150,
									w1 + 180, h1 + 90, null);
						}
						if (maps[i][j] == 4) {
							g2.drawImage(iHouse2, j * w1 - 130, i * h1 - 70,
									w1 + 150, h1 + 90, null);
						}
					}
					// Map 2
					if (temp == 2) {
						if (maps2[i][j] == 2) {
							g2.drawImage(iTree3, j * w1, i * h1, null);
						}
					}
				}
			}
			// Draw Item
			for (Item item : mItem) {
				item.draw(g2);
			}
			// Draw Figure
			figure.draw(g2);
			// Draw Dragon
			if (dragonLand.alive) {
				dragonLand.draw(g2);
			}
			if (dragonLand1.alive) {
				dragonLand1.draw(g2);
			}
			// Draw Spider
			if (spider.alive) {
				spider.draw(g2);
			}
			if (spider1.alive) {
				spider1.draw(g2);
			}
			// Draw Kuma
			if (kuma.alive) {
				kuma.draw(g2);
			}
			if (kuma1.alive) {
				kuma1.draw(g2);
			}
			// Draw Giant
			if (giant.alive) {
				giant.draw(g2);
			}
			if (giant1.alive) {
				giant1.draw(g2);
			}
			//
			for (int i = 0; i < rowMap; i++) {
				for (int j = 0; j < colMap; j++) {
					// Map 0
					if (temp == 0) {
						if (maps[i][j] == 1) {
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
						if (maps[i][j] == 5) {
							g2.drawImage(iTree, j * w1, i * h1, null);
						}
						if (maps[i][j] == 6) {
							g2.drawImage(iWater, j * w1, i * h1, null);
						}
					} else if (temp == 1) {
						// Map 1
						if (maps1[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
						if (maps1[i][j] == 2) {
							g2.drawImage(iTree1, j * w1, i * h1, null);
						}
						if (maps1[i][j] == 3) {
							g2.drawImage(iTree2, j * w1, i * h1, null);
						}
						if (maps1[i][j] == 4) {
							g2.drawImage(iTree3, j * w1, i * h1, null);
						}

						dragonWater.draw(g2);
					} else {
						// Map 2
						if (maps2[i][j] == 1) {
							g2.setColor(Color.BLUE);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("Cổng", j * w1 + 10, i * h1 + 30);
						}
						if (maps2[i][j] == 3) {
							g2.drawImage(iTree4, j * w1, i * h1, null);
						}
						if (maps2[i][j] == 4) {
							g2.drawImage(iTree5, j * w1, i * h1, null);
						}
						if (maps2[i][j] == 5) {
							g2.setColor(Color.RED);
							g2.setFont(new Font("Consolas", Font.BOLD, 15));
							g2.drawImage(iGate, j * w1, i * h1, w1, h1, null);
							g2.drawString("END", j * w1 + 10, i * h1 + 30);
						}
					}
				}
			}
			// Draw Bird
			if (bird.alive) {
				bird.draw(g2);
			}
			if (bird1.alive) {
				bird1.draw(g2);
			}
			// Draw Explosion
			for (Explosion explosion : explosionList) {
				explosion.draw(g2);
			}
			// Draw Effect
			for (Effect effect : effectList) {
				effect.draw(g2);
			}
			// Draw Skill
			for (SkillFigure skill : skillFigure) {
				skill.draw(g2);
			}
			for (SkillMonster skill : skillMonster) {
				skill.draw(g2);
			}
			// Draw Damage
			if (damage) {
				g2.setColor(Color.RED);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 20));
				g2.drawString((currentHP - previousHP) + "", (int) positionX,
						(int) positionY);
			}
			// Draw Next Map
			if (checkMap) {
				g2.setColor(Color.BLUE);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
				g2.drawString("NEXT MAP", GameMain.WIDTH / 3 + 50,
						GameMain.HEIGHT / 2);
			}
			// Draw Level
			if (checkLevel) {
				GameMain.audios.get(AudioLibrary.UP_LEVEL).play();
				g2.drawImage(iLevelUp, GameMain.WIDTH / 3 - 100,
						GameMain.HEIGHT / 3 - 100, null);
			}
			// Draw Up HP, MP
			if (checkUp) {
				g2.setColor(Color.RED);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 19));
				if (hp > 0) {
					g2.drawString("+" + hp + " HP", GameMain.WIDTH / 3 + 80,
							GameMain.HEIGHT / 2 + 20);
				}
				if (mp > 0) {
					g2.setColor(Color.BLUE);
					g2.drawString("+" + mp + " MP", GameMain.WIDTH / 3 + 180,
							GameMain.HEIGHT / 2 + 20);
				}
			}
			// Draw Info
			g2.drawImage(info, 0, 0, null);
			g2.setColor(Color.RED);
			g2.fillRect(65, 5,
					(int) (181 * (figure.health * 1.0 / figure.healthMax)), 11);
			g2.setColor(Color.YELLOW);
			g2.fillRect(
					65,
					17,
					(int) (129 * (figure.getMana() * 1.0 / figure.getManaMax())),
					8);
			g2.setColor(Color.BLUE);
			g2.fillRect(65, 26,
					(int) (119 * (figure.getEXP() * 1.0 / figure.getMaxEXP())),
					8);
			g2.setColor(Color.WHITE);
			g2.setFont(new Font("Consolas", Font.BOLD, 11));
			g2.drawString(figure.health + "/" + figure.healthMax, 142, 13);
			g2.setFont(new Font("Consolas", Font.BOLD, 10));
			g2.setColor(Color.GREEN);
			g2.drawString(figure.getMana() + "/" + figure.getManaMax(), 114, 24);
			g2.setColor(Color.YELLOW);
			g2.setFont(new Font("Consolas", Font.BOLD, 9));
			g2.drawString(figure.getEXP() + "/" + figure.getMaxEXP() + "EXP",
					90, 33);
			g2.setColor(Color.RED);
			g2.setFont(new Font(FONT_NAME, Font.BOLD, 19));
			g2.drawString(figure.getLevel() + "", 15, 30);
			//
			if (checkEnd) {
				checkEnd = false;
				GameMain.audios.stopAll();
				g2.setColor(Color.BLUE);
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 60));
				g2.drawString("YOU DIE ! ^^", 300, 250);
				for (int i = 0; i < over.length; i++) {
					if (i == select) {
						g2.setColor(Color.WHITE);
					} else {
						g2.setColor(Color.BLACK);
					}
					g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
					g2.drawString(over[i], 430 + i * 20, 360 + i * 90);
				}
			}
		}

		if (mGameOver) {
			GameMain.audios.stopAll();
			g2.drawImage(gameOver, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null);
			g2.setColor(Color.ORANGE);
			g2.setFont(new Font(FONT_NAME, Font.BOLD, 60));
			g2.drawString("WINER ! ^^", 300, 250);
			for (int i = 0; i < over.length; i++) {
				if (i == select) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.BLACK);
				}
				g2.setFont(new Font(FONT_NAME, Font.BOLD, 40));
				g2.drawString(over[i], 430 + i * 20, 360 + i * 90);
			}
		}
	}

	public void save() {
		String[] inf = new String[8];
		inf[0] = String.valueOf(figure.health);
		inf[1] = String.valueOf(figure.getMana());
		inf[2] = String.valueOf(figure.healthMax);
		inf[3] = String.valueOf(figure.getManaMax());
		inf[4] = String.valueOf(figure.attack);
		inf[5] = String.valueOf(figure.defense);
		inf[6] = String.valueOf(figure.getEXP());
		inf[7] = String.valueOf(figure.getLevel());
		saveGame.saveGame(inf);
	}

	public void keyPressed(KeyEvent e) {
		figure.keyPressed(e);
		switch (e.getKeyCode()) {
		case KeyEvent.VK_DOWN:
			if (!mPlaying) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				currentSelection++;
				if (currentSelection >= options.length) {
					currentSelection = 0;
				}
			}
			if ((mGameOver && !mPlaying) || checkEnd) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				select++;
				if (select >= over.length) {
					select = 0;
				}
			}
			break;
		case KeyEvent.VK_UP:
			if (!mPlaying && !mGameOver) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				currentSelection--;
				if (currentSelection < 0) {
					currentSelection = options.length - 1;
				}
			}
			if ((mGameOver && !mPlaying) || checkEnd) {
				GameMain.audios.get(AudioLibrary.MENU).play();
				select--;
				if (select < 0) {
					select = over.length - 1;
				}
			}
			break;
		case KeyEvent.VK_ENTER:
			if (!mPlaying && !mGameOver) {
				if (currentSelection == 0) {
					GameMain.audios.get(AudioLibrary.MAP + 2).loop();
					mPlaying = !mPlaying;
				} else if (currentSelection == 1) {
					new Help();
				} else if (currentSelection == 2) {
					new About();
				} else if (currentSelection == 3) {
					JOptionPane.showMessageDialog(null, "GoodBye ! ^^");
					System.exit(0);
				}
			}
			if ((mGameOver && !mPlaying) || (checkEnd && !mGameOver)) {
				if (select == 0) {
					init();
					GameMain.audios.get(AudioLibrary.TITLE).stop();
					GameMain.audios.get(AudioLibrary.MAP + 2).loop();
					mPlaying = true;
				} else if (select == 1) {
					JOptionPane.showMessageDialog(null, "GoodBye ! ^^");
					System.exit(0);
				}
			}
			break;
		case KeyEvent.VK_Z:
			if (mPlaying) {
				check1 = true;
			}
			break;
		case KeyEvent.VK_X:
			if (mPlaying) {
				check = true;
			}
			break;
		case KeyEvent.VK_A:
			if (mPlaying) {
				GameMain.audios.get(AudioLibrary.ATTACK).play();
				if (figure.collision(spider)) {
					check2 = true;
				}
				if (figure.collision(dragonLand)) {
					check3 = true;
				}
				if (figure.collision(giant)) {
					check4 = true;
				}
				if (figure.collision(giant1)) {
					check5 = true;
				}
				if (figure.collision(kuma)) {
					check6 = true;
				}
				if (figure.collision(dragonLand1)) {
					check7 = true;
				}
				if (figure.collision(spider1)) {
					check8 = true;
				}
				if (figure.collision(kuma1)) {
					check9 = true;
				}
			}
			break;
		case KeyEvent.VK_S:
			if (mPlaying) {
				GameMain.audios.get(AudioLibrary.ATTACK).play();
				if (figure.collision(spider)) {
					check2 = true;
				}
				if (figure.collision(dragonLand)) {
					check3 = true;
				}
				if (figure.collision(giant)) {
					check4 = true;
				}
				if (figure.collision(giant1)) {
					check5 = true;
				}
				if (figure.collision(kuma)) {
					check6 = true;
				}
				if (figure.collision(dragonLand1)) {
					check7 = true;
				}
				if (figure.collision(spider1)) {
					check8 = true;
				}
				if (figure.collision(kuma1)) {
					check9 = true;
				}
			}
			break;
		case KeyEvent.VK_I:
			infoFigure = new InfoFigure(figure.health, figure.getMana(),
					figure.attack, figure.defense, figure.getEXP(),
					figure.getLevel());
			infoFigure.setVisible(true);
			break;
		case KeyEvent.VK_P:
			int choice = JOptionPane.showConfirmDialog(null, "Are you sure ?",
					"QUIT", JOptionPane.YES_NO_OPTION);
			if (choice == JOptionPane.YES_OPTION) {
				save();
				JOptionPane
						.showMessageDialog(null, "Save success ! Bye Bye ^^");
				System.exit(0);
			} else {
				init();
				mPlaying = true;
				GameMain.audios.get(AudioLibrary.MAP + 2).loop();
			}
			break;
		default:
			break;
		}
	}

	public void keyReleased(KeyEvent e) {
		figure.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		figure.keyTyped(e);
	}
}
