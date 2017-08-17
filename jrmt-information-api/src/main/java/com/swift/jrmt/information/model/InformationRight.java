package com.swift.jrmt.information.model;

import com.swift.jrmt.common.model.BaseModel;


/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : InformationRight
 * 功能  :  资讯权限
 * 
 **/

public class InformationRight extends BaseModel{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5456792966404689153L;

	/**资讯id**/
	private Long informationId;

	/**组id**/
	private Long groupId;

	/**用户id**/
	private Long userId;

	/**创建时间**/
	private java.util.Date cTime;

	/**修改时间**/
	private java.util.Date uTime;

	public Long getInformationId() {
		return informationId;
	}

	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public java.util.Date getcTime() {
		return cTime;
	}

	public void setcTime(java.util.Date cTime) {
		this.cTime = cTime;
	}

	public java.util.Date getuTime() {
		return uTime;
	}

	public void setuTime(java.util.Date uTime) {
		this.uTime = uTime;
	}
}
