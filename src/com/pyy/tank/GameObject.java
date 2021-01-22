package com.pyy.tank;

import java.awt.Graphics;

/**
 * ��Ϸ�򽻵�  
 * @author PeiYY
 * Last_update:2021��1��20������9:48:13
 */
public abstract class GameObject {
	
	public int x,y;
	
	public abstract void paint(Graphics g) ;
	
	public abstract int getWitdh();
	public abstract int getHeight();

}
