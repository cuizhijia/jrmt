package com.swift.jrmt.upload.controller;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.swift.jrmt.common.vo.RpcResult;
import com.swift.jrmt.file.FileService;
import com.swift.jrmt.user.service.UserInfoService;


@Controller
@RequestMapping("/upload")
public class UploadController {

	final static Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name="fileService")
	private FileService fileService;
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	@RequestMapping("/avatarupload")
	public void avatarUpload(HttpServletRequest request, HttpServletResponse response,File avatar,String user_id){
		
		RpcResult rpcResult = RpcResult.success();
		try {
			String url = fileService.upload(avatar);
			logger.info("url:{}",url);
			Boolean bool = userInfoService.updateAvatarOrnickname(url, null, user_id);
			if(!bool) {
				rpcResult = RpcResult.fail();
			}
			rpcResult.addAttribute("avatar", url);
			logger.info("url:{}",url);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		    rpcResult = RpcResult.fail();
		}
		rpcResult.out(response);
	}
	
}
