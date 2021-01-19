package com.pyy.tank;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pyy.tank.abstractfactory.BaseBullet;
import com.pyy.tank.abstractfactory.BaseExplode;
import com.pyy.tank.abstractfactory.DefaultUIFactory;
import com.pyy.tank.abstractfactory.GameFactory;
import com.pyy.tank.abstractfactory.ReactUIFactory;

/**
 * ̹�˴�����
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:49
 */
public class TankFrame extends Frame {

	Tank tank = new Tank(200, 500, Dir.UP,false,Group.GOOD, this);
	public List<BaseBullet> bullets = new CopyOnWriteArrayList<>();
	public List<Tank> enemyTanks = new CopyOnWriteArrayList<Tank>();
	public List<BaseExplode> explodes=new CopyOnWriteArrayList<>();
	
	public GameFactory gf=new ReactUIFactory();
	
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
	

	public List<BaseBullet> getBullets() {
		return bullets;
	}

	public void setBullets(List<BaseBullet> bullets) {
		this.bullets = bullets;
	}



	// �����Ļһ��һ�������⣬����һ����ҳ��һ�����ͼƬ��ÿ�ν�ͼƬһ���Լ��ص��ڴ���
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
		Color color = g.getColor();
		g.setColor(Color.white);
		g.drawString("�ӵ���������" + this.bullets.size(), 10, 60);
		g.drawString("̹�˵�������" + this.enemyTanks.size(), 10, 80);
		g.drawString("��ը��������" + this.explodes.size(), 10, 100);
		g.setColor(color);
		tank.paint(g);
		enemyTanks.forEach(item -> {
			item.paint(g);
		});
		bullets.forEach(item -> {
			item.paint(g);
		});
		
		explodes.forEach(item->{
			item.paint(g);
		});
		
		// ��ײ��⣬�ӵ��򵽵з�̹�ˣ��ӵ��͵з�̹��һ������
		bullets.forEach(bullet -> {
			enemyTanks.forEach(enemyTank -> {
				bullet.collideWith(enemyTank);
			});
		});
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
				tank.fire();
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

			if (!bL && !bR && !bU && !bD) {// û�а�ס�������Ҽ�������̹�˾�ֹ
				tank.setMoving(false);
			} else {
				tank.setMoving(true);// ����̹���ƶ�

				if (bL)
					tank.setDir(Dir.LEFT);
				if (bR)
					tank.setDir(Dir.RIGHT);
				if (bU)
					tank.setDir(Dir.UP);
				if (bD)
					tank.setDir(Dir.DOWN);
			}

		}

	}

}
