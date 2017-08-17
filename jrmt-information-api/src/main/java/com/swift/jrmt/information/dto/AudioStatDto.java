package com.swift.jrmt.information.dto;

import com.swift.jrmt.information.model.AudioStat;

public class AudioStatDto extends AudioStat {

	/**
	 * 
	 */
	private static final long serialVersionUID = 563036640791207776L;
	//标记更新那个字段值：1、访问数(真实的访问数)   2、评论数   3、顶数    4、踩数     5、分享数     6、收藏数      7、播放数
		private Integer mark;

		public Integer getMark() {
			return mark;
		}

		public void setMark(Integer mark) {
			this.mark = mark;
		}
		
}
