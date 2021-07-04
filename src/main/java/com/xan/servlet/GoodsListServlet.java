package com.xan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Goods;
import com.xan.model.Page;
import com.xan.model.Type;
import com.xan.service.GoodsService;
import com.xan.service.TypeService;

/**
 * Servlet implementation class GoodsListServlet
 */
@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {

	private GoodsService gService = new GoodsService();
    private TypeService tService = new   TypeService();


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id =0;
		if(request.getParameter("id")!=null) {
			
			id = Integer.parseInt(request.getParameter("id"));
		}
//		List<Goods> list = gService.selectGoods(id, 1, 8);
//		request.setAttribute("list", list);
		
		int pageNo=1;
		if(request.getParameter("pageNo")!=null) {
			pageNo = Integer.parseInt( request.getParameter("pageNo"));
		}
		
		Page p = gService.getGoodsPage(id, pageNo);
		request.setAttribute("p", p);
		request.setAttribute("id", id);

		Type t=null;
		if(id!=0) {
			t=tService.select(id);
		}
		request.setAttribute("t", t);
		
		request.getRequestDispatcher("/goodslist.jsp").forward(request, response);
	}

	

}
