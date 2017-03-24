package com.datvt.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.datvt.entities.InterfaceGame;
import com.datvt.images.ImageLibrary;

public class About extends JFrame {

	private static final long serialVersionUID = 1L;

	public About() {
		init();
		setTitle("Info");
		setIconImage(GameMain.images.get(ImageLibrary.ICON));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void init() {
		JPanel jPanel1 = new JPanel(new BorderLayout());
		jPanel1.setPreferredSize(new Dimension(700, 300));
		jPanel1.setBackground(new Color(255, 255, 102));
		add(jPanel1);

		JPanel title = new JPanel();
		title.setBackground(new Color(255, 255, 102));
		JLabel lable = new JLabel("Product Information", JLabel.CENTER);
		lable.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 26));
		lable.setForeground(Color.BLUE);
		title.add(lable, JPanel.CENTER_ALIGNMENT);
		jPanel1.add(title, BorderLayout.NORTH);

		JPanel body = new JPanel(new GridLayout(9, 2, 5, 5));
		body.setBackground(new Color(255, 255, 102));
		JLabel jLabel1 = new JLabel();
		jLabel1.setFont(new Font("MV Boli", Font.BOLD, 18));
		jLabel1.setText("Software Name: ");
		JLabel jLabel2 = new JLabel();
		jLabel2.setFont(new Font("MV Boli", Font.BOLD, 18));
		jLabel2.setText("Author: ");
		JLabel jLabel3 = new JLabel();
		jLabel3.setFont(new Font("MV Boli", Font.BOLD, 18));
		jLabel3.setText("Finish Day: ");
		JLabel jLabel4 = new JLabel();
		jLabel4.setFont(new Font("MV Boli", Font.BOLD, 18));
		jLabel4.setText("Version: ");
		JLabel jLabel5 = new JLabel();
		jLabel5.setFont(new Font("MV Boli", Font.BOLD, 18));
		jLabel5.setText("CopyRight: ");
		JLabel jLabel6 = new JLabel();
		jLabel6.setFont(new Font("MV Boli", Font.PLAIN, 18));
		jLabel6.setText("Game Simple RPG- Zoro Adventure");
		JLabel jLabel8 = new JLabel();
		jLabel8.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabel8.setText("Vũ Thế Đạt - 20130889");
		JLabel jLabel9 = new JLabel();
		jLabel9.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabel9.setText("Nguyễn Minh Đức - 20131022");
		JLabel jLabe20 = new JLabel();
		jLabe20.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabe20.setText("Đào Quang Duy - 20130592");
		JLabel jLabe21 = new JLabel();
		jLabe21.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabe21.setText("Vũ Quang Hải - 20131279");
		JLabel jLabe23 = new JLabel();
		jLabe23.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabe23.setText("Nguyễn Thúc Huynh - 20131840");
		JLabel jLabel22 = new JLabel();
		jLabel22.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabel22.setText("01-12-2015");
		JLabel jLabel12 = new JLabel();
		jLabel12.setFont(new Font("Consolas", Font.PLAIN, 18));
		jLabel12.setText("Version 1.0");
		JLabel jLabel13 = new JLabel();
		jLabel13.setFont(new Font("MV Boli", Font.PLAIN, 18));
		jLabel13.setText("Copyright @ Dat Otaku");
		JLabel j1 = new JLabel();
		JLabel j2 = new JLabel();
		JLabel j3 = new JLabel();
		JLabel j4 = new JLabel();
		body.add(jLabel1);
		body.add(jLabel6);
		body.add(jLabel2);
		body.add(jLabel8);
		body.add(j1);
		body.add(jLabel9);
		body.add(j2);
		body.add(jLabe20);
		body.add(j3);
		body.add(jLabe21);
		body.add(j4);
		body.add(jLabe23);
		body.add(jLabel3);
		body.add(jLabel22);
		body.add(jLabel4);
		body.add(jLabel12);
		body.add(jLabel5);
		body.add(jLabel13);
		jPanel1.add(body, BorderLayout.CENTER);
		pack();
	}
}
