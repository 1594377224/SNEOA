package cn.mvtech.util;

import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpUtils {
	
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(FtpUtils.class);
	public static void main(String[] args) {
		FTPClient ftp=FtpUtils.getFTPClient("39.105.204.84", "root", "xuTONG@2018", 22);
		LOGGER.info("-----------");
	}
	
    public static FTPClient getFTPClient(String ftpHost, String ftpUserName,
            String ftpPassword, int ftpPort) {
         FTPClient ftpClient = new FTPClient();
         try {
           ftpClient = new FTPClient();
           ftpClient.connect(ftpHost, ftpPort);// 连接FTP服务器
           ftpClient.login(ftpUserName, ftpPassword);// 登陆FTP服务器
         if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
        	 LOGGER.info("未连接到FTP，用户名或密码错误。");
        	 	ftpClient.disconnect();
         } else {
        	 LOGGER.info("FTP连接成功。");
         }
         } catch (SocketException e) {
        	 e.printStackTrace();
        	 LOGGER.info("异常信息="+e.getMessage());
         } catch (IOException e) {
        	 e.printStackTrace();
        	 LOGGER.info("异常信息="+e.getMessage());
         }
         return ftpClient;
    }
}
