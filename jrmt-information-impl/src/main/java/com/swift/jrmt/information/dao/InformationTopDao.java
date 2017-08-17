package com.swift.jrmt.information.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.InformationTopDto;
import com.swift.jrmt.information.model.InformationTop;

/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : InformationTop
 * 功能  :  InformationTopDao数据库操作接口类
 * 
 **/

public interface InformationTopDao   extends IBaseDao<InformationTop>{

	List<InformationTopDto> findInformationTopListPage(InformationTopDto model);

}