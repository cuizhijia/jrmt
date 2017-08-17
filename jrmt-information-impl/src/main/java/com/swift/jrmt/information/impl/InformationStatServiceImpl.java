package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.information.dao.InformationStatDao;
import com.swift.jrmt.information.dto.InformationStatDto;
import com.swift.jrmt.information.model.InformationStat;
import com.swift.jrmt.information.service.InformationStatService;

@Service("informationStatService")
public class InformationStatServiceImpl  extends BaseServiceImpl<InformationStat> implements InformationStatService{

	@Resource
	public InformationStatDao informationStatDao;
	
	@Override
	public IBaseDao<InformationStat> getDao() {
		return informationStatDao;
	}

	@Override
	public void updateInformationStat(InformationStatDto model) {
		
		// mark :1、访问数(真实的访问数)    2、评论数   3、顶数    4、踩数     5、分享数    6、收藏数 
		
		InformationStatDto statModel = new InformationStatDto();
		InformationStatDto statModelNew = new InformationStatDto();
		statModel = informationStatDao.findByModel(model);
		if(statModel != null){
			if(model.getMark()==1){
				statModelNew.setVisitNum((statModel.getVisitNum()==null?0:statModel.getVisitNum())+1);
				statModelNew.setvNum((statModel.getvNum()==null?0:statModel.getvNum())+1);
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
			}
		}
		
		statModelNew.setInformationId(model.getInformationId());
		statModelNew.setUpdated(new Date());
		informationStatDao.updateInformationStat(statModelNew);
	}
}
