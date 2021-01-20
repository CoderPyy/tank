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

	private int x, y;
	private Dir dir;

	private GameModel gm = null;// 窗体对象的引用
	private boolean living = true;// 子弹是否超出边界

	private Group group = Group.BAD;

	private Rectangle bulletRect = new Rectangle();// 子弹的矩形（单例模式）

	public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.gm = gm;

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
			this.gm.remove(this);
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
	 * 子弹和敌方坦克碰撞检测 Last_update:2020年12月20日下午2:46:12
	 * 
	 * @param enemyTank
	 */
	public Boolean collideWith(Tank enemyTank) {
		if (this.group == enemyTank.getGroup())
			return false;// 如果坦克和子弹是属于一方的，那就不需要碰撞检测（默认不开启队友伤害）

		// TODO:用一个rect来记录子弹的位置，减少不必要的内存消耗
//		Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// 子弹的矩形
//		Rectangle tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.WIDTH, enemyTank.HEIGHT);// 坦克的矩形
		if (bulletRect.intersects(enemyTank.tankRect)) {
			this.die();
			enemyTank.die();
			int eX = enemyTank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;// 计算爆炸的x
			int eY = enemyTank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;// 计算爆炸的y
			this.gm.add(new Explode(eX, eY, this.gm));
			return true;
		}
		
		return false;
	}

	/**
	 * 子弹死亡 Last_update:2020年12月20日下午3:34:21
	 */
	private void die() {
		this.living = false;
	}

}
