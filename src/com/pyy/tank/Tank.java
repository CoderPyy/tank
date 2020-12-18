package com.pyy.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * ̹����
 * 
 * @author PeiYY Last_update:2020��12��17������11:47:29
 */
public class Tank {

	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 5;
	private Boolean moving = false;
	private TankFrame tFrame;// ̹������������̹�˴������

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
		//����̹�˵ķ��򣬻�̹�˵�ͼƬ
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
	 * �����ӵ�(�ӵ��ķ����̹�˵�һ��)
	 * 
	 * Last_update:2020��12��17������2:32:44
	 */
	public void fire() {
		this.tFrame.bullets.add(new Bullet(this.x + 11, this.y + 11, this.dir, this.tFrame));// �����������new̹�ˣ�ÿnewһ��̹�ˣ�Ȼ�󿪻𣬾����ô������������ӵ�
	}

}
