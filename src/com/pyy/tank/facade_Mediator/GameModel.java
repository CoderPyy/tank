package com.pyy.tank.facade_Mediator;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
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
import com.pyy.tank.Wall;
import com.pyy.tank.collider.BulletTankCollider;
import com.pyy.tank.collider.Collider;
import com.pyy.tank.collider.ColliderChain;
import com.pyy.tank.collider.TankTankCollider;

/**
 * 门面 模式
 * @author PeiYY
 * Last_update:2021年1月20日下午8:12:14
 */
public class GameModel implements Serializable{
	
	/**单例设计模式**/
	/*
	 * private static final GameModel INSTANCE=new GameModel();
	 * 
	 * public static GameModel getInstance() { return INSTANCE; }
	 */
	
	Tank myTank;
	
//	public List<Bullet> bullets = new CopyOnWriteArrayList<Bullet>();
//	public List<Tank> enemyTanks = new CopyOnWriteArrayList<Tank>();
//	public List<Explode> explodes=new CopyOnWriteArrayList<Explode>();
	
//	Collider collider=new BulletTankCollider();
//	Collider collider2=new TankTankCollider();
	
	ColliderChain chain=new ColliderChain();
	
	public List<GameObject> objects=new ArrayList<>();

	public GameModel() {
		//初始化主战坦克
		myTank = new Tank(200, 500, Dir.UP,false,Group.GOOD,this);
		int tankCount = PropertyMgr.getInt("initTankCount");

		// 初始化敌方坦克
		for (int i = 0; i < tankCount; i++) {
			add(new Tank(50 + i * 80, 200, Dir.DOWN,true,Group.BAD,this));
		}
		
		//初始化墙
		add(new Wall(150, 150, 200, 50));
		add(new Wall(550, 150, 200, 50));
		add(new Wall(300, 300, 50, 200));
		add(new Wall(550, 300, 50, 200));
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
		myTank.paint(g);
		
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
		return myTank;
		
	}
	
	public void save() {
		File f=new File("w:/tank.data");
		ObjectOutputStream oos=null;
		try {
			oos=new ObjectOutputStream(new FileOutputStream(f));
			oos.writeObject(myTank);
			oos.writeObject(objects);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(oos!=null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void load() {
		File f=new File("w:/tank.data");
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream(f));
			try {
				myTank=(Tank)ois.readObject();
				objects=(List)ois.readObject();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(ois!=null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
