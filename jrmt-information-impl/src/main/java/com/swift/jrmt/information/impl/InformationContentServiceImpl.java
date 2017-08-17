package com.swift.jrmt.information.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.information.dao.InformationContentDao;
import com.swift.jrmt.information.dto.InformationContentDto;
import com.swift.jrmt.information.model.InformationContent;
import com.swift.jrmt.information.service.InformationContentService;

@Service("informationContentService")
public class InformationContentServiceImpl  extends BaseServiceImpl<InformationContent>  implements InformationContentService {
	
	@Resource
	public InformationContentDao informationContentDao;
	
	@Override
	public IBaseDao<InformationContent> getDao() {
		return informationContentDao;
	}

	@Override
	public InformationContentDto findInformationContentModel(InformationContentDto model) {
		return informationContentDao.findInformationContentModel(model);
	}

	

}
