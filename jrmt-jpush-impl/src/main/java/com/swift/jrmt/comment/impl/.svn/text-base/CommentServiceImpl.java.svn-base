package com.swift.jrmt.comment.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.swift.jrmt.comment.dao.CommentDao;
import com.swift.jrmt.comment.dto.CommentAppDto;
import com.swift.jrmt.comment.dto.CommentCrmDto;
import com.swift.jrmt.comment.model.Comment;
import com.swift.jrmt.comment.service.CommentService;
import com.swift.jrmt.common.dao.IBaseDao;
import com.swift.jrmt.common.service.impl.BaseServiceImpl;
import com.swift.jrmt.common.utils.IDGenerator;

@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment> implements CommentService {

	@Resource
	private CommentDao commentDao;
	
	/*************************************APP**********************************/
	/**
	 * 保存资询评论
	 */
	@Override
	public boolean saveComment(Comment comment) {
		if(comment == null||comment.getInformationId()==null){
			return false;
		}
		//评论初始化状态 默认1，-1 已删除，0冻结与下架，4已发布与上架与解冻
		Comment comm = new Comment();
		comm.setId(IDGenerator.getUniqueID());
		comm.setCreated(new Date());
		comm.setUpdated(new Date());
		comm.setPubTime(new Date());
		comm.setDeleted(false);
		comm.setInformationId(comment.getInformationId());
		comm.setUserId(comment.getUserId() == null?0L:comment.getUserId());
		comm.setStatus(1);
		comm.setContent(comment.getContent() == null?"":comment.getContent());
		commentDao.insertSelective(comm);
		return true;
	}
	
	/**
	 * 获取资讯评论列表
	 */
	@Override
	public Map<String,Object> getCommentList(long infoId, String versionId, int limit, int page) {
			//1.初始化返回参数
			Map<String,Object> resultMap = new HashMap<String,Object>();
			//2. 参取指定pagesize pageno 的评论列表
			Comment comment = new Comment();
			comment.setPageNo(page);
			comment.setPageSize(limit);
			comment.setStatus(4);
			comment.setInformationId(infoId);
			List<CommentAppDto> comments = commentDao.getCommentListPage(comment);
			//3.获取给定资讯评论的总条数
			int totalNum = comment.getTotalCount();
			//4.当内容压入map 集合中
			resultMap.put("commentList", comments);
			resultMap.put("commentCount",totalNum );
			return resultMap;			
	}
	
	/*************************************CRM**********************************/
	/**
	 * crm 端获取资询列表
	 */
	@Override
	public Map<String, Object> crmGetCommentList(CommentCrmDto commentCrmDto) {
		List<CommentCrmDto> commentList = commentDao.crmGetCommentListPage(commentCrmDto);
		Integer commentCount = commentCrmDto.getTotalCount();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("commentList", commentList);
		map.put("commentCount", commentCount);
		return map;
	}
	
	@Override
	public IBaseDao<Comment> getDao() {
		
		return commentDao;
	}


	
	
}
