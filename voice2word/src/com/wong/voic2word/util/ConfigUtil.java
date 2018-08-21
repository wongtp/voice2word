package com.wong.voic2word.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author 艾小天 
 * @email :wongtp@outlook.com
 * @date ：2017年12月12日 下午9:16:10
 */
public class ConfigUtil {
	
	private static Properties props = null;
	
	static {
		InputStream inputStream = null;
		try {
			props = new Properties();
			inputStream = new FileInputStream("config.properties");
			if(inputStream != null) {
				props.load(inputStream);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getProperty(String key) {
		return getProperty(key, "");
	}
	
	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

}
