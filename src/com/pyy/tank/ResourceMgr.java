package com.pyy.tank;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author PeiYY
 * @version 2020年12月18日 下午4:55:31 读取图片资源
 */
public class ResourceMgr {
	public static BufferedImage tankL, tankU, tankR, tankD;
	public static BufferedImage tankGL, tankGU, tankGR, tankGD;
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	public static BufferedImage[] explodes = new BufferedImage[16];
	
	/**静态内部类的方式 实现单例设计模式**/
	private ResourceMgr() {}
	
	private static class ResourceMgrHolder{
		private static final ResourceMgr INSTANCE=new ResourceMgr();
	}
	
	public static ResourceMgr getInstance() {
		return ResourceMgrHolder.INSTANCE;
	}

	static {
		try {
			tankU = ImageIO.read(ResourceMgr.getInstance().getClass().getClassLoader().getResourceAsStream("images/BadTank1.png"));
			tankL = ImageUtil.rotateImage(tankU, -90);
			tankR = ImageUtil.rotateImage(tankU, 90);
			tankD = ImageUtil.rotateImage(tankU, 180);
			
			tankGU = ImageIO.read(ResourceMgr.getInstance().getClass().getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			tankGL = ImageUtil.rotateImage(tankGU, -90);
			tankGR = ImageUtil.rotateImage(tankGU, 90);
			tankGD = ImageUtil.rotateImage(tankGU, 180);

			bulletU = ImageIO.read(ResourceMgr.getInstance().getClass().getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);

			for (int i = 0; i < 16; i++) {
				explodes[i] = ImageIO
						.read(ResourceMgr.getInstance().getClass().getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
