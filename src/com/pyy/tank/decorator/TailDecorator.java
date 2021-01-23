package com.pyy.tank.decorator;

import java.awt.Color;
import java.awt.Graphics;

import com.pyy.tank.GameObject;

/**
  *   尾巴装饰器
 * @author PeiYY
 * Last_update:2021年1月22日下午11:50:33
 */
public class TailDecorator extends GoDecorator {

	public TailDecorator(GameObject go) {
		super(go);
	}

	@Override
	public void paint(Graphics g) {
		this.x=go.x;
		this.y=go.y;
		go.paint(g);
		
		Color c=g.getColor();
		g.setColor(Color.WHITE);
		g.drawLine(super.go.x, super.go.y, super.go.x+this.getWitdh(), super.go.y+this.getHeight());
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
