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

public class Help extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel label1 = new JLabel();
	private JLabel label2 = new JLabel();
	private JLabel label3 = new JLabel();
	private JLabel label4 = new JLabel();
	private JLabel label6 = new JLabel();
	private JLabel label7 = new JLabel();

	public Help() {
		init();
		setTitle("Help");
		setIconImage(GameMain.images.get(ImageLibrary.ICON));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public void init() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(850, 300));
		panel.setBackground(Color.CYAN);
		add(panel);
		JPanel p = new JPanel();
		p.setBackground(Color.CYAN);
		label1.setText("How To Play The Game");
		label1.setFont(new Font(InterfaceGame.FONT_NAME, Font.BOLD, 22));
		label1.setForeground(Color.RED);
		p.add(label1);
		panel.add(p, BorderLayout.NORTH);

		JPanel pa1 = new JPanel(new GridLayout(5, 1, 5, 5));
		pa1.setBackground(Color.CYAN);
		label2.setText(" - Nhấn phím mũi tên trên bàn phím để di chuyển nhân vật sang trái, phải, lên xuống, và lựa chọn menu.");
		label2.setFont(new Font("Consolas", Font.ITALIC, 15));
		label3.setText(" - Nhân vật có hai kiểu tấn công: Thông thường với các phím A và S. Kỹ năng phím Z và X.");
		label3.setFont(new Font("Consolas", Font.ITALIC, 15));
		label4.setText(" - Sử dụng các phím E và R để chạy nhan sang trái phải. Phím I và P để xem thông tin nhân vật, thoát game.");
		label4.setFont(new Font("Consolas", Font.ITALIC, 15));
		label6.setText(" - Nhân vật có thể di chuyển qua các map khác nhau thông qua các cổng trên map.");
		label6.setFont(new Font("Consolas", Font.ITALIC, 15));
		label7.setText(" - Khi tiêu diệt quái vật, nhân vật được nhận EXP để lên Level. Có xác suất nhặt được vật phẩm từ quái vật.");
		label7.setFont(new Font("Consolas", Font.ITALIC, 15));
		pa1.add(label2);
		pa1.add(label3);
		pa1.add(label4);
		pa1.add(label6);
		pa1.add(label7);
		panel.add(pa1, BorderLayout.CENTER);
		pack();
	}
}
