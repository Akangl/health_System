package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.study.dao.CheckItemDao;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;
import com.study.service.CheckItemService;
import com.study.domain.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查项服务
 * @author 12551
 */
@Service(interfaceClass=CheckItemService.class)
@Transactional
public class CheckItemServiceImpl implements CheckItemService {
    //注入Dao
    @Autowired
    /**
     *
     */
    private CheckItemDao checkItemDao;

    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    /**
     * 分页查询，条件分页查询
     * @param queryPageBean
     * @return
     */
    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage,pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);

        //得到有多少条数据
        long total = page.getTotal();
        //返回查到的数据
        List<CheckItem> rows = page.getResult();

        return new PageResult(total,rows);
    }

    public CheckItem findById(Integer id) {
        long count = checkItemDao.findCountById(id);
        if(count > 0){
            return checkItemDao.findById(id);
        }else {
          new RuntimeException();
          return null;
        }

    }

    /**
     * 修改检查项
     * @param checkItem
     */
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    /**
     * 删除检查项
     * @param id
     */
    public void delete(Integer id) {
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0){
            new RuntimeException();
        }
        checkItemDao.delete(id);


    }
}
