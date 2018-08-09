package dao;

import uite.PageUtil;

import java.io.Serializable;
import java.util.List;

/**
 * 所有接口的父接口
 * 所有接口的公共方法
 */
public interface IBaseDao<T> {

    //父类的添加
    int add(T t);

    //父类的删除
    int deleteByCondition(Serializable id);

    //父类的修改
    int update(T t);

    //父类带条件查询
    T findByCondition(Serializable id);

    //父类查询全部
    List<T> findAll();

    int findRownum();

    //父类 分页信息
    List<T> findAllByPage(PageUtil util, Object... params);
}