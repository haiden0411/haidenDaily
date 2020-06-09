package com.huawei;
import com.huawei.springboot.domain.User;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
/**
 * Author：胡灯
 * Date：2020-05-29 21:57
 * Description：<描述>
 */
public class TestRestTemplate
{
    @Test
    public void testGet(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/20";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
    }

    @Test
    public void testGetRest(){
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        //requestFactory.setConnectTimeout(1000);
        requestFactory.setReadTimeout(1000);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        String url = "http://localhost:8080/user/{id}";
        String forObject = restTemplate.getForObject(url, String.class, 40);
        System.out.println(forObject);
    }

    @Test
    public void testPostJson(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/entity";
        User user = new User(20,"haiden",30);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<User> httpEntity = new HttpEntity<User>(user,httpHeaders);
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(url,httpEntity,User.class);
        System.out.println(responseEntity);
    }

    @Test
    public void testPostMutiPara(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/format";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        LinkedMultiValueMap<String, Object> reqeustBody = new LinkedMultiValueMap<>();
        reqeustBody.add("date","2020-5-31 20:30:22");
        reqeustBody.add("number",20);
        HttpEntity<Object> entity = new HttpEntity<>(reqeustBody,httpHeaders);
        ResponseEntity<Map> mapResponseEntity = restTemplate.postForEntity(url, entity, Map.class);
        Map body = mapResponseEntity.getBody();
        System.out.println(body);
    }

    @Test
    public void testInterceptor(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/interceptor/start";
        String forObject = restTemplate.getForObject(url, String.class);
        System.out.println(forObject);
    }

    @Test
    public void testAop(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8080/user/print?id=10&name=haiden&age=20";
        User forObject = restTemplate.getForObject(url, User.class);
        System.out.println(forObject);

    }

}
