package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.information.dao.InformationTopDao;
import com.swift.jrmt.information.dto.InformationTopDto;
import com.swift.jrmt.information.model.InformationTop;
import com.swift.jrmt.information.service.InformationTopService;

@Service("informationTopService")
public class InformationTopServiceImpl  extends BaseServiceImpl<InformationTop> implements InformationTopService{

	@Resource
	public InformationTopDao informationTopDao;
	
	@Override
	public IBaseDao<InformationTop> getDao() {
		return informationTopDao;
	}

	@Override
	public List<InformationTopDto> findInformationTopList(
			InformationTopDto model, Integer pageSize, Integer pageNo) {
		model.setPageSize(pageSize);
		model.setPageNo(pageNo);
		List<InformationTopDto> list = new ArrayList<InformationTopDto>(); 
		list = informationTopDao.findInformationTopListPage(model);
		list.add(model);
		return list;
	}

	@Override
	public void updateInformationTop(Long id, Integer status, Long channelId,
			Long informationId,Long userId) {
		InformationTop inTop = new InformationTop();
		if(status == 1){
			inTop.setId(id);
			inTop.setStatus(status);
			inTop.setUpdated(new Date());
		}else if(status == 4){
			inTop.setId(id);
			inTop.setStatus(status);
			inTop.setUpdated(new Date());
			inTop.setPubTime(new Date());
			inTop.setPubUid(userId);
			inTop.setChannelId(channelId);
			inTop.setInformationId(informationId);
		}
		informationTopDao.updateByPrimaryKeySelective(inTop);
		
	}
}
