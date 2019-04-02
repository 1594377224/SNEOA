package cn.mvtech.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
/**
 * 图片上传工具类
 * @author
 *
 */
public class UploadPhoto {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(UploadPhoto.class);
	/*
	 * 把图片上传到ftp服务器
	 */
	public Map<String, Object> uploadFtp(@RequestParam(value="file",required=false)MultipartFile file) throws IOException, SftpException{
		LOGGER.info("[把图片上传到ftp服务器]========>");
		 Map<String, Object> resultMap = new HashMap<String, Object>();
		//上传至服务器
		//sftp主机
//		String host = env.getProperty("ftpHost");
		//sftp端口
//		int port = Integer.parseInt(env.getProperty("ftpPort"));
		//sftp用户名
//		String username = env.getProperty("ftpUserName");
		//sftp密码
//		String pwd = env.getProperty("ftpPwd");
		//路径
//		String filePath = env.getProperty("address");
		 String filePath = "D:/img/";
		//上传图片地址
//		String address = env.getProperty("address");
		 String address = "/imgUplode";
		 SftpUtils ftp = new SftpUtils();
		 ChannelSftp sftp = ftp.connect("39.105.204.84", 22, "root", "xuTONG@2018");
		 String url = "img/";
		 SftpATTRS attes = null;
		 try {
			 attes = sftp.stat(address);
			 LOGGER.info("[attes]==>"+attes);
		} catch (SftpException e1) {
			LOGGER.info("[ftp第一次判断目录]==>"+address+"不存在则创建");
			e1.printStackTrace();
		}
		 if(attes == null){
			 sftp.mkdir(address);
			 LOGGER.info("[创建目录]==>"+address);
		 }
		//切换ftp目录
		try {
			LOGGER.info("[切换ftp目录入]==>");
			sftp.cd(address);
			LOGGER.info("[切换ftp目录出]==>");
		} catch (Exception e) {
			LOGGER.info("[ftp没有此目录]==>");
			e.printStackTrace();
		}
		String fileName = file.getOriginalFilename();
		LOGGER.info("[+======+]==>"+fileName);
		 if(fileName!=null&&fileName!=""){   
			 String fileF = fileName.substring(fileName.lastIndexOf("."), fileName.length());//文件后缀
	         fileName=new Date().getTime()+"_"+new Random().nextInt(1000)+fileF;//新的文件名
		 }
		 // 新图片，写入磁盘
         File f = new File(filePath, fileName);
		 FileInputStream fis=null;
		 try{
				file.transferTo(f);
				fis=new FileInputStream(f);
				try {
					sftp.put(fis, fileName);
				} catch (SftpException e) {
					e.printStackTrace();
				}
				String URL = url+fileName;
				resultMap.put("url",URL);
				resultMap.put("img",fileName);
				resultMap.put("resultCode", "0");
				resultMap.put("resultMsg", "操作成功");
				LOGGER.info("[上传图片完成返回的路径，图片名称]"+resultMap);
		}catch(IOException e){
			LOGGER.error("IOException",  e);
			resultMap.put("resultCode", "-1");
			resultMap.put("resultMsg", "上传失败！");
		}finally{
			fis.close();
		}
		 return resultMap;
	}

}
