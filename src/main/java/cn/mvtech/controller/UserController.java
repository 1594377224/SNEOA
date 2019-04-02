package cn.mvtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.mvtech.service.UserService;
import cn.mvtech.util.G4Utils;

@RestController
@RequestMapping("/zx")
public class UserController {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	
	@RequestMapping("findUserList")
	public ModelAndView  findUserList(@RequestParam("id") String id){
		LOGGER.info("[查询用户信息]");
		LOGGER.info("---id--->"+id);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		Map<String, Object> uesrMap = userService.findUserList(id);
		LOGGER.info("uesrMap==>"+uesrMap);
		
		List<Map<String,Object>> userList = userService.findAllUserList();
		
		if(G4Utils.isNotEmpty(uesrMap)){
			resultMap.put("resultCode", "2");
      		resultMap.put("resultMsg", "操作成功！！");
    	 	ModelAndView mv = new ModelAndView("/user");
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("resultMap", resultMap);
		    mv.addObject("userList", userList);
		    LOGGER.info("操作成功！！");
			return mv;
			
		} else {
			resultMap.put("resultCode", "-1");
      		resultMap.put("resultMsg", "操作失败！！");
    	 	ModelAndView mv = new ModelAndView("/error");
		    mv.addObject("resultMap", resultMap);
		    LOGGER.info("操作失败！！");
			return mv;
		}
	}
	
	
	@RequestMapping("upUserById")
	public ModelAndView  upUserById(@RequestParam("id") String id,@RequestParam("userId") String userId,@RequestParam("name") String name
			,@RequestParam("phone") String phone,@RequestParam("address") String address){
		LOGGER.info("[修改用户信息]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---userId--->"+userId);
		LOGGER.info("---name--->"+name);
		LOGGER.info("---phone--->"+phone);
		LOGGER.info("---address--->"+address);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("userId", userId);
		paramMap.put("name", name);
		paramMap.put("phone", phone);
		paramMap.put("address", address);
		int num = userService.upUserById(paramMap);
		if(num > 0){
			resultMap.put("resultCode", "0");
     		resultMap.put("resultMsg", "操作成功！");
     		ModelAndView mv = new ModelAndView("/user");
			mv.addObject("resultMap", resultMap);
			mv.addObject("uesrMap", paramMap);
			LOGGER.info("<----成功----->");
			return mv;
		} else {
			resultMap.put("resultCode", "-1");
      		resultMap.put("resultMsg", "操作失败！！");
    	 	ModelAndView mv = new ModelAndView("/error");
		    mv.addObject("resultMap", resultMap);
		    LOGGER.info("操作失败！！");
			return mv;
		}
		
	}
	
	@RequestMapping("updUserPwd")
	public ModelAndView  updUserPwd(@RequestParam("id") String id,@RequestParam("password") String password){
		LOGGER.info("[修改用户密码]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---passWord--->"+password);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("password", password);
		int num = userService.updUserPwd(paramMap);
		if (num > 0 ){
			Map<String, Object> uesrMap = userService.findUserList(id);
			LOGGER.info("uesrMap==>"+uesrMap);
			if(G4Utils.isNotEmpty(uesrMap)){
				resultMap.put("resultCode", "0");
	      		resultMap.put("resultMsg", "操作成功！！");
	    	 	ModelAndView mv = new ModelAndView("/user");
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("resultMap", resultMap);
			    LOGGER.info("操作成功！！");
				return mv;
			} else {
				resultMap.put("resultCode", "-1");
	      		resultMap.put("resultMsg", "操作失败！！");
	    	 	ModelAndView mv = new ModelAndView("/error");
			    mv.addObject("resultMap", resultMap);
			    LOGGER.info("操作失败！！");
				return mv;
			}
		} else {
			resultMap.put("resultCode", "-1");
      		resultMap.put("resultMsg", "操作失败！！");
    	 	ModelAndView mv = new ModelAndView("/error");
		    mv.addObject("resultMap", resultMap);
		    LOGGER.info("操作失败！！");
			return mv;
		}
	}
	
	
	@RequestMapping("addUser")
	public ModelAndView  addUser(@RequestParam("id") String id,@RequestParam("name") String name
			,@RequestParam("addUserName") String addUserName,@RequestParam("addUserId") String addUserId,@RequestParam("addPassword") String addPassword
			,@RequestParam("addPhone") String addPhone,@RequestParam("addAddress") String addAddress){
		LOGGER.info("[修改用户信息]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---name--->"+name);
		LOGGER.info("---addUserName--->"+addUserName);
		LOGGER.info("---addUserId--->"+addUserId);
		LOGGER.info("---addPhone--->"+addPhone);
		LOGGER.info("---addAddress--->"+addAddress);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("addUserName", addUserName);
		paramMap.put("addUserId", addUserId);
		paramMap.put("addPassword", addPassword);
		paramMap.put("addPhone", addPhone);
		paramMap.put("addAddress", addAddress);
		paramMap.put("state", 1);
		Map<String, Object> userMap = userService.findUserList(id);
//		Map<String, Object> userMap = new HashMap<String, Object>();
		userMap.put("id", id);
		userMap.put("name", name);
		//查询用户是否已经存在
		int count = userService.findUserByUserName(addUserName);
		LOGGER.info("---查询用户是否已经存在--->"+count);
		if(count > 0){
			resultMap.put("resultCode", "-1");
      		resultMap.put("resultMsg", "用户名称已存在，请重新输入！！");
      		ModelAndView mv = new ModelAndView("/user");
		    mv.addObject("uesrMap", userMap);
		    mv.addObject("resultMap", resultMap);
		    return mv;
		} else {
			int num = userService.addUser(paramMap);
			if(num > 0){
				LOGGER.info("<----成功----->");
				Map<String, Object> uesrMap = userService.findUserList(id);
				LOGGER.info("uesrMap==>"+uesrMap);
				List<Map<String,Object>> userList = userService.findAllUserList();
				if(G4Utils.isNotEmpty(uesrMap) && G4Utils.isNotEmpty(userList)){
					resultMap.put("resultCode", "2");
		      		resultMap.put("resultMsg", "操作成功！！");
		    	 	ModelAndView mv = new ModelAndView("/user");
				    mv.addObject("uesrMap", uesrMap);
				    mv.addObject("resultMap", resultMap);
				    mv.addObject("userList", userList);
				    LOGGER.info("操作成功！！");
					return mv;
				}
			} else {
				resultMap.put("resultCode", "-1");
	      		resultMap.put("resultMsg", "操作失败！！");
	    	 	ModelAndView mv = new ModelAndView("/error");
			    mv.addObject("resultMap", resultMap);
			    LOGGER.info("操作失败！！");
				return mv;
			}
		}
		return null;
	}
	
}
