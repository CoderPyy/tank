package com.pyy.tank;

/**
 * 主函数
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:46
 */
public class Main {

	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		
		TankFrame tf = new TankFrame();
		
		//music
//		new Thread(()->new Audio("audio/war1.wav").loop()).start();

		while 
			(true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}
}
