package com.plin.bookebuy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.plin.bookebuy.mapper.UserMapper;
import com.plin.bookebuy.pojo.User;
import com.plin.bookebuy.service.IUserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookebuyApplicationTests {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private IUserService userService;
	@Test
	public void testUserMapper() {
		User user = userMapper.selectByPrimaryKey(1);
		System.out.println(user);
	}
	
	@Test
	public void testLoginService() {
		User user = userService.login("aaa","123");
		System.out.println(user);
	}

}
