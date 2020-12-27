package com.pyy.tank.strategy;

import com.pyy.tank.Tank;

/**
 * 坦克fire的策略
 * 1：默认发射一颗子弹
 * 2：四方同时发射子弹
 * 3：发射榴弹
 * @author PeiYY
 * Last_update:2020年12月27日下午6:19:55
 */
public interface FireStrategy {
	void fire(Tank t);
}
