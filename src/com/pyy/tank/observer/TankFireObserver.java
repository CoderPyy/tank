package com.pyy.tank.observer;

import java.io.Serializable;

/**
 *   坦克开火观察者
 * @author PeiYY
 * Last_update:2021年1月23日下午8:20:21
 */
public interface TankFireObserver extends Serializable {

	void actionOnFire(TankFireEvent event);
	
}
