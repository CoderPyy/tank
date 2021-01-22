package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;
import com.pyy.tank.Wall;

/**
  *   �ӵ���ǽ����ײ��ʵ��
 * @author PeiYY
 * Last_update:2021��1��20������10:33:38
 */
public class BulletWallCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Wall) {
			Bullet b=(Bullet)o1;
			Wall w=(Wall)o2;
			if(b.bulletRect.intersects(w.rect)) {
				b.die();
			}
		}else if(o1 instanceof Wall && o2 instanceof Bullet) {
			return collide(o2,o1);
		}

		return true;
	}

}
