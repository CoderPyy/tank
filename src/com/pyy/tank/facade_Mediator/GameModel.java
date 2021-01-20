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
 * ���� ģʽ
 * @author PeiYY
 * Last_update:2021��1��20������8:12:14
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

		// ��ʼ���з�̹��
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
		 * g.drawString("�ӵ���������" + this.bullets.size(), 10, 60); g.drawString("̹�˵�������" +
		 * this.enemyTanks.size(), 10, 80); g.drawString("��ը��������" +
		 * this.explodes.size(), 10, 100);
		 */
		g.setColor(color);
		tank.paint(g);
		
		for(int i=0;i<objects.size();i++) {
			objects.get(i).paint(g);
		}
		
		//������ײ
		for(int i=0;i<objects.size();i++) {
			for(int j=i+1;j<objects.size();j++) {// comparator-->compare(o1,o2) �����ڱȽ��������дһ�� ��ײ��
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
		
		// ��ײ��⣬�ӵ��򵽵з�̹�ˣ��ӵ��͵з�̹��һ������
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
