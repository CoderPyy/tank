package com.pyy.tank.collider;

import java.io.Serializable;

import com.pyy.tank.GameObject;

/**
  *   ��ײ��
 * @author PeiYY
 * Last_update:2021��1��20������10:32:21
 */
public interface Collider extends Serializable{
	Boolean collide(GameObject o1,GameObject o2);

}
