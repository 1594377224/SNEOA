package cn.mvtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {

	List<Map<String, Object>> findMenuList();

	int addMenu(Map<String, Object> paramMap);

	int delMenu(int menuId);

}
