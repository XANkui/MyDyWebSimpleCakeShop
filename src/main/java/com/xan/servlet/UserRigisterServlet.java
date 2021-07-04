package com.xan.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.xan.model.User;
import com.xan.service.UserService;

/**
 * Servlet implementation class UserRigisterServlet
 */
@WebServlet("/user_register")
public class UserRigisterServlet extends HttpServlet {
	private UserService uService = new UserService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.copyProperties(user,request.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(user);
		boolean isSuccess = uService.register(user);
		if(isSuccess) {
			request.setAttribute("msg", "注册成功，请登录");
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
		}else {
			request.setAttribute("msg", "用户名或者密邮箱重复，请重新注册");
			request.getRequestDispatcher("user_register.jsp").forward(request, response);
		}
		
	}

	

}
