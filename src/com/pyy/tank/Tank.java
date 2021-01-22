package com.pyy.tank;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.pyy.tank.facade_Mediator.GameModel;
import com.pyy.tank.strategy.DefaultFireStrategy;
import com.pyy.tank.strategy.FireStrategy;
import com.pyy.tank.strategy.FourDirFireStrategy;

/**
 * ̹����
 * 
 * @author PeiYY Last_update:2020��12��17������11:47:29
 */
public class Tank extends GameObject{

	private int x, y;
	private int oldX,oldY;//��¼֮ǰ�ƶ���λ��
	private Dir dir = Dir.UP;
	private static final int SPEED = 5;

	public static int WIDTH = ResourceMgr.tankD.getWidth();
	public static int HEIGHT = ResourceMgr.tankD.getHeight();

	private Boolean moving;
//	private TankFrame tFrame;// ̹������������̹�˴������
	private Boolean living=true;
	
	private Group group=Group.BAD;
	private Random random=new Random();
	
	public Rectangle tankRect = new Rectangle();// ̹�˵ľ��Σ�����ģʽ��
	
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
		// ����̹�˵ķ��򣬻�̹�˵�ͼƬ
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
		
		//��¼֮ǰ�ƶ���λ��
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
		
		
		
		//�����ҷ�̹���ƶ�������
//		if(this.group == Group.GOOD) new Thread(()->new Audio("audio/tank_move.wav").play()).start();
		
		if(this.group==Group.BAD&&random.nextInt(100)>95) this.fire();//�����100���ڣ�����95���з�̹�˿���
		
		if(this.group==Group.BAD&&random.nextInt(100)>95) this.radomDir();//�����100���ڣ�����95���з�̹���ƶ�

		//�߽���
		boundsCheck();
		
		//update rect
		tankRect.x=this.x;
		tankRect.y=this.y;
	}
	
	
	/**
	 *   �߽��ƶ����
	 * Last_update:2020��12��21������11:00:52
	 */
	private void boundsCheck() {
		if(this.x<2) x=2;
		if(this.y<28) y=28;
		if(this.x>TankFrame.GAME_WIDTH-Tank.WIDTH) x=TankFrame.GAME_WIDTH-Tank.WIDTH-2;
		if(this.y>TankFrame.GAME_HEIGHT-Tank.HEIGHT) y=TankFrame.GAME_HEIGHT-Tank.HEIGHT-2;
	}

	/**
	 *   ����ƶ�������
	 * Last_update:2020��12��21������9:48:12
	 */
	private void radomDir() {
		this.dir=Dir.values()[random.nextInt(4)];
	}

	/**
	 * �����ӵ�(�ӵ��ķ����̹�˵�һ��)
	 * 
	 * Last_update:2020��12��17������2:32:44
	 */
	public void fire() {
		this.strategy.fire(this);
	}

	/**
	 * ̹������
	 * 
	 * Last_update:2020��12��20������2:55:37
	 */
	public void die() {
		this.setLiving(false);
	}

	public void stop() {
		this.moving=false;
	}

}
