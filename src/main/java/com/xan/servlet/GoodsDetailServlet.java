package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Goods;
import com.xan.service.GoodsService;

/**
 * Servlet implementation class GoodsDetailServlet
 */
@WebServlet("/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		
		int id = Integer.parseInt(request.getParameter("id"));
		Goods g = gService.getById(id);
		request.setAttribute("g", g);
		request.getRequestDispatcher("goods_detail.jsp").forward(request, response);
	}

	

}
