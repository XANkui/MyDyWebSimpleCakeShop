package com.xan.dao;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.xan.model.Order;
import com.xan.model.OrderItem;
import com.xan.utils.DBUtil;

public class OrderDao {
	public void insertOrder(Connection conn, Order order) throws SQLException {
		QueryRunner r = new QueryRunner();
		// order 关键字冲突，所以添加 `` 作为 区分，（` 是 1 旁边的 tab 上边的 英文状态下）
		String sql = "insert into `order`(total,amount,status,paytype,name,phone,address,datetime,user_id) value(?,?,?,?,?,?,?,?,?)";
		r.update(conn,sql,
				order.getTotal(),order.getAmount(), order.getStatus(),
				order.getPaytype(),order.getName(),order.getPhone(),
				order.getAddress(),order.getDatetime(),order.getUser().getId());
	}
	
	public void insertOrderItem(Connection conn,OrderItem item) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "insert into orderitem(price,amount,goods_id,order_id) value(?,?,?,?)";
		r.update(conn,sql, item.getPrice(),item.getAmount(),item.getGoods().getId(),item.getOrder().getId());
	}
	
	public int getLastInsertId(Connection conn) throws SQLException {
		QueryRunner r = new QueryRunner();
		String sql = "select last_insert_id()";	// 查找最后插入的id
		BigInteger bi = r.query(conn, sql,new ScalarHandler<BigInteger>());
		return Integer.parseInt(bi.toString());
	}
	
	public List<Order> selectAll(int userId) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select * from `order` where user_id=? order by datetime desc"; // 倒序排列选择结果
		return r.query(sql, new BeanListHandler<Order>(Order.class), userId);
		
	}
	
	public List<OrderItem> selectAllItem(int orderId) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "select i.id,i.price,i.amount,g.name from orderitem i,goods g where i.order_id=? and i.goods_id=g.id";
		return r.query(sql, new BeanListHandler<OrderItem>(OrderItem.class),orderId);
	}
	
	
	public int getOrderCount(int status) throws SQLException {
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql="";
		if(status==0) {	// 查全部
			
			sql="select count(*) from `order`";
			return r.query(sql, new ScalarHandler<Long>()).intValue();
		}else {
			
			sql="select count(*) from `order` where status=?" ;
			return r.query(sql, new ScalarHandler<Long>(),status).intValue();
		}

	}
	
	public List<Order> selectOrderList(int status, int pageNo, int pageSize) throws SQLException{
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql="";
		if(status==0) {
			sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc limit ?,?";
			return r.query(sql, new BeanListHandler<Order>(Order.class), (pageNo-1)*pageSize, pageSize );
		}else {
			sql = "select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and status=? order by o.datetime desc limit ?,?";
			return r.query(sql, new BeanListHandler<Order>(Order.class),status, (pageNo-1)*pageSize,pageSize );
		}
	}
	
	public void updateStatus(int id, int status) throws SQLException {
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "update `order` set status=? where id=?";
		r.update(sql,status,id);
	}
	
	public void deleteOrder(Connection conn,int id ) throws SQLException {
			
			QueryRunner r = new QueryRunner(DBUtil.getDataSource());
			String sql = "delete from `order` where id=?";
			r.update(conn,sql,id);
	}
	
	public void deleteOrderItem(Connection conn,int id ) throws SQLException {
		
		QueryRunner r = new QueryRunner(DBUtil.getDataSource());
		String sql = "delete from orderItem where order_id=?";
		r.update(conn,sql,id);
}
	
		
	public static void main(String[] args) throws SQLException {
		int id = new OrderDao().getLastInsertId(DBUtil.getDataSource().getConnection());
		System.out.println(id);
	}
}
