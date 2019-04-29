package cn.mvtech.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jcraft.jsch.SftpException;

import cn.mvtech.service.MenuService;
import cn.mvtech.service.UserService;
import cn.mvtech.util.G4Utils;
import cn.mvtech.util.Upload;

@RestController
@RequestMapping("/zx")
public class MenuController {
	private static Logger LOGGER=(Logger) LoggerFactory.getLogger(MenuController.class);
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("findMenuList")
	public ModelAndView  findMenuList(@RequestParam("id") String id,@RequestParam("name") String name,@RequestParam("state") String state
			,@RequestParam("start") String start,@RequestParam("limit") String limit){
		LOGGER.info("[查询主菜单信息]");
		LOGGER.info("---id--->"+id);
		LOGGER.info("---name--->"+name);
		LOGGER.info("---state--->"+state);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		uesrMap.put("id", id);
		uesrMap.put("name", name);
		uesrMap.put("state", state);
		if (G4Utils.isEmpty(start)) {
			start = "0";
		}
		if (G4Utils.isEmpty(limit)) {
			limit = "10";
		}
		int starts = Integer.parseInt(start);
		int limits = Integer.parseInt(limit);
		uesrMap.put("start", starts);
		uesrMap.put("limit", limits);
		int count = menuService.findCountMenu();
		String pre = "no";
		String next = "no";
		if (starts != 0) {
			pre = "yes";
		}
		if (starts < count-10) {
			next = "yes";
		}
		uesrMap.put("pre", pre);
		uesrMap.put("next", next);
		uesrMap.put("count", count);
		List<Map<String,Object>> menulist = menuService.findMenuList(uesrMap);
		if(G4Utils.isNotEmpty(menulist)){
			LOGGER.info("查询成功！！"+uesrMap);
			resultMap.put("resultCode", "0");
      		resultMap.put("resultMsg", "操作成功！！");
      		resultMap.put("menuAdd", "off");
    	 	ModelAndView mv = new ModelAndView("/menu");
    	 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("menulist", menulist);
		    LOGGER.info("操作成功！！");
			return mv;
			
		} else {
			LOGGER.info("查询成功没数据！！"+uesrMap);
			resultMap.put("resultCode", "0");
      		resultMap.put("resultMsg", "操作成功-查询没数据！！");
      		resultMap.put("menuAdd", "off");
    	 	ModelAndView mv = new ModelAndView("/menu");
    	 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("menulist", menulist);
		    LOGGER.info("操作成功！！");
			return mv;
		}
		
	}
	
