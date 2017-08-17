package com.swift.jrmt.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.swift.jrmt.common.page.PageJrmt;


/**
 * 描述:<br>
 * 部分实体公共属性如创建时间，修改时间
 * @author howsun ->[howsun.zhang@gmail.com]
 * 2015年3月10日
 */
public abstract class BaseModel extends PageJrmt implements Serializable{

	private static final long serialVersionUID = -5919409541334409856L;

	protected Long id;
	
	/** 更新时间 **/
	protected Date updated;

	/** 创建时间 **/
	protected Date created;

	/** 是否已删除-> true:删除，false:未删除 **/
	protected Boolean deleted;

	/**
	 * 用于实体类承载其它数据，该数据不会持久到数据库
	 */
	protected Map<String, Object> attributes;
	
	
	//--------------------------------------------------------------------------
	
	public BaseModel() {
		super();
	}

	public BaseModel(Long id) {
		super();
		this.id = id;
	}

	public void addAttribute(String key, Object value){
		if(this.attributes == null){
			this.attributes = new HashMap<String, Object>();
		}
		this.attributes.put(key, value);
	}
	
	public Object getAttribute(String key){
		return this.attributes == null ? null : this.attributes.get(key);
	}
	
	
	//--------------------------------------------------------------------------
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
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

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	
	//--------------------------------------------------------------------------
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
