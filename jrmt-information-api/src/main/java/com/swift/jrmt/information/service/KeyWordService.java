package com.swift.jrmt.information.service;

import java.util.List;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.KeyWordDto;
import com.swift.jrmt.information.model.KeyWord;

public interface KeyWordService extends BaseService<KeyWord>{

	/**
	 * 添加新关键词
	 * @param title 关键词名称 ,不能为Null
	 */
	void saveNewKeyWord(String title,Long userId);
	
	
	/**
	 * 关键词列表查询
	 * @param model
	 * @return
	 */
	List<KeyWordDto> findKeyWordList(KeyWordDto model,Integer pageSize,Integer pageNo);

}
