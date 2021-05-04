package com.study.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.constant.MessageConstant;
import com.study.domain.CheckGroup;
import com.study.domain.CheckItem;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;
import com.study.entity.Result;
import com.study.service.CheckGroupService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 12551
 */
@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    /**
     * com.alibaba.dubbo.config.annotation.Reference
     */
    @Reference
    private CheckGroupService checkGroupService;

    /**
     * 查询所有检查项信息
     *
     * @return 检查项数据集合
     */
    @RequestMapping("/findAll")
    public Result findAll() {
        try {
            List<CheckItem> checkItemList = checkGroupService.findAll();
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItemList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }


    /**
     * 新增检查组信息
     *
     * @param checkitemIds 检查组对应的检查项
     * @param checkGroup   新增检查组信息
     * @return 返回是否添加检查组信息成功
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkitemIds, @RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.add(checkitemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
        for (Integer checkitemId : checkitemIds) {
            System.out.println(checkitemId);
        }
        System.out.println(checkGroup.getId());
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 分页查询
     *
     * @param queryPageBean
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {

        return checkGroupService.findPage(queryPageBean);
    }

    /**
     * 根据检查组id查询检查组信息
     *
     * @param id 检查组Id
     * @return 返回所查询到的检查组信息
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            CheckGroup checkGroup = checkGroupService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    /**
     * 编辑检查组信息
     *
     * @param checkGroup 修改后的检查组信息
     * @return 是否修改成功
     */
    @RequestMapping("/edit")
    public Result edit(Integer[] checkitemIds,
                       @RequestBody CheckGroup checkGroup) {
        try {
            checkGroupService.edit(checkitemIds, checkGroup);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    /**
     * 根据检查组id，返回所对应的检查项信息集合
     *
     * @param id 检查组Id
     * @return 根据检查组Id所查询到的检查项集合
     */
    @RequestMapping("/findCheckitemIdsByCheckgroupId")
    public Result findCheckitemIdsByCheckgroupId(Integer id) {
        try {
            List<Integer> checkgroupIdList = checkGroupService.findCheckitemIdsByCheckgroupId(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkgroupIdList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    /**
     * 根据id删除检查组
     *
     * @param id 需要删除的检查组id
     * @return 是否删除成功
     */
    @RequestMapping("delete")
    public Result delete(Integer id) {
        try {
            checkGroupService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
    }
}

