package com.xan.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xan.model.Goods;
import com.xan.model.Recommend;
import com.xan.utils.DBUtil;

public class GoodsDao {
	
	// select g.id,g.name,g.cover,g.price,t.name typename from recommend r, goods g, type t where type=2 and r.goods_id=g.id and g.type_id = t.id;
	public List<Map<String, Object>> getGoodsList(int recommendType) throws SQLException{
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.price,t.name typename from recommend r, goods g, type t where type=? and r.goods_id=g.id and g.type_id = t.id";
		return r.query(sql, new MapListHandler(),recommendType);
	}
	
	// select g.id,g.name,g.cover,g.price from recommend r, goods g where type=1 and r.goods_id=g.id;
	public Map<String, Object> getScrollGoods() throws SQLException{
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.price from recommend r, goods g where type=1 and r.goods_id=g.id ";
		return r.query(sql, new MapHandler());
	}
	
	public List<Goods> selectGoods(int typeId,int pageNo, int pageSize) throws SQLException{
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(typeId==0) {
			String sql = "select * from goods limit ?,?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNo-1)*pageSize,pageSize);
		}else {
			
			String sql = "select * from goods where type_id=? limit ?,?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),typeId,(pageNo-1)*pageSize,pageSize);
		}
	}
	
	public int getGoodsCount(int typeId) throws SQLException {
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql ="";
		if(typeId==0) {
			
			sql = "select count(*) from goods";
			return r.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			
			sql = "select count(*) from goods where type_id=?";
			return r.query(sql, new ScalarHandler<Long>(),typeId).intValue();
		}
		
		
	}
	
	public List<Goods> selectGoodsRecommend(int type,int pageNo,int pageSize) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		if(type==0) {	// 查询所有
			
			String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g, type t where g.type_id=t.id limit ?,?";
			return r.query(sql, new BeanListHandler<Goods>(Goods.class),(pageNo-1)*pageSize,pageSize);
		}
		String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.intro,g.price,g.stock,t.name typename from goods g, recommend r, type t where g.id = r.goods_id and g.type_id=t.id and r.type=? limit ?,?";
		return r.query(sql, new BeanListHandler<Goods>(Goods.class), type,(pageNo-1)*pageSize,pageSize);
	}
	
	public int selectGoodsRecommendCount(int type) throws SQLException{
		if(type==0) {
			return getGoodsCount(0);
		}
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from recommend where type=?";
		return r.query(sql, new ScalarHandler<Long>(),type).intValue();
	}
	
	public Goods getById(int id) throws SQLException  {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id=? and g.type_id=t.id";
		return r.query(sql, new BeanHandler<Goods>(Goods.class),id);
	}
	
	public int getSearchCount(String keyword) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select count(*) from goods where name like ?";
		return r.query(sql, new ScalarHandler<Long>(),"%"+keyword+"%").intValue();
	}
	
	public List<Goods> selectSearchGoods(String keyWord,int pageNo, int pageSize) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from goods where name like ? limit ?,?";
		return r.query(sql, new BeanListHandler<Goods>(Goods.class),"%"+keyWord+"%",(pageNo-1)*pageSize, pageSize);
	}
	
	public boolean isScroll(Goods g) throws SQLException {
		return isRecommend(g,1);
	}
	
	public boolean isHot(Goods g) throws SQLException {
		return isRecommend(g,2);
	}
	public boolean isNew(Goods g) throws SQLException {
		return isRecommend(g,3);
	}
	
	private boolean isRecommend(Goods g, int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from recommend where type=? and goods_id=?";
		Recommend recommend = r.query(sql, new BeanHandler<Recommend>(Recommend.class),type,g.getId());
		
		return recommend!=null?true:false;
	}
	
	
	public void addRecommend(int goodsId, int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "insert into recommend(type,goods_id) values(?,?)";
		r.update(sql,type,goodsId);
	}
	
	public void removeRecommend(int goodsId, int type) throws SQLException {
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from recommend where type=? and goods_id=?";
		r.update(sql,type,goodsId);
	}
	
	
	
	
	
	public static void main(String[] args) throws SQLException {
		
		Goods g = new GoodsDao().getById(2);
		System.out.println(g.toString());
	}
	
}
