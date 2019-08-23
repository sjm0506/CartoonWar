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
* @Description:��Ϸ�ͻ���
* @author neuedu_sjm
* @date 2019��8��19�� ����10:40:56
*
*/                            // ���
public class GameClient extends Frame{
	// ����һ��Plane����
//	Plane plane = new Plane(100,200,"plane/plane.png",this,true);
	
	// ����һ���ҷ���ɫ����
	public List<Plane> planes = new ArrayList<>();
	
	// ����һ���ӵ��ļ���
    public List<Bullet>bullets = new ArrayList<>();
		
    // ����һ������ͼ����
    BackGround backImg = new BackGround(0, 0, "B_G1.png");
    
    //����һ����ը����
    public List<Boom> booms = new ArrayList<>();
    
    // �����з�����
    public List<Plane> enemys= new ArrayList<>();
    
    // �������߼���
    public List<Prop> props = new ArrayList<Prop>();
    
    // ����һ��Boss����
    public List<Plane> bosss = new ArrayList<>();
    
	// ���ͼƬ����
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
	// ���ɿͻ��˴��ڵķ���
	public void launchFrame() {
		SoundPlayer soundPlayer = new SoundPlayer("com/neuedu/sounds/Jamesketed.mp3");
        soundPlayer.start();
		
		
		// ���ô��ڵĴ�С
		this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		// ���ñ���
		this.setTitle("��ͨ����ս");
		// �����Ƿ���ʾ
		this.setVisible(true);
//		Image image = GetImageUtil.getImage("com/neuedu/img/icon4.png");
//		this.setIconImage(image);
		
		// ��ֹ���
		this.setResizable(false);
		// ���ھ���      null ���������Ļ����
		this.setLocationRelativeTo(null);
		// �رմ���  ��ӹرռ�����
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		plane = new Plane(100,200,"plane/plane.png",this,true);	
		// ���ҷ�����������Լ�
		 planes.add(plane);
		
		 // ���������
//		this.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//		        System.out.println("�����һ�����");
//			}
//		});
		// ��Ӽ��̼���
		this.addKeyListener(new KeyAdapter() {
			// �������µ������
			@Override              // KeyEvent��������˼��������еļ�
			public void keyPressed(KeyEvent e) {
				plane.keyPressed(e);
		}
			// �����ͷ�
			@Override
			public void keyReleased(KeyEvent e) {
				plane.keyReleased(e);
			}
		});
		// �����߳�
		new paintThread().start();
		
		
		
		// �ڵз���������ӵ���
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
		// ���Boss
		bosss.add(new Boss(200, 10, this,false));// ****************
	}
	// ��дpaint����
	@Override
	public void paint(Graphics g) {
		backImg.draw(g);
		// ����
		g.drawLine(0, 180, 500, 180);
		for(int i=0;i<planes.size();i++ ) {
			Plane plane2 = planes.get(i);
			plane2.draw(g);
			plane2.containItems(props);
			
		}
		// ��ǿѭ������ÿ���ӵ�
		// ��ǿforѭ���������κβ���
        for(int i=0;i<bullets.size();i++) {
        	Bullet bullet = bullets.get(i);
        	bullet.draw(g); 
        	// ��з�
        	bullet.hitMonsters(enemys);
            // ���Լ�
        	bullet.hitMonsters(planes);
        	bullet.hitMonsters(bosss);
       
        }
        // ��ӡ��ǰ�ӵ�������
        g.drawString("��ǰ�ӵ���������"+bullets.size(),20,60);
        g.drawString("��ǰ�����������"+enemys.size(), 20, 80);
        g.drawString("��ǰ��ը��������"+enemys.size(), 20, 100);
        g.drawString("��ǰ�ҷ���������"+planes.size(), 20, 120);
        g.drawString("��ǰ���ߵ�������"+props.size(), 20, 140);
        // ѭ�����з�
        for(int i=0;i<enemys.size();i++) {
        	enemys.get(i).draw(g);
        }
        // ѭ����ը
        for(int i =0;i<booms.size();i++) {
        	// �����ը ����
        	if(booms.get(i).isLive() == true) {
        		// ��
        		booms.get(i).draw(g);
        	}
        }
        // ѭ��������
        for(int i =0;i<props.size();i++) {
        	
        		props.get(i).draw(g);
        }
        if(enemys.size()==0) {
        	
        	// ѭ��Boss����
            for(int i =0;i<bosss.size();i++) {
            	
            	bosss.get(i).draw(g);
        	}
        }
}
	// �ڲ���
	class paintThread extends Thread{
		
		@Override // ��дrun����
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
