package com.swift.jrmt.comment.model;

import java.util.Date;

import com.swift.jrmt.common.model.BaseModel;
/**
 * 1.评论实体
 * @author fengyanwei
 *
 */
public class Comment extends BaseModel{
   
	/**
	 * 
	 */
	private static final long serialVersionUID = 5188288099212751749L;

	/**资讯id*/
	private Long informationId;

	/**创建人id*/
    private Long userId;

    /**发布人id(审核人)*/
    private Long pubUid;

    /**状态，默认1，-1已删除，0冻结与下架，4已发布与上架与解冻*/
    private Integer status;

    /**评论内容*/
    private String content;

    /**发布时间(审核时间)*/
    private Date pubTime;


    public Long getInformationId() {
        return informationId;
    }

    public void setInformationId(Long informationId) {
        this.informationId = informationId;
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

	public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }
}