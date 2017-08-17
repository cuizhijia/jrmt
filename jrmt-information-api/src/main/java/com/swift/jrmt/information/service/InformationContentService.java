package com.swift.jrmt.information.service;


import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.InformationContentDto;
import com.swift.jrmt.information.model.InformationContent;

public interface InformationContentService extends BaseService<InformationContent>{
	InformationContentDto findInformationContentModel(InformationContentDto model);
}
