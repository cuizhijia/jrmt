package com.swift.jrmt.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.swift.jrmt.common.constants.Constants;
import com.swift.jrmt.information.model.Information;
import com.swift.jrmt.information.service.InformationService;
import com.swift.jrmt.jpush.model.Push;
import com.swift.jrmt.jpush.service.PushService;
import com.swift.jrmt.tools.JrmtMessageUtil;



/**
 * 
 * 说明:发布订单发送消息，定时器任务
 * 发送站内信+极光推送+短消息
 * 
 * @author denny.zhang
 * 
 */
@Component
@Controller
public class SpringOrderMessageTask {

	private Logger logger = LoggerFactory.getLogger(SpringOrderMessageTask.class);
	@Resource
	private PushService pushService;
	@Resource
	private InformationService informationService;

	
	/**
	 * 一分钟扫描一次push推送表,查询预定发布时间比当前时间大的推送，
	 *
	 */
	@Scheduled(cron = "0 0/5 * * * ? ")
	public void contractPlanPublishTask() {

		//查询预定发布时间比当前时间大的推送
		List<Push> queryPushByPt = pushService.queryPushByPt();
		
		logger.info("预定发布时间比当前时间大的推送条数："+queryPushByPt.size());
		//遍历每个定时任务
		for (Push push : queryPushByPt) {
			Information information = informationService.selectByPrimaryKey(push.getInformationId());
			Map<String, String> map = new HashMap<String, String>();
			map.put("state", "6");
			map.put("informationId", push.getInformationId().toString());
			JrmtMessageUtil.sendJPush(Constants.MSG_TITLE, information.getTitle(),map);
			push.setStatus(4);
			pushService.updateByPrimaryKeySelective(push);
		}
			
			
			
			
	}
}
