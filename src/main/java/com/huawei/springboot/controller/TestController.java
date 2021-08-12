package com.huawei.springboot.controller;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.exception.BizException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Author：胡灯
 * Date：2020-05-28 22:40
 * Description：<描述>
 */
@RestController
@RequestMapping("/user")
public class TestController
{
    @GetMapping("/{id}")
    public String test01(@PathVariable("id") Long id){
        try
        {
            Thread.sleep(2000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("id:"+id);
        return "id:"+id;
    }

    @PostMapping("/format")
    public Map<String,Object> format(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @NumberFormat(pattern = "#,###.##") Double number){
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("date",date);
        dataMap.put("number",number);
        dataMap.forEach((s, o) -> {
            System.out.println("key:"+s+" value:"+o);
        });
        return dataMap;
    }

    @PostMapping("/entity")
    public ResponseEntity<User> inSertUser(@RequestBody User user){
        System.out.println("user:"+user);
        HttpHeaders httpHeaders = new HttpHeaders();
        String success = user == null ? "false" : "true";
        return new ResponseEntity<User>(user,httpHeaders, HttpStatus.CREATED);

    }

    @PostMapping("/annotation")
    @ResponseStatus(HttpStatus.CREATED)
    public User inSertUserAnotation(@RequestBody User user){
        System.out.println("user:"+user);
        return user;
    }

    @PostMapping("/exc")
    public boolean insert(@RequestBody User user) {
        System.out.println("开始新增...");
        //如果姓名为空就手动抛出一个自定义的异常！
        if (user.getName() == null) {
            throw new BizException("-1", "用户姓名不能为空！");
        }
        return true;
    }

    @PutMapping("/exc")
    public boolean update(@RequestBody User user) {
        System.out.println("开始更新...");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");
        return true;
    }

    @DeleteMapping("/exc")
    public boolean delete(@RequestBody User user) {
        System.out.println("开始删除...");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/exc")
    public List<User> findByUser(User user) {
        System.out.println("开始查询...");
        List<User> userList = new ArrayList<>();
        User user2 = new User();
        user2.setId(1);
        user2.setName("xuwujing");
        user2.setAge(18);
        userList.add(user2);
        return userList;
    }

}
