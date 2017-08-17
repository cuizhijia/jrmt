package com.swift.jrmt.selfmedia.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.swift.jrmt.common.exception.JrmtRuntimeException;
import com.swift.jrmt.common.utils.AssertUtil;
import com.swift.jrmt.common.vo.RpcResult;
import com.swift.jrmt.constants.AdminStatusCode;
import com.swift.jrmt.media.model.SelfMediaApply;
import com.swift.jrmt.media.service.SelfMediaApplyService;


@Controller
@RequestMapping("selfmedia")
public class SelfMediaController {

	Logger log = LoggerFactory.getLogger(SelfMediaController.class);
	
	@Resource(name = "selfMediaApplyService")
	private SelfMediaApplyService selfMediaApplyService;
	
	
	
	
	
	@RequestMapping(value="/insertselfmedia", method={RequestMethod.POST})
	public void insertSelfMedia(HttpServletRequest request, HttpServletResponse response,
								SelfMediaApply selfMediaApply
							   ) {
				// 初始化							
				RpcResult rpc = RpcResult.success();
				try {
					AssertUtil.isFalse(selfMediaApply==null, AdminStatusCode.admin_request_param_error);
					Boolean bool = selfMediaApplyService.insert(selfMediaApply);
					if(!bool) {
						rpc = RpcResult.fail();
					}	
				} catch (JrmtRuntimeException e) {
					log.error(e+"");
					rpc = RpcResult.status(e.getStatusCode());
				} catch (Exception e) {
					e.printStackTrace();
					rpc  = RpcResult.fail();
				}
				rpc.out(response);
		
	}
	
	
	
	
}
