package com.pyy.tank.observer;

import java.io.Serializable;

/**
 *   ̹�˿���۲���
 * @author PeiYY
 * Last_update:2021��1��23������8:20:21
 */
public interface TankFireObserver extends Serializable {

	void actionOnFire(TankFireEvent event);
	
}
