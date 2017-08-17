package com.swift.jrmt.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 作者 phil E-mail: s@m1c.cn
 * @date 创建时间：2016年5月27日
 * @version v0.0.1
 * @since 
 * @description 
 * 代码结构：该代码使用的是开源框架 fastdfs-client-java 作为源工具类使用的。原始github地址：https://github.com/happyfish100/fastdfs-client-java
 * 个人地址：https://github.com/ghsqt/fastdfs-client-java
 * 使用该工具时，一定要切记在代码包中预制一个名字为：*【fdfs_client.conf】*的文件
 * 内容如下：【
  	connect_timeout = 2
	network_timeout = 30
	charset = utf-8
	http.tracker_http_port = 8080
	http.anti_steal_token = no
	http.secret_key = FastDFS1234567890
	
	tracker_server = 192.168.1.210:22122
 * 】其中 tracker_server 可以为多个
 */
public class FastDFSTool {
	static Logger logger = LoggerFactory.getLogger(FastDFSTool.class);
	private static ClassPathResource cpr;
	private static TrackerServer trackerServer = null;
    public static void main( String[] args ){
    	System.out.println(upload(new File("C:/Users/qitao/Desktop/12.txt")));
    }
	public static String upload(MultipartFile file) {
		String fileId = "";
		try {
			logger.info("save file to fastDFS,the filename:{}",file.getOriginalFilename());
			String tempFileName = file.getOriginalFilename();
			byte[] fileBuff = file.getBytes();
			Integer index = tempFileName.lastIndexOf(".");
			String fileExtName = null;
			if(index !=-1){
				fileExtName = tempFileName.substring(index+1);
			}
		    //fastDFS方式 
		    init();
		    StorageServer storageServer = null;
		    StorageClient1 client = new StorageClient1(trackerServer, storageServer);
		    //上传文件
		    fileId = client.upload_file1(fileBuff, fileExtName, null);
		} catch (Exception e) {
			logger.error("upload file error contract admin .", e);
		}
		return fileId;
	}
	public static String upload(File file) {
		String fileId = "";
		try {
			logger.info("save file to fastDFS,the filename:{}",file.getName());
			String tempFileName = file.getName();
			byte[] fileBuff = getBytes(file);
			Integer index = tempFileName.lastIndexOf(".");
			String fileExtName = null;
			if(index !=-1){
				fileExtName = tempFileName.substring(index+1);
			}
		    //fastDFS方式 
		    init();
		    StorageServer storageServer = null;
		    StorageClient1 client = new StorageClient1(trackerServer, storageServer);
		    //上传文件
		    fileId = client.upload_file1(fileBuff, fileExtName, null);
		} catch (Exception e) {
			logger.error("upload file error contract admin .", e);
		}
		return fileId;
	}
	
	public static void init(){
		try {
			if(cpr == null){
				cpr = new ClassPathResource("fdfs_client.conf");
				ClientGlobal.init(cpr.getClassLoader().getResource("fdfs_client.conf").getPath());
				  //建立连接
			    TrackerClient tracker = new TrackerClient();
			    trackerServer = tracker.getConnection();
			}
		} catch (FileNotFoundException e) {
			logger.error("配置文件路径不对，请修改。",e);
		} catch (IOException e) {
			logger.error("配置文件读取有问题",e);
		} catch (MyException e) {
			logger.error("预制参数文件有问题",e);
		}
	}
	 public static byte[] getBytes(File file){
	        byte[] buffer = null;  
	        try {  
	            FileInputStream fis = new FileInputStream(file);  
	            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);  
	            byte[] b = new byte[1000];  
	            int n;  
	            while ((n = fis.read(b)) != -1) {  
	                bos.write(b, 0, n);  
	            }  
	            fis.close();  
	            bos.close();  
	            buffer = bos.toByteArray();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	        return buffer;  
	    }  
}
