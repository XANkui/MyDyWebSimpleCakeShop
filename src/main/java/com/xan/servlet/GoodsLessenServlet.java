package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.model.Order;

/**
 * Servlet implementation class GoodsLessenServlet
 */
@WebServlet("/goods_lessen")
public class GoodsLessenServlet extends HttpServlet {
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = (Order) request.getSession().getAttribute("order");
		int goodsId = Integer.parseInt(request.getParameter("goodsid"));
		o.lessen(goodsId);
		response.getWriter().print("ok");
	}

}
