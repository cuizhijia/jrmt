package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.IDGenerator;
import com.swift.jrmt.information.dao.AudioDao;
import com.swift.jrmt.information.dao.AudioStatDao;
import com.swift.jrmt.information.dto.AudioDto;
import com.swift.jrmt.information.dto.AudioStatDto;
import com.swift.jrmt.information.model.Audio;
import com.swift.jrmt.information.model.AudioStat;
import com.swift.jrmt.information.service.AudioService;
@Service("audioService")
public class AudioServiceImpl extends BaseServiceImpl<Audio> implements AudioService {
	protected final Logger logger = LoggerFactory.getLogger(super.getClass());
	@Resource
	private AudioDao audiodao;
	@Resource
	private AudioStatDao audioStatDao;
	
	@Override
	public IBaseDao<Audio> getDao() {
		return audiodao;
	}
	@Override
	public List<AudioDto> findAudioList(AudioDto model, Integer pageSize,
			Integer pageNo) {
		List<AudioDto> list = new ArrayList<AudioDto>();
		model.setPageNo(pageNo);
		model.setPageSize(pageSize);
		list =audiodao.findAudioListPage(model);
		list.add(model);
		return list;
	}
	@Override
	public void saveAudio(String title, Long userId, String pic_url,String audio_url, String remark) {
		Audio audio = new Audio();
		audio.setId(IDGenerator.getUniqueID());
		audio.setCreated(new Date());
		audio.setDeleted(false);
		audio.setUpdated(new Date());
		audio.setTitle(title);
		audio.setStatus(1);//状态，默认1，-1已删除，0冻结与下架，1待审核，2审核不通过，3待发布与通过，4已发布与上架与解冻
		audio.setPicUrl(pic_url);
		audio.setAudioUrl(audio_url);
		audio.setRemark(remark);
		audio.setUserId(userId);
		audio.setPubUid(userId);
		audio.setPubTime(new Date());
		audiodao.insertSelective(audio);
		
		
		AudioStatDto statModel = new AudioStatDto();
		AudioStatDto statModelFind = new AudioStatDto();
		statModel.setAudioId(audio.getId());
		statModelFind = audioStatDao.findByModel(statModel);
		if(statModelFind != null){
			logger.info("audio评论数据以存在，不重复添加");
		}else{
			AudioStat audioStat = new AudioStat();
			audioStat.setId(IDGenerator.getUniqueID());
			audioStat.setCreated(new Date());
			audioStat.setDeleted(false);
			audioStat.setUpdated(new Date());
			audioStat.setAudioId(audio.getId());
			audioStat.setPlayNum(0);
			audioStat.setVisitNum(0);
			audioStat.setCommentNum(0);
			audioStat.setPraiseNum(0);
			audioStat.setCriticalNum(0);
			audioStat.setShareNum(0);
			audioStat.setCollectNum(0);
			audioStatDao.insertSelective(audioStat);
		}
		
	}
	
	@Override
	public void updateAudio(Audio model, Integer markUpdate,Long userId) {
		//markUpdate 1、编辑 2、冻结 、下架3、解冻、上架4、审核
		model.setUpdated(new Date());
		if(markUpdate == 1){
			audiodao.updateByPrimaryKeySelective(model);
		}else if(markUpdate == 4){//审核
			model.setAuditorId(userId);
			model.setAuditTime(new Date());
			audiodao.updateByPrimaryKeySelective(model);
		}else{
			model.setPubUid(userId);
			model.setPubTime(new Date());
			audiodao.updateByPrimaryKeySelective(model);
		}
		
	}
	@Override
	public Audio findAudioModelById(Long id) {
		return audiodao.selectByPrimaryKey(id);
	}

}
