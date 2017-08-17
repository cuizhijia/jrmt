package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.IDGenerator;
import com.swift.jrmt.information.dao.TagDao;
import com.swift.jrmt.information.dto.TagDto;
import com.swift.jrmt.information.model.Tag;
import com.swift.jrmt.information.service.TagService;

@Service("tagService")
public class TagServiceImpl extends BaseServiceImpl<Tag> implements TagService {
	
	@Resource
	public TagDao tagDao;
	
	@Override
	public IBaseDao<Tag> getDao() {
		return tagDao;
	}

	@Override
	public List<TagDto> findTagList(TagDto model,Integer pageSize,Integer pageNo) {
		List<TagDto> tagList = new ArrayList<TagDto>();
		model.setPageNo(pageNo);
		model.setPageSize(pageSize);
		tagList = tagDao.findTagListPage(model);
		tagList.add(model);
		return tagList;
	}

	@Override
	public void saveNewTag(String title,Long userId) {
		Tag model = new Tag();
		model.setId(IDGenerator.getUniqueID());
		model.setUpdated(new Date());
		model.setCreated(new Date());
		model.setDeleted(false);
		model.setTitle(title);
		model.setUserId(userId);		
		model.setPubTime(new Date());
		model.setStatus(4);//-1删除，0冻结，4正常
		tagDao.insertSelective(model);
		
	}

}
