package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.neuedu.action.ActionAble;
import com.neuedu.constant.Constant;
import com.neuedu.utll.GetImageUtil;

/**
* @ClassName: Prop
* @Description:������
* @author neuedu_sjm
* @date 2019��8��21�� ����7:37:40
*
*/
public class Prop extends GameObject implements ActionAble{


	public Prop() {
		
	}
	private int speed;
	
	public Prop(int x, int y,String imgName) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImage(imgName);
		this.speed = 4;
	}
	
	
	
	
	// theta �Ƕ�
	private double theta = Math.PI/4;
	@Override
	public void move() {
		x += speed*Math.cos(theta);
		y += speed*Math.sin(theta);
		if(x<0 || x>Constant.GAME_WIDTH-img.getWidth(null)) {
			theta = Math.PI-theta;	
		}if(y<35 || y>Constant.GAME_HEIGHT-img.getHeight(null)) {
			theta = -theta;
		}
			
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
	}
	// �õ���ǰ���ߴ��ڵľ���
	public Rectangle getRect() {
		return new Rectangle(x,y,this.img.getWidth(null),this.img.getHeight(null));
	}
	
	
	

}
