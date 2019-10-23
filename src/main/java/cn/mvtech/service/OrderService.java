package cn.mvtech.service;

import java.util.List;
import java.util.Map;

public interface OrderService {

	int addOrder(Map<String, Object> paramMap);

	List<Map<String, Object>> findOrderList(Map<String, Object> uesrMap);

	List<Map<String, Object>> findOrderListAll(Map<String, Object> uesrMap);

	int findCountOrderListAll(Map<String, Object> uesrMap);

	int findCountOrderList(Map<String, Object> uesrMap);

	int delOrderById(Map<String, Object> uesrMap);

	List<Map<String, Object>> findAllProductorder(Map<String, Object> uesrMap);


}
