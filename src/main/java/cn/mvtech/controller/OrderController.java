package cn.mvtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.mvtech.service.OrderService;
import cn.mvtech.util.G4Utils;
import net.sf.json.JSONObject;

@RestController
@RequestMapping("/zx")
public class OrderController {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private OrderService orderuService;
	@RequestMapping("findOrderList")
	@ResponseBody
	public ModelAndView  findMenuList(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("state") String state
			,@RequestParam("start") String start,@RequestParam("limit") String limit){
		LOGGER.info("[查询用户订单数据]");
		LOGGER.info("id"+id);
		LOGGER.info("name"+name);
		LOGGER.info("state"+state);
		LOGGER.info("start"+start);
		LOGGER.info("limit"+limit);
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		uesrMap.put("id", id);
		uesrMap.put("name", name);
		uesrMap.put("state", state);
		if (G4Utils.isEmpty(start)) {
			start = "0";
		}
		if (G4Utils.isEmpty(limit)) {
			limit = "8";
		}
		int starts = Integer.parseInt(start);
		int limits = Integer.parseInt(limit);
		uesrMap.put("start", starts);
		uesrMap.put("limit", limits);
		List<Map<String,Object>> orderList = null ;
		int count = 0;
		if(Integer.parseInt(state) == 0){
			count = orderuService.findCountOrderListAll(uesrMap);
			String pre = "no";
			String next = "no";
			if (starts != 0) {
				pre = "yes";
			}
			if (starts < count-8) {
				next = "yes";
			}
			uesrMap.put("pre", pre);
			uesrMap.put("next", next);
			orderList = orderuService.findOrderListAll(uesrMap);
		} else {
			count = orderuService.findCountOrderList(uesrMap);
			String pre = "no";
			String next = "no";
			if (starts != 0) {
				pre = "yes";
			}
			if (starts < count-8) {
				next = "yes";
			}
			uesrMap.put("pre", pre);
			uesrMap.put("next", next);
			orderList = orderuService.findOrderList(uesrMap);
		}
		if(G4Utils.isNotEmpty(orderList)){
			uesrMap.put("count", count);
			LOGGER.info("uesrMap===>"+uesrMap.toString());
			resultMap.put("resultCode", "0");
	  		resultMap.put("resultMsg", "操作成功！！");
		 	ModelAndView mv = new ModelAndView("/orderList");
		 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("orderList", orderList);
		    LOGGER.info("操作成功！！");
			return mv;
		} else {
			uesrMap.put("count", count);
			LOGGER.info("uesrMap===》"+uesrMap.toString());
			resultMap.put("resultCode", "-1");
	  		resultMap.put("resultMsg", "没有订单！！");
		 	ModelAndView mv = new ModelAndView("/orderList");
		 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("orderList", orderList);
		    LOGGER.info("没有订单！！");
			return mv;
		}
		
		
	}
	
	@RequestMapping("addOrder")
	@ResponseBody
	public Map<String, Object>  addOrder(@RequestBody JSONObject param){
		LOGGER.info("[生成订单数据]");
		LOGGER.info(param.toString());
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		//尺寸
		String size = param.getString("size");
		//数量
		int num = Integer.parseInt(param.getString("num"));
		//用户id
		int userId = Integer.parseInt(param.getString("userId"));
		//用户名称
		String userName = param.getString("userName");
		//商品id
		int productId = Integer.parseInt(param.getString("productId"));
		//商品名称
		String productName = param.getString("productName");
		//商品单价
		int productPrice = Integer.parseInt(param.getString("productPrice"));
		//商品总价格
		int productPrices = Integer.parseInt(param.getString("productPrices"));
		//是否为管理员
		int state = Integer.parseInt(param.getString("state"));
		paramMap.put("size", size);
		paramMap.put("num", num);
		paramMap.put("userId", userId);
		paramMap.put("userName", userName);
		paramMap.put("productId", productId);
		paramMap.put("productName", productName);
		paramMap.put("productPrice", productPrice);
		paramMap.put("productPrices", productPrices);
		LOGGER.info("总价"+productPrices);
		int count = orderuService.addOrder(paramMap);
		if(count > 0 ){
			uesrMap.put("id", userId);
			uesrMap.put("name", userName);
			uesrMap.put("state", state);
		    LOGGER.info("操作成功！！");
			return uesrMap;
		} else {
		    LOGGER.info("操作失败！！");
			return null;
		}
	}
	
	@RequestMapping("delOrderById")
	public ModelAndView  delUser(@RequestParam("orderId") String orderId,@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("state") String state
			,@RequestParam("start") String start,@RequestParam("limit") String limit){
		LOGGER.info("[删除订单信息]");
		LOGGER.info("---orderId--->"+orderId);
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		uesrMap.put("id", id);
		uesrMap.put("name", name);
		uesrMap.put("state", state);
		uesrMap.put("orderId", orderId);
		int num = orderuService.delOrderById(uesrMap);
		if(num > 0){
			if (G4Utils.isEmpty(start)) {
				start = "0";
			}
			if (G4Utils.isEmpty(limit)) {
				limit = "8";
			}
			int starts = Integer.parseInt(start);
			int limits = Integer.parseInt(limit);
			uesrMap.put("start", starts);
			uesrMap.put("limit", limits);
			List<Map<String,Object>> orderList = null ;
			int count = 0;
			if(Integer.parseInt(state) == 0){
				count = orderuService.findCountOrderListAll(uesrMap);
				String pre = "no";
				String next = "no";
				if (starts != 0) {
					pre = "yes";
				}
				if (starts < count-8) {
					next = "yes";
				}
				uesrMap.put("pre", pre);
				uesrMap.put("next", next);
				orderList = orderuService.findOrderListAll(uesrMap);
			} else {
				count = orderuService.findCountOrderList(uesrMap);
				String pre = "no";
				String next = "no";
				if (starts != 0) {
					pre = "yes";
				}
				if (starts < count-8) {
					next = "yes";
				}
				uesrMap.put("pre", pre);
				uesrMap.put("next", next);
				orderList = orderuService.findOrderList(uesrMap);
			}
			if(G4Utils.isNotEmpty(orderList)){
				uesrMap.put("count", count);
				LOGGER.info("uesrMap===>"+uesrMap.toString());
				resultMap.put("resultCode", "0");
		  		resultMap.put("resultMsg", "操作成功！！");
			 	ModelAndView mv = new ModelAndView("/orderList");
			 	mv.addObject("resultMap", resultMap);
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("orderList", orderList);
			    LOGGER.info("操作成功！！");
				return mv;
			} else {
				uesrMap.put("count", count);
				LOGGER.info("uesrMap===》"+uesrMap.toString());
				resultMap.put("resultCode", "-1");
		  		resultMap.put("resultMsg", "没有订单！！");
			 	ModelAndView mv = new ModelAndView("/orderList");
			 	mv.addObject("resultMap", resultMap);
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("orderList", orderList);
			    LOGGER.info("没有订单！！");
				return mv;
			}
		} else {
			return null;
		}
	}
	
