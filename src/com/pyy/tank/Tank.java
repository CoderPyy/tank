package com.pyy.tank;

import java.awt.Graphics;

public class Tank {
	
	private int x,y;
	private Dir dir=Dir.DOWN;
	private static final int SPEED=10;
	private Boolean moving=false;
	
	public Tank(int x,int y,Dir dir) {
		this.x=x;
		this.y=y;
		this.dir=dir;
	}
	
	

	public int getX() {
		return x;
	}



	public void setX(int x) {
		this.x = x;
	}



	public int getY() {
		return y;
	}



	public void setY(int y) {
		this.y = y;
	}



	public Dir getDir() {
		return dir;
	}



	public void setDir(Dir dir) {
		this.dir = dir;
	}
	
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	public Boolean getMoving() {
		return moving;
	}


	public void paint(Graphics g) {
		g.fillRect(x, y, 50, 50);
		move();
	}


	private void move() {
		if(!moving) return;
		switch(dir) {
		case LEFT:
			x-=SPEED;
			break;
		case RIGHT:
			x+=SPEED;
			break;
		case UP:
			y-=SPEED;
			break;
		case DOWN:
			y+=SPEED;
			break;
		default:
			break;
		}
		
	}
	
	
	

}
