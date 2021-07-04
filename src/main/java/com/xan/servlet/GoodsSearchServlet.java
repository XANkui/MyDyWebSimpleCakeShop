package com.xan.servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Page;
import com.xan.service.GoodsService;

/**
 * Servlet implementation class GoodsSearchServlet
 */
@WebServlet("/goods_search")
public class GoodsSearchServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyWord= request.getParameter("keyWord");
		int pageNo =1;
		if(request.getParameter("pageNo")!=null) {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}
		Page p =gService.getSearchGoodsPage(keyWord, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("keyWord", URLEncoder.encode(keyWord,"utf-8"));
		request.getRequestDispatcher("/goods_search.jsp").forward(request, response);
	}

	

}
