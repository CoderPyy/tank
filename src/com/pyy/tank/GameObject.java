package com.pyy.tank;

import java.awt.Graphics;
import java.io.Serializable;

/**
 * 游戏打交道  
 * @author PeiYY
 * Last_update:2021年1月20日下午9:48:13
 */
public abstract class GameObject implements Serializable{
	
	public int x,y;
	
	public abstract void paint(Graphics g) ;
	
	public abstract int getWitdh();
	public abstract int getHeight();

}
