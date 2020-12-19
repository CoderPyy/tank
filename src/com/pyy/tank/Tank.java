package com.pyy.tank;

import java.awt.Graphics;

/**
 * 坦克类
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:47:29
 */
public class Tank {

	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	
	public static int WIDTH=ResourceMgr.tankD.getWidth();
	public static int HEIGHT=ResourceMgr.tankD.getHeight();
	
	private Boolean moving = false;
	private TankFrame tFrame;// 坦克类里面引用坦克窗体对象

	public Tank(int x, int y, Dir dir, TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tFrame = tFrame;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void paint(Graphics g) {
		//根据坦克的方向，换坦克的图片
		switch (dir) {
		case LEFT:
			g.drawImage(ResourceMgr.tankL, x, y, null);
			break;
		case RIGHT:
			g.drawImage(ResourceMgr.tankR, x, y, null);
			break;
		case UP:
			g.drawImage(ResourceMgr.tankU, x, y, null);
			break;
		case DOWN:
			g.drawImage(ResourceMgr.tankD, x, y, null);
			break;
		default:
			break;
		}
		
		move();
	}

	private void move() {
		if (!moving)
			return;
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

	}

	/**
	 * 发射子弹(子弹的方向和坦克的一致)
	 * 
	 * Last_update:2020年12月17日下午2:32:44
	 */
	public void fire() {
		//todo 子弹的计算需要优化
		int bulletX=this.x+Tank.WIDTH/2-Bullet.WIDTH/2;//计算子弹的x
		int bulletY=this.y+Tank.HEIGHT/2-Bullet.HEIGHT/2;//计算子弹的y
		this.tFrame.bullets.add(new Bullet(bulletX, bulletY, this.dir, this.tFrame));// 窗体对象里面new坦克，每new一个坦克，然后开火，就引用窗体对象里面的子弹
	}

}
