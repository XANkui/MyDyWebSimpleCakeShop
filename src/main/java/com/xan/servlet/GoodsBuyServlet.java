package com.xan.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Goods;
import com.xan.model.Order;
import com.xan.service.GoodsService;

/**
 * Servlet implementation class GoodsBuyServlet
 */
@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
	
	private GoodsService gService = new GoodsService();


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String[]> map = request.getParameterMap();
//		for(String key : map.keySet()) {
//			
//			System.out.println(key+":"+map.get(key));
//		}
		
		Order o = null;
		if(request.getSession().getAttribute("order") != null) {
			o=(Order)request.getSession().getAttribute("order");
		}else {
			o = new Order();
			request.getSession().setAttribute("order", o);
		}
		
		
		System.out.println(request.getParameter("goodsid"));
		
		int goodsId = Integer.parseInt(request.getParameter("goodsid"));
		Goods goods = gService.getById(goodsId);
		if(goods.getStock()>0) {
			o.AddGoods(goods);
			response.getWriter().print("ok");
		}else {
			response.getWriter().print("fail");
		}
	}

}
