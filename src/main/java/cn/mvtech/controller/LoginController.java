package cn.mvtech.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import cn.mvtech.service.LoginClService;

@Controller
@RequestMapping("/")
public class LoginController {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginClService loginClService;
	
	@RequestMapping("login")
	public ModelAndView getLogin(@RequestParam("name") String name,@RequestParam("password") String password,@RequestParam("pwd") String pwd, HttpServletRequest request,HttpServletResponse response){
		LOGGER.info("name==>"+name+"password==>"+password+"pwd==>"+pwd);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		paramMap.put("name", name);
		paramMap.put("password", password);
		int num = loginClService.findIsCount(paramMap);
		Map<String, Object> uesrMap = loginClService.findUser(paramMap);
		LOGGER.info("uesrMap==>"+uesrMap);
		LOGGER.info("num==>"+num);
		String code = null;
		//1:获取cookie里面的验证码信息
        Cookie[] cookies = request.getCookies();
        LOGGER.info("cookies==>"+cookies.length);
        for (Cookie cookie : cookies) {
        	String key = cookie.getValue()+"imagecode";
        	code = (String) request.getSession().getAttribute(key);
        	LOGGER.info("code==>"+code);
        }
        
        if(!StringUtils.isEmpty(pwd)){
//          全部转化为小写
        	pwd = pwd.toLowerCase();
        	code = code.toLowerCase();
        	if(code.equals(pwd)){
             	if(num>0){
             		resultMap.put("resultCode", "0");
             		resultMap.put("resultMsg", "操作成功！");
             		resultMap.put("name", name);
        			ModelAndView mv = new ModelAndView("/index");
        			mv.addObject("resultMap", resultMap);
        			mv.addObject("uesrMap",uesrMap);
        			LOGGER.info("<----成功----->");
        			return mv;
           		} else {
           			resultMap.put("resultCode", "-1");
             		resultMap.put("resultMsg", "用户名或密码错误！！");
           			ModelAndView mv = new ModelAndView("/error");
           		    mv.addObject("resultMap", resultMap);
           		    LOGGER.info("用户名或密码错误！");
           			return mv;
           		}		
             } else {
            	resultMap.put("resultCode", "-1");
          		resultMap.put("resultMsg", "验证码错误！！");
        	 	ModelAndView mv = new ModelAndView("/error");
    		    mv.addObject("resultMap", resultMap);
    		    LOGGER.info("验证码错误！！");
    			return mv;
     		}
      } else {
	    	resultMap.put("resultCode", "-1");
	    	resultMap.put("resultMsg", "验证码不能为空！！");
	    	ModelAndView mv = new ModelAndView("/error");
		    mv.addObject("resultMap", resultMap);
		    mv.addObject("resultMsg", "验证码不能为空！");
		    LOGGER.info("验证码不能为空！！");
			return mv;
	}
  }
}
