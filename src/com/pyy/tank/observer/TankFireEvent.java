package com.pyy.tank.observer;
/**
 * 坦克开火事件 
 * @author PeiYY
 * Last_update:2021年1月23日下午8:19:17
 */

import com.pyy.tank.Tank;

public class TankFireEvent {
	/**
	 * 事件源：坦克
	 */
	private Tank tank;
	
	public TankFireEvent(Tank tank) {
		this.tank=tank;
	}
	
	/**
	 * 获取 事件源
	 * Last_update:2021年1月23日下午8:49:53
	 * @return
	 */
	public Tank getSource() {
		return tank;
	}
}
