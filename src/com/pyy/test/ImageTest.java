package com.pyy.test;

import static org.junit.Assert.assertNotNull;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

/**
* @author PeiYY
* @version 2020��12��17�� ����5:03:24
* ��˵��
*/
public class ImageTest {
	
	@Test
	void test() {
		try {
//			BufferedImage image = ImageIO.read(new File(""));
//			assertNotNull(image);
			
			BufferedImage image2 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
			assertNotNull(image2);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
