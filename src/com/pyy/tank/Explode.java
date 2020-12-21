package com.pyy.tank;

import java.awt.Graphics;

/**
 *   ��ը��
 * @author PeiYY
 * Last_update:2020��12��20������5:15:42
 *
 */
public class Explode {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;

	private TankFrame tFrame = null;// ������������
	
	private int step=0;
	
	public Explode(int x, int y,TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.tFrame = tFrame;
		
		new Audio("audio/explode.wav").play();;
	}
	
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step>=ResourceMgr.explodes.length) tFrame.explodes.remove(this);;
	}
	

}
