package com.xan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Type;
import com.xan.service.TypeService;

/**
 * Servlet implementation class AdminGoodsTypeListServlet
 */
@WebServlet("/admin/goodstype_list")
public class AdminGoodsTypeListServlet extends HttpServlet {
	TypeService tService = new TypeService();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Type> list = tService.selectAll();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/admin/goodstype_list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
