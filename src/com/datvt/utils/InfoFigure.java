package com.datvt.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.datvt.entities.InterfaceGame;
import com.datvt.images.ImageLibrary;
import com.datvt.main.GameMain;

public class InfoFigure extends JFrame {

	private static final long serialVersionUID = 1L;

	public InfoFigure(int hp, int mp, int attack, int defense, long exp,
			int level) {
		setTitle("Info Figure");
		setIconImage(GameMain.images.get(ImageLibrary.ICON));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		init(hp, mp, attack, defense, exp, level);
		setLocationRelativeTo(null);
	}

	private void init(int hp, int mp, int attack, int defense, long exp,
			int level) {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(350, 400));
		this.setContentPane(panel);
		pack();
		panel.setLayout(new GridLayout(2, 1));

		JPanel image = new JPanel(new GridLayout(1, 2));
		image.setBackground(new Color(255, 255, 102));
		panel.add(image);
		JLabel label = new JLabel();
		label.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.FIGURE)));
		image.add(label);
		JLabel label1 = new JLabel("Roronoa Zoro");
		label1.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.FLAG)));
		label1.setFont(new Font(InterfaceGame.FONT_NAME, Font.ITALIC
				+ Font.BOLD, 16));
		label1.setForeground(Color.RED);
		image.add(label1);

		JPanel info = new JPanel(new GridLayout(3, 2));
		info.setBackground(new Color(255, 255, 102));
		panel.add(info);
		JLabel HP = new JLabel("HP : " + hp);
		HP.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.HP)));
		HP.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		HP.setForeground(Color.BLUE);
		info.add(HP);
		JLabel MP = new JLabel("MP : " + mp);
		MP.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.MP)));
		MP.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		MP.setForeground(Color.BLUE);
		info.add(MP);
		JLabel Attack = new JLabel("Attack : " + attack);
		Attack.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.ATTACK)));
		Attack.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		Attack.setForeground(Color.BLUE);
		info.add(Attack);
		JLabel Defense = new JLabel("Defense : " + defense);
		Defense.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.DEFENSE)));
		Defense.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		Defense.setForeground(Color.BLUE);
		info.add(Defense);
		JLabel Exp = new JLabel("EXP : " + exp);
		Exp.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.EXP)));
		Exp.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		Exp.setForeground(Color.BLUE);
		info.add(Exp);
		JLabel Level = new JLabel("Level : " + level);
		Level.setIcon(new ImageIcon(GameMain.images.get(ImageLibrary.LEVEL)));
		Level.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 14));
		Level.setForeground(Color.BLUE);
		info.add(Level);
	}
}
