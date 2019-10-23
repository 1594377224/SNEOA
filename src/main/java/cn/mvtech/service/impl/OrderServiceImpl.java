package cn.mvtech.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.mvtech.mapper.OrderMapper;
import cn.mvtech.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Override
	public int addOrder(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return orderMapper.addOrder(paramMap);
	}
	@Override
	public List<Map<String, Object>> findOrderList(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderList(uesrMap);
	}
	@Override
	public List<Map<String, Object>> findOrderListAll(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.findOrderListAll(uesrMap);
	}
	@Override
	public int findCountOrderListAll(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.findCountOrderListAll(uesrMap);
	}
	@Override
	public int findCountOrderList(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.findCountOrderList(uesrMap);
	}
	@Override
	public int delOrderById(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.delOrderById(uesrMap);
	}
	@Override
	public List<Map<String, Object>> findAllProductorder(Map<String, Object> uesrMap) {
		// TODO Auto-generated method stub
		return orderMapper.findAllProductorder(uesrMap);
	}

}
