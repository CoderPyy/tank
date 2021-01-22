package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;
import com.pyy.tank.Wall;

/**
  *   ̹����ǽ����ײ��ʵ��
 * @author PeiYY
 * Last_update:2021��1��20������10:33:38
 */
public class TankWallCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Wall) {
			Tank t=(Tank)o1;
			Wall w=(Wall)o2;
			if(t.tankRect.intersects(w.rect)) {
				t.back();
			}
		}else if(o1 instanceof Wall && o2 instanceof Tank) {
			return collide(o2,o1);
		}

		return true;
	}

}
