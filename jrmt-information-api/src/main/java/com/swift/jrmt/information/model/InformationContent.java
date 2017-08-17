package com.swift.jrmt.information.model;
import com.swift.jrmt.common.model.BaseModel;


/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : InformationContent
 * 功能  :  资讯内容
 * 
 **/

public class InformationContent extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1471164566531304481L;

	/**资讯id**/
	private Long informationId;

	/**文章内容**/
	private String content;


	public void setInformationId(Long informationId){
		this.informationId = informationId;
	}

	public Long getInformationId(){
		return this.informationId;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

}
