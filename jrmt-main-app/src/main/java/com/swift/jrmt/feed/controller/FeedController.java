package com.swift.jrmt.feed.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.swift.jrmt.common.exception.JrmtRuntimeException;
import com.swift.jrmt.common.vo.RpcResult;
import com.swift.jrmt.feedback.model.Feedback;
import com.swift.jrmt.feedback.service.FeedbackService;
import com.swift.jrmt.person.controller.UserController;

@Controller
@RequestMapping("/feed")
public class FeedController {

	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="feedbackService")
	private FeedbackService feedbackService;
	
	
	@RequestMapping(value="/addfeedback", method={RequestMethod.POST})
	public void addFeedback(HttpServletRequest request, HttpServletResponse response,
							  Feedback feedback
							 ) {
		// 初始化
		RpcResult rpc = RpcResult.success();
		try {
			Boolean bool = feedbackService.insert(feedback);
			if(!bool) {
				rpc = RpcResult.fail();
			}
		} catch (JrmtRuntimeException e) {
			log.error(e+"");
			rpc = RpcResult.status(e.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			rpc = RpcResult.fail();
		}
		rpc.out(response);
		
	}
}
