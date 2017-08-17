package com.swift.jrmt.file.impl;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.swift.jrmt.file.FileService;
import com.swift.jrmt.tools.FastDFSTool;
@Service("fileService")
public class FileServiceImpl implements FileService {

	@Override
	public String upload(MultipartFile multipartFile) {
		return FastDFSTool.upload(multipartFile);
	}

	@Override
	public String upload(File file) {
		return FastDFSTool.upload(file);
	}

}
