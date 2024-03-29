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
* @Description: 创建一个飞机类
* @author neuedu_sjm
* @date 2019年8月19日 下午1:24:08
*
*/
public class Plane extends GameObject implements ActionAble{
 
	SinglePlay play = new SinglePlay();
	// 速度
	private int speed;
	// 方法布尔变量
	private boolean left,up,right,down;
	
	// 客户端拿过来
	public GameClient gc;
	
	// 判断是否是我军还是敌军
	public boolean isGood;
	
	// 添加飞机子弹等级变量
	public int Bulletlevel = 1;
	
	// 添加血值条
	public int blood;
	
	
	// 无参构造器
	public Plane() {
		
	}// 有参构造器
	public Plane(int x,int y,String imgName,GameClient gc,boolean isGood) {
		this.x = x;
		this.y = y;
		this.img = GetImageUtil.getImage(imgName);
		this.speed = 20;
		this.gc = gc;
		this.isGood = isGood;
		this.blood = 1000;
	}
	// 移动方法
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
		outOfBound();  //调用边界
	}
	// 画一架飞机出来
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		g.drawString("当前血量："+blood, 20, 160);
	}
	// 处理飞机的边界问题
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
	
	// 键盘摁下
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
		case  KeyEvent.VK_M: // 摁下M 发射子弹
			fire();
			break;
		default:
			break;
		}
	}
	// 键盘释放
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
	// 飞机开火发射子弹
	public void fire() {
		play.play("com/neuedu/sounds/SOUND_BOSS_WILD_01.mp3");
		// 调整子弹与飞机的距离*
		Bullet b = new Bullet(x+this.img.getWidth(null)/2-20, y+this.img.getHeight(null)-280, "bulletlevel/B_L0"+Bulletlevel+".png",gc,true);
		gc.bullets.add(b);
	}
	
	
	
	
	// 获取到当前的矩形
			public Rectangle getRec()
			{
				return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
				
			}
	
	// 检测我方角色碰撞到道具
	public void containItem(Prop prop) {
		if(this.getRec().intersects(prop.getRect())) {
			// 移除道具
			gc.props.remove(prop);
			if(Bulletlevel>7) {
				Bulletlevel = 8;
				return;
			}
			// 更改子弹等级
			this.Bulletlevel +=1;
			
		}
	}
	//检测我方角色碰撞到多个道具
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