	@RequestMapping("addMenu")
	public ModelAndView  addMenu(@RequestParam("state") String state,@RequestParam("name") String name,@RequestParam("id") String id,@RequestParam("menuName") String menuName,@RequestParam("price") String price,@RequestParam("remark") String remark,
			@RequestParam(value="file",required=false)MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws SftpException{
		LOGGER.info("[添加菜单信息]");
		LOGGER.info("---name--->"+name);
		LOGGER.info("---id--->"+id);
		LOGGER.info("---menuNmae--->"+menuName);
		LOGGER.info("---price--->"+price);
		LOGGER.info("---remark--->"+remark);
		LOGGER.info("---state--->"+state);
		String fileName = file.getOriginalFilename();
		LOGGER.info("---file--->"+fileName);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", menuName);
		paramMap.put("price", price);
		paramMap.put("remark", remark);
//		UploadPhoto uploadPhoto = new UploadPhoto();
		Upload uploadPhoto = new Upload();
		try {
			//上传到文件服务器
//			Map<String, Object> uploadFtp = uploadPhoto.uploadFtp(file);
			//上传到本地目录
			Map<String, Object> uploadFtp = uploadPhoto.upload(file);
			LOGGER.info("【上传图片返回的结果】->"+uploadFtp);
			String resultCode = uploadFtp.get("resultCode").toString();
			if("0".equals(resultCode)){
				String imgUrl = uploadFtp.get("url").toString();
				String imgName = uploadFtp.get("img").toString();
				paramMap.put("imgUrl", imgUrl);
				paramMap.put("imgName", imgName);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		LOGGER.info("[添加菜单信息]---保存信息入参--->"+paramMap);
		int num = menuService.addMenu(paramMap);
		if(num > 0){
			LOGGER.info("[添加菜单信息]---保存成功--->");
			Map<String, Object> uesrMap = new HashMap<String, Object>();
			uesrMap.put("id", id);
			uesrMap.put("name", name);
			uesrMap.put("state", state);
			String start=null;
			String limit=null;
			if (G4Utils.isEmpty(start)) {
				start = "0";
			}
			if (G4Utils.isEmpty(limit)) {
				limit = "10";
			}
			int starts = Integer.parseInt(start);
			int limits = Integer.parseInt(limit);
			uesrMap.put("start", starts);
			uesrMap.put("limit", limits);
			int count = menuService.findCountMenu();
			String pre = "no";
			String next = "no";
			if (starts != 0) {
				pre = "yes";
			}
			if (starts < count-10) {
				next = "yes";
			}
			uesrMap.put("pre", pre);
			uesrMap.put("next", next);
			uesrMap.put("count", count);
			List<Map<String,Object>> menulist = menuService.findMenuList(uesrMap);
			if(G4Utils.isNotEmpty(menulist)){
				resultMap.put("resultCode", "0");
				resultMap.put("menuAdd", "yes");
	      		resultMap.put("resultMsg", "操作成功！！");
	    	 	ModelAndView mv = new ModelAndView("/menu");
	    	 	mv.addObject("resultMap", resultMap);
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("menulist", menulist);
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
			LOGGER.info("[添加菜单信息]---保存失败参--->");
		}
		return null;
		
	}
	
	@RequestMapping("updateMenu")
	@ResponseBody
	public ModelAndView  updateMenu(@RequestParam("state") String state,@RequestParam("name") String name,@RequestParam("id") String id
			,@RequestParam("menuName") String menuName,@RequestParam("price") String price,@RequestParam("remark") String remark,
			@RequestParam(value="file",required=false)MultipartFile file,@RequestParam("menuId") String menuId){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		LOGGER.info("[修改菜单信息]");
		LOGGER.info("---name--->"+name);
		LOGGER.info("---id--->"+id);
		LOGGER.info("---menuNmae--->"+menuName);
		LOGGER.info("---price--->"+price);
		LOGGER.info("---remark--->"+remark);
		LOGGER.info("---state--->"+state);
		String fileName = file.getOriginalFilename();
		LOGGER.info("---file--->"+fileName);
		Map<String, Object> uesrMap = new HashMap<String, Object>();
		uesrMap.put("id", id);
		uesrMap.put("name", name);
		uesrMap.put("state", state);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("name", menuName);
		paramMap.put("price", price);
		paramMap.put("remark", remark);
		paramMap.put("menuId", menuId);
		int num;
		if(G4Utils.isNotEmpty(fileName)){
			LOGGER.info("---已更改图片信息--->"+fileName);
			Upload uploadPhoto = new Upload();
			Map<String, Object> uploadFtp;
			try {
				uploadFtp = uploadPhoto.upload(file);
				LOGGER.info("【上传图片返回的结果】->"+uploadFtp);
				String resultCode = uploadFtp.get("resultCode").toString();
				if("0".equals(resultCode)){
					String imgUrl = uploadFtp.get("url").toString();
					String imgName = uploadFtp.get("img").toString();
					paramMap.put("imgUrl", imgUrl);
					paramMap.put("imgName", imgName);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			LOGGER.info("[修改菜单信息]---修改保存信息入参--->"+paramMap);
			num = menuService.updateMenu(paramMap);
		} else {
			LOGGER.info("---图片名称为空--->"+fileName);
			num = menuService.updateMenuById(paramMap);
		}
		if(num > 0){
			LOGGER.info("[添加菜单信息]---保存成功--->");
			String start=null;
			String limit=null;
			if (G4Utils.isEmpty(start)) {
				start = "0";
			}
			if (G4Utils.isEmpty(limit)) {
				limit = "10";
			}
			int starts = Integer.parseInt(start);
			int limits = Integer.parseInt(limit);
			uesrMap.put("start", starts);
			uesrMap.put("limit", limits);
			int count = menuService.findCountMenu();
			String pre = "no";
			String next = "no";
			if (starts != 0) {
				pre = "yes";
			}
			if (starts < count-10) {
				next = "yes";
			}
			uesrMap.put("pre", pre);
			uesrMap.put("next", next);
			uesrMap.put("count", count);
			List<Map<String,Object>> menulist = menuService.findMenuList(uesrMap);
			if(G4Utils.isNotEmpty(menulist)){
				resultMap.put("resultCode", "0");
				resultMap.put("menuAdd", "yes");
	      		resultMap.put("resultMsg", "操作成功！！");
	    	 	ModelAndView mv = new ModelAndView("/menu");
	    	 	mv.addObject("resultMap", resultMap);
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("menulist", menulist);
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
			LOGGER.info("[添加菜单信息]---保存失败参--->");
		}
		return null;
		
	}
	
	@RequestMapping("delMenu")
	@ResponseBody
	public ModelAndView  delMenu(@RequestParam("id") String id,@RequestParam("userName") String userName,@RequestParam("userId") String userId){
		LOGGER.info("[删除菜单信息]");
		LOGGER.info("---菜单id--->"+id);
		LOGGER.info("---userId--->"+userId);
		LOGGER.info("---userName--->"+userName);
		int menuId = Integer.parseInt(id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		int num = menuService.delMenu(menuId);
		if(num > 0 ){
			LOGGER.info("[添加菜单信息]---保存成功--->");
			Map<String, Object> uesrMap = new HashMap<String, Object>();
			uesrMap.put("id", userId);
			uesrMap.put("name", userName);
			uesrMap.put("state", "0");
			String start=null;
			String limit=null;
			if (G4Utils.isEmpty(start)) {
				start = "0";
			}
			if (G4Utils.isEmpty(limit)) {
				limit = "10";
			}
			int starts = Integer.parseInt(start);
			int limits = Integer.parseInt(limit);
			uesrMap.put("start", starts);
			uesrMap.put("limit", limits);
			int count = menuService.findCountMenu();
			String pre = "no";
			String next = "no";
			if (starts != 0) {
				pre = "yes";
			}
			if (starts < count-10) {
				next = "yes";
			}
			uesrMap.put("pre", pre);
			uesrMap.put("next", next);
			uesrMap.put("count", count);
			List<Map<String,Object>> menulist = menuService.findMenuList(uesrMap);
			if(G4Utils.isNotEmpty(menulist)){
				resultMap.put("resultCode", "0");
				resultMap.put("menuAdd", "yes");
	      		resultMap.put("resultMsg", "操作成功！！");
	    	 	ModelAndView mv = new ModelAndView("/menu");
	    	 	mv.addObject("resultMap", resultMap);
			    mv.addObject("uesrMap", uesrMap);
			    mv.addObject("menulist", menulist);
			    LOGGER.info("操作成功！！");
				return mv;
		} else {
			resultMap.put("resultCode", "0");
			resultMap.put("menuAdd", "yes");
      		resultMap.put("resultMsg", "操作成功-已经删除全部数据！！");
    	 	ModelAndView mv = new ModelAndView("/menu");
    	 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("menulist", menulist);
		    LOGGER.info("操作成功-已经删除全部数据！！");
			return mv;
		}
	}
		return null;
  }
	
	
	@RequestMapping("findMenuPer")
	@ResponseBody
	public ModelAndView  findMenuPer(@RequestParam("id") String id,@RequestParam("userId") String userId){
		LOGGER.info("[详情页面信息]");
		LOGGER.info("---userId--->"+userId);
		LOGGER.info("---id--->"+id);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> uesrMap = userService.findUserList(userId);
		LOGGER.info("uesrMap==>"+uesrMap);
		int menuId = Integer.parseInt(id);
		Map<String, Object> menuPerMap = menuService.findMenuMap(menuId);
		LOGGER.info("menuPerMap==>"+menuPerMap);
		if(G4Utils.isNotEmpty(uesrMap)&&(G4Utils.isNotEmpty(menuPerMap))){
			resultMap.put("resultCode", "0");
      		resultMap.put("resultMsg", "操作成功！！");
    	 	ModelAndView mv = new ModelAndView("/menuPre");
    	 	mv.addObject("resultMap", resultMap);
		    mv.addObject("uesrMap", uesrMap);
		    mv.addObject("menuPerMap", menuPerMap);
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
}