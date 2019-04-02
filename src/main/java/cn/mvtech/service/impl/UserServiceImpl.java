package cn.mvtech.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.mapper.UserMapper;
import cn.mvtech.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper UserMapper;

	@Override
	public Map<String, Object> findUserList(String id) {
		// TODO Auto-generated method stub
		return UserMapper.findUserList(id);
	}

	@Override
	public int upUserById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return UserMapper.upUserById(paramMap);
	}

	@Override
	public int updUserPwd(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return UserMapper.updUserPwd(paramMap);
	}

	@Override
	public int addUser(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return UserMapper.addUser(paramMap);
	}

	@Override
	public int findUserByUserName(String addUserName) {
		// TODO Auto-generated method stub
		return UserMapper.findUserByUserName(addUserName);
	}

	@Override
	public List<Map<String, Object>> findAllUserList() {
		// TODO Auto-generated method stub
		return UserMapper.findAllUserList();
	}

}
