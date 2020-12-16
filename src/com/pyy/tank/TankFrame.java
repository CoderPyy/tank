package com.pyy.tank;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
	int x = 200, y = 200;

	public TankFrame() {
		setSize(800, 600);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);

		this.addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

	}

	@Override
	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
//		x += 10;
//		y += 10;
		
	}

	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			System.out.println("keyPressed...");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
//				x -= 10;
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
//				x += 10;
				bR = true;
				break;
			case KeyEvent.VK_UP:
//				y -= 10;
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
//				y += 10;
				bD = true;
				break;
			default:
				break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			System.out.println("keyRelased...");
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
//				x -= 10;
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
//				x += 10;
				bR = false;
				break;
			case KeyEvent.VK_UP:
//				y -= 10;
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
//				y += 10;
				bD = false;
				break;
			default:
				break;
			}
		}

	}

}
