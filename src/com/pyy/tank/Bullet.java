package com.pyy.tank;

import java.awt.Color;
import java.awt.Graphics;

/**
 * �ӵ���
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:26
 */
public class Bullet {
	private static final int SPEED = 10;
	private static final int WIDTH = 20, HEIGHT = 20;

	private int x, y;
	private Dir dir;

	private TankFrame tFrame = null;// ������������
	private boolean live = true;// �ӵ��Ƿ񳬳��߽�

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
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillOval(x, y, WIDTH, HEIGHT);
		g.setColor(c);
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
			this.live = false;
		}

	}

}
