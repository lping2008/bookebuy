package com.plin.bookebuy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.plin.bookebuy.pojo.User;
import com.plin.bookebuy.service.IUserService;

@Controller
public class UserController {
	
	@Autowired
	private IUserService userService;
	@RequestMapping("/user/login")
	@ResponseBody
	public User login(User user,HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
		User userExist = userService.login(user.getUsername(), user.getPassword());		
		if(userExist!=null) {
			// 自动登录
			String autologin = request.getParameter("autologin");
			String username = user.getUsername();
			String password = user.getPassword();
			if ("on".equals(autologin)) {
				// 勾选了自动登录，就将用户名与密码存储到cookie中.
				Cookie cookie = new Cookie("autologin",
						URLEncoder.encode(username , "utf-8") + "%springboot%" + password );
				cookie.setMaxAge(7 * 24 * 60 * 60);
				cookie.setPath("/");
				response.addCookie(cookie);
			} else {
				Cookie cookie = new Cookie("autologin",
						URLEncoder.encode(username, "utf-8") + "%springboot%" + password);
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			
			// 登录成功后，将用户存储到session中.
			request.getSession().invalidate();
			//将用户对象返回到前段
			request.getSession().setAttribute("user", user);
			request.setAttribute("username", username);
			response.sendRedirect(request.getContextPath() + "/index");
			return userExist;
		}else {
			request.setAttribute("login.message", "用户名或密码错误");
			request.getRequestDispatcher("/login").forward(request,
					response);
			return null;
		}		
	}
}
