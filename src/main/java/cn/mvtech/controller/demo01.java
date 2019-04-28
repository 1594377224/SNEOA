package cn.mvtech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import cn.mvtech.util.SftpUtils;
import cn.mvtech.util.UploadPhoto;

@Controller
@RequestMapping("/")
public class demo01 {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(demo01.class);
	@RequestMapping("testdemo")
	public String demo(){
		
		return "demo05";
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

	
	
}
