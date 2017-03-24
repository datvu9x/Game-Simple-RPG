package com.datvt.utils;

import java.io.File;
import java.util.Scanner;

public class Map {

	private int[][] maps;
	private int row, col;

	public Map(String path) {
		loadMap(path);
	}

	public void loadMap(String path) {
		try {
			Scanner input = new Scanner(new File("./src/" + path));
			col = input.nextInt();
			row = input.nextInt();
			maps = new int[row][col];

			while (input.hasNext()) {
				for (int i = 0; i < row; i++) {
					for (int j = 0; j < col; j++) {
						maps[i][j] = input.nextInt();
					}
				}
			}

			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int[][] getMaps() {
		return maps;
	}

	public void setMaps(int[][] maps) {
		this.maps = maps;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
}
