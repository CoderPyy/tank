package com.pyy.tank;

/**
 * ������
 * 
 * @author PeiYY Last_update:2020��12��17������11:44:46
 */
public class Main {

	public static void main(String[] args) throws InterruptedException {
		TankFrame tf = new TankFrame();
		
		//��ʼ���з�̹��
		for(int i=0;i<5;i++) {
			tf.enemyTanks.add(new Tank(50+i*80,200,Dir.DOWN,tf));
		}

		while (true) {
			Thread.sleep(50);
			tf.repaint();
		}
	}

}
