package com.pyy.tank;

import java.awt.Color;
import java.awt.Graphics;

import com.pyy.tank.abstractfactory.BaseExplode;

/**
  * �����UI��ը
 * @author PeiYY
 * Last_update:2021��1��19������9:49:36
 */
public class ReactExplode extends BaseExplode {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;

	private TankFrame tFrame = null;// ������������
	
	private int step=0;
	
	public ReactExplode(int x, int y,TankFrame tFrame) {
		this.x = x;
		this.y = y;
		this.tFrame = tFrame;
		
//		new Audio("audio/explode.wav").play();
	}
	
	@Override
	public void paint(Graphics g) {
//		g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		
		Color c=g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10*step, 10*step);
		step++;
		
		if(step>=5) tFrame.explodes.remove(this);
		g.setColor(c);
	}

}
