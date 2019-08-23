package com.neuedu.client;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import com.neuedu.constant.Constant;
import com.neuedu.entity.BackGround;
import com.neuedu.entity.Boom;
import com.neuedu.entity.Bullet;
import com.neuedu.entity.EnemyPlane;
import com.neuedu.entity.Plane;
import com.neuedu.entity.Prop;
import com.neuedu.sounds.Boss;
import com.neuedu.utll.GetImageUtil;
import com.neuedu.utll.SoundPlayer;

/**
* @ClassName: GameClient
* @Description:游戏客户端
* @author neuedu_sjm
* @date 2019年8月19日 上午10:40:56
*
*/                            // 框架
public class GameClient extends Frame{
	// 创建一个Plane出来
//	Plane plane = new Plane(100,200,"plane/plane.png",this,true);
	
	// 创建一个我方角色集合
	public List<Plane> planes = new ArrayList<>();
	
	// 创建一个子弹的集合
    public List<Bullet>bullets = new ArrayList<>();
		
    // 创建一个背景图出来
    BackGround backImg = new BackGround(0, 0, "B_G1.png");
    
    //创建一个爆炸集合
    public List<Boom> booms = new ArrayList<>();
    
    // 创建敌方集合
    public List<Plane> enemys= new ArrayList<>();
    
    // 创建道具集合
    public List<Prop> props = new ArrayList<Prop>();
    
    // 创建一个Boss集合
    public List<Plane> bosss = new ArrayList<>();
    
	// 解决图片闪屏
    @Override
	public void update(Graphics g) {
	    Image backImg = createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        Graphics backg = backImg.getGraphics();
        Color color = backg.getColor();
        backg.setColor(color.WHITE);
        backg.fillRect(0, 0, Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
	    backg.setColor(color);
	    paint(backg);
	    g.drawImage(backImg, 0, 0, null);
		}
    Plane plane = null;
	// 生成客户端窗口的方法
	public void launchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sounds/Jamesketed.mp3");
        soundPlayer.start();
		
		
		// 设置窗口的大小
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// 设置标题
		this.setTitle("卡通大作战");
		// 设置是否显示
		this.setVisible(true);
//		Image image = GetImageUtil.getImage("com/neuedu/img/icon4.png");
//		this.setIconImage(image);
		
		// 禁止最大化
		this.setResizable(false);
		// 窗口居中      null 是相对于屏幕居中
		this.setLocationRelativeTo(null);
		// 关闭窗口  添加关闭监听器
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		plane = new Plane(100,200,"plane/plane.png",this,true);	
		// 往我方容器中添加自己
		 planes.add(plane);
		
		 // 添加鼠标监听
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//		        System.out.println("你点了一下鼠标");
//			}
//		});
		// 添加键盘监听
		this.addKeyListener(new KeyAdapter() {
			// 键盘摁下的情况下
			@Override              // KeyEvent类里包含了键盘上所有的键
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
		}
			// 键盘释放
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		// 启动线程
		new paintThread().start();
		
		
		
		// 在敌方容器中添加敌人
		EnemyPlane enemy1 = new EnemyPlane(30,50,1,this,false);
	    EnemyPlane enemy2 = new EnemyPlane(180,50,2,this,false);
	    EnemyPlane enemy3 = new EnemyPlane(330,50,3,this,false);
	    EnemyPlane enemy4 = new EnemyPlane(30,200,4,this,false);
	    EnemyPlane enemy5 = new EnemyPlane(180,200,5,this,false);
	    EnemyPlane enemy6 = new EnemyPlane(330,200,6,this,false);
	    
//		EnemyPlane enemy7 = new EnemyPlane(30,50,1,this,false);
//	    EnemyPlane enemy8 = new EnemyPlane(180,50,2,this,false);
//	    EnemyPlane enemy9 = new EnemyPlane(330,50,3,this,false);
//	    EnemyPlane enemy10 = new EnemyPlane(30,200,4,this,false);
//	    EnemyPlane enemy11= new EnemyPlane(180,200,5,this,false);
//	    EnemyPlane enemy12= new EnemyPlane(330,200,6,this,false);
		enemys.add(enemy1);
		enemys.add(enemy2);
		enemys.add(enemy3);
		enemys.add(enemy4);
		enemys.add(enemy5);
		enemys.add(enemy6);
		// 添加Boss
		bosss.add(new Boss(200, 10, this,false));// ****************
	}
	// 重写paint方法
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		// 画线
		g.drawLine(0, 180, 500, 180);
		for(int i=0;i<planes.size();i++ ) {
			Plane plane2 = planes.get(i);
			plane2.draw(g);
			plane2.containItems(props);
			
		}
		// 增强循环画出每个子弹
		// 增强for循环不能做任何操作
        for(int i=0;i<bullets.size();i++) {
        	Bullet bullet = bullets.get(i);
        	bullet.draw(g); 
        	// 打敌方
        	bullet.hitMonsters(enemys);
            // 打自己
        	bullet.hitMonsters(planes);
        	bullet.hitMonsters(bosss);
       
        }
        // 打印当前子弹的数量
        g.drawString("当前子弹的数量："+bullets.size(),20,60);
        g.drawString("当前怪物的数量："+enemys.size(), 20, 80);
        g.drawString("当前爆炸的数量："+enemys.size(), 20, 100);
        g.drawString("当前我方的数量："+planes.size(), 20, 120);
        g.drawString("当前道具的数量："+props.size(), 20, 140);
        // 循环画敌方
        for(int i=0;i<enemys.size();i++) {
        	enemys.get(i).draw(g);
        }
        // 循环爆炸
        for(int i =0;i<booms.size();i++) {
        	// 如果爆炸 活着
        	if(booms.get(i).isLive() == true) {
        		// 画
        		booms.get(i).draw(g);
        	}
        }
        // 循环画道具
        for(int i =0;i<props.size();i++) {
        	
        		props.get(i).draw(g);
        }
        if(enemys.size()==0) {
        	
        	// 循环Boss集合
            for(int i =0;i<bosss.size();i++) {
            	
            	bosss.get(i).draw(g);
        	}
        }
}
	// 内部类
	class paintThread extends Thread{
		
		@Override // 重写run方法
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
		}
	}
}
