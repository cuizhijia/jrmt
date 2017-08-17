package org.csource.fastdfs;
/**
 * @author 作者 phil E-mail: s@m1c.cn
 * @date 创建时间：2016年5月27日
 * @version v0.0.1
 * @since 
 * @description 补充原代码必须要通过配置文件来进行配置，抽象出一层让子项目使用
 */
@SuppressWarnings("unused")
public abstract class DFSConfig {
	
	private Integer connect_timeout = 2;
	private Integer		network_timeout = 30;
	private String charset = "utf-8";
	private int 		http_tracker_http_port = 8080;
	private boolean	http_anti_steal_token = false;
	private String	http_secret_key = "FastDFS1234567890";
	private String[] tracker_servers = {"192.168.1.89:22122"};
	
	public DFSConfig(Integer connect_timeout, Integer network_timeout, String charset, int http_tracker_http_port,
			boolean http_anti_steal_token, String http_secret_key, String[] tracker_servers) {
		super();
		this.connect_timeout = connect_timeout;
		this.network_timeout = network_timeout;
		this.charset = charset;
		this.http_tracker_http_port = http_tracker_http_port;
		this.http_anti_steal_token = http_anti_steal_token;
		this.http_secret_key = http_secret_key;
		this.tracker_servers = tracker_servers;
	}

	public Integer getConnect_timeout() {
		return connect_timeout;
	}

	public Integer getNetwork_timeout() {
		return network_timeout;
	}

	public String getCharset() {
		return charset;
	}

	public int getHttp_tracker_http_port() {
		return http_tracker_http_port;
	}

	public boolean getHttp_anti_steal_token() {
		return http_anti_steal_token;
	}

	public String getHttp_secret_key() {
		return http_secret_key;
	}

	public String[] getTracker_servers() {
		return tracker_servers;
	}

	
	

}
