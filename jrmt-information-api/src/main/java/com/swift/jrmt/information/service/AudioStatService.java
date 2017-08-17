package com.swift.jrmt.information.service;

import com.swift.jrmt.common.service.BaseService;
import com.swift.jrmt.information.dto.AudioStatDto;
import com.swift.jrmt.information.model.AudioStat;

public interface AudioStatService  extends BaseService<AudioStat> {

	void updateAudioStat(AudioStatDto model);

}
