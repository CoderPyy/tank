package com.pyy.tank.facade;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.pyy.tank.Bullet;
import com.pyy.tank.Dir;
import com.pyy.tank.Explode;
import com.pyy.tank.Group;
import com.pyy.tank.PropertyMgr;
import com.pyy.tank.Tank;

/**
 * ���� ģʽ
 * @author PeiYY
 * Last_update:2021��1��20������8:12:14
 */
public class GameModel {
	
	Tank tank = new Tank(200, 500, Dir.UP,false,Group.GOOD, this);
	
	public List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
	public List<Tank> enemyTanks = new CopyOnWriteArrayList<Tank>();
	public List<Explode> explodes=new CopyOnWriteArrayList<Explode>();

	public GameModel() {
		int tankCount = PropertyMgr.getInt("initTankCount");

		// ��ʼ���з�̹��
		for (int i = 0; i < tankCount; i++) {
			this.enemyTanks.add(new Tank(50 + i * 80, 200, Dir.DOWN,true,Group.BAD, this));
		}
	}
	
	public void paint(Graphics g) {
		Color color = g.getColor();
		g.setColor(Color.white);
		g.drawString("�ӵ���������" + this.bullets.size(), 10, 60);
		g.drawString("̹�˵�������" + this.enemyTanks.size(), 10, 80);
		g.drawString("��ը��������" + this.explodes.size(), 10, 100);
		g.setColor(color);
		tank.paint(g);
		enemyTanks.forEach(item -> {
			item.paint(g);
		});
		bullets.forEach(item -> {
			item.paint(g);
		});
		
		explodes.forEach(item->{
			item.paint(g);
		});
		
		// ��ײ��⣬�ӵ��򵽵з�̹�ˣ��ӵ��͵з�̹��һ������
		bullets.forEach(bullet -> {
			enemyTanks.forEach(enemyTank -> {
				bullet.collideWith(enemyTank);
			});
		});
		
	}

	public Tank getMaintank() {
		// TODO Auto-generated method stub
		
		return tank;
		
	}
	
}
