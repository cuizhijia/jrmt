package com.swift.jrmt.person.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.swift.jrmt.common.constants.Constants;
import com.swift.jrmt.common.exception.JrmtRuntimeException;
import com.swift.jrmt.common.utils.AssertUtil;
import com.swift.jrmt.common.vo.RpcResult;
import com.swift.jrmt.constants.AdminStatusCode;
import com.swift.jrmt.user.model.UserInfo;
import com.swift.jrmt.user.service.UserInfoService;


@Controller
@RequestMapping("/user")
public class UserController {
	Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Resource(name="userInfoService")
	private UserInfoService userInfoService;
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 */
	@RequestMapping(value="/thirdAppLogin", method={RequestMethod.POST})
	public void thirdAppLogin(HttpServletRequest request, HttpServletResponse response,
							 @RequestParam(value="user_id") String userId,
							 UserInfo userInfo
							 ) {
		log.info("登录信息:"+userInfo);
		// 初始化
		RpcResult rpc = RpcResult.success();
		try {
			String avatar = "";
			String nickname = "";
			
			AssertUtil.notNull(userId, AdminStatusCode.admin_request_param_error);
			UserInfo user = userInfoService.selectByUserId(userId);
			if(user==null) {
				Boolean bool = userInfoService.insert(userInfo);
				AssertUtil.isFalse(!bool, AdminStatusCode.failed);
				nickname = userInfo.getNickname();
				avatar = userInfo.getAvatar();
			} else {
				nickname = user.getNickname();
				// 判断是否修改过
				if(user.getAvatar().contains("http")) {
					avatar = userInfo.getAvatar();
				} else {
					avatar = Constants.SERVER_PATH+user.getAvatar();
				}
			}
			// 返回头像和昵称
			rpc.addDatabody("avatar", avatar)
			   .addDatabody("nickname", nickname);
		} catch (JrmtRuntimeException e) {
			log.error(e+"");
			rpc = RpcResult.status(e.getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
			rpc = RpcResult.fail();
		}
		rpc.out(response);
		
	}
	
	
	/**
	 * 修改头像 或 昵称
	 * @param request
	 * @param response
	 * @param userInfo
	 */
	@RequestMapping(value="/updateuserinfo", method={RequestMethod.POST})
	public void updateUserinfo(HttpServletRequest request, HttpServletResponse response,
							  @RequestParam(value="avatar", required=false) String avatar,
							  @RequestParam(value="nickname", required=false) String nickname, 
							  @RequestParam(value="userId", required=true) String userId
							 ) {
		// 初始化
		RpcResult rpc = RpcResult.success();
		try {
			Boolean bool = userInfoService.updateAvatarOrnickname(avatar, nickname, userId);
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
