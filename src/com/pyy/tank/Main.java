package com.pyy.tank;

/**
 * ������
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:46
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame f = new TankFrame();

		while (true) {
			Thread.sleep(50);
			f.repaint();
		}
	}

}
