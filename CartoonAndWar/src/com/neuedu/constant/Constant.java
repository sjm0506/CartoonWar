package com.neuedu.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
* @ClassName: Constant
* @Description: 常量类
* @author neuedu_sjm
* @date 2019年8月17日 下午3:16:17
*
*/
public class Constant {
	
	// 如何读取配置文件
	public static Properties prop = new Properties();
	static Integer Game_Width = null;
	static Integer Game_Height = null;
	static {
		InputStream inStream = Constant.class.getResourceAsStream("/gameConfiguration.properties");
		try {
			prop.load(inStream);
			Game_Width = Integer.parseInt(prop.getProperty("Game_Width"));
			Game_Height = Integer.parseInt(prop.getProperty("Game_Height"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 定义宽度
	public static final int GAME_WIDTH =Game_Width;
	// 定义高度
	public static final int GAME_HEIGHT = Game_Height;

	public static void main(String[] args) {
		System.out.println(Constant.Game_Width);
		System.out.println(Constant.Game_Height);
	}
}










