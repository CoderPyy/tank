package com.pyy.tank.collider;

import com.pyy.tank.GameObject;

/**
  *   碰撞器
 * @author PeiYY
 * Last_update:2021年1月20日下午10:32:21
 */
public interface Collider {
	Boolean collide(GameObject o1,GameObject o2);

}
