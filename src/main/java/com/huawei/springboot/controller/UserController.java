package com.huawei.springboot.controller;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.service.UserService;
import com.huawei.springboot.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * Author：胡灯
 * Date：2020-06-02 23:13
 * Description：<描述>
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private UserService userService;

    @RequestMapping("/print")
    @ResponseBody
    public User printUser(int id, String name, int age){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setAge(age);
        userService.printUser(user);
        return user;
    }

    @RequestMapping("/vp")
    @ResponseBody
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
}
