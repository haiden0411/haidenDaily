package com.huawei.springboot.controller;
import com.huawei.springboot.domain.SysUser;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.domain.vo.RegisterReqVO;
import com.huawei.springboot.domain.vo.UpdateUserReqVO;
import com.huawei.springboot.mapper.SysUserMapper;
import com.huawei.springboot.service.UserService;
import com.huawei.springboot.service.UserValidator;
import io.swagger.annotations.Api;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
/**
 * Author：胡灯
 * Date：2020-06-02 23:13
 * Description：<描述>
 */
@RestController
@RequestMapping("/api")
@Api(tags="用户模块",description = "用户模块相关接口")
public class UserController
{
    @Autowired
    private UserService userService;

    @GetMapping("/print")
    public User printUser(int id, String name, int age){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        userService.printUser(user);
        return user;
    }
    @GetMapping("/vp")
    public User validateAndPut(int id, String name, int age){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        UserValidator userValidator = (UserValidator) userService;
        if (userValidator.validate(user))
        {
            userService.printUser(user);
        }
        return user;
    }

    @GetMapping("/user/{id}")
    public SysUser findUserById(@PathVariable  String id){
       return userService.getUserInfo(id);
    }

    @PostMapping("/user")
    public String insertUser(@RequestBody  RegisterReqVO vo){

        return userService.register(vo);
    }

    @PutMapping("/user")
    public String updateUser(@RequestBody UpdateUserReqVO vo){
        return userService.updateUserInfo(vo);
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable  String id){
        return userService.deletedUserInfo(id);
    }
}
