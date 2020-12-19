package com.pyy.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * 子弹类
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:26
 */
public class Bullet {
	private static final int SPEED = 10;
	
	public static int WIDTH=ResourceMgr.bulletD.getWidth();
	public static int HEIGHT=ResourceMgr.bulletD.getHeight();

	private int x, y;
	private Dir dir;

	private TankFrame tFrame = null;// 窗体对象的引用
	private boolean live = true;// 子弹是否超出边界

	public Bullet(int x, int y, Dir dir, TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tFrame = tFrame;
	}

	public void paint(Graphics g) {
		if (!this.live) {
			tFrame.bullets.remove(this);
		}
		/*
		 * Color c = g.getColor(); g.setColor(Color.RED); g.fillOval(x, y, WIDTH,
		 * HEIGHT); g.setColor(c);
		 */
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.bulletL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.bulletR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.bulletU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.bulletD, x, y, null);
			break;
		default:
			break;
		}
		move();
	}

	private void move() {
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}

		// 子弹跟自身重合或者飞出边界，就应该消失了
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.live = false;
		}

	}

}
