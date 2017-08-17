package com.swift.jrmt.information.model;

import com.swift.jrmt.common.model.BaseModel;


/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : information.tag
 * 功能  :  频道
 * 
 **/

public class Channel  extends BaseModel {

	
	private static final long serialVersionUID = 8919154757329653005L;

	/**创建人id**/
	private Long userId;

	/**发布人id**/
	private Long pubUid;

	/**-1删除，0冻结，4解冻**/
	private Integer status;

	/**标题**/
	private String title;

	/**权重，数值越高，显示越靠前**/
	private Integer rank;

	/**发布时间**/
	private java.util.Date pubTime;
	
	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setPubUid(Long pubUid){
		this.pubUid = pubUid;
	}

	public Long getPubUid(){
		return this.pubUid;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setRank(Integer rank){
		this.rank = rank;
	}

	public Integer getRank(){
		return this.rank;
	}

	public void setPubTime(java.util.Date pubTime){
		this.pubTime = pubTime;
	}

	public java.util.Date getPubTime(){
		return this.pubTime;
	}

}
