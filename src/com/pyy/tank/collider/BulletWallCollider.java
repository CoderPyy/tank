package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;
import com.pyy.tank.Wall;

/**
  *   子弹与墙的碰撞器实现
 * @author PeiYY
 * Last_update:2021年1月20日下午10:33:38
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
