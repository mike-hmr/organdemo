package com.syswin.systoon.research.controller;

import com.syswin.systoon.research.entity.User;
import com.syswin.systoon.research.response.CommonResult;
import com.syswin.systoon.research.service.UserService;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rocky on 2018/1/23.
 */
@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取完整组织树json
     * @return
     */
    @RequestMapping("/treeJson")
    public String getChildrenJson(){
        List<User> list = userService.getAllUser();
        List<User> result = new ArrayList<>();
        User json = new User();
        json.setText("用户列表");
        json.setChildren(list);
        result.add(json);

        return result.toString();
    }

    /**
     * 设置用户可见关系
     * @return
     */
    @RequestMapping("/setcm")
    public String setReleation(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                userService.setFullReleation();
            }
        }).start();

        return CommonResult.success().toString();
    }

    /**
     * 设置用户可见关系
     * @return
     */
    @RequestMapping("/fire")
    public String unbindReleation(){

        userService.unbindReleation();
        return CommonResult.success().toString();
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping("/getColleage")
    public String getColleage(@RequestParam("id")Integer id){

        List<User> result = userService.getColleage(id);

        return CommonResult.success(result).toString();
    }

}