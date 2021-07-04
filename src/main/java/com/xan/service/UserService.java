package com.xan.service;

import java.sql.SQLException;
import java.util.List;

import com.xan.dao.UserDao;
import com.xan.model.Order;
import com.xan.model.OrderItem;
import com.xan.model.Page;
import com.xan.model.User;

public class UserService {
	private UserDao uDao = new UserDao();
	
	public boolean register(User user) {
		
		try {
			if(uDao.isUsernameExist(user.getUsername())) {
				return false;
			}
			
			if(uDao.isUsernameExist(user.getEmail())) {
				return false;
			}
			
			uDao.addUser(user);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public User login(String ue,String password) {
		User user=null;
		try {
			user = uDao.selcetByUsernamePassword(ue,password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(user!=null) {
			return user;
		}
		try {
			user = uDao.selcetByEmailPassword(ue,password);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		if(user!=null) {
			return user;
		}
		
		return null;
	}
	

	public void updateUserAddress(User user) {
		
		try {
			uDao.updateUserAddress(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateUserPwd(User user) {
		try {
			uDao.updateUserPwd(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public User getUserById(int id) {
		User user=null;
		try {
			user = uDao.selcetById(id);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return user;
	}
	
	public Page getUserPage(int pageNo) {
		Page p = new Page();
		p.setPageNo(pageNo);
		int pageSize=7;
		int totalCount =0;
		try {
			totalCount = uDao.getUserCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.setPageSizeAndTotalCount(pageSize, totalCount);
		List list =null;
		try {
			list = uDao.selectUserList( pageNo, pageSize);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		p.setList(list);
		return p;
	}
	
	public boolean deleteUser(int id) {
		try {
			uDao.delete(id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
