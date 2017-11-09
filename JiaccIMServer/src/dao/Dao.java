package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.LoginBean;

public class Dao {
	public LoginBean query(String sql,String username)throws SQLException{
		DBHelper helper=new DBHelper(sql);
		helper.pst.setString(1, username);
		ResultSet rs=helper.pst.executeQuery();
		LoginBean bean=new LoginBean();
		while(rs.next()){
			bean.username=rs.getString(1);
			bean.password=rs.getString(2);
		}
		return bean;
	}
	
	public int insert(String sql,LoginBean loginbean) throws SQLException{
		DBHelper helper=new DBHelper(sql);
		helper.pst.setString(1, loginbean.username);
		helper.pst.setString(2, loginbean.password);
		int code=helper.pst.executeUpdate();
		helper.close();
		return code;
	}
}
