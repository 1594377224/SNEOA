package cn.mvtech.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.mapper.MenuMapper;
import cn.mvtech.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Map<String, Object>> findMenuList() {
		// TODO Auto-generated method stub
		return menuMapper.findMenuList();
	}

	@Override
	public int addMenu(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return menuMapper.addMenu(paramMap);
	}

	@Override
	public int delMenu(int menuId) {
		// TODO Auto-generated method stub
		return menuMapper.delMenu(menuId);
	}
}
