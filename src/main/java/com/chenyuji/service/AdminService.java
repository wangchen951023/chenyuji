package com.chenyuji.service;

import com.chenyuji.pojo.Admin;

public interface AdminService {
	
	int insert(Admin admin);
	//查询Admin
	Admin selectAdmin(Admin admin);
	
	Admin findAdminByName(String name);
	

}
