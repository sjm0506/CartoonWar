package com.neuedu.utll;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
* @ClassName: GetImageUtil
* @Description: ��ȡͼƬ�ù�����
* @author neuedu_sjm
* @date 2019��8��17�� ����4:02:45
*
*/
public class GetImageUtil {
	
	// URL Uniform Resource Locator ͳһ��Դ��λ��  ���Զ�λ���������ϵ���Դ
	// ��ȡͼƬ�ķ���                           ��ͼƬ��ַ
	public static Image getImage(String imgName){
		// ����
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


