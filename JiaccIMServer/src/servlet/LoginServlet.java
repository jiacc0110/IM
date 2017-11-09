package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.LoginService;
import utils.Constant;

@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("----------------------");
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		final PrintWriter pw = resp.getWriter();
		
		String method=req.getParameter("method");
		LoginService service=new LoginService();
		if("register".equals(method)){
			int i=service.register(req);
			pw.print("register result:"+i);
		}else if("login".equals(method)){
			service.login(req,new LoginCallback() {
				
				@Override
				public void onFinish(int retCode, String msg) {
					switch (retCode) {
					case Constant.RESULT_SUCESS:
						pw.println("{retCode:"+retCode+",msg:\""+msg+"\"}");
						break;

					case Constant.RESULT_PASSWORD_ERROR:
						pw.println("{retCode:"+retCode+",msg:\""+msg+"\"}");
						break;
					case Constant.RESULT_USERNAME_EXIST:
						pw.println("{retCode:"+retCode+",msg:\""+msg+"\"}");
						break;
						
					case Constant.RESULT_FAILED:
						pw.println("{retCode:"+retCode+",msg:\""+msg+"\"}");
						break;
					default:
						pw.println("{retCode:"+retCode+",msg:\""+msg+"\"}");
						break;
					}
				}
			});
			
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
}
