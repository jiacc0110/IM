package utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Utils {

	public static String EncoderByMd5(String str) {
		String newstr=null;
		try{
		//确定计算方法
		MessageDigest md5=MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		//加密后的字符串
		 newstr=base64en.encode(md5.digest(str.getBytes("utf-8")));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return newstr;
	}

	public static boolean checkpassword(String newStr,String oldStr) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(EncoderByMd5(newStr).equals(oldStr))
			return true;
		else
			return false;
	}
}
