package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.RepaintManager;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.constant.Constant;
import com.neuedu.utll.GetImageUtil;
import com.neuedu.utll.SinglePlay;

/**
* @ClassName: Plane
* @Description: ����һ���ɻ���
* @author neuedu_sjm
* @date 2019��8��19�� ����1:24:08
*
*/
public class Plane extends GameObject implements ActionAble{
 
	SinglePlay play = new SinglePlay();
	// �ٶ�
	private int speed;
	// ������������
	private boolean left,up,right,down;
	
	// �ͻ����ù���
	public GameClient gc;
	
	// �ж��Ƿ����Ҿ����ǵо�
	public boolean isGood;
	
	// ��ӷɻ��ӵ��ȼ�����
	public int Bulletlevel = 1;
	
	// ���Ѫֵ��
	public int blood;
	
	
	// �޲ι�����
	public Plane() {
		
	}// �вι�����
	public Plane(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImage(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 1000;
	}
	// �ƶ�����
	@Override
	public void move() {
		if(left) {
			x -= speed;
		}
		if(up){
			y-= speed;
		}
		if(right) {
			x += speed;
		}
		if(down) {
			y += speed;
		}	
		outOfBound();  //���ñ߽�
	}
	// ��һ�ܷɻ�����
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("��ǰѪ����"+blood, 20, 160);
	}
	// ����ɻ��ı߽�����
	public void outOfBound() {
	    if(y<=30) {
	    	y = 30;
	    }
	    if(x<=10) { 
	    	x=10;
	    }
	    if(x>Constant.GAME_WIDTH-img.getWidth(null)) {
	    	x=Constant.GAME_WIDTH-img.getWidth(null);
	    }
	    if(y>=Constant.GAME_HEIGHT-img.getHeight(null)) {
	    	y=Constant.GAME_HEIGHT-img.getHeight(null);
	    }
		
	}
	
	// ��������
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case  KeyEvent.VK_A:
			left = true;
			break;
		case  KeyEvent.VK_W:
			up = true;
			break;
		case  KeyEvent.VK_D:
			right = true;
			break;
		case  KeyEvent.VK_S:
			down = true;
			break;
		case  KeyEvent.VK_M: // ����M �����ӵ�
			fire();
			break;
		default:
			break;
		}
	}
	// �����ͷ�
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case  KeyEvent.VK_A:
			left = false;
			break;
		case  KeyEvent.VK_W:
			up = false;
			break;
		case  KeyEvent.VK_D:
			right = false;
			break;
		case  KeyEvent.VK_S:
			down = false;
			break;
		default:
			break;
		}
	}
	// �ɻ��������ӵ�
	public void fire() {
		play.play("com/neuedu/sounds/SOUND_BOSS_WILD_01.mp3");
		// �����ӵ���ɻ��ľ���*
		Bullet b = new Bullet(x+this.img.getWidth(null)/2-20, y+this.img.getHeight(null)-280, "bulletlevel/B_L0"+Bulletlevel+".png",gc,true);
		gc.bullets.add(b);
	}
	
	
	
	
	// ��ȡ����ǰ�ľ���
			public Rectangle getRec()
			{
				return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
				
			}
	
	// ����ҷ���ɫ��ײ������
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRect())) {
			// �Ƴ�����
			gc.props.remove(prop);
			if(Bulletlevel>7) {
				Bulletlevel = 8;
				return;
			}
			// �����ӵ��ȼ�
			this.Bulletlevel +=1;
			
		}
	}
	//����ҷ���ɫ��ײ���������
	public void containItems(List<Prop>props) {
		if(props==null) {
			return;
		}else {
			for(int i=0;i<props.size();i++) {
				containItem(props.get(i));
			}
		}
	}
	
	
}
