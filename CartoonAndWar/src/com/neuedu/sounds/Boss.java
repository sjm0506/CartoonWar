package com.neuedu.sounds;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.entity.Plane;
import com.neuedu.utll.GetImageUtil;

/**
* @ClassName: Boss
* @Description: Boss类
* @author neuedu_sjm
* @date 2019年8月22日 下午8:47:36
*
*/
public class Boss extends Plane implements ActionAble{

	private boolean up = true;
	
	private int speed = 3;
	
	public Boss() {
	}
	public Boss(int x,int y,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.gc = gc;
		this.isGood = isGood;
		
	}
	
	// 动态初始化一个图片数组
	private static Image[] imgs = new Image[9];
    static
    {
    	for(int i = 0;i<imgs.length;i++) {
    		imgs[i] = GetImageUtil.getImage("boss/Boos"+(i+1)+".png");
    	}
    }
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count > 8) {
			count = 0;
		}
		g.drawImage(imgs[count++], x, y,null);
		move();
	}
	@Override
	public void move() {
		y += speed;
		
//
//		if(y> 500) {
//			if(up) {
//				y+= speed;
//			}if(!up) {
//				y-= speed;
//			}
//			x = 500;
//			if(x<Constant.GAME_HEIGHT-imgs[0].getHeight(null)) {
//				up = true;
//			}
//			if(x>30) {
//				up = false;
//			}
//		}	
	}
	// 获取Boss所在的矩形
	public Rectangle getRec()
	{
		return new Rectangle(x, y, imgs[0].getWidth(null), imgs[0].getHeight(null));
		
	}
	
	
}
