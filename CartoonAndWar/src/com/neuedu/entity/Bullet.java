package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.sounds.Boss;
import com.neuedu.utll.GetImageUtil;
import com.neuedu.utll.SinglePlay;

/**
* @ClassName: Bullet
* @Description: �ӵ���
* @author neuedu_sjm
* @date 2019��8��19�� ����6:57:22
*
*/                    // �̳�      ʵ�ֻ��㷽�� �ӿ�
public class Bullet extends GameObject implements ActionAble{
	
	// ���ʲ������ֵĶ���
	SinglePlay singlePlay = new SinglePlay();
	
	// �����ٶ�����
	private Integer speed;
	
	//�õ��ͻ���
	public GameClient gc;
	
	// �ӵ�����
	public boolean isGood;
	
	// �޲�
	public Bullet() {
		
	}
	// �в�
	public Bullet(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImage(imgName);
		this.speed = 40;
		this.gc = gc;
		this.isGood = isGood;
	}
	@Override
	public void move() {
		// �ӵ��ƶ�
		if(isGood) {
			y -= speed;	
		}else {
			y += speed;
		}	
	}
	// ���ӵ��ķ���
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		outOfBounds();
			
		
	}
	// �ӵ�Խ������
	public void outOfBounds() {
		if(y>Constant.GAME_HEIGHT|| y < 0) {
			gc.bullets.remove(this); 
		}
	}
	//������ɵ���
	Random random = new Random(); 
	
	// �ӵ���һ������
	public boolean hitMonster(Plane plane) {
		Boom boom = new Boom(plane.x, plane.y, gc);
		if(this.getRec().intersects(plane.getRec())&&this.isGood != plane.isGood) 
		{
			if(plane.isGood) {
				
				plane.blood -=10;
				if(plane.blood == 0) {
					// ��������
					gc.planes.remove(plane);
//					boom.setLive(false);
				}	
				// �Ƴ��ӵ�
				gc.bullets.remove(this);
			} else {
				singlePlay.play("com/neuedu/sounds/SOUND_BOSS_DEATH_02.mp3");
				if(plane instanceof Boss) {
					gc.bosss.remove(plane);
					// �Ƴ��ӵ�
					gc.bullets.remove(this);
				}
				else {
					// �Ƴ����еĵ���
					gc.enemys.remove(plane);
					// �Ƴ��ӵ�
					gc.bullets.remove(this);
					// �������һ�����߳���
					if(random.nextInt(500)>200) {
						if(plane instanceof EnemyPlane) {
							// �ѵ��߷��м�
							EnemyPlane enemyPlane = (EnemyPlane)plane;
							Prop prop = new Prop(plane.x+enemyPlane.imgs1[0].getHeight(null)/2, plane.y+enemyPlane.imgs1[0].getHeight(null)/2, "prop/Prop1.png");
							gc.props.add(prop);
						}
					}
				}
            }
			// ��ӱ�ը
			gc.booms.add(boom);
			return true;
		}
		return false;
	}
	// �ӵ���������
	public boolean hitMonsters(List<Plane>monsters) {
		for(int i=0;i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;  
			}
		}
		return false;
	}
	
	// ��ȡ���ӵ��ľ���
	public Rectangle getRec()
	{
		return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
		
	}
	
}
