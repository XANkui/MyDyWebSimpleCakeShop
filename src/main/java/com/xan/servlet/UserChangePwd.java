package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.User;
import com.xan.service.UserService;

/**
 * Servlet implementation class UserChangePwd
 */
@WebServlet("/user_changepwd")
public class UserChangePwd extends HttpServlet {
	
	private UserService uService = new UserService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");
		
		User u = (User)request.getSession().getAttribute("user");
		if(password.equals(u.getPassword())) {
			u.setPassword(newPassword);
			uService.updateUserPwd(u);
			request.setAttribute("msg", "修改成功");
			
		}else {
			
			request.setAttribute("failMsg", "密码修改失败，原密码错误");
			
		}
		
		request.getRequestDispatcher("/user_center.jsp").forward(request, response);
	}

}
