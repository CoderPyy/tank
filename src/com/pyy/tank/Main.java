package com.pyy.tank;

/**
 * ������
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:46
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
