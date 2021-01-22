package com.pyy.tank.decorator;

import java.awt.Graphics;

import com.pyy.tank.GameObject;

/**
 * 装饰器模式
 * @author PeiYY
 * Last_update:2021年1月22日下午11:47:43
 */
public abstract class GoDecorator extends GameObject {

	GameObject go;
	
	public GoDecorator(GameObject go) {
		this.go = go;
	}


	@Override
	public void paint(Graphics g) {
		go.paint(g);
	}

}
