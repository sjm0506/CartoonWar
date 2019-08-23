package com.neuedu.utll;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: 获取图片得工具类
* @author neuedu_sjm
* @date 2019年8月17日 下午4:02:45
*
*/
public class GetImageUtil {
	
	// URL Uniform Resource Locator 统一资源定位符  可以定位到互联网上的资源
	// 获取图片的方法                           传图片地址
	public static Image getImage(String imgName){
		// 反射
		URL resource = GetImageUtil.class.getClassLoader().getResource("com/neuedu/img/"+imgName);
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bufferedImage;
	}
}


