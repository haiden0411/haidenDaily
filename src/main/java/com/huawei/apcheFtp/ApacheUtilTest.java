package com.huawei.apcheFtp;

import com.google.common.escape.Escapers;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;
import org.junit.Test;
/**
 * Author：胡灯
 * Date：2020-04-26 21:25
 * Description：<描述>
 */
public class ApacheUtilTest
{
    @Test
    public void testArrayUtils(){
        int arr[] = {1,5,10,20,30,25};


    }
    @Test
    public void testBeanUtils(){


    }

    @Test
    public void testStringUtils1(){
        String str = "aaabbbcccdddeeffggaaagcccafakadfkaf";
        System.out.println(StringUtils.countMatches(str, "aaa"));
    }
    @Test
    public void testStringUtils2(){


    }
    @Test
    public void testStringUtils3(){


    }

    @Test
    public void testEscapeUtils1(){
        String str = "thi is a test 这是一个测试";
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<persons>\n" +
                "   <person id=\"23\">\n" +
                "         <name>张   三</name>\n" +
                "         <age>26</age>\n" +
                "  </person>\n" +
                "  <person id=\"22\">\n" +
                "        <name>李四</name>\n" +
                "        <age>25</age>\n" +
                " </person>\n" +
                "</persons>";
        System.out.println("用escapeJava方法转义之后的字符串为:"+ StringEscapeUtils.escapeJava(str));

        System.out.println("用unescapeJava方法反转义之后的字符串为:"+StringEscapeUtils.unescapeJava(StringEscapeUtils.escapeJava(str)));

        System.out.println("用escapeHtml3方法转义之后的字符串为:"+StringEscapeUtils.escapeHtml3(xml));
    }

}
