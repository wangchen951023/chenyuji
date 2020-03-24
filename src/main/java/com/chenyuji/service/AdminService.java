package com.chenyuji.service;

import com.chenyuji.pojo.Admin;

public interface AdminService {
	
	int insert(Admin admin);
	//查询Admin
	Admin selectAdmin(Admin admin);
	Admin selectByPrimaryKey(String loginId);
	Admin findAdminByName(String name);
	

}
