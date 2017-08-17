package com.swift.jrmt.common.mongodb;

import java.util.LinkedHashMap;

/**
 * 排序封装类
 *
 * @author denny.zhang
 * 2015年8月28日
 */
public class OrderBean {

	private LinkedHashMap<String, Boolean> orderby = new LinkedHashMap<String, Boolean>();

	public OrderBean(){}

	public OrderBean(String field , boolean isAsc){
		orderby.put(field, isAsc);
	}

	public OrderBean add(String field , boolean isAsc){
		this.orderby.put(field, isAsc);
		return this;
	}

	public static OrderBean order(String field , boolean isAsc){
		OrderBean o = new OrderBean(field, isAsc);
		return o;
	}

	public void remove(String field){
		this.orderby.remove(field);
	}

	public String toSQL(String alias){
		alias = alias == null ? "" :
			(alias.endsWith(".") ? alias : alias + ".");
		StringBuffer result = new StringBuffer("");
		if(orderby != null && orderby.size() > 0){
			result.append(" order by ");
			for(String key : orderby.keySet()){
				result.append(alias).append(key).append(" ").append(orderby.get(key) ? "asc" : "desc").append(",");
			}
			result.deleteCharAt(result.length()-1);
		}
		return result.toString();
	}

	public void clear(){
		this.orderby.clear();
	}

	public int fieldsTotal(){
		return this.orderby.size();
	}


	public LinkedHashMap<String, Boolean> getOrderValue() {
		return orderby;
	}

	public static void main(String[] args) {
		System.out.println("a1".toUpperCase());
		for (int i = 0; i < 200; i++) {
			System.out.print((char)i);
		}
	}
}
