package com.huawei.springboot.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 * Author：胡灯
 * Date：2020-05-31 10:36
 * Description：<描述>
 */
@Controller
public class FileController
{
    @GetMapping("/upload/page")
    public String uploadPage(){
        return "file/upload";
    }

    @PostMapping("/upload/reuqest")
    @ResponseBody
    public Map<String,Object> uploadReqeust(HttpServletRequest request){
        MultipartHttpServletRequest mreq = null;
        if (request instanceof MultipartHttpServletRequest)
        {
            mreq = (MultipartHttpServletRequest) request;
        }else {
            return dealWithResultMap(false,"上传失败");
        }
        MultipartFile mf = mreq.getFile("file");
        String originalFilename = mf.getOriginalFilename();
        File file = new File(originalFilename);
        try
        {
            mf.transferTo(file);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return dealWithResultMap(false,"上传失败");

        }
        return dealWithResultMap(true,"上传成功");
    }

    @PostMapping("/upload/multipart")
    @ResponseBody
    public Map<String,Object> uploadMultipart(MultipartFile file){
        String fileName = file.getOriginalFilename();
        File dest = new File(fileName);
        try
        {
            file.transferTo(dest);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return dealWithResultMap(false,"上传失败");
        }
        return dealWithResultMap(true,"上传成功");
    }

    @PostMapping("/upload/part")
    @ResponseBody
    public Map<String,Object> uploadMultipart(Part file){
        String fileName = file.getSubmittedFileName();
        try
        {
            file.write(fileName);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return dealWithResultMap(false,"上传失败");
        }
        return dealWithResultMap(true,"上传成功");
    }


    private Map<String,Object> dealWithResultMap(boolean success,String message){
        Map<String,Object> result = new HashMap<>();
        result.put("success",success);
        result.put("message",message);
        return result;
    }
}
