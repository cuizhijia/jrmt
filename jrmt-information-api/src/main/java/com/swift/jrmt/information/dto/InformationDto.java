package com.swift.jrmt.information.dto;

import java.util.Date;
import java.util.List;

import com.swift.jrmt.information.model.Information;
import com.swift.jrmt.information.model.KeyWord;

public class InformationDto extends Information {

	
	private static final long serialVersionUID = 5773420216669056127L;
	
	 // 查询创建起始时间
    private Date startCreatedTime;

    // 查询创建结束时间
    private Date endCreatedTime;
    
    // 查询发布起始时间
    private Date startPubTime;

    // 查询发布结束时间
    
    private Date endPubTime;
    
    private List<KeyWord> keyWordsList;
    
    private String channelTitle;//频道名称
    
    private String username;//创建人
    
    private String auditName;//审核人
    
    private String mediaTitle;//资讯来源
    
    /**文章内容**/
	private String content;
    

	public Date getStartCreatedTime() {
		return startCreatedTime;
	}

	public void setStartCreatedTime(Date startCreatedTime) {
		this.startCreatedTime = startCreatedTime;
	}

	public Date getEndCreatedTime() {
		return endCreatedTime;
	}

	public void setEndCreatedTime(Date endCreatedTime) {
		this.endCreatedTime = endCreatedTime;
	}

	public Date getStartPubTime() {
		return startPubTime;
	}

	public void setStartPubTime(Date startPubTime) {
		this.startPubTime = startPubTime;
	}

	public Date getEndPubTime() {
		return endPubTime;
	}

	public void setEndPubTime(Date endPubTime) {
		this.endPubTime = endPubTime;
	}

	public List<KeyWord> getKeyWordsList() {
		return keyWordsList;
	}

	public void setKeyWordsList(List<KeyWord> keyWordsList) {
		this.keyWordsList = keyWordsList;
	}

	public String getChannelTitle() {
		return channelTitle;
	}

	public void setChannelTitle(String channelTitle) {
		this.channelTitle = channelTitle;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getMediaTitle() {
		return mediaTitle;
	}

	public void setMediaTitle(String mediaTitle) {
		this.mediaTitle = mediaTitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
