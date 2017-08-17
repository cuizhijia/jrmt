package com.swift.jrmt.information.service;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.InformationStatDto;
import com.swift.jrmt.information.model.InformationStat;

public interface InformationStatService extends BaseService<InformationStat> {

	void updateInformationStat(InformationStatDto model);

}
