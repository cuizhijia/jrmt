package com.swift.jrmt.comment.dto;

import java.util.Date;

import com.swift.jrmt.comment.model.Comment;

public class CommentCrmDto extends Comment {

	/**
	 * 模块 评论管理
	 * crm 评论管理 Dto 
	 */
	private static final long serialVersionUID = 861572789556550698L;
	
	//资讯标题
	private String infoTitle;
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	//审核人
	private String pubUserName;
	//开始日期
	private Date startDate;
	//结束时间
	private Date endDate;

	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getPubUserName() {
		return pubUserName;
	}
	public void setPubUserName(String pubUserName) {
		this.pubUserName = pubUserName;
	}
}
