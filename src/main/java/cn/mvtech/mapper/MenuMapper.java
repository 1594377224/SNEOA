package cn.mvtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {

	int addMenu(Map<String, Object> paramMap);

	int delMenu(int menuId);

	Map<String, Object> findMenuMap(int menuId);

	int updateMenu(Map<String, Object> paramMap);

	int updateMenuById(Map<String, Object> paramMap);

	int findCountMenu();

	List<Map<String, Object>> findMenuList(Map<String, Object> uesrMap);

}
