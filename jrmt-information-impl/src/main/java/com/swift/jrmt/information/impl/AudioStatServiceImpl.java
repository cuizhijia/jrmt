package com.swift.jrmt.information.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.information.dao.AudioStatDao;
import com.swift.jrmt.information.dto.AudioStatDto;
import com.swift.jrmt.information.model.AudioStat;
import com.swift.jrmt.information.service.AudioStatService;

@Service("audioStatService")
public class AudioStatServiceImpl extends BaseServiceImpl<AudioStat> implements AudioStatService {
	protected final Logger logger = LoggerFactory.getLogger(super.getClass());
	@Resource
	private AudioStatDao audioStatDao;
	@Override
	public IBaseDao<AudioStat> getDao() {
		return audioStatDao;
	}
	@Override
	public void updateAudioStat(AudioStatDto model) {
		// mark : 1、访问数  2、评论数   3、顶数    4、踩数     5、分享数     6、收藏数      7、播放数 
		
		AudioStatDto statModel = new AudioStatDto();
		AudioStatDto statModelNew = new AudioStatDto();
		statModel = audioStatDao.findByModel(model);
		if(statModel != null){
			if(model.getMark()==1){
				statModelNew.setVisitNum((statModel.getVisitNum()==null?0:statModel.getVisitNum())+1);
			}else if(model.getMark()==2){
				statModelNew.setCommentNum((statModel.getCommentNum()==null?0:statModel.getCommentNum())+1);
			}else if(model.getMark()==3){
				statModelNew.setPraiseNum((statModel.getPraiseNum()==null?0:statModel.getPraiseNum())+1);
			}else if(model.getMark()==4){
				statModelNew.setCriticalNum((statModel.getCriticalNum()==null?0:statModel.getCriticalNum())+1);
			}else if(model.getMark()==5){
				statModelNew.setShareNum((statModel.getShareNum()==null?0:statModel.getShareNum())+1);
			}else if(model.getMark()==6){
				statModelNew.setCollectNum((statModel.getCollectNum()==null?0:statModel.getCollectNum())+1);
			}else if(model.getMark()==7){
				statModelNew.setPlayNum((statModel.getPlayNum()==null?0:statModel.getPlayNum())+1);
			}
		}
		
		statModelNew.setAudioId(model.getAudioId());
		statModelNew.setUpdated(new Date());
		audioStatDao.updateAudioStat(statModelNew);
		
	}

}
