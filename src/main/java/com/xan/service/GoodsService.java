package com.xan.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.xan.dao.GoodsDao;
import com.xan.model.Goods;
import com.xan.model.Page;

public class GoodsService {
	private GoodsDao gDao = new GoodsDao();
	
	public List<Map<String, Object>> getHotGoodsList(){
		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list ;
	}
	
	public List<Map<String, Object>> getNewGoodsList(){
		List<Map<String, Object>> list = null;
		try {
			list = gDao.getGoodsList(3);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list ;
	}
	
	public Map<String, Object> getScrollGoods(){
		Map<String, Object> goods = null;
		try {
			goods = gDao.getScrollGoods();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods ;
	}
	
//	public List<Goods> selectGoods(int typeId,int pageNo, int pageSize) {
//		List<Goods> list = null;
//		try {
//			 list = gDao.selectGoods(typeId,pageNo,pageSize);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		return list;
//	}
	
	public Page getGoodsPage(int typeId,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int totalNumber=0;
		try {
			totalNumber = gDao.getGoodsCount(typeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8, totalNumber);
		List list =null;
		
		try {
			 list = gDao.selectGoods(typeId,pageNo,8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		
		return p;
	}
	
	public Page getGoodsRecommendPage(int typeId,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int totalNumber=0;
		try {
			totalNumber = gDao.selectGoodsRecommendCount(typeId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8, totalNumber);
		List list =null;
		
		try {
			 list = gDao.selectGoodsRecommend(typeId, pageNo, 8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		
		return p;
	}
	
	public Goods getById(int id) {
		
		Goods goods =null;
		try {
			goods = gDao.getById(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return goods;
	}
	
	public Page getSearchGoodsPage(String keyWord,int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int totalNumber=0;
		try {
			totalNumber = gDao.getSearchCount(keyWord);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setPageSizeAndTotalCount(8, totalNumber);
		List list =null;
		
		try {
			 list = gDao.selectSearchGoods(keyWord,pageNo,8);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		p.setList(list);
		
		return p;
	}
}
