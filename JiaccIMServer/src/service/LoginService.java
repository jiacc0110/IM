package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.synth.SynthSeparatorUI;

import bean.LoginBean;
import dao.DBHelper;
import dao.Dao;
import servlet.LoginCallback;
import utils.Constant;
import utils.Utils;


/**
 * 
 * 该类的目的是将数据库查询的结果转化成实体类
 * @author jiacc
 */
public class LoginService {
	/**
	 * 注册
	 */
	public int register(HttpServletRequest req){
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		if(Utils.isNullOrEmpty(username)) return Constant.RESULT_FAILED;
		if(Utils.isNullOrEmpty(password)) return Constant.RESULT_FAILED;
		Dao dao=new Dao();
		String sql="select * from accounts where username=?";
		LoginBean bean=null;
		try{
			bean=dao.query(sql,username);
		}catch(SQLException e){
			e.printStackTrace();
		}
		if(bean==null||Utils.isNullOrEmpty(bean.username)){
			bean=new LoginBean();
			bean.username=username;
			bean.password=password;
		    String sql1="insert into accounts (username,password) values (?,?)";
			try {
				
				System.out.print("-=-----1------");
				int i=dao.insert(sql1,bean);
				System.out.print("------2------："+i);
				return i;
			} catch (SQLException e) {
				System.out.print("------333333333--");
				e.printStackTrace();
				return Constant.RESULT_FAILED;
			}
		}else{
			return Constant.RESULT_USERNAME_EXIST;
		}
		
	}
	/**
	 * 登录
	 */
	public int login(HttpServletRequest req,LoginCallback callback){
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		if(Utils.isNullOrEmpty(username)) return Constant.RESULT_FAILED;
		if(Utils.isNullOrEmpty(password)) return Constant.RESULT_FAILED;
		Dao dao=new Dao();
		String sql="select * from accounts where username=?";
		LoginBean bean=null;
		try{
		bean=dao.query(sql,username);
		}catch(SQLException e){
			e.printStackTrace();
			callback.onFinish(Constant.RESULT_FAILED, "RESULT_FAILED");
			return Constant.RESULT_FAILED;
		}
		if(bean==null||Utils.isNullOrEmpty(bean.username)){
			System.out.println("RESULT_USERNAME_NOT_EXIST");
			callback.onFinish(Constant.RESULT_USERNAME_NOT_EXIST, "RESULT_USERNAME_NOT_EXIST");
			return Constant.RESULT_USERNAME_NOT_EXIST;
		}
		if(password.equals(bean.password)){
			System.out.println("RESULT_SUCESS");
			callback.onFinish(Constant.RESULT_SUCESS,username);
			return Constant.RESULT_SUCESS;
		}else{
			System.out.println("RESULT_PASSWORD_ERROR");
			callback.onFinish(Constant.RESULT_PASSWORD_ERROR, "RESULT_PASSWORD_ERROR");
			return Constant.RESULT_PASSWORD_ERROR;
		}
	}
	
	/**
	 * 查询
	 */
	
	/**
	 * 批量查询
	 */

}
