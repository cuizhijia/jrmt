package com.swift.jrmt.information.dao;


import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.information.dto.InformationStatDto;
import com.swift.jrmt.information.model.InformationStat;

/**
 * 日期  : 2016-08-01
 * 作者  : YJB
 * 项目  : InformationStat
 * 功能  :  InformationStatDao数据库操作接口类
 * 
 **/

public interface InformationStatDao   extends IBaseDao<InformationStat>{

	void updateInformationStat(InformationStatDto model);

	InformationStatDto findByModel(InformationStatDto model);


}