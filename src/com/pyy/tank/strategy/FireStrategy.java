package com.pyy.tank.strategy;

import java.io.Serializable;

import com.pyy.tank.Tank;

/**
 * ̹��fire�Ĳ���
 * 1��Ĭ�Ϸ���һ���ӵ�
 * 2���ķ�ͬʱ�����ӵ�
 * 3��������
 * @author PeiYY
 * Last_update:2020��12��27������6:19:55
 */
public interface FireStrategy extends Serializable{
	void fire(Tank t);
}
