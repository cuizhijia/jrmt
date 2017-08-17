package com.swift.jrmt.adopeningscreen.model;

import java.util.Date;

import com.swift.jrmt.common.model.BaseModel;

public class AdOpeningScreen extends BaseModel{
  

    /**
	 * 
	 */
	private static final long serialVersionUID = 6928984939216410967L;
	
	/**审核人id*/
	private Long auditorId;

	/**创建人id*/
    private Long userId;

    /**发布人id*/
    private Long pubUid;

    /**状态，默认1，-1已删除，0冻结与下架，1待审核，2审核不通过，3待发布与通过，4已发布与上架与解冻*/
    private Integer status;

    /**类别,0:普通,1:活动*/
    private Integer adType;

    /**广告链接地址*/
    private String adLinkUrl;

    /**图片链接List*/
    private String picUrl;

    /**用户*/
    private String adCustomer;

    /**负责人*/
    private String adManager;

    /**备注*/
    private String adRemarks;

    /**驳回原因*/
    private String rejectReason;

    /**启动页名称*/
    private String adName;

    /**展示开始时间*/
    private Date displayTimeStart;

    /**展示结束时间*/
    private Date displayTimeEnd;

    /**审核时间*/
    private Date auditTime;

    /**发布时间*/
    private Date pubTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(Long auditorId) {
        this.auditorId = auditorId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPubUid() {
        return pubUid;
    }

    public void setPubUid(Long pubUid) {
        this.pubUid = pubUid;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getAdType() {
        return adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    public String getAdLinkUrl() {
        return adLinkUrl;
    }

    public void setAdLinkUrl(String adLinkUrl) {
        this.adLinkUrl = adLinkUrl == null ? null : adLinkUrl.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getAdCustomer() {
        return adCustomer;
    }

    public void setAdCustomer(String adCustomer) {
        this.adCustomer = adCustomer == null ? null : adCustomer.trim();
    }

    public String getAdManager() {
        return adManager;
    }

    public void setAdManager(String adManager) {
        this.adManager = adManager == null ? null : adManager.trim();
    }

    public String getAdRemarks() {
        return adRemarks;
    }

    public void setAdRemarks(String adRemarks) {
        this.adRemarks = adRemarks == null ? null : adRemarks.trim();
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason == null ? null : rejectReason.trim();
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName == null ? null : adName.trim();
    }

    public Date getDisplayTimeStart() {
        return displayTimeStart;
    }

    public void setDisplayTimeStart(Date displayTimeStart) {
        this.displayTimeStart = displayTimeStart;
    }

    public Date getDisplayTimeEnd() {
        return displayTimeEnd;
    }

    public void setDisplayTimeEnd(Date displayTimeEnd) {
        this.displayTimeEnd = displayTimeEnd;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}