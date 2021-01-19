package com.pyy.tank.abstractfactory;

import java.awt.Graphics;

import com.pyy.tank.Tank;

/**
  *   抽象子弹
 * @author PeiYY
 * Last_update:2021年1月19日下午9:21:52
 */
public abstract class BaseBullet {
	public abstract void paint(Graphics g);

	public abstract void collideWith(Tank enemyTank);
}
