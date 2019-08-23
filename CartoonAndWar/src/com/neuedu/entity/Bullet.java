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
* @Description: 子弹类
* @author neuedu_sjm
* @date 2019年8月19日 下午6:57:22
*
*/                    // 继承      实现画点方法 接口
public class Bullet extends GameObject implements ActionAble{
	
	// 单词播放音乐的对象
	SinglePlay singlePlay = new SinglePlay();
	
	// 创建速度属性
	private Integer speed;
	
	//拿到客户端
	public GameClient gc;
	
	// 子弹类型
	public boolean isGood;
	
	// 无参
	public Bullet() {
		
	}
	// 有参
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
		// 子弹移动
		if(isGood) {
			y -= speed;	
		}else {
			y += speed;
		}	
	}
	// 画子弹的方法
	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
		move();
		outOfBounds();
			
		
	}
	// 子弹越界销毁
	public void outOfBounds() {
		if(y>Constant.GAME_HEIGHT|| y < 0) {
			gc.bullets.remove(this); 
		}
	}
	//随机生成道具
	Random random = new Random(); 
	
	// 子弹打一个怪物
	public boolean hitMonster(Plane plane) {
		Boom boom = new Boom(plane.x, plane.y, gc);
		if(this.getRec().intersects(plane.getRec())&&this.isGood != plane.isGood) 
		{
			if(plane.isGood) {
				
				plane.blood -=10;
				if(plane.blood == 0) {
					// 销毁自身
					gc.planes.remove(plane);
//					boom.setLive(false);
				}	
				// 移除子弹
				gc.bullets.remove(this);
			} else {
				singlePlay.play("com/neuedu/sounds/SOUND_BOSS_DEATH_02.mp3");
				if(plane instanceof Boss) {
					gc.bosss.remove(plane);
					// 移除子弹
					gc.bullets.remove(this);
				}
				else {
					// 移除打中的敌人
					gc.enemys.remove(plane);
					// 移除子弹
					gc.bullets.remove(this);
					// 随机生成一个道具出来
					if(random.nextInt(500)>200) {
						if(plane instanceof EnemyPlane) {
							// 把道具放中间
							EnemyPlane enemyPlane = (EnemyPlane)plane;
							Prop prop = new Prop(plane.x+enemyPlane.imgs1[0].getHeight(null)/2, plane.y+enemyPlane.imgs1[0].getHeight(null)/2, "prop/Prop1.png");
							gc.props.add(prop);
						}
					}
				}
            }
			// 添加爆炸
			gc.booms.add(boom);
			return true;
		}
		return false;
	}
	// 子弹打多个怪物
	public boolean hitMonsters(List<Plane>monsters) {
		for(int i=0;i<monsters.size();i++) {
			if(hitMonster(monsters.get(i))) {
				return true;  
			}
		}
		return false;
	}
	
	// 获取到子弹的矩形
	public Rectangle getRec()
	{
		return new Rectangle(x, y, this.img.getWidth(null), this.img.getHeight(null));
		
	}
	
}
