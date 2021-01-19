package com.pyy.tank.abstractfactory;

import java.awt.Graphics;

import com.pyy.tank.Tank;

/**
  *   �����ӵ�
 * @author PeiYY
 * Last_update:2021��1��19������9:21:52
 */
public abstract class BaseBullet {
	public abstract void paint(Graphics g);

	public abstract void collideWith(Tank enemyTank);
}
