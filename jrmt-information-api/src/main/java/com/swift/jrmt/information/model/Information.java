package com.swift.jrmt.information.model;

import com.swift.jrmt.common.model.BaseModel;


/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : Information
 * 功能  :  资讯
 * 
 **/

public class Information  extends BaseModel {

	
	private static final long serialVersionUID = -3216463351528615629L;

	/**频道id**/
	private Long channelId;

	/**作者Id(admin_user)**/
	private Long userId;

	/**审核人id(admin_user)**/
	private Long auditorId;

	/**发布人id(admin_user)**/
	private Long pubUid;

	/**状态，默认1，-1已删除，0冻结与下架，1待审核，2审核不通过，3待发布与通过，4已发布与上架与解冻**/
	private Integer status;

	/**文章标题**/
	private String title;

	/**摘要**/
	private String remark;

	/**缩略图地址**/
	private String picUrl;

	/**视频地址**/
	private String vedio;

	/**文章来源，关联media_id**/
	private Long mediaId;

	/**文章关键词ids**/
	private String keywords;

	/**文章标签id**/
	private Long tagId;

	/**投放省**/
	private String provinceId;

	/**投放市区**/
	private String cityId;

	/**投放区域**/
	private String areaId;

	/**0不置顶，1置顶**/
	private Integer isTop;

	/**0不推荐，1推荐**/
	private Integer recommend;

	/**显示顺序，数值越高，显示越靠前**/
	private Integer rank;

	/**本文章内容H5链接**/
	private String contentLink;

	/**生成H5分享页面**/
	private String shareLink;

	/**是否本地，2:本地，0:非本地**/
	private Integer isLocal;

	/**不通过原因**/
	private String rejectReason;

	/**审核时间**/
	private java.util.Date auditTime;

	/**发布时间**/
	private java.util.Date pubTime;



	public void setChannelId(Long channelId){
		this.channelId = channelId;
	}

	public Long getChannelId(){
		return this.channelId;
	}

	public void setUserId(Long userId){
		this.userId = userId;
	}

	public Long getUserId(){
		return this.userId;
	}

	public void setAuditorId(Long auditorId){
		this.auditorId = auditorId;
	}

	public Long getAuditorId(){
		return this.auditorId;
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

	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getEemark(){
		return this.remark;
	}

	public void setPicUrl(String picUrl){
		this.picUrl = picUrl;
	}

	public String getPicUrl(){
		return this.picUrl;
	}

	public void setVedio(String vedio){
		this.vedio = vedio;
	}

	public String getVedio(){
		return this.vedio;
	}

	public void setMediaId(Long mediaId){
		this.mediaId = mediaId;
	}

	public Long getMediaId(){
		return this.mediaId;
	}

	public void setKeywords(String keywords){
		this.keywords = keywords;
	}

	public String getKeywords(){
		return this.keywords;
	}

	public void setTagId(Long tagId){
		this.tagId = tagId;
	}

	public Long getTagId(){
		return this.tagId;
	}

	public void setProvinceId(String provinceId){
		this.provinceId = provinceId;
	}

	public String getProvinceId(){
		return this.provinceId;
	}

	public void setCityId(String cityId){
		this.cityId = cityId;
	}

	public String getCityId(){
		return this.cityId;
	}

	public void setAreaId(String areaId){
		this.areaId = areaId;
	}

	public String getAreaId(){
		return this.areaId;
	}

	public void setIsTop(Integer isTop){
		this.isTop = isTop;
	}

	public Integer getIsTop(){
		return this.isTop;
	}

	public void setRecommend(Integer recommend){
		this.recommend = recommend;
	}

	public Integer getRecommend(){
		return this.recommend;
	}

	public void setRank(Integer rank){
		this.rank = rank;
	}

	public Integer getRank(){
		return this.rank;
	}

	public void setContentLink(String contentLink){
		this.contentLink = contentLink;
	}

	public String getContentLink(){
		return this.contentLink;
	}

	public void setShareLink(String shareLink){
		this.shareLink = shareLink;
	}

	public String getShareLink(){
		return this.shareLink;
	}

	public void setIsLocal(Integer isLocal){
		this.isLocal = isLocal;
	}

	public Integer getIsLocal(){
		return this.isLocal;
	}

	public void setRejectReason(String rejectReason){
		this.rejectReason = rejectReason;
	}

	public String getRejectReason(){
		return this.rejectReason;
	}

	public void setAuditTime(java.util.Date auditTime){
		this.auditTime = auditTime;
	}

	public java.util.Date getAuditTime(){
		return this.auditTime;
	}

	public void setPubTime(java.util.Date pubTime){
		this.pubTime = pubTime;
	}

	public java.util.Date getPubTime(){
		return this.pubTime;
	}

	public String getRemark() {
		return remark;
	}

}
