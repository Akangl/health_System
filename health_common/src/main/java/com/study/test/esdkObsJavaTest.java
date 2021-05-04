package com.study.test;

import com.obs.services.ObsClient;
import com.obs.services.exception.ObsException;
import com.obs.services.model.HeaderResponse;
import org.junit.Test;

import java.io.File;

public class esdkObsJavaTest {
    @Test
    public void testesdk() {
        // 您的工程中可以只保留一个全局的ObsClient实例
        // ObsClient是线程安全的，可在并发场景下使用
        ObsClient obsClient = null;
        try {
            String endPoint = "https://obs.cn-east-3.myhuaweicloud.com";
            String ak = "LT9GIKLCAMYKHSQGWPD2";
            String sk = "zrERwVS8ETkwA8JSeRkv13uJciYNFbtmk6NzYxbB";
            // 创建ObsClient实例
            obsClient = new ObsClient(ak, sk, endPoint);
            // 调用接口进行操作，例如上传对象
            HeaderResponse response = obsClient.putObject("zhou111", "objectname", new File("D:\\Zwk\\kb.jpg"));  // localfile为待上传的本地文件路径，需要指定到具体的文件名
            System.out.println(response);
        } catch (ObsException e) {
            System.out.println("HTTP Code: " + e.getResponseCode());
            System.out.println("Error Code:" + e.getErrorCode());
            System.out.println("Error Message: " + e.getErrorMessage());

            System.out.println("Request ID:" + e.getErrorRequestId());
            System.out.println("Host ID:" + e.getErrorHostId());
        } finally {
            // 关闭ObsClient实例，如果是全局ObsClient实例，可以不在每个方法调用完成后关闭
            // ObsClient在调用ObsClient.close方法关闭后不能再次使用
            if (obsClient != null) {
                // obsClient.close();
            }
        }

    }
}
