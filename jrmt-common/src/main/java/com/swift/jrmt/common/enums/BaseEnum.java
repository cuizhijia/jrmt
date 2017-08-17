package com.swift.jrmt.common.enums;

/**
 * @Description: 枚举接口
 * @Author: zs
 * @CreateTime: 2015年8月20日 上午11:14:11
 * @Company: SFXD
 */
public interface BaseEnum<T> {
    
    public String getName();

    public int getOrdinal();

    public String getChineseName();

    public T parsing(String element);
}
