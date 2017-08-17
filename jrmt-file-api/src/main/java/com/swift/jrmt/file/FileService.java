package com.swift.jrmt.file;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileService  {
	/**
	 * @param multipartFile
	 * @return file url
	 * @description 文件上传 
	 */
	public String upload(MultipartFile multipartFile);
	
	/**
	 * @param file
	 * @return file url
	 * @description 文件上传 
	 */
	public String upload(File file);
}