	@RequestMapping("rcbchart")
    @ResponseBody
    private JSONObject dwcb(@RequestParam("scrq1") String scrq1,@RequestParam("scrq2") String scrq2,
                        @RequestParam("dwmc") String dwmc) throws ParseException {
		LOGGER.info("[---------------1]scrq1:"+scrq1+"scrq2:"+scrq2+"dwmc:"+dwmc);
		Calendar now = Calendar.getInstance(); 
		int y = now.get(Calendar.YEAR);
		int m = (now.get(Calendar.MONTH) + 1);
		int d = now.get(Calendar.DAY_OF_MONTH);
		String data = null;
		LOGGER.info("年: " + y +"月: " +m+"日: " +d); 
	 	List category1 = new ArrayList();     
	 	List value1 = new ArrayList();    
	 	List category2 = new ArrayList();     
	 	List value2 = new ArrayList();  
	 	List category3 = new ArrayList();     
	 	List value3 = new ArrayList();  
	 	List category4 = new ArrayList();     
	 	List value4 = new ArrayList();  
	 	Map<String, Object> uesrMap = new HashMap<String, Object>();
	 	//今天
		if("1".equals(dwmc)){
			if(10==m||11==m||12==m){
				data= y+"-"+m+"-"+d;
			}else{
				data= y+"-0"+m+"-"+d;
			}
			uesrMap.put("data", data);
			LOGGER.info("data: "+data); 
		 	List<Map<String,Object>> findAllOrderList = orderuService.findAllProductorder(uesrMap);
		 	LOGGER.info("findAllOrderList: "+findAllOrderList.toString()); 
		 	for (int i = 0; i < findAllOrderList.size(); i++) {
		 		category1.add(findAllOrderList.get(i).get("productName"));
		 		value1.add(findAllOrderList.get(i).get("totalPrice"));
			}
		}
		//上月
		if("2".equals(dwmc)){
			if(m==1){
				y=y-1;
				m=12;
			}else {
				 m = m-1;
			}
			if(10==m||11==m||12==m){
				data= y+"-"+m;
			}else{
				data= y+"-0"+m;
			}
			uesrMap.put("data", data);
			LOGGER.info("data: "+data); 
		 	List<Map<String,Object>> findAllOrderList = orderuService.findAllProductorder(uesrMap);
		 	LOGGER.info("findAllOrderList: "+findAllOrderList.toString()); 
		 	for (int i = 0; i < findAllOrderList.size(); i++) {
		 		category2.add(findAllOrderList.get(i).get("productName"));
		 		value2.add(findAllOrderList.get(i).get("totalPrice"));
			}
		}
		//本月
		if("3".equals(dwmc)){
			if(10==m||11==m||12==m){
				data= y+"-"+m;
			}else{
				data= y+"-0"+m;
			}
			uesrMap.put("data", data);
			LOGGER.info("data: "+data); 
		 	List<Map<String,Object>> findAllOrderList = orderuService.findAllProductorder(uesrMap);
		 	LOGGER.info("findAllOrderList: "+findAllOrderList.toString()); 
		 	for (int i = 0; i < findAllOrderList.size(); i++) {
		 		category3.add(findAllOrderList.get(i).get("productName"));
		 		value3.add(findAllOrderList.get(i).get("totalPrice"));
			}
		}
		//今年
		if("4".equals(dwmc)){
			data= y+"";
			uesrMap.put("data", data);
			LOGGER.info("data: "+data); 
		 	List<Map<String,Object>> findAllOrderList = orderuService.findAllProductorder(uesrMap);
		 	LOGGER.info("findAllOrderList: "+findAllOrderList.toString()); 
		 	for (int i = 0; i < findAllOrderList.size(); i++) {
		 		category4.add(findAllOrderList.get(i).get("productName"));
		 		value4.add(findAllOrderList.get(i).get("totalPrice"));
			}
		}
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("categorydata1",category1);
        jsonObject.put("valuedata1",value1);
        jsonObject.put("categorydata2",category2);
        jsonObject.put("valuedata2",value2);
        jsonObject.put("categorydata3",category3);
        jsonObject.put("valuedata3",value3);
        jsonObject.put("categorydata4",category4);
        jsonObject.put("valuedata4",value4);
        jsonObject.put("dwmc",dwmc);
        JSONObject result = JSONObject.fromObject(jsonObject);
        LOGGER.info("[---------------2]"+result);
        return result;
	}
}
