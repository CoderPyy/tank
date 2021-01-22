package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.Explode;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;
import com.pyy.tank.facade_Mediator.GameModel;

/**
  *   �ӵ���̹�˵���ײ��ʵ��
 * @author PeiYY
 * Last_update:2021��1��20������10:33:38
 */
public class BulletTankCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b=(Bullet)o1;
			Tank t=(Tank)o2;
			if(this.collideWith(b,t)) {
				return false;
			}
		}else if(o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2,o1);
		}

		return true;
	}
	
	/**
	 * �ӵ��͵з�̹����ײ��� Last_update:2020��12��20������2:46:12
	 * 
	 * @param enemyTank
	 */
	public Boolean collideWith(Bullet b,Tank t) {
		if (b.getGroup() == t.getGroup())
			return false;// ���̹�˺��ӵ�������һ���ģ��ǾͲ���Ҫ��ײ��⣨Ĭ�ϲ����������˺���

		// TODO:��һ��rect����¼�ӵ���λ�ã����ٲ���Ҫ���ڴ�����
//		Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// �ӵ��ľ���
//		Rectangle tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.WIDTH, enemyTank.HEIGHT);// ̹�˵ľ���
		if (b.bulletRect.intersects(t.tankRect)) {
			b.die();
			t.die();
			int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;// ���㱬ը��x
			int eY = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;// ���㱬ը��y
			t.gm.add(new Explode(eX, eY,t.gm));
			return true;
		}
		
		return false;
	}

}
