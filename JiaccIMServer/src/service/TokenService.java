package service;

import java.util.ArrayList;
import java.util.List;

import utils.MD5Utils;

public class TokenService {
	private List<String> tokens=new ArrayList<String>();

	private void createToken(String username,String password){
		StringBuilder sb=new StringBuilder();
		sb.append(username).append("|")
		  .append(password).append("|");
    	 tokens.add(MD5Utils.EncoderByMd5(sb.toString())+","+System.currentTimeMillis());
	}
	
	private int checkToken(String str){
		int resultCode=0;
		
		return resultCode;
	}
	
	private void deleteToke(String token){
		tokens.remove(token);
	}
	
}
