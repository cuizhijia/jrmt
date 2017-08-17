package com.swift.jrmt.common.service.impl;

import com.swift.jrmt.common.dao.IBaseDao;

public abstract class BaseServiceImpl<T> {
    // 增
    public int insertSelective(T model) {
        return getDao().insertSelective(model);
    }

    // 根据Id删除
    public int deleteByPrimaryKey(Long id) {
        return getDao().deleteByPrimaryKey(id);
    }

    // 根据传入对象ID修改
    public int updateByPrimaryKey(T model) {
        return getDao().updateByPrimaryKey(model);
    }
    
    // 根据传入对象ID,选择性修改
    public int updateByPrimaryKeySelective(T model) {
        return getDao().updateByPrimaryKeySelective(model);
    }

    // 根据Id查询
    public T selectByPrimaryKey(Long id) {
        T cargoCatalog = (T) getDao().selectByPrimaryKey(id);
        return cargoCatalog;
    }
/**
 * 子类中必须要覆盖实现
 */
    public abstract IBaseDao<T> getDao();
}
