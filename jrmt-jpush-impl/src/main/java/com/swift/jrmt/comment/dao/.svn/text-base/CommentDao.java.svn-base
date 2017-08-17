/**
 * 
 */
package com.swift.jrmt.comment.dao;

import java.util.List;

import com.swift.jrmt.comment.dto.CommentAppDto;
import com.swift.jrmt.comment.dto.CommentCrmDto;
import com.swift.jrmt.comment.model.Comment;
import com.swift.jrmt.common.dao.IBaseDao;

/**
 * 1.资询评论dao 层
 * @author fengyanwei
 *
 */
public interface CommentDao extends IBaseDao<Comment> {
	/***************************************移动端******************************************/
	/**
	 * 获取资讯评论列表
	 * @param comment
	 * @return
	 */
	public List<CommentAppDto> getCommentListPage(Comment comment);
	
	
	/***************************************CRM******************************************/
	public List<CommentCrmDto> crmGetCommentListPage(CommentCrmDto commentCrmDto);
}
