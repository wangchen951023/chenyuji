package com.chenyuji.controller;

import com.chenyuji.pojo.Admin;
import com.chenyuji.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@EnableAutoConfiguration(exclude= {})
@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("/login")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String loginId = request.getParameter("login_id");
		Admin admin = adminService.selectByPrimaryKey(loginId);
		String password = request.getParameter("password");
		if (admin!=null){
			if (!admin.getPassword().equals(password)){
				System.out.println("zuol");
				return "error";
			}
		}
		return "success";
	}
	/**
	 * 用户注册
	 * @param
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String register(HttpServletRequest request) {

		String loginId = request.getParameter("login_id");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		Admin admin = new Admin();
		admin.setId(UUID.randomUUID().toString());
		admin.setLoginId(loginId);
		admin.setPassword(password);
		admin.setNickname(nickName);
		adminService.insert(admin);


		return "success";
	}

	
	
	

	

}
