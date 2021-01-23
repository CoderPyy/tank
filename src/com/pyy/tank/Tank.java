package com.pyy.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.pyy.tank.facade_Mediator.GameModel;
import com.pyy.tank.observer.TankFireEvent;
import com.pyy.tank.observer.TankFireHandler;
import com.pyy.tank.observer.TankFireObserver;
import com.pyy.tank.strategy.DefaultFireStrategy;
import com.pyy.tank.strategy.FireStrategy;
import com.pyy.tank.strategy.FourDirFireStrategy;

/**
 * 坦克类
 * 
 * @author PeiYY Last_update:2020年12月17日上午11:47:29
 */
public class Tank extends GameObject{

	private int oldX,oldY;//记录之前移动的位置
	private Dir dir = Dir.UP;
	private static final int SPEED = 5;

	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private Boolean moving;
//	private TankFrame tFrame;// 坦克类里面引用坦克窗体对象
	private Boolean living=true;
	
	private Group group=Group.BAD;
	private Random random=new Random();
	
	public Rectangle tankRect = new Rectangle();// 坦克的矩形（单例模式）
	
	FireStrategy strategy;
	public GameModel gm;
	
	public Tank(int x, int y, Dir dir,Boolean moving, Group group,GameModel gm) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.moving=moving;
		this.group=group;
		this.gm=gm;
		
		tankRect.x=this.x;
		tankRect.y=this.y;
		tankRect.width=WIDTH;
		tankRect.height=HEIGHT;
		
		if(this.group==Group.GOOD) {
			String goodFSName=(String)PropertyMgr.get("goodFS");
			try {
				strategy=(FireStrategy) Class.forName(goodFSName).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			String badFSName=(String)PropertyMgr.get("badFS");
			try {
				strategy=(FireStrategy) Class.forName(badFSName).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
//		GameModel.getInstance().add(this);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Boolean getMoving() {
		return moving;
	}
	
	public Boolean getLiving() {
		return living;
	}

	public void setLiving(Boolean living) {
		this.living = living;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}


	public void paint(Graphics g) {
		if(!this.living) {
			gm.remove(this);
		}
		// 根据坦克的方向，换坦克的图片
		if(this.group==Group.BAD) {
			switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.tankL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.tankR, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.tankU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.tankD, x, y, null);
				break;
			default:
				break;
			}
		}
		
		if(this.group==Group.GOOD) {
			switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.tankGL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.tankGR, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.tankGU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.tankGD, x, y, null);
				break;
			default:
				break;
			}
		}
		
		
		move();
	}
	
	public void back() {
		x=oldX;
		y=oldY;
	}

	private void move() {
		
		//记录之前移动的位置
		oldX=x;
		oldY=y;
		
		if (!this.moving)
			return;
		switch (dir) {
		case LEFT:
			x -= SPEED;
			break;
		case RIGHT:
			x += SPEED;
			break;
		case UP:
			y -= SPEED;
			break;
		case DOWN:
			y += SPEED;
			break;
		default:
			break;
		}
		
		
		
		//播放我方坦克移动的声音
//		if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		
		if(this.group==Group.BAD&&random.nextInt(100)>95) this.fire();//随机数100以内，大于95，敌方坦克开火
		
		if(this.group==Group.BAD&&random.nextInt(100)>95) this.radomDir();//随机数100以内，大于95，敌方坦克移动

		//边界检测
		boundsCheck();
		
		//update rect
		tankRect.x=this.x;
		tankRect.y=this.y;
	}
	
	
	/**
	 *   边界移动检测
	 * Last_update:2020年12月21日下午11:00:52
	 */
	private void boundsCheck() {
		if(this.x<2) x=2;
		if(this.y<28) y=28;
		if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
		if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
	}

	/**
	 *   随机移动换方向
	 * Last_update:2020年12月21日下午9:48:12
	 */
	private void radomDir() {
		this.dir=Dir.values()[random.nextInt(4)];
	}

	/**
	 * 发射子弹(子弹的方向和坦克的一致)
	 * 
	 * Last_update:2020年12月17日下午2:32:44
	 */
	public void fire() {
		this.strategy.fire(this);
	}

	/**
	 * 坦克死亡
	 * 
	 * Last_update:2020年12月20日下午2:55:37
	 */
	public void die() {
		this.setLiving(false);
	}

	public void stop() {
		this.moving=false;
	}

	@Override
	public int getWitdh() {
		return WIDTH;
	}

	@Override
	public int getHeight() {
		return HEIGHT;
	}
	
	
	private List<TankFireObserver> fireObservers=Arrays.asList(new TankFireHandler());

	/**
	 * 处理开火按键
	 * Last_update:2021年1月23日下午8:23:09
	 */
	public void handleFireKey() {
		TankFireEvent event=new TankFireEvent(this);
		for(TankFireObserver observer:fireObservers) {
			observer.actionOnFire(event);
		}
	}

}
