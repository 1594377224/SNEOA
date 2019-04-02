package cn.mvtech.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.mapper.LoginMapper;
import cn.mvtech.service.LoginClService;

@Service
public class LoginClServiceImpl implements LoginClService{

	@Autowired
	private LoginMapper loginMapper;
	@Override
	public int findIsCount(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loginMapper.findIsCount(paramMap);
	}
	@Override
	public Map<String, Object> findUser(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return loginMapper.findUser(paramMap);
	}

}
