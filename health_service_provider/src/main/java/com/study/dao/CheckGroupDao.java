package com.study.dao;

import com.github.pagehelper.Page;
import com.study.domain.CheckGroup;
import com.study.domain.CheckItem;

import java.util.List;
import java.util.Map;

public interface CheckGroupDao {
    /**
     * 查询所有检查项信息
     *
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查组
     *
     * @param checkGroup
     */
    void addCheckGroup(CheckGroup checkGroup);


    /**
     * 新增检查项与检查组对应关系
     *
     * @param map
     */
    void addCheckGroupCheckitem(Map map);

    /**
     * @param queryString
     */
    Page<CheckGroup> findByCondition(String queryString);

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
    void edit(CheckGroup checkGroup);

    /**
     * 根据检查组Id获取到对应检查项信息
     *
     * @param id
     * @return
     */
    List<Integer> findCheckitemIdsByCheckgroupId(Integer id);

    /**
     * 根据对应检查组Id，删除该检查组下所有的检查项信息
     *
     * @param checkGroup
     */
    void deleteCheckitemIds(CheckGroup checkGroup);

    /**
     * 根据Id删除检查组对应的信息
     *
     * @param id 检查组Id
     */
    void deleteCheckGroupById(Integer id);

    /**
     * 查询当前检查组Id下是否存在检查项
     *
     * @param id 检查组Id
     * @return 当前检查组先的检查项总数
     */
    long selectCheckGroupCheckItemById(Integer id);

    /**
     * 根基检查组Id删除检查组-检查项对应关系表(t_checkgroup_checkitem)
     *
     * @param id 检查组Id
     */
    void deleteCheckGroupCheckItemById(Integer id);
}
