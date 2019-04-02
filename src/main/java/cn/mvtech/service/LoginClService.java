package cn.mvtech.service;

import java.util.Map;

public interface LoginClService {

	int findIsCount(Map<String, Object> paramMap);

	Map<String, Object> findUser(Map<String, Object> paramMap);

}
