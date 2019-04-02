package cn.mvtech.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.mvtech.service.UserService;

@Controller
@RequestMapping("zx")
public class IndexController {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(IndexController.class);
	/*
	 * 登录页面
	 */
	@RequestMapping("index")
	public String userLogin(){
		
		return "login";
	}
	
	@Autowired
	private UserService userService;
	/*
	 * 主页 
	 */
	@RequestMapping("indexUser")
	public ModelAndView  updUserPwd(@RequestParam("id") String id,@RequestParam("name") String name){
		LOGGER.info("[--跳转主页--]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---name--->"+name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("id", id);
		Map<String, Object> uesrMap = userService.findUserList(id);
		resultMap.put("name", name);
		resultMap.put("resultCode", "0");
 		resultMap.put("resultMsg", "操作成功！");
 		resultMap.put("state", "0");
		ModelAndView mv = new ModelAndView("/index");
		mv.addObject("uesrMap",uesrMap);
		mv.addObject("resultMap",resultMap);
		LOGGER.info("<----成功----->");
		return mv;
	}
	
	/*
	 * 添加菜单
	 */
	@RequestMapping("addMenuHtml")
	public ModelAndView  addMenuHtml(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("state") String state){
		LOGGER.info("[--跳转添加菜单--]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---name--->"+name);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("id", id);
		resultMap.put("name", name);
		resultMap.put("state", state);
		resultMap.put("resultCode", "0");
 		resultMap.put("resultMsg", "操作成功！");
		ModelAndView mv = new ModelAndView("/menuAdd");
		mv.addObject("uesrMap",resultMap);
		LOGGER.info("<----成功----->");
		return mv;
	}
	
}
