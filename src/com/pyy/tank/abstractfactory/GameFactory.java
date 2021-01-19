package com.pyy.tank.abstractfactory;

import com.pyy.tank.Dir;
import com.pyy.tank.Group;
import com.pyy.tank.TankFrame;

/**
 * ���� ��Ϸ����
 * @author PeiYY
 * Last_update:2021��1��19������9:18:01
 */
public abstract class GameFactory {
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tFrame);
	public abstract BaseExplode createExplode(int x,int y,TankFrame tFrame);
	public abstract BaseTank createTank(int x,int y,TankFrame tFrame);
}
