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
	public int addMenu(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return menuMapper.addMenu(paramMap);
	}

	@Override
	public int delMenu(int menuId) {
		// TODO Auto-generated method stub
		return menuMapper.delMenu(menuId);
	}

	@Override
	public Map<String, Object> findMenuMap(int menuId) {
		// TODO Auto-generated method stub
		return menuMapper.findMenuMap(menuId);
	}

	@Override
	public int updateMenu(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return menuMapper.updateMenu(paramMap);
	}

	@Override
	public int updateMenuById(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return menuMapper.updateMenuById(paramMap);
	}

	@Override
	public int findCountMenu() {
		// TODO Auto-generated method stub
		return menuMapper.findCountMenu();
	}

	@Override
	public List<Map<String, Object>> findMenuList(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return menuMapper.findMenuList(uesrMap);
	}
}
