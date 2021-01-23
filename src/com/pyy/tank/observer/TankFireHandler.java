package com.pyy.tank.observer;

import com.pyy.tank.Tank;

/**
  *   类说明
 * @author PeiYY
 * Last_update:2021年1月23日下午8:21:36
 */
public class TankFireHandler implements TankFireObserver {

	@Override
	public void actionOnFire(TankFireEvent event) {
		Tank t=event.getSource();
		t.fire();
	}

}
