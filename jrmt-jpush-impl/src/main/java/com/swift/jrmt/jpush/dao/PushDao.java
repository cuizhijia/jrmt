package com.swift.jrmt.jpush.dao;

import java.util.List;

import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.page.PageJrmt;
import com.swift.jrmt.jpush.model.Push;

public interface PushDao extends IBaseDao<Push> {

	List<Push> queryPushListPage(PageJrmt pageJrmt);
	
	List<Push> queryPushByPt();

}
