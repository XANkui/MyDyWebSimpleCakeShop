package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.service.GoodsService;

/**
 * Servlet implementation class AdminGoodsRecommendServlet
 */
@WebServlet("/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String method = request.getParameter("method");
		int typeTarget = Integer.parseInt(request.getParameter("typeTarget"));
		if(method.equals("add")) {
			
			gService.addRecommend(id, typeTarget);
		}else {
			gService.removeRecommend(id, typeTarget);
		}
		
		// 仍然跳转到当前，会铭记 传过来的参数 （传给 /admin/goods_recommend 的参数）
		request.getRequestDispatcher("/admin/goods_list").forward(request, response);
	}

	

}
