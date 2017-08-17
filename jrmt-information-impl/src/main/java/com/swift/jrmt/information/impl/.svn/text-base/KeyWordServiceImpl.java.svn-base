package com.swift.jrmt.information.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.IDGenerator;
import com.swift.jrmt.information.dao.KeyWordDao;
import com.swift.jrmt.information.dto.KeyWordDto;
import com.swift.jrmt.information.model.KeyWord;
import com.swift.jrmt.information.service.KeyWordService;

@Service("keyWordService")
public class KeyWordServiceImpl extends BaseServiceImpl<KeyWord> implements KeyWordService{

	@Resource
	public KeyWordDao keyWordDao;

	@Override
	public IBaseDao<KeyWord> getDao() {
		return keyWordDao;
	}

	@Override
	public void saveNewKeyWord(String title,Long userId) {
		KeyWord keyWord = new KeyWord();
		keyWord.setId(IDGenerator.getUniqueID());
		keyWord.setUpdated(new Date());
		keyWord.setCreated(new Date());
		keyWord.setDeleted(false);
		keyWord.setTitle(title);
		keyWord.setUserId(userId);
		keyWord.setPubUid(userId);
		keyWord.setPubTime(new Date());
		keyWord.setStatus(4);//-1删除，0冻结，4正常
		keyWordDao.insertSelective(keyWord);
	}

	@Override
	public List<KeyWordDto> findKeyWordList(KeyWordDto model,Integer pageSize,Integer pageNo) {
		List<KeyWordDto> kwList = new ArrayList<KeyWordDto>();
		model.setPageNo(pageNo);
		model.setPageSize(pageSize);
		kwList = keyWordDao.findKeyWordListPage(model);
		kwList.add(model);
		return kwList;
	}

}
