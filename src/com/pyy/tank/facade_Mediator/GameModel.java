package com.pyy.tank.facade_Mediator;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pyy.tank.Bullet;
import com.pyy.tank.Dir;
import com.pyy.tank.Explode;
import com.pyy.tank.GameObject;
import com.pyy.tank.Group;
import com.pyy.tank.PropertyMgr;
import com.pyy.tank.Tank;
import com.pyy.tank.collider.BulletTankCollider;
import com.pyy.tank.collider.Collider;
import com.pyy.tank.collider.ColliderChain;
import com.pyy.tank.collider.TankTankCollider;

/**
 * 门面 模式
 * @author PeiYY
 * Last_update:2021年1月20日下午8:12:14
 */
public class GameModel {
	
	Tank tank = new Tank(200, 500, Dir.UP,false,Group.GOOD, this);
	
//	public List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
//	public List<Tank> enemyTanks = new CopyOnWriteArrayList<Tank>();
//	public List<Explode> explodes=new CopyOnWriteArrayList<Explode>();
	
//	Collider collider=new BulletTankCollider();
//	Collider collider2=new TankTankCollider();
	
	ColliderChain chain=new ColliderChain();
	
	public List<GameObject> objects=new ArrayList<>();

	public GameModel() {
		int tankCount = PropertyMgr.getInt("initTankCount");

		// 初始化敌方坦克
		for (int i = 0; i < tankCount; i++) {
			add(new Tank(50 + i * 80, 200, Dir.DOWN,true,Group.BAD, this));
		}
	}
	
	public void add(GameObject go) {
		this.objects.add(go);
	}
	
	public void remove(GameObject go) {
		this.objects.remove(go);
	}
	
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.white);
		/*
		 * g.drawString("子弹的数量：" + this.bullets.size(), 10, 60); g.drawString("坦克的数量：" +
		 * this.enemyTanks.size(), 10, 80); g.drawString("爆炸的数量：" +
		 * this.explodes.size(), 10, 100);
		 */
		g.setColor(color);
		tank.paint(g);
		
		for(int i=0;i<objects.size();i++) {
			objects.get(i).paint(g);
		}
		
		//互相碰撞
		for(int i=0;i<objects.size();i++) {
			for(int j=i+1;j<objects.size();j++) {// comparator-->compare(o1,o2) 类似于比较器，这边写一个 碰撞器
				GameObject o1=objects.get(i);
				GameObject o2=objects.get(j);
				chain.collide(o1,o2);
			}
		}
		
		
		/*
		 * enemyTanks.forEach(item -> { item.paint(g); }); bullets.forEach(item -> {
		 * item.paint(g); });
		 * 
		 * explodes.forEach(item->{ item.paint(g); });
		 */
		
		// 碰撞检测，子弹打到敌方坦克，子弹和敌方坦克一起灭亡
		/*
		 * bullets.forEach(bullet -> { enemyTanks.forEach(enemyTank -> {
		 * bullet.collideWith(enemyTank); }); });
		 */
		
	}

	public Tank getMaintank() {
		// TODO Auto-generated method stub
		
		return tank;
		
	}
	
}
