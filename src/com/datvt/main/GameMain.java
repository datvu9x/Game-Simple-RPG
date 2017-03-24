package com.datvt.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.datvt.audios.AudioLibrary;
import com.datvt.entities.InterfaceGame;
import com.datvt.images.ImageLibrary;

public class GameMain extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	public static ImageLibrary images;
	public static AudioLibrary audios;
	public static final int WIDTH = 1024, HEIGHT = 700;
	public static final int FPS = 60;

	private boolean running = false;
	private Thread gameThread;
	private Graphics2D g2;
	private BufferedImage bufferedImage;
	private InterfaceGame interfaceGame;

	public void init() {
		bufferedImage = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		g2 = (Graphics2D) bufferedImage.getGraphics();
		RenderingHints hints = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		hints.put(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHints(hints);
		this.addKeyListener(this);
		interfaceGame = new InterfaceGame();
	}

	public synchronized void start() {
		if (running)
			return;
		running = true;
		gameThread = new Thread(this);
		gameThread.start();
	}

	public synchronized void stop() {
		if (!running)
			return;
		running = false;
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		init();
		double timePerTick = 1000000000 / FPS;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if (delta >= 1) {
				update();
				draw(g2);
				delta--;
			}
		}
		stop();
	}

	public void update() {
		interfaceGame.update();
	}

	public void draw(Graphics2D g2) {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		g2 = (Graphics2D) bs.getDrawGraphics();

		g2.clearRect(0, 0, WIDTH, HEIGHT);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		interfaceGame.draw(g2);

		g2.dispose();
		bs.show();
	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		GameMain game = new GameMain();
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		game.requestFocus();
		game.setFocusable(true);

		images = ImageLibrary.getInstance();
		images.loadAllImage();
		audios = AudioLibrary.getInstance();
		audios.loadAllAudio();

		JFrame frame = new JFrame("Zoro Adventure");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(GameMain.images.get(ImageLibrary.ICON));
		frame.setResizable(false);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				int choice = JOptionPane.showConfirmDialog(null,
						"Are you sure ?", "QUIT", JOptionPane.YES_NO_OPTION);
				if (choice == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "GoodBye ! ^^");
					System.exit(0);
				}
			}
		});
		game.start();
	}

	public void keyPressed(KeyEvent e) {
		interfaceGame.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		interfaceGame.keyReleased(e);
	}

	public void keyTyped(KeyEvent e) {
		interfaceGame.keyTyped(e);
	}

}
