package com.pyy.tank;

import java.awt.Graphics;

import com.pyy.tank.facade.GameModel;

/**
 *   爆炸类
 * @author PeiYY
 * Last_update:2020年12月20日下午5:15:42
 *
 */
public class Explode {

	public static int WIDTH = ResourceMgr.explodes[0].getWidth();
	public static int HEIGHT = ResourceMgr.explodes[0].getHeight();

	private int x, y;

	private GameModel gm = null;// 窗体对象的引用
	
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
