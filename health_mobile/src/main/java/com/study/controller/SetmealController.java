package com.study.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.study.constant.MessageConstant;
import com.study.domain.Setmeal;
import com.study.entity.Result;
import com.study.service.SetmealService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 套餐管理
 *
 * @author 12551
 */
@RequestMapping("/setmeal")
@RestController
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    /**
     * 移动端
     * 查询所有套餐
     *
     * @return
     */
    @RequestMapping("getSetmeal")
    public Result getSetmeal() {
        try {
            List<Setmeal> list = setmealService.findAll();
            return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_SETMEAL_LIST_FAIL);
        }
    }

    /**
     * 根据套餐id查询套餐信息：(套餐基本信息，套餐对应的检查组信息，检查组对应检查项信息)
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public Result findById(Integer id) {
        try {
            Setmeal setmeal = setmealService.findByID(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }
}
