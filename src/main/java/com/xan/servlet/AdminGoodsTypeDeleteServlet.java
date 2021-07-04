package com.xan.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xan.service.TypeService;

/**
 * Servlet implementation class AdminGoodsTypeDeleteServlet
 */
@WebServlet("/admin/goodstype_delete")
public class AdminGoodsTypeDeleteServlet extends HttpServlet {
	
	TypeService tService = new TypeService();
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean isSuccess = tService.delete(id);
		if(isSuccess) {
			request.setAttribute("msg", "删除类目："+id+" 成功");
		}else {
			request.setAttribute("failMsg", "删除类目："+id+" 失败,请事先删掉关联的商品");
		}
		
		request.getRequestDispatcher("/admin/goodstype_list").forward(request, response);
	}

	
}
