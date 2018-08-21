package com.wong.voic2word.util;

/**
 * @author 黄小天  1853955116@qq.com
 * @date 2018年8月21日 上午10:49:34
 * 字符串工具类
 */
public class StringTool {
	
    /**
     * 判断是否有中文文字
     * @param c 
     * @return boolean
     */
    private static final boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {  
            return true;  
        }  
        return false;  
    }
    
    /**
     * 判断字符串中是否有中文文字
     * @param text
     * @return boolean
     */
    public static final boolean isChinese(String text) {  
        char[] ch = text.toCharArray();  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (isChinese(c)) {  
                return true;  
            }  
        }  
        return false;  
    }
    
    /**
	 * 判断字符串是否是空
	 * @param str
	 * @return boolean
	 */
	public static boolean isEmpty(String str) {
		if(str == null || "".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}
}
