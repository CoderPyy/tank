package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;

/**
  *   子弹与坦克的碰撞器实现
 * @author PeiYY
 * Last_update:2021年1月20日下午10:33:38
 */
public class BulletTankCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b=(Bullet)o1;
			Tank t=(Tank)o2;
			if(b.collideWith(t)) {
				return false;
			}
		}else if(o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2,o1);
		}

		return true;
	}

}
