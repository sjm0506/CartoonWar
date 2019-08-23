package com.neuedu.action;

import java.awt.Graphics;

/**
* @ClassName: ActionAble
* @Description: 行为接口
* @author neuedu_sjm
* @date 2019年8月19日 下午1:00:23
*
*/
public interface ActionAble {
    // 移动的方法
	void move();
	// 画方法     画笔
	void draw(Graphics g);
	
	
	
}
