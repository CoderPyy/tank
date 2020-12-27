package com.pyy.tank.strategy;

import com.pyy.tank.Audio;
import com.pyy.tank.Bullet;
import com.pyy.tank.Group;
import com.pyy.tank.Tank;

/**
 * 默认射击 策略
 * 
 * @author PeiYY Last_update:2020年12月27日下午6:36:40
 */
public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		// TODO: 子弹的计算需要优化
		int bulletX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;// 计算子弹的x
		int bulletY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;// 计算子弹的y
		new Bullet(bulletX, bulletY, t.getDir(), t.getGroup(), t.gettFrame());
		// 播放我方坦克开火的声音
		if (t.getGroup() == Group.GOOD)
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

}
