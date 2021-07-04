package com.xan.service;

import java.sql.SQLException;
import java.util.List;

import com.xan.dao.TypeDao;
import com.xan.model.Type;

public class TypeService {
	private TypeDao tDao = new TypeDao();
	
	public List<Type> selectAll(){
		
		List<Type> list = null;
		
		try {
			list = tDao.seectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Type select(int id) {
		
		Type t = null;
		try {
			t = tDao.select(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return t;
	}
}
