package com.datvt.images;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;

public class ImageLibrary {
	private static ImageLibrary mInstance;
	
	public static final String PNG = ".png";
	public static final String GIF = ".gif";
	public static final String ICON = "icon";
	public static final String TREE = "tree";
	public static final String START_GAME = "start";
	public static final String GAME_OVER = "gameOver";
	public static final String OPTION_MENU = "option";
	public static final String HOUSE = "house";
	public static final String MAP = "map";
	public static final String WATER = "water";
	public static final String INFO_FIGURE = "info";
	public static final String ITEM = "item";
	public static final String MONSTER_WATER = "dragonWater";
	public static final String LEVEL_UP = "levelup";
	public static final String ATTACK = "attack";
	public static final String DEFENSE = "defense";
	public static final String LEVEL = "level";
	public static final String EXP = "exp";
	public static final String HP = "health";
	public static final String FLAG = "flag";
	public static final String MP = "mp";
	public static final String DIE = "die";
	public static final String FIGURE = "Zoro";

	private HashMap<String, Image> mImageMap;
	private Toolkit mToolkit;

	private ImageLibrary() {
		mImageMap = new HashMap<>();
		mToolkit = Toolkit.getDefaultToolkit();
	}

	public static ImageLibrary getInstance() {
		if (mInstance == null) {
			mInstance = new ImageLibrary();
		}
		return mInstance;
	}

	public void loadAllImage() {
		put(START_GAME, PNG);
		put(GAME_OVER, PNG);
		put(OPTION_MENU, PNG);
		put(ICON, PNG);
		put(WATER, PNG);
		put(INFO_FIGURE, PNG);
		put(LEVEL_UP, GIF);
		put(ATTACK, PNG);
		put(DEFENSE, PNG);
		put(HP, PNG);
		put(MP, PNG);
		put(EXP, PNG);
		put(LEVEL, PNG);
		put(FIGURE, PNG);
		put(FLAG, PNG);
		
		for (int index = 0; index < 2; index++) {
			put(DIE + index, PNG);
		}


		for (int index = 0; index < 4; index++) {
			put(ITEM + index, PNG);
		}

		for (int index = 0; index < 3; index++) {
			put(MAP + index, PNG);
		}

		for (int index = 0; index < 3; index++) {
			put(HOUSE + index, PNG);
		}

		for (int index = 0; index < 7; index++) {
			put(TREE + index, PNG);
		}

		for (int index = 0; index < 2; index++) {
			put(MONSTER_WATER + index, PNG);
		}
	}

	public Image get(String name) {
		return mImageMap.get(name);
	}

	public void put(String name, String end) {
		mImageMap.put(name, loadImage(name, end));
	}

	private Image loadImage(String name, String end) {
		String path = "com/datvt/images/" + name + end;
		Image image = mToolkit.getImage(ImageLibrary.class.getClassLoader()
				.getResource(path));
		return image;
	}
}
