package com.study.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import com.study.constant.HuaweiCloudOBS;
import com.study.constant.MessageConstant;
import com.study.domain.CheckGroup;
import com.study.domain.Setmeal;
import com.study.entity.PageResult;
import com.study.entity.QueryPageBean;
import com.study.entity.Result;
import com.study.service.SetmealService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

/**
 * @author 12551
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Reference
    private SetmealService setmealService;

    @RequestMapping("/findCheckgroupAll")
    public Result findCheckgroupAll() {
        try {
            List<CheckGroup> checkgroupAll = setmealService.findCheckgroupAll();
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkgroupAll);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
        }
    }

    @RequestMapping("/upload")
    public Result upload(@PathVariable("imgFile") MultipartFile imgFile) {
        //原文件名称
        String oldFileName = imgFile.getOriginalFilename();

        //得到原文件的后缀
        int lastIndexOf = oldFileName.lastIndexOf(".");
        String extention = oldFileName.substring(lastIndexOf - 1);

        //设置一个新的文件名，使用UUID。
        String newFileName = UUID.randomUUID().toString() + extention;
        System.out.println(newFileName);

        try {

            InputStream inputStream = imgFile.getInputStream();

            //创建云服务器
            ObsClient obsClient = new ObsClient(HuaweiCloudOBS.AK, HuaweiCloudOBS.SK, HuaweiCloudOBS.ENDPOINT);
            //上传文件到云服务器
            PutObjectResult putObjectResult = obsClient.putObject(HuaweiCloudOBS.BUCEK_NAME, newFileName, inputStream);

            String url = putObjectResult.getObjectUrl();
            System.out.println("上传路径为" + url);

            inputStream.close();
            obsClient.close();

        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
        return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, newFileName);
    }

    /**
     * 新增套餐信息
     *
     * @param checkgroupIds
     * @param setmeal
     * @return
     */
    @RequestMapping("/add")
    public Result add(Integer[] checkgroupIds,
                      @RequestBody Setmeal setmeal) {
        try {
            setmealService.add(checkgroupIds, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = setmealService.findPage(queryPageBean);
        return pageResult;
    }

    @RequestMapping("/findSetmealById")
    public Result findSetmealById(Integer id) {
        try {
            Setmeal setmeal = setmealService.findSetmealById(id);
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
        }
    }

    @RequestMapping("/edit")
    public Result edit(Integer[] checkgroupIds,
                       @RequestBody Setmeal setmeal) {
        try {
            setmealService.edit(checkgroupIds, setmeal);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.EDIT_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_SETMEAL_SUCCESS);
    }

    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            setmealService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_SETMEAL_FAIL);
        }
        return new Result(true, MessageConstant.DELETE_SETMEAL_SUCCESS);
    }

    @RequestMapping("/findCheckgroupIdsBySetmealId")
    public Result findCheckgroupIdsBySetmealId(Integer id) {
        try {
            List<Integer> setmealList = setmealService.findCheckgroupIdsBySetmealId(id);
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, setmealList);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_SETMEALLIST_SUCCESS);
        }
    }
}
