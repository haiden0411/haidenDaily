package com.huawei.springboot.controller;
import com.huawei.springboot.domain.SysUser;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.domain.vo.RegisterReqVO;
import com.huawei.springboot.domain.vo.UpdateUserReqVO;
import com.huawei.springboot.mapper.SysUserMapper;
import com.huawei.springboot.service.UserService;
import com.huawei.springboot.service.UserValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @ApiOperation("获取用户信息接口")
    @ApiResponses({@ApiResponse(code = 0,message = "成功",response = SysUser.class)})
    @ApiImplicitParams({@ApiImplicitParam(name = "id",value = "用户ID",dataType ="String",paramType = "query",required = true,defaultValue = "0f09e661-7e80-4e1b-b66a-2e266bb593bf")})
    public SysUser findUserById(@ApiParam(value = "用户ID",required = true) @PathVariable  String id,HttpServletRequest request){
        System.out.println(request.getHeader("token"));
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
