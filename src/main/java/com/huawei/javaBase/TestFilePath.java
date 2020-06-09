package com.huawei.javaBase;

import com.google.common.primitives.Chars;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author：胡灯
 * Date：2019-08-30 22:24
 * Description：<描述>
 */
public class TestFilePath
{
    public static void main(String[] args)
    {
        URL url = TestFilePath.class.getClassLoader().getResource("config.properties");
        File file = new File(url.getFile());
        System.out.println(file.exists());
        char[] aa = {'1','2','a','b','c','d'};
        System.out.println(Chars.max(aa));
        String join = Chars.join("~", aa);
        System.out.println(join);
    }
}
