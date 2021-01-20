package com.pyy.tank;

import java.awt.Graphics;

import com.pyy.tank.facade.GameModel;

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

	private GameModel gm = null;// ������������
	
	private int step=0;
	
	public Explode(int x, int y,GameModel gm) {
		this.x = x;
		this.y = y;
		this.gm = gm;
		
//		new Audio("audio/explode.wav").play();
	}
	
	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		if(step>=ResourceMgr.explodes.length) gm.explodes.remove(this);;
	}
	

}
