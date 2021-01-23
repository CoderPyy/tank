package com.pyy.tank.observer;
/**
 * ̹�˿����¼� 
 * @author PeiYY
 * Last_update:2021��1��23������8:19:17
 */

import com.pyy.tank.Tank;

public class TankFireEvent {
	/**
	 * �¼�Դ��̹��
	 */
	private Tank tank;
	
	public TankFireEvent(Tank tank) {
		this.tank=tank;
	}
	
	/**
	 * ��ȡ �¼�Դ
	 * Last_update:2021��1��23������8:49:53
	 * @return
	 */
	public Tank getSource() {
		return tank;
	}
}
