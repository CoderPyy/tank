package com.pyy.tank;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * 墙实体类
 * @author PeiYY
 * Last_update:2021年1月21日下午10:11:56
 */
public class Wall extends GameObject{

	int w,h;
	
	public Rectangle rect;
	
	public Wall(int x,int y,int w, int h) {
		super();
		this.x=x;
		this.y=y;
		this.w = w;
		this.h = h;
		this.rect = new Rectangle(x,y,w,h);
	}

	@Override
	public void paint(Graphics g) {
		Color c=g.getColor();
		g.setColor(Color.DARK_GRAY);
		g.fillRect(x, y, w, h);
		g.setColor(c);
	}

}
