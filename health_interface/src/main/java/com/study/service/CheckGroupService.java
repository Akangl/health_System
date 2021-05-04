package com.study.service;

import com.study.domain.CheckGroup;
import com.study.domain.CheckItem;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;

import java.util.List;

/**
 * @author 12551
 */
public interface CheckGroupService {
    /**
     * 查询所有检查项信息
     *
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 添加检查组
     *
     * @param checkitemIds
     * @param checkGroup
     */
    void add(Integer[] checkitemIds, CheckGroup checkGroup);

    /**
     * 分页查询；根据条件分页查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * 根据Id获取对应检查组信息
     *
     * @param id
     * @return
     */
    CheckGroup findById(Integer id);

    /**
     * 编辑检查组信息
     *
     * @param checkGroup
     */
    void edit(Integer[] checkitemIds,CheckGroup checkGroup);

    /**
     * 根据检查组Id获取到对应检查项信息
     *
     * @param id
     * @return
     */
    List<Integer> findCheckitemIdsByCheckgroupId(Integer id);

    void delete(Integer id);
}
