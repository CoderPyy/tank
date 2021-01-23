package com.pyy.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.pyy.tank.GameObject;

/**
  *   方块装饰器
 * @author PeiYY
 * Last_update:2021年1月22日下午11:50:33
 */
public class RectDecorator extends GoDecorator {

	public RectDecorator(GameObject go) {
		super(go);
	}

	@Override
	public void paint(Graphics g) {
		this.x=go.x;
		this.y=go.y;
		go.paint(g);
		
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.drawRect(super.go.x, super.go.y, this.getWitdh()+2, this.getHeight()+2);
		g.setColor(c);
	}

	@Override
	public int getWitdh() {
		return go.getWitdh();
	}

	@Override
	public int getHeight() {
		return go.getHeight();
	}
	
	

}
