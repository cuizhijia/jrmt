package com.swift.jrmt.common.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.swift.jrmt.common.constants.StatusCode;
import com.swift.jrmt.common.utils.StringUtil;

/**
 * 说明:移动客户端返回结果包装<br>
 * 
 * 使用示例：<br>
 * 1.失败返回<br>
 * RpcResult.status(StatusCode.failed).out(response);<br><br>
 * 
 * 2.成功返回单条数据<br>
 * RpcResult.status(StatusCode.success).addDatabody("id", 1).out(response);<br><br>
 * 
 * 3.成功返回数组数据（非循环）<br>
 * RpcResult.status(StatusCode.success).addArray().addDatabody("id", 1).addArray().addDatabody("id", 1).out(response);<br>
 * 注意：每增加一条记录都必须调用addArray()方法<br><br>
 * 
 * 4.成功返回数组数据（循环）<br>
 * RpcResult result = RpcResult.status(StatusCode.success);
 * for(Article article : articles){
 * 		result.addArray().addDatabody("new_title", article.getTitle()).addDatabody("new_url", "http://xxx.xxx.xxx/news/" + article.getTitle()).addDatabody("new_time", article.getTime())
 * }<br>
 * 注意：每增加一条记录都必须调用addData()方法<br><br>
 * 
 * 5.携带附加值<br>
 * RpcResult.status(StatusCode.success).addArrtibute("pages":10).addArrtibute("total_record":9832).out(response)<br><br>
 * 
 * @author howsun ->[howsun.zhang@gmail.com]
 * 
 * 2015年5月6日 上午11:33:58
 */
public class RpcResult implements Serializable {

	private static final long serialVersionUID = 3468685425329223164L;

	private int code = Integer.MIN_VALUE;
	
	private String message;
	
	private Map<String, Object> attributes;

	private Object databody;

	private Map<String, Object> _databodyMap;
	
	private List<Map<String, Object>> _databodyMaps;
	
	private RpcResult(){}
	
	
	/**
	 * 构造对象
	 * @param statusCode
	 * @return
	 * RpcResult
	 */
	public static RpcResult status(StatusCode statusCode){
		return status(statusCode, null);
	}
	
	/**
	 * 构造对象
	 * @param statusCode
	 * @return
	 * RpcResult
	 */
	public static RpcResult status(StatusCode statusCode, String defaultMessage){
		RpcResult rpcResult = new RpcResult();
		rpcResult.setCode(statusCode.getCode());
		rpcResult.setMessage(StringUtil.hasLength( defaultMessage ) ? defaultMessage : statusCode.getMessage());
		return rpcResult;
	}
	
	/**
	 * 构造为成功状态对象
	 * @return
	 * RpcResult
	 */
	public static RpcResult success(){
		return status(StatusCode.success);
	}
	
	/**
	 * 构造为失败状态对象
	 * @return
	 * RpcResult
	 */
	public static RpcResult fail(){
		return status(StatusCode.failed);
	}
	
	
	/**
	 * 添加一条数组记录
	 * @return
	 * RpcResult
	 */
	public RpcResult addArray(){
		if(this._databodyMaps == null){
			this._databodyMaps = new ArrayList<Map<String,Object>>();
		}
		this._databodyMaps.add(new HashMap<String, Object>());
		return this;
	}
	
	
	/**
	 * 添加数据，如果之前已调用addData()方法，则添加到数据组中
	 * @param key
	 * @param value
	 * @return
	 * RpcResult
	 */
	public RpcResult addDatabody(String key, Object value){
		if(this._databodyMaps != null && this._databodyMaps.size() > 0){
			this._databodyMaps.get(this._databodyMaps.size() - 1).put(key, value==null?"":value);
			return this;
		}
		
		if(this._databodyMap == null){
			this._databodyMap = new HashMap<String, Object>();
		}
		this._databodyMap.put(key, value==null?"":value);
		return this;
	}
	
	
	/**
	 * 添加附加属性
	 * @param key
	 * @param value
	 * @return
	 * RpcResult
	 */
	public RpcResult addAttribute(String key, Object value){
		if(this.attributes == null){
			this.attributes = new HashMap<String, Object>();
		}
		this.attributes.put(key, value==null?"":value);
		return this;
	}
	
	
	/**
	 * 输出Json值
	 * @param response
	 * void
	 */
	public void out(HttpServletResponse response){
		try {
			response.setCharacterEncoding("utf-8");
			if(!StringUtil.hasLengthBytrim(response.getContentType())){
				response.setContentType("application/json");
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.
			response.getWriter().print(this.toString());
			response.getWriter().flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			//log.info("输出错误：", e);
		}
	}
	/**
	 * @param response
	 * 解决跨域问题
	 */
	public void outCrossOrigin(HttpServletResponse response){
		try {
			response.setCharacterEncoding("utf-8");
			if(!StringUtil.hasLengthBytrim(response.getContentType())){
				response.setContentType("application/json");
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 3600); // Proxies.
			
			 response.setHeader("Access-Control-Max-Age", "3600");
			
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
			response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			
			response.getWriter().print(this.toString());
			response.getWriter().flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			//log.info("输出错误：", e);
		}
	}
	
	
	/**
	 * 输出Json值
	 * @param response
	 * void
	 */
	public void outHtml(HttpServletResponse response){
		try {
			response.setCharacterEncoding("utf-8");
			if(!StringUtil.hasLengthBytrim(response.getContentType())){
				response.setContentType("application/html");
			}
			response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
			response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
			response.setDateHeader("Expires", 0); // Proxies.
			response.getWriter().print(this.toString());
			response.getWriter().flush();
		}
		catch (Exception e) {
			e.printStackTrace();
			//log.info("输出错误：", e);
		}
	}

	
	/**
	 * 切换数据
	 * void
	 */
	private void change(){
		if(this._databodyMaps != null && this._databodyMaps.size() > 0){
			this.databody = this._databodyMaps;
			this._databodyMaps = null;
			return;
		}
		
		this.databody = this._databodyMap;
		this._databodyMap = null;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getDatabody() {
		if(this.databody == null){
			change();
		}
		return databody;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}


	/**
	 * 生成Json值
	 */
	@Override
	public String toString() {
		if(this._databodyMaps != null && this._databodyMaps.size() > 0){
			this.addAttribute("databodysize", this._databodyMaps.size());
		}else{
			if(this.databody!=null){
				this.addAttribute("databodysize", 1);
			}else{
				this.addAttribute("databodysize", 0);
			}
		}
		change();
		return JSON.toJSONString(this);
	}
	
	
}
