package com.pyy.tank;

/**
 * 主函数
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:46
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
