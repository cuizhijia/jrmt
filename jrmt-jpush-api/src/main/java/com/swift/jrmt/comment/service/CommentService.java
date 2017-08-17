package com.swift.jrmt.comment.service;

import java.util.Map;

import com.swift.jrmt.comment.dto.CommentCrmDto;
import com.swift.jrmt.comment.model.Comment;
import com.swift.jrmt.common.service.BaseService;
/**
 * 2.资询评论 service接口层，
 * @author fengyanwei
 *
 */
public interface CommentService extends BaseService<Comment>{
	
	/**********************************APP******************************************************/
	/**
	 * 保存资询评论 
	 * @param comment 资询评论实体
	 * @return	true 保存成功,false 保存失败
	 */
	public boolean saveComment(Comment comment);
	
	/**
	 * 获取资讯列表
	 * @param infoId	资讯ID
	 * @param versionId		接收版本ID
	 * @param limit		每页的评论数据
	 * @param page		第几页
	 * @return
	 */
	public Map<String,Object> getCommentList(long infoId, String versionId, int limit, int page);

	
	/**********************************CRM******************************************************/
	/**
	 * @param commentCrmDto  crm 端评论实体
	 * @return 包含实体内容，和实体条数的map集合
	 */
	Map<String, Object> crmGetCommentList(CommentCrmDto commentCrmDto);
	
}
