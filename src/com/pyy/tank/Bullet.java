package com.pyy.tank;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * �ӵ���
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:26
 */
public class Bullet {
	private static final int SPEED = 10;

	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();

	private int x, y;
	private Dir dir;

	private TankFrame tFrame = null;// ������������
	private boolean living = true;// �ӵ��Ƿ񳬳��߽�
	
	private Group group=Group.BAD;

	public Bullet(int x, int y, Dir dir, Group group,TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group=group;
		this.tFrame = tFrame;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!this.living) {
			this.tFrame.bullets.remove(this);
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

		// �ӵ��������غϻ��߷ɳ��߽磬��Ӧ����ʧ��
		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
			this.living = false;
		}

	}

	/**
	 * �ӵ��͵з�̹����ײ��� Last_update:2020��12��20������2:46:12
	 * 
	 * @param enemyTank
	 */
	public void collideWith(Tank enemyTank) {
		if(this.group==enemyTank.getGroup()) return;//���̹�˺��ӵ�������һ���ģ��ǾͲ���Ҫ��ײ��⣨Ĭ�ϲ����������˺���
		
		//TODO:��һ��rect����¼�ӵ���λ�ã����ٲ���Ҫ���ڴ�����
		Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// �ӵ��ľ���
		Rectangle tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.WIDTH, enemyTank.HEIGHT);// ̹�˵ľ���
		if (bulletRect.intersects(tankRect)) {
			this.die();
			enemyTank.die();
			this.tFrame.explodes.add(new Explode(x, y, tFrame));
		}
	}

	/**
	 * �ӵ����� Last_update:2020��12��20������3:34:21
	 */
	private void die() {
		this.living = false;
	}

}
