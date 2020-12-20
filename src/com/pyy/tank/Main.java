package com.pyy.tank;

/**
 * 主函数
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:44:46
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		
		//初始化敌方坦克
		for(int i=0;i<5;i++) {
			tf.enemyTanks.add(new Tank(50+i*80,200,Dir.DOWN,tf));
		}

		while (true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}

}
