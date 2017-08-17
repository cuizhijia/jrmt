package com.swift.jrmt.file.controller;

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


@Controller
@RequestMapping("/fileController")
public class FileController {
	final static Logger logger = LoggerFactory.getLogger(FileController.class);
	@Resource
	private FileService fileService;
	
	@RequestMapping("/fileUpload")
	public void fileUpload(HttpServletRequest request, HttpServletResponse response,File file){
		
		logger.info("==========================fileUpload start==========================");
		RpcResult rpcResult = RpcResult.success();
		try {
			String url = fileService.upload(file);
			rpcResult.addAttribute("fileUrl", url);
			logger.info("url:{}",url);
		} catch (Exception e) {
		    logger.info(e.getMessage());
		    rpcResult = RpcResult.fail();
		}
		logger.info("==========================fileUpload end==========================");
		rpcResult.out(response);
	}
}
