package com.pyy.tank.abstractfactory;

import java.awt.Graphics;

import com.pyy.tank.Bullet;
import com.pyy.tank.Dir;
import com.pyy.tank.Explode;
import com.pyy.tank.Group;
import com.pyy.tank.TankFrame;

/**
 * 默认的UI画风的工厂
 * @author PeiYY
 * Last_update:2021年1月19日下午9:28:03
 */
public class DefaultUIFactory extends GameFactory{

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tFrame) {
		// TODO Auto-generated method stub
		return new Bullet(x, y, dir, group, tFrame);
	}

	@Override
	public BaseExplode createExplode(int x,int y,TankFrame tFrame) {
		return new Explode(x,y,tFrame);
	}

	@Override
	public BaseTank createTank(int x,int y,TankFrame tFrame) {
		// TODO Auto-generated method stub
		return null;
	}

}
