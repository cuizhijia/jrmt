package com.swift.jrmt.comment.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swift.jrmt.comment.dto.CommentAppDto;
import com.swift.jrmt.comment.model.Comment;
import com.swift.jrmt.comment.service.CommentService;
import com.swift.jrmt.common.constants.StatusCode;
import com.swift.jrmt.common.utils.DateUtils;
import com.swift.jrmt.common.vo.RpcResult;
import com.swift.jrmt.constants.JpushStatusCode;

/**
 * 资讯模块 5.资讯评论控制层
 * 
 * @author fengyanwei
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {
	// logger 日记工具
	final static Logger logger = LoggerFactory.getLogger(CommentController.class);

	@Resource(name = "commentService")
	private CommentService commentService;

	/**
	 * 保存资讯评论调用接口
	 * @param request
	 * @param response
	 * @param info_id  资讯ID
	 * @param user_id  用户ID
	 * @param content  评论内容
	 */
	@RequestMapping(value = "/saveComment", method = { RequestMethod.POST })
	public void saveComment(HttpServletRequest request, HttpServletResponse response, Long info_id, Long user_id,
			String content) {
		RpcResult result = null;

		if (info_id == null || info_id.equals("") || content == null || content.equals("") || user_id == null
				|| user_id.equals("")) {
			// 1.当资讯的id为空或零内容为空时，认为非法请求，
			result = RpcResult.status(JpushStatusCode.comment_save_prop_null_error);
			result.out(response);
			return;
		}
		// 2.创建要保存的资讯评论对象
		Comment comment = new Comment();
		comment.setInformationId(info_id);
		comment.setUserId(user_id);
		comment.setContent(content);
		try {
			// 3.保存评论
			if (commentService.saveComment(comment)) {
				// 保存成功
				result = RpcResult.status(JpushStatusCode.comment_save_succ);
			} else {
				// 保存失败
				result = RpcResult.status(JpushStatusCode.comment_save_unknow_error);
			}
		} catch (Exception e) {
			logger.error("保存资讯评论时出现错误------" + e);
			result = RpcResult.status(JpushStatusCode.comment_save_unknow_error);
			result.out(response);
		}
		result.out(response);
	}

	/**
	 * 获取资讯评论列表接口
	 */
	@RequestMapping(value = "/getCommentList", method = { RequestMethod.POST })
	public void getCommentList(HttpServletRequest request, HttpServletResponse response, long info_id,
			String version_id, int limit, int page) {
		RpcResult result = null;
		// 1.检验传递参数是否合法
		if (info_id == 0L || limit == 0) {
			result = RpcResult.status(JpushStatusCode.comment_getList_prop_null_error);
			result.out(response);
			logger.debug("传递的资讯id为0 、或设置的每次请求条数为0");
			return;
		}
		// 2.获取资讯评论列表
		List<CommentAppDto> commentList = null;
		try {
			Map<String, Object> map = commentService.getCommentList(info_id, version_id, limit, page);
			if (map == null) {
				// 获取内容出错
				result = RpcResult.status(StatusCode.failed);
				logger.debug("获取的资讯评论列表为空");
			} else {
				// 提取map内容
				// 评论列表
				Object list = map.get("commentList");
				if (list instanceof List) {
					commentList = extracted(list);
				} else {
					throw new Exception("获取的评论列表类型出现错误");
				}
				// 评论数
				int commentCount = (int) map.get("commentCount");
				result = RpcResult.status(StatusCode.success);
				// 组织返回json
				for (CommentAppDto comment : commentList) {
					result.addArray().addDatabody("comment_id", comment.getId().toString())
							.addDatabody("create_time",DateUtils.format(comment.getCreated(), DateUtils.PATTERN_YYYY_MM_DD_HH_MM_SS))
							.addDatabody("user_name", comment.getUserName())
							.addDatabody("content", comment.getContent());
				}
				result.addAttribute("count", commentCount);
			}
		} catch (Exception e) {
			result = RpcResult.status(StatusCode.failed);
			result.out(response);
			logger.error("获取资讯评论列表出错------" + e);
		}
		result.out(response);
	}

	@SuppressWarnings("unchecked")
	private List<CommentAppDto> extracted(Object list) {
		return (List<CommentAppDto>) list;
	}
}
