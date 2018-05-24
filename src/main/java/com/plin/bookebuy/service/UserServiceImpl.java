package com.plin.bookebuy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plin.bookebuy.mapper.UserMapper;
import com.plin.bookebuy.pojo.User;
import com.plin.bookebuy.pojo.UserExample;
import com.plin.bookebuy.pojo.UserExample.Criteria;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username,String password) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		criteria.andPasswordEqualTo(password);
		List<User> list = userMapper.selectByExample(example );
		if (list!=null) {
			User user=list.get(0);
			return user;
		}
		return null;
	}
}
