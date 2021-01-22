package com.pyy.tank.collider;

import com.pyy.tank.Bullet;
import com.pyy.tank.Explode;
import com.pyy.tank.GameObject;
import com.pyy.tank.Tank;
import com.pyy.tank.facade_Mediator.GameModel;

/**
  *   子弹与坦克的碰撞器实现
 * @author PeiYY
 * Last_update:2021年1月20日下午10:33:38
 */
public class BulletTankCollider implements Collider {

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		if(o1 instanceof Bullet && o2 instanceof Tank) {
			Bullet b=(Bullet)o1;
			Tank t=(Tank)o2;
			if(this.collideWith(b,t)) {
				return false;
			}
		}else if(o1 instanceof Tank && o2 instanceof Bullet) {
			return collide(o2,o1);
		}

		return true;
	}
	
	/**
	 * 子弹和敌方坦克碰撞检测 Last_update:2020年12月20日下午2:46:12
	 * 
	 * @param enemyTank
	 */
	public Boolean collideWith(Bullet b,Tank t) {
		if (b.getGroup() == t.getGroup())
			return false;// 如果坦克和子弹是属于一方的，那就不需要碰撞检测（默认不开启队友伤害）

		// TODO:用一个rect来记录子弹的位置，减少不必要的内存消耗
//		Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);// 子弹的矩形
//		Rectangle tankRect = new Rectangle(enemyTank.getX(), enemyTank.getY(), enemyTank.WIDTH, enemyTank.HEIGHT);// 坦克的矩形
		if (b.bulletRect.intersects(t.tankRect)) {
			b.die();
			t.die();
			int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;// 计算爆炸的x
			int eY = t.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;// 计算爆炸的y
			t.gm.add(new Explode(eX, eY,t.gm));
			return true;
		}
		
		return false;
	}

}
