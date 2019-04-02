package cn.mvtech.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

	int findIsCount(Map<String, Object> paramMap);

	Map<String, Object> findUser(Map<String, Object> paramMap);

}
