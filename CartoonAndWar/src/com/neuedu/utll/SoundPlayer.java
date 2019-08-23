package com.neuedu.utll;

import java.io.InputStream;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

/**
* @ClassName: SoundPlayer
* @Description: �����ű�������
* @author neuedu_sjm
* @date 2019��8��22�� ����6:30:42
*
*/
public class SoundPlayer extends Thread{

	private String mp3Name;
	
	private void SoundPlayer() {
		

	}
	public SoundPlayer(String mp3Name) {
		this.mp3Name = mp3Name;
	}
	
	@Override
	public void run() {
		for(;;) {
			InputStream resourceAsStream = SoundPlayer.class.getClassLoader().getResourceAsStream(mp3Name);
			try {
				AdvancedPlayer advancedPlayer = new AdvancedPlayer(resourceAsStream);
				advancedPlayer.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
			}
		}
	}
}
