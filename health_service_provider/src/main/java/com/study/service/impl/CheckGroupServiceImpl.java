package com.study.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.study.dao.CheckGroupDao;
import com.study.domain.CheckGroup;
import com.study.domain.CheckItem;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;
import com.study.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 12551
 */
@Service(interfaceClass = CheckGroupService.class)
@Transactional
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    public List<CheckItem> findAll() {
        return checkGroupDao.findAll();
    }

    public void delete(Integer id) {
        //查询当前检查组中是否有对应的检查项
        long count = checkGroupDao.selectCheckGroupCheckItemById(id);

        //count > 0 表示当前检查组有对应的检查项
        if (count > 0) {
            checkGroupDao.deleteCheckGroupCheckItemById(id);
        }

        checkGroupDao.deleteCheckGroupById(id);
    }

    public List<Integer> findCheckitemIdsByCheckgroupId(Integer id) {
        return checkGroupDao.findCheckitemIdsByCheckgroupId(id);
    }

    public void edit(Integer[] checkitemIds, CheckGroup checkGroup) {
        checkGroupDao.edit(checkGroup);
        checkGroupDao.deleteCheckitemIds(checkGroup);
        extracted(checkitemIds, checkGroup);
    }

    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    public PageResult findPage(QueryPageBean queryPageBean) {
        Integer pageSize = queryPageBean.getPageSize();
        Integer currentPage = queryPageBean.getCurrentPage();
        String queryString = queryPageBean.getQueryString();

        PageHelper.startPage(currentPage, pageSize);

        Page<CheckGroup> checkGroups = checkGroupDao.findByCondition(queryString);

        return new PageResult(checkGroups.getTotal(), checkGroups.getResult());
    }

    public void add(Integer[] checkitemIds, CheckGroup checkGroup) {
        checkGroupDao.addCheckGroup(checkGroup);

        extracted(checkitemIds, checkGroup);

    }

    private void extracted(Integer[] checkitemIds, CheckGroup checkGroup) {
        if (checkitemIds != null && checkitemIds.length > 0) {
            for (Integer checkitemId : checkitemIds) {
                Map<String, Integer> hashMap = new HashMap<String, Integer>();
                System.out.println(checkGroup.getId());
                hashMap.put("checkgroup_id", checkGroup.getId());
                hashMap.put("checkitem_id", checkitemId);
                checkGroupDao.addCheckGroupCheckitem(hashMap);
            }
        }
    }
}
