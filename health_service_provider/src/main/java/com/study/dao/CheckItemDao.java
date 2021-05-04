package com.study.dao;

import com.github.pagehelper.Page;
import com.study.domain.CheckItem;

/**
 * @author 12551
 */
public interface CheckItemDao {
    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页+条件查询
     * @param queryString
     * @return
     */
    Page<CheckItem> selectByCondition(String queryString);

    /**
     * 根据ID删除检查项
     * @param id
     */
    void delete(Integer id);

    /**
     * 查询数据库是否有当前检查项
     * @param id
     * @return
     */
    long findCountByCheckItemId(Integer id);

    /**
     * 查询数据库是否有当前检查项
     * @param id
     * @return
     */
    long findCountById(Integer id);

    /**
     *根据Id查询检查项，并返回
     * @param id
     * @return
     */
    CheckItem findById(Integer id);


    /**
     * 修改检查项
     * @param checkItem
     */
    void edit(CheckItem checkItem);
}
