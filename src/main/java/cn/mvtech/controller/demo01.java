package cn.mvtech.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.SftpException;

import cn.mvtech.service.OrderService;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/")
public class demo01 {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(demo01.class);
	@RequestMapping("testdemo")
	public ModelAndView demo(){
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		uesrMap.put("name1", "1");
		uesrMap.put("name2", "2");
		uesrMap.put("name3", "3");
		uesrMap.put("name4", "4");
		uesrMap.put("name5", "5");
		System.out.println("没有订单！！");
		ModelAndView mv = new ModelAndView("/demo05");
	 	mv.addObject("uesrMap", uesrMap);
	    LOGGER.info("没有订单！！");
	    System.out.println("没有订单！！1");
		return mv;
	}
	
	@RequestMapping("testdemo01")
	public String demo01(){
		
		return "menuAdd";
	}
	@Autowired
	private Environment env;
	
	@RequestMapping("upload")
	public Map<String, Object> upload(@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request) throws IOException, SftpException{
		LOGGER.info("[把图片上传到本地目录]========>");
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String basePath =  env.getProperty("basePath");
		String url =  env.getProperty("url");
		System.out.println(basePath);
		String fileName = file.getOriginalFilename();
		LOGGER.info("[+======+]==>"+fileName);
		if(fileName!=null&&fileName!=""){   
			 String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	         fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
		}
        try {
        	file.transferTo(new File(basePath + fileName));
        } catch (Exception e) {
            // TODO
        }
		String URL = url+fileName;
		resultMap.put("url",URL);
		resultMap.put("img",fileName);
		resultMap.put("resultCode", "0");
		resultMap.put("resultMsg", "操作成功");
		LOGGER.info("[上传图片完成返回的路径，图片名称]"+resultMap);
		return resultMap;
	}
	@Autowired
	private OrderService orderuService;
	@RequestMapping("")
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
