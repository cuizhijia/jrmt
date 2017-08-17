package com.swift.jrmt.information.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.InformationContentDto;
import com.swift.jrmt.information.model.InformationContent;

/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : InformationContent
 * 功能  :  InformationContentDao数据库操作接口类
 * 
 **/

public interface InformationContentDao   extends IBaseDao<InformationContent>{

	InformationContentDto findInformationContentModel(InformationContentDto model);

}