package com.datvt.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class SaveGame {
	
	public void saveGame(String[] info) {
		File file = new File("./src/res/savegame.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < info.length; i++) {
				bw.write(info[i] + " ");
			}
			bw.close();
			fw.close();
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
		}
	}

	public String[] loadGame() {
		String[] arr = null;
		File file = new File("./src/res/savegame.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String line = "";
			while ((line = br.readLine()) != null) {
				arr = line.split(" ");
			}
			br.close();
			fr.close();
			return arr;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			return null;
		}
	}
}
