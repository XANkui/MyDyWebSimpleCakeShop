package com.xan.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.service.GoodsService;

/**
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取横幅的商品
		Map<String, Object> scrollGoods = gService.getScrollGoods();
		request.setAttribute("scrollGoods", scrollGoods);
		
		// 获取热销商品
		List<Map<String, Object>> hotList = gService.getHotGoodsList();
		request.setAttribute("hotList", hotList);
		
		// 获取新品商品
		List<Map<String, Object>> newList = gService.getNewGoodsList();
		request.setAttribute("newList", newList);

		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	

}
