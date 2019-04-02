package cn.mvtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class demo01 {
	
	@RequestMapping("testdemo")
	public String demo(){
		
		return "demo05";
	}
	
	@RequestMapping("testdemo01")
	public String demo01(){
		
		return "menuAdd";
	}
}
