package com.swift.jrmt.jpush.model;

import java.util.Date;

import com.swift.jrmt.common.model.BaseModel;



public class Push extends BaseModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7222634718237314709L;

	/**推送类型: 1 资讯， 2 广告， 3电台*/
	private Integer type;

	/**资讯id*/
    private Long informationId;

    /**创建人id*/
    private Long userId;

    /**发布人id*/
    private Long pubUid;

    /**该条推送状态 0 取消推送， 1 待推送， 4 已推送，5 即将推送（延迟推送）*/
    private Integer status;

    /**预定发布时间*/
    private Date pushTime;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

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

	public Date getPushTime() {
        return pushTime;
    }

    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }
}