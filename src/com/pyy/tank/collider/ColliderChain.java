package com.pyy.tank.collider;

import java.util.LinkedList;
import java.util.List;

import com.pyy.tank.GameObject;

/**
  *  碰撞器责任链模式
 * @author PeiYY
 * Last_update:2021年1月20日下午11:00:45
 */
public class ColliderChain implements Collider{
	
	private List<Collider> colliders=new LinkedList<>();
	
	public ColliderChain() {
		add(new BulletTankCollider());
		add(new TankTankCollider());
		add(new BulletWallCollider());
		add(new TankWallCollider());
	}
	
	public void add(Collider c) {
		colliders.add(c);
	}

	@Override
	public Boolean collide(GameObject o1, GameObject o2) {
		for(int i=0;i<colliders.size();i++) {
			if(!colliders.get(i).collide(o1, o2)) {
				return false;
			}
		}
		
		return true;
	}

}
