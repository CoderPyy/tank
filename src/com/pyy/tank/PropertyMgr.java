package com.pyy.tank;
/**
  *   类说明
 * @author PeiYY
 * Last_update:2020年12月23日下午9:56:53
 */

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertyMgr {
	static Properties props=new Properties();
	
	static {
		try {
			props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Object get(String key) {
		if(props==null) return null;
		return props.get(key);
	}
	
	public static int getInt(String key) {
		if(props==null) return (Integer) null;
		return Integer.parseInt((String) props.get(key));
	}
	
	public static String getString(String key) {
		if(props==null) return null;
		return (String) props.get(key);
	}
	
	@Test
	public void test() {
		System.out.println(PropertyMgr.getInt("initTankCount"));
	}
}
