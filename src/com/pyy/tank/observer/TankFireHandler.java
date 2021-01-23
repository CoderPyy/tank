package com.pyy.tank.observer;

import com.pyy.tank.Tank;

/**
  *   ��˵��
 * @author PeiYY
 * Last_update:2021��1��23������8:21:36
 */
public class TankFireHandler implements TankFireObserver {

	@Override
	public void actionOnFire(TankFireEvent event) {
		Tank t=event.getSource();
		t.fire();
	}

}
