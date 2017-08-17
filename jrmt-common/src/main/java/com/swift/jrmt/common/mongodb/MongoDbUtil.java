package com.swift.jrmt.common.mongodb;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.swift.jrmt.common.page.PageJrmt;
import com.swift.jrmt.common.utils.CollectionUtil;
import com.swift.jrmt.common.utils.StringUtil;

/**
 * mongoDB工具类
 *
 * @author denny.zhang
 * 2015年8月28日
 */
public class MongoDbUtil {
	
	/**
	 * 更新字段，hashmap转update
	 * @param values
	 * @return
	 */
	public static Update toUpdate( Map<String, Object> values ){
		Update update = new Update();
		for(Map.Entry<String, Object> value : values.entrySet()){
			update.set(value.getKey(), value.getValue());
		}
		return update;
	}
	
	/**
	 * 绑定分页
	 * @param entityName
	 * @param mongoOperations
	 * @param query
	 * @param page
	 */
	public static void bindPaging(Class<?> entityName, MongoOperations mongoOperations, Query query, PageJrmt page){
		if(page == null){
			return;
		}
		if(page.getTotalCount() == 0){
			page.setTotalCount((int)mongoOperations.count(query, entityName));
		}
		query.skip(page.getStartNum()).limit(page.getPageSize());
	}
	
	/**
	 * 绑定排序
	 * @param query
	 * @param orderBean
	 */
	public static void bindOrders(Query query, OrderBean orderBean){
		if(orderBean == null){
			return;
		}
		LinkedHashMap<String, Boolean> ov = orderBean.getOrderValue();
		for(Map.Entry<String, Boolean> o : ov.entrySet()){
			query.with(new Sort(o.getValue() ? Sort.Direction.ASC : Sort.Direction.DESC, o.getKey()));
		}
	}
	
	/**
	 * 定制字段
	 * @param query
	 * @param fields 指定的字段，多个以*号隔开
	 */
	public static void bindFields(Query query, String fields){
		if(StringUtil.hasLengthBytrim(fields)){
			String[] fs = fields.split("\\*");
			for(String f : fs){
				query.fields().include(f);
			}
		}
	}
	
	public static void bindFields(Query query, Set<String> fields){
		if(CollectionUtil.isEmpty(fields) || "*".equals(fields.iterator().next())){
			return;
		}
		
		for(String f : fields){
			query.fields().include(f);
		}
	}

}
