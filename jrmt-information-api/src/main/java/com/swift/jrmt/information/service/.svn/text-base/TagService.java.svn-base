package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.TagDto;
import com.swift.jrmt.information.model.Tag;

public interface TagService  extends BaseService<Tag>{
	
	/**
	 * 标签列表查询
	 * @param model
	 * @return
	 */
	List<TagDto> findTagList(TagDto model,Integer pageSize,Integer pageNo);

	/**
	 * 添加标签
	 * @param title 标签名称，不能为Null
	 */
	void saveNewTag(String title,Long userId);

	
}
