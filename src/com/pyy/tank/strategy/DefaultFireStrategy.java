package com.pyy.tank.strategy;

import com.pyy.tank.Audio;
import com.pyy.tank.Bullet;
import com.pyy.tank.Group;
import com.pyy.tank.Tank;

/**
 * Ĭ����� ����
 * 
 * @author PeiYY Last_update:2020��12��27������6:36:40
 */
public class DefaultFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank t) {
		// TODO: �ӵ��ļ�����Ҫ�Ż�
		int bulletX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;// �����ӵ���x
		int bulletY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;// �����ӵ���y
		new Bullet(bulletX, bulletY, t.getDir(), t.getGroup(), t.gettFrame());
		// �����ҷ�̹�˿��������
		if (t.getGroup() == Group.GOOD)
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

}
