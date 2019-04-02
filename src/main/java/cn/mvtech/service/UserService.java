package cn.mvtech.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, Object> findUserList(String id);

	int upUserById(Map<String, Object> paramMap);

	int updUserPwd(Map<String, Object> paramMap);

	int addUser(Map<String, Object> paramMap);

	int findUserByUserName(String addUserName);

	List<Map<String, Object>> findAllUserList();
}
