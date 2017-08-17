package com.swift.jrmt.information.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.information.dao.InformationRightDao;
import com.swift.jrmt.information.model.InformationRight;
import com.swift.jrmt.information.service.InformationRightService;

@Service("informationRightService")
public class InformationRightServiceImpl extends BaseServiceImpl<InformationRight> implements InformationRightService{

	@Resource
	public InformationRightDao informationRightDao;
	
	@Override
	public IBaseDao<InformationRight> getDao() {
		return informationRightDao;
	}
}
