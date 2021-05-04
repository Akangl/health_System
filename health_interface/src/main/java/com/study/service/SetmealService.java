package com.study.service;

import com.study.domain.CheckGroup;
import com.study.domain.Setmeal;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;

import java.util.List;

public interface SetmealService {

    /**
     * 查询所有检查组信息
     *
     * @return 检查组信息集合
     */
    List<CheckGroup> findCheckgroupAll();

    /**
     * 新增套餐
     *
     * @param checkgroupIds 套餐对应的检查组ID
     * @param setmeal       新增套餐参数
     */
    void add(Integer[] checkgroupIds, Setmeal setmeal);

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    PageResult findPage(QueryPageBean queryPageBean);

    /**
     * @param id
     * @return
     */
    Setmeal findSetmealById(Integer id);

    /**
     * @param checkgroupIds
     * @param setmeal
     */
    void edit(Integer[] checkgroupIds, Setmeal setmeal);

    /**
     * 根据ID删除指定套餐
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * @param id
     * @return
     */
    List<Integer> findCheckgroupIdsBySetmealId(Integer id);

    /**
     * 移动端
     * 查询所有套餐
     *
     * @return
     */
    List<Setmeal> findAll();

    /**
     * 根据套餐id查询套餐信息：(套餐基本信息，套餐对应的检查组信息，检查组对应检查项信息)
     *
     * @param id
     * @return
     */
    Setmeal findByID(Integer id);
}
