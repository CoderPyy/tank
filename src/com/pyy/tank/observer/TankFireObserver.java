package com.pyy.tank.observer;
/**
 *   坦克开火观察者
 * @author PeiYY
 * Last_update:2021年1月23日下午8:20:21
 */
public interface TankFireObserver {

	void actionOnFire(TankFireEvent event);
	
}
