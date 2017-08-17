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
import com.swift.jrmt.information.dao.InformationContentDao;
import com.swift.jrmt.information.dao.InformationDao;
import com.swift.jrmt.information.dao.InformationStatDao;
import com.swift.jrmt.information.dto.InformationDto;
import com.swift.jrmt.information.dto.InformationStatDto;
import com.swift.jrmt.information.model.Information;
import com.swift.jrmt.information.model.InformationContent;
import com.swift.jrmt.information.model.InformationStat;
import com.swift.jrmt.information.service.InformationService;

@Service("informationService")
public class InformationServiceImpl  extends BaseServiceImpl<Information> implements InformationService{
	protected final Logger logger = LoggerFactory.getLogger(super.getClass());
	@Resource
	public InformationDao informationDao;
	@Resource
	public InformationContentDao informationContentDao;
	@Resource
	public InformationStatDao informationStatDao;
	@Override
	public IBaseDao<Information> getDao() {
		return informationDao;
	}

	@Override
	public void saveNewInformation(InformationDto information, Long userId) {
		information.setId(IDGenerator.getUniqueID());
		information.setCreated(new Date());
		information.setDeleted(false);
		information.setUpdated(new Date());
		information.setUserId(userId);
		information.setStatus(1);
		informationDao.insertSelective(information);
		//保存资讯内容
		InformationContent icontent = new InformationContent();
		icontent.setId(IDGenerator.getUniqueID());
		icontent.setCreated(new Date());
		icontent.setDeleted(false);
		icontent.setUpdated(new Date());
		icontent.setInformationId(information.getId());
		icontent.setContent(information.getContent());
		informationContentDao.insertSelective(icontent);
		//保存浏览数、评论数等
		
		InformationStatDto statModel = new InformationStatDto();
		InformationStatDto newInStat = new InformationStatDto();
		newInStat.setInformationId(information.getId());
		statModel = informationStatDao.findByModel(newInStat);
		if(statModel!=null){
			logger.info("资讯已存在不重复添加");
		}else{
			InformationStat istat = new InformationStat();
			istat.setId(IDGenerator.getUniqueID());
			istat.setCreated(new Date());
			istat.setDeleted(false);
			istat.setUpdated(new Date());
			istat.setInformationId(information.getId());
			istat.setVisitNum(0);
			istat.setCommentNum(0);
			istat.setPraiseNum(0);
			istat.setCriticalNum(0);
			istat.setShareNum(0);
			istat.setCollectNum(0);
			istat.setvNum(0);
			informationStatDao.insertSelective(istat);
		}
	}

	@Override
	public List<InformationDto> findInformationList(InformationDto model,
			Integer pageSize, Integer pageNo) {
		List<InformationDto> informationList = new ArrayList<InformationDto>();
		model.setPageNo(pageNo);
		model.setPageSize(pageSize);
		informationList = informationDao.findInformationListPage(model);
		System.out.println(model.getTotalCount());
		informationList.add(model);
		return informationList;
	}

	@Override
	public void updateInformation(Information model, Integer markUpdate,
			Long userId) {
		//markUpdate 1、编辑 2、冻结 、下架3、解冻、上架4、审核
		model.setUpdated(new Date());
		if(markUpdate == 1){
			informationDao.updateByPrimaryKeySelective(model);
		}else if(markUpdate == 4){//审核
			model.setAuditorId(userId);
			model.setPubUid(userId);
			model.setAuditTime(new Date());
			model.setPubTime(new Date());
			informationDao.updateByPrimaryKeySelective(model);
		}else{
			model.setPubUid(userId);
			model.setPubTime(new Date());
			informationDao.updateByPrimaryKeySelective(model);
		}
		
	}

	@Override
	public InformationDto findInformationModelById(Long id) {
		return informationDao.findInformationModelById(id);
	}
}
