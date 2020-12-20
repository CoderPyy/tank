package com.pyy.tank;

import java.awt.Graphics;
import java.util.Random;

/**
 * ̹����
 * 
 * @author PeiYY Last_update:2020��12��17������11:47:29
 */
public class Tank {

	private int x, y;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 1;

	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private Boolean moving = true;
	private TankFrame tFrame;// ̹������������̹�˴������
	private Boolean living=true;
	
	private Group group=Group.BAD;
	private Random random=new Random();

	public Tank(int x, int y, Dir dir, Group group,TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group=group;
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
	
	public Boolean getLiving() {
		return living;
	}

	public void setLiving(Boolean living) {
		this.living = living;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if(!this.living) {
			this.tFrame.enemyTanks.remove(this);
		}
		// ����̹�˵ķ��򣬻�̹�˵�ͼƬ
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
		if (!this.moving)
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
		
		if(random.nextInt(10)>5) this.fire();//�����10���ڣ�����5������

	}

	/**
	 * �����ӵ�(�ӵ��ķ����̹�˵�һ��)
	 * 
	 * Last_update:2020��12��17������2:32:44
	 */
	public void fire() {
		// todo �ӵ��ļ�����Ҫ�Ż�
		int bulletX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;// �����ӵ���x
		int bulletY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;// �����ӵ���y
		this.tFrame.bullets.add(new Bullet(bulletX, bulletY, this.dir, this.group,this.tFrame));// �����������new̹�ˣ�ÿnewһ��̹�ˣ�Ȼ�󿪻𣬾����ô������������ӵ�
		//�����ҷ�̹�˵�����
//		if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
	}

	/**
	 * ̹������
	 * 
	 * Last_update:2020��12��20������2:55:37
	 */
	public void die() {
		this.setLiving(false);
	}

}
