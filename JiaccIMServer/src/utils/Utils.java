package utils;

public class Utils {
	public static boolean isNullOrEmpty(String str){
		if(str==null)return true;
		if("".equals(str))return true;
		return false;
	}
}
