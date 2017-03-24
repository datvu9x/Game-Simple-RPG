package com.datvt.audios;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;

public class AudioLibrary {

	private static AudioLibrary mInstance;
	private HashMap<String, AudioClip> mAudioMap;

	public static final String MAP = "map";
	public static final String TITLE = "title";
	public static final String MENU = "menu";
	public static final String ATTACK = "attack";
	public static final String SKILL = "skill";
	public static final String DIE = "die";
	public static final String EXPLOSION = "explosion";
	public static final String ITEM = "item";
	public static final String UP_LEVEL = "up";
	public static final String GAME_OVER = "gameover";

	public static final int NUMBER_MAP = 3;

	private AudioLibrary() {
		mAudioMap = new HashMap<String, AudioClip>();
	}

	public static AudioLibrary getInstance() {
		if (mInstance == null) {
			mInstance = new AudioLibrary();
		}
		return mInstance;
	}

	public void loadAllAudio() {
		put(TITLE);
		put(ITEM);
		put(EXPLOSION);
		put(ATTACK);
		put(SKILL);
		put(MENU);
		put(DIE);
		put(UP_LEVEL);
		put(GAME_OVER);

		for (int index = 0; index < NUMBER_MAP; index++) {
			put(MAP + index);
		}
	}
	
	public void stopAll() {
		for (int index = 0; index < NUMBER_MAP; index++) {
			get(MAP + index).stop();;
		}
	}
	
	public void put(String name) {
		AudioClip audioClip = Applet.newAudioClip(AudioLibrary.class
				.getResource(name + ".wav"));
		mAudioMap.put(name, audioClip);
	}

	public AudioClip get(String name) {
		return mAudioMap.get(name);
	}
}
