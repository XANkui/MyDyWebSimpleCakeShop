package com.xan.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xan.model.Type;
import com.xan.utils.DBUtil;

public class TypeDao {
	public List<Type> seectAll() throws SQLException{
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type ";
		return r.query(sql, new BeanListHandler<Type>(Type.class));
	}
	
	public Type select(int id) throws SQLException {
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from type where id = ?";
		
		return r.query(sql, new BeanHandler<Type>(Type.class),id);
	}
}
