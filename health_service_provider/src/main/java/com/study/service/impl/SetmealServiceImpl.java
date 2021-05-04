package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.study.dao.SetmealDao;
import com.study.domain.CheckGroup;
import com.study.domain.Setmeal;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;
import com.study.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 12551
 */
@Service(interfaceClass = SetmealService.class)
@Transactional
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;


    /**
     * @param checkgroupIds 套餐对应的检查组ID
     * @param setmeal       新增套餐参数
     */
    public void add(Integer[] checkgroupIds, Setmeal setmeal) {
        setmealDao.addSetmeal(setmeal);
        Integer setmealId = setmeal.getId();
        System.out.println(setmealId);

        Map map = new HashMap<String, Integer>();

        if (checkgroupIds != null && checkgroupIds.length > 0) {
            System.out.println("hello");
            for (Integer checkgroupId : checkgroupIds) {
                map.put("checkgroupId", checkgroupId);
                map.put("setmealId", setmealId);
                setmealDao.addSetmealCheckgroup(map);
            }
        }
    }

    /**
     * @param id
     * @return
     */
    public List<Integer> findCheckgroupIdsBySetmealId(Integer id) {
        return setmealDao.findCheckgroupIdsBySetmealId(id);
    }

    /**
     * 根据套餐id查询套餐信息：(套餐基本信息，套餐对应的检查组信息，检查组对应检查项信息)
     *
     * @param id
     * @return
     */
    public Setmeal findByID(Integer id) {
        return setmealDao.findById(id);
    }

    /**
     * 移动端
     * 查询所有套餐
     *
     * @return
     */
    public List<Setmeal> findAll() {
        return setmealDao.findAll();
    }

    public void delete(Integer setmealId) {

        //先查询setmealId在t_setmeal_checkgroup是否存在值
        long count = setmealDao.selectSetmealCheckgroupById(setmealId);
        if (count > 0) {
            //根据setmeal Id 删除t_setmeal_checkgroup中所有数据
            setmealDao.deleteSetmealCheckgroup(setmealId);
        }

        //在删除t_setmeal表中对应ID的值
        setmealDao.deleteSetmeal(setmealId);
    }

    public void edit(Integer[] checkgroupIds, Setmeal setmeal) {
        //获取setmealId的值
        Integer setmealId = setmeal.getId();

        //修改setmeal表中信息
        setmealDao.updateSetmeal(setmeal);

        //先查询setmealId在t_setmeal_checkgroup是否存在值
        long count = setmealDao.selectSetmealCheckgroupById(setmealId);
        if (count > 0) {
            //根据setmeal Id 删除t_setmeal_checkgroup中所有数据
            setmealDao.deleteSetmealCheckgroup(setmealId);
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        //将新的t_setmeal_checkgroup值插入进去
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            for (Integer checkgroupId : checkgroupIds) {
                map.put("checkgroupId", checkgroupId);
                map.put("setmealId", setmealId);
                setmealDao.addSetmealCheckgroup(map);
            }
        }
    }

    public Setmeal findSetmealById(Integer id) {
        return setmealDao.findSetmealById(id);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer currentPage = queryPageBean.getCurrentPage();
        Integer pageSize = queryPageBean.getPageSize();
        String queryString = queryPageBean.getQueryString();
        PageHelper.startPage(currentPage, pageSize);

        Page<Setmeal> page = setmealDao.selectByCondition(queryString);

        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * @return
     */
    public List<CheckGroup> findCheckgroupAll() {
        return setmealDao.findCheckgroupAll();
    }
}
