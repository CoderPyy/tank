package com.pyy.tank.abstractfactory;

import com.pyy.tank.Dir;
import com.pyy.tank.Group;
import com.pyy.tank.TankFrame;

/**
  *   ����UI�Ĺ���
 * @author PeiYY
 * Last_update:2021��1��19������9:48:03
 */
public class ReactUIFactory extends GameFactory {

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tFrame) {
		// TODO Auto-generated method stub
		return new ReactBullet(x,y,dir,group,tFrame);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tFrame) {
		return new ReactExplode(x, y, tFrame);
	}

	@Override
	public BaseTank createTank(int x,int y,TankFrame tFrame) {
		// TODO Auto-generated method stub
		return null;
	}

}
