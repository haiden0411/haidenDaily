package com.huawei.suanfa;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Author：胡灯
 * Date：2020-03-22 11:16
 * Description：<描述>
 */
public class LinkMain
{
    public static void main(String[] args)
    {
        List<String> arryList = new ArrayList<>();
        List<String> linkList = new LinkedList<>();

        for (int i = 0; i < 100000; i++)
        {
            arryList.add("gree"+i);
            linkList.add("gree"+i);
        }
        long start = System.currentTimeMillis();
        //arryList.add(5000,"haiden");
        linkList.add(5000,"haiden");
        long end = System.currentTimeMillis();
        System.out.println("takes:" + (end - start));
    }

}
