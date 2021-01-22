package com.pyy.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.pyy.tank.facade_Mediator.GameModel;

/**
 * 子弹类
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:26
 */
public class Bullet extends GameObject{
	private static final int SPEED = 10;

	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();

	private Dir dir;

	private GameModel gm = null;// 窗体对象的引用
	private boolean living = true;// 子弹是否超出边界

	private Group group = Group.BAD;

	public Rectangle bulletRect = new Rectangle();// 子弹的矩形（单例模式）

	public Bullet(int x, int y, Dir dir, Group group,GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm=gm;

		bulletRect.x = this.x;
		bulletRect.y = this.y;
		bulletRect.width = WIDTH;
		bulletRect.height = HEIGHT;
		
		//窗体对象里面new坦克，每new一个坦克，然后开火，就引用窗体对象里面的子弹
		gm.add(this);

	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!this.living) {
			gm.remove(this);
		}

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

		// update rect
		bulletRect.x = this.x;
		bulletRect.y = this.y;

		// 子弹跟自身重合或者飞出边界，就应该消失了
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.living = false;
		}

	}
	

	/**
	 * 子弹死亡 Last_update:2020年12月20日下午3:34:21
	 */
	public void die() {
		this.living = false;
	}

	@Override
	public int getWitdh() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}

}
