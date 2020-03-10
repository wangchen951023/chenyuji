package com.chenyuji.service.impl;

import com.chenyuji.mapper.AdminMapper;
import com.chenyuji.pojo.Admin;
import com.chenyuji.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;





@Service

public class AdminSeviceImpl implements AdminService {
	@Autowired
	private AdminMapper adminMapper;


	@Override
	public int insert(Admin admin) {
		return adminMapper.insert(admin);
	}

	@Override
	public Admin selectAdmin(Admin admin) {
		return null;
	}

	/**
	 * 测试单机版redis的时候查询，将查询出的值放到redis缓存中
	 */
	@Override
	@Cacheable(value="myname")//键值对的值
	public Admin findAdminByName(String name) {
		System.out.println("从数据库中查询");
		return adminMapper.selectByPrimaryKey(name);
	}

	

}
