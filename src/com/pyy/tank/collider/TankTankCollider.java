package com.pyy.tank.collider;


import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;

/**
  *   ̹����̹�˵���ײ��ʵ��
 * @author PeiYY
 * Last_update:2021��1��20������10:33:38
 */
public class TankTankCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Tank && o2 instanceof Tank) {
			Tank t1=(Tank)o1;
			Tank t2=(Tank)o2;
			if(t1.tankRect.intersects(t2.tankRect)) {
				t1.back();
				t2.back();
			}
		}
		
		return true;
		
	}

}
