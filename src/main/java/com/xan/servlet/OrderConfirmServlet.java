package com.xan.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.xan.model.Order;
import com.xan.model.User;
import com.xan.service.OrderService;

/**
 * Servlet implementation class OrderConfirmServlet
 */
@WebServlet("/order_confirm")
public class OrderConfirmServlet extends HttpServlet {
	

	private OrderService oService = new OrderService();

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Order o = (Order)request.getSession().getAttribute("order") ;
		try {
			BeanUtils.copyProperties(o, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		o.setDatetime(LocalDateTime.now());
		o.setStatus(2); //默认设置已经支付
		o.setUser((User)request.getSession().getAttribute("user"));
		oService.addOrder(o);
		
		request.getSession().removeAttribute("order");
		
		request.setAttribute("msg", "订单支付成功");
		request.getRequestDispatcher("/order_success.jsp").forward(request, response);;
	}

}
