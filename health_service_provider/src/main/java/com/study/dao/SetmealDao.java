package com.study.dao;

import com.github.pagehelper.Page;
import com.study.domain.CheckGroup;
import com.study.domain.Setmeal;

import java.util.List;
import java.util.Map;

public interface SetmealDao {
    /**
     * 查询所有检查组信息
     *
     * @return 检查组信息集合
     */
    List<CheckGroup> findCheckgroupAll();

    /**
     * 新增检查套餐
     *
     * @param setmeal 检查套餐参数
     */
    void addSetmeal(Setmeal setmeal);

    /**
     * 新增数据到t_setmeal_checkgroup setmealID <==> checkgroupID
     *
     * @param map
     */
    void addSetmealCheckgroup(Map map);

    /**
     * @param queryString
     * @return
     */
    Page<Setmeal> selectByCondition(String queryString);

    /**
     * 根据setmealId查询t_setmeal表中对应的数据
     *
     * @param id
     * @return
     */
    Setmeal findSetmealById(Integer id);

    /**
     * 修改t_setmeal表中的数据
     *
     * @param setmeal
     */
    void updateSetmeal(Setmeal setmeal);

    /**
     * @param setmealId
     */
    void deleteSetmealCheckgroup(Integer setmealId);

    /**
     * 根据setmealID删除t_setmeal_checkgroup表中对应的checkgroupID
     *
     * @param setmealId
     * @return
     */
    long selectSetmealCheckgroupById(Integer setmealId);

    /**
     * 根据setmealID删除t_setmeal表中对应的数据
     *
     * @param setmealId
     */
    void deleteSetmeal(Integer setmealId);

    /**
     * 根据setmealId查询t_setmeal_checkgroup表中相对应的checkgroupID集合
     *
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
    Setmeal findById(Integer id);


    /**
     * @param checkgroup
     * @return
     */
    List<Integer> findCheckgroupCheckitemById(Integer checkgroup);
}
