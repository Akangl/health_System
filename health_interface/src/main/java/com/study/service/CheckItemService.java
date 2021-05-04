package com.study.service;

import com.study.domain.CheckItem;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;

/**
 * @author 12551
 */
public interface CheckItemService {
    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 分页查询+条件分页查询
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项（根据ID进行删除）
     * @param id
     */
    void delete(Integer id);

    /**
     *根据Id查找检查项，并返回
     * @param id
     * @return
     */
    CheckItem findById(Integer id);

    /**
     *修改检查项
     * @param checkItem
     */
    void edit(CheckItem checkItem);
}
