package com.pyy.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pyy.tank.facade_Mediator.GameModel;

/**
 * 坦克窗体类
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:49
 */
public class TankFrame extends Frame implements Serializable{

	GameModel gm=new GameModel();
	
	static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
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

	// 解决屏幕一闪一闪的问题，定义一个和页面一样大的图片，每次将图片一次性加载到内存中
	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color color = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(color);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	@Override
	public void paint(Graphics g) {
		
		gm.paint(g);
		
	}

	class MyKeyListener extends KeyAdapter {

		boolean bL = false;
		boolean bU = false;
		boolean bR = false;
		boolean bD = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = true;
				break;
			case KeyEvent.VK_RIGHT:
				bR = true;
				break;
			case KeyEvent.VK_UP:
				bU = true;
				break;
			case KeyEvent.VK_DOWN:
				bD = true;
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit(0);
				break;
			case KeyEvent.VK_CONTROL:
//				gm.getMaintank().fire();
				gm.getMaintank().handleFireKey();
				break;
			case KeyEvent.VK_S:
				gm.save();
				break;
			case KeyEvent.VK_L:
				gm.load();
				break;
			default:
				break;
			}

			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_LEFT:
				bL = false;
				break;
			case KeyEvent.VK_RIGHT:
				bR = false;
				break;
			case KeyEvent.VK_UP:
				bU = false;
				break;
			case KeyEvent.VK_DOWN:
				bD = false;
				break;
			default:
				break;
			}

			setMainTankDir();

		}

		private void setMainTankDir() {

			if (!bL && !bR && !bU && !bD) {// 没有按住上下左右键，设置坦克静止
				gm.getMaintank().setMoving(false);
			} else {
				gm.getMaintank().setMoving(true);// 设置坦克移动

				if (bL)
					gm.getMaintank().setDir(Dir.LEFT);
				if (bR)
					gm.getMaintank().setDir(Dir.RIGHT);
				if (bU)
					gm.getMaintank().setDir(Dir.UP);
				if (bD)
					gm.getMaintank().setDir(Dir.DOWN);
			}

		}

	}

}
