package com.swift.jrmt.information.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.InformationDto;
import com.swift.jrmt.information.model.Information;

/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : Information
 * 功能  :  InformationDao数据库操作接口类
 * 
 **/

public interface InformationDao   extends IBaseDao<Information>{

	List<InformationDto> findInformationListPage(InformationDto model);

	InformationDto findInformationModelById(Long id);

}