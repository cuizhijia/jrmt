package com.swift.jrmt.common.service;

import com.swift.jrmt.common.dao.IBaseDao;

public interface   BaseService<T> {
    // 增
    public int insertSelective(T model) ;

    // 根据Id删除
    public int deleteByPrimaryKey(Long id) ;

    // 根据传入对象ID修改
    public int updateByPrimaryKey(T model);
    
    // 根据传入对象ID,选择性修改
    public int updateByPrimaryKeySelective(T model) ;

    // 根据Id查询
    public T selectByPrimaryKey(Long id) ;
/**
 * 子类中必须要覆盖实现
 */
    public abstract IBaseDao<T> getDao();
}
