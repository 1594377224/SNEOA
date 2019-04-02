package cn.mvtech.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
@Mapper
public interface UserMapper {

	Map<String, Object> findUserList(String id);

	int upUserById(Map<String, Object> paramMap);

	int updUserPwd(Map<String, Object> paramMap);

	int addUser(Map<String, Object> paramMap);

	int findUserByUserName(String addUserName);

	List<Map<String, Object>> findAllUserList();
}
