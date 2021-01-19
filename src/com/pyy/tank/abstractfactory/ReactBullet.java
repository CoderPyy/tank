package com.pyy.tank.abstractfactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.pyy.tank.Dir;
import com.pyy.tank.Explode;
import com.pyy.tank.Group;
import com.pyy.tank.ResourceMgr;
import com.pyy.tank.Tank;
import com.pyy.tank.TankFrame;

/**
  *   ��˵��
 * @author PeiYY
 * Last_update:2021��1��19������10:08:49
 */
public class ReactBullet extends BaseBullet {

	private static final int SPEED = 10;

	public static int WIDTH = ResourceMgr.bulletD.getWidth();
	public static int HEIGHT = ResourceMgr.bulletD.getHeight();

	private int x, y;
	private Dir dir;

	private TankFrame tFrame = null;// ������������
	private boolean living = true;// �ӵ��Ƿ񳬳��߽�

	private Group group = Group.BAD;

	private Rectangle bulletRect = new Rectangle();// �ӵ��ľ��Σ�����ģʽ��

	public ReactBullet(int x, int y, Dir dir, Group group, TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tFrame = tFrame;

		bulletRect.x = this.x;
		bulletRect.y = this.y;
		bulletRect.width = WIDTH;
		bulletRect.height = HEIGHT;
		
		//�����������new̹�ˣ�ÿnewһ��̹�ˣ�Ȼ�󿪻𣬾����ô������������ӵ�
		tFrame.bullets.add(this);

	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	@Override
	public void paint(Graphics g) {
		if (!this.living) {
			this.tFrame.bullets.remove(this);
		}

		Color c=g.getColor();
		g.setColor(Color.yellow);
		g.fillRect(x, y, 20, 20);
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

		// update rect
		bulletRect.x = this.x;
		bulletRect.y = this.y;

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
		if (this.group == enemyTank.getGroup())
			return;// ���̹�˺��ӵ�������һ���ģ��ǾͲ���Ҫ��ײ��⣨Ĭ�ϲ����������˺���

		// TODO:��һ��rect����¼�ӵ���λ�ã����ٲ���Ҫ���ڴ�����
//		Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// �ӵ��ľ���
//		Rectangle tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.WIDTH, enemyTank.HEIGHT);// ̹�˵ľ���
		if (bulletRect.intersects(enemyTank.tankRect)) {
			this.die();
			enemyTank.die();
			int eX = enemyTank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;// ���㱬ը��x
			int eY = enemyTank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;// ���㱬ը��y
			this.tFrame.explodes.add(this.tFrame.gf.createExplode(eX, eY, tFrame));
		}
	}

	/**
	 * �ӵ����� Last_update:2020��12��20������3:34:21
	 */
	private void die() {
		this.living = false;
	}

}
