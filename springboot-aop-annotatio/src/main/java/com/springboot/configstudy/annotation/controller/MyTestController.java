package com.springboot.configstudy.annotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.configstudy.annotation.logRecord.LogTrack;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Nuri
 * @CreateTime 2021/2/20
 * @Describe
 */
@RestController
@RequestMapping("/test")
public class MyTestController {

    @ResponseBody
    @LogTrack("testGet1 接口")
    @GetMapping("/testGet1")
    public void testGet1(@RequestParam("UserId") String userId,@RequestParam("toUserId")String toUserId){
        System.out.println("已经进入GET测试接口,参数userId：" + userId+  "参数toUserId:"+toUserId);
    }

    @ResponseBody
    @GetMapping("/testGet2")
    @LogTrack("testGet2 接口")
    public void testGet2(@RequestParam Map map){
        System.out.println("已经进入GET测试接口,参数：" + map.get("userId"));
    }

    @ResponseBody
    @PostMapping("/testPost1")
    @LogTrack("testPost1 接口")
    public void testPost1(@RequestBody Map map){
        System.out.println("已经进入POST测试接口,参数：" + map.toString());
    }

    @ResponseBody
    @LogTrack("testPost2 接口")
    @PostMapping("/testPost2")
    public void testPost2(@RequestBody JSONObject jsonObject){
        System.out.println("已经进入POST测试接口,参数：" + jsonObject.toString());
    }
}
