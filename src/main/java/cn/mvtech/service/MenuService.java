package cn.mvtech.service;

import java.util.List;
import java.util.Map;

public interface MenuService {

	List<Map<String, Object>> findMenuList();

	int addMenu(Map<String, Object> paramMap);

	int delMenu(int menuId);

	Map<String, Object> findMenuMap(int menuId);

	int updateMenu(Map<String, Object> paramMap);

	int updateMenuById(Map<String, Object> paramMap);

}
