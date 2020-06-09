package com.huawei.springboot.controller;
import com.huawei.springboot.domain.User;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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

}
