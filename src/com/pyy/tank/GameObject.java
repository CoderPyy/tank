package com.pyy.tank;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * ��Ϸ�򽻵�  
 * @author PeiYY
 * Last_update:2021��1��20������9:48:13
 */
public abstract class GameObject implements Serializable{
	
	public int x,y;
	
	public abstract void paint(Graphics g) ;
	
	public abstract int getWitdh();
	public abstract int getHeight();

}
