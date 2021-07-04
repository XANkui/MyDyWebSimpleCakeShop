package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Page;
import com.xan.service.GoodsService;

/**
 * Servlet implementation class GoodsReommendList
 */
@WebServlet("/goodsrecommend_list")
public class GoodsReommendList extends HttpServlet {
	
	private GoodsService gService = new GoodsService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int type =0;
		if(request.getParameter("type")!=null) {
			
			type = Integer.parseInt(request.getParameter("type"));
		}

		int pageNo=1;
		if(request.getParameter("pageNo")!=null) {
			pageNo = Integer.parseInt( request.getParameter("pageNo"));
		}
		
		Page p = gService.getGoodsRecommendPage(type, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("t", type);
		request.getRequestDispatcher("goodsrecommendlist.jsp").forward(request, response);
	}

	

}
