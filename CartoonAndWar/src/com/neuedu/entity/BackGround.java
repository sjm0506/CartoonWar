package com.neuedu.entity;

import java.awt.Graphics;

import com.neuedu.action.ActionAble;
import com.neuedu.utll.GetImageUtil;

/**
* @ClassName: BackGround
* @Description: 背景类
* @author neuedu_sjm
* @date 2019年8月20日 上午8:46:24
*
*/
public class BackGround  extends GameObject implements ActionAble{

	private Integer speed;

	public BackGround() {
		// TODO Auto-generated constructor stub
	}
	
	public BackGround(int x,int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImage(imgName);
		this.speed = 2;
	}
	
	
	
	@Override
	public void move() {
		y -= speed;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}

	
	
	
	
	
	
	
	
	
	
}
