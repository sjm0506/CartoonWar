package com.neuedu.entity;

import java.awt.Graphics;
import java.awt.Image;

import com.neuedu.action.ActionAble;
import com.neuedu.client.GameClient;
import com.neuedu.utll.GetImageUtil;

/**
* @ClassName: Boom
* @Description: 爆炸类
* @author neuedu_sjm
* @date 2019年8月21日 上午9:59:49
*
*/
public class Boom extends GameObject implements ActionAble{
	
	private boolean isLive;
	private GameClient gc;
	
	public boolean isLive() {
		return isLive;
	}
	public void setLive(boolean isLive) {
		this.isLive = isLive;
	}
	private static Image[] boomImgs = 
		{
				GetImageUtil.getImage("boom/B01.png"),
				GetImageUtil.getImage("boom/B02.png"),
				GetImageUtil.getImage("boom/B03.png"),
				GetImageUtil.getImage("boom/B04.png"),
				GetImageUtil.getImage("boom/B05.png"),
		};
	// 动态初始化爆炸图
//	private static Image[] boomImgs = new Image[5];
//	static
//	for(int i=0;i<5;i++){
//		boomImgs[i] = GetImageUtil.getImage("boom/"+(i+1)+".png");
//	}
	
	
	public Boom() {
		
	}
	public Boom(int x,int y,GameClient gc) {
		this.x = x;
		this.y = y;
		this.isLive = true;
		this.gc = gc;
	}
	
	@Override
	public void move() {
		
		
	}
    int  count = 0;
	@Override
	public void draw(Graphics g) {
		// 如果爆炸true 执行
		if(isLive) {
			//画爆炸
			if(count>4) {
				// 爆炸移除
				gc.booms.remove(this);
				this.setLive(false);
				return;
			}
			g.drawImage(boomImgs[count++], x, y, null);
		}
	}
}
