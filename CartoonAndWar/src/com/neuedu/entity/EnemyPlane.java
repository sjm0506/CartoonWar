package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.utll.GetImageUtil;

/**
* @ClassName: EnemyPlane
* @Description: �о��ɻ�
* @author neuedu_sjm
* @date 2019��8��20�� ����11:35:45
*
*/
public class EnemyPlane extends Plane implements ActionAble{

	private Integer enemyType;
	
	private Integer speed;
	
	private GameClient gc;
	
	
	
	
	public static Image[] imgs1 = {
			
	    GetImageUtil.getImage("enemy/01.png"),
	    GetImageUtil.getImage("enemy/02.png"),
	    GetImageUtil.getImage("enemy/03.png"),
	    GetImageUtil.getImage("enemy/04.png"),
	    GetImageUtil.getImage("enemy/05.png"),
	    GetImageUtil.getImage("enemy/06.png"),
			
	};
//	public static Image[] imgs2 = {
//			
//		    GetImageUtil.getImage("enemy/BOSS_01.png"),
//		    GetImageUtil.getImage("enemy/BOSS_02.png"),
//		    GetImageUtil.getImage("enemy/BOSS_03.png"),
//		    GetImageUtil.getImage("enemy/BOSS_04.png"),
//		    GetImageUtil.getImage("enemy/BOSS_05.png"),
//				
//		};
			
	public EnemyPlane() {
		
	}
	public EnemyPlane(int x,int y,int enemyType,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.enemyType = enemyType;
		this.speed = 1;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
		public void move() {
		y += speed;

	}
	int count = 0;
	@Override
	public void draw(Graphics g) {
		if(count > 5) {
			count = 0;
		}
	
       g.drawImage(imgs1[count++], x, y, null);
//       g.drawImage(imgs2[count++], x, y, null);
       move();
       
       if(random.nextInt(500)>490) {
    	   
    	   fire();
       }
      
	}
	
	// ��������ӵ���
	Random random = new Random();
	
	
	

	// �о�����
	public void fire() {
		Bullet bullet = new Bullet(x+imgs1[0].getWidth(null)/2, y, "bullet/Bullet3.png", gc,false);
		gc.bullets.add(bullet);
	}
	// ��ȡ���ӵ��ľ���
	public Rectangle getRec(){
		return new Rectangle(x, y, this.imgs1[0].getWidth(null), this.imgs1[0].getHeight(null));
			
	}
		
	
	
	
	
}		
	
	
	
	
	
	
	
	
	
