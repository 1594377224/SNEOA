package cn.mvtech.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
public class Upload {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(Upload.class);
	public Map<String, Object> upload(@RequestParam(value="file",required=false)MultipartFile file) throws IOException{
		LOGGER.info("[把图片上传到本地目录]========>"+file.getSize());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String basePath =  "D://img/";
		String url =  "/static/img/";
		String fileName = file.getOriginalFilename();
		LOGGER.info("[+原图片名称+]==>"+fileName);
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
