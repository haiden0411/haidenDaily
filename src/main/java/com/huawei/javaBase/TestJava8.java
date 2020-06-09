package com.huawei.javaBase;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Author：胡灯
 * Date：2019-08-28 22:34
 * Description：<描述>
 */
public class TestJava8
{
    public static void main(String[] args) throws IOException
    {
        Path output = Paths.get("C:\\test\\out.txt");
        Files.deleteIfExists(output);
        if (!Files.exists(output))
        {
            Files.createFile(output);
        }
        Path input = Paths.get("C:\\test\\testsql.txt");
        List<String> lines = Files.readAllLines(input, Charset.forName("UTF-8"));
        String regex = "Note*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = null;
        for (String line : lines)
        {
            matcher = pattern.matcher(line);
            if (matcher.find())
            {
                System.out.println(line);
            }
        }
        List<String> results = new ArrayList<>();
        List<String> toResult = new ArrayList<>();
        //app*:3 #1 #2 #3
        // dump*:3 #4 #5 #6
        results.add("app*:#1");
        results.add("app*:#2");
        results.add("app*:#3");
        results.add("dump*:#4");
        results.add("dump*:#5");
        results.add("dump*:#6");
        Map<String, List<String>> aaaaaa = getResult(results);
        for (Map.Entry<String, List<String>> stringListEntry : aaaaaa.entrySet())
        {
            String key = stringListEntry.getKey();
            List<String> value = stringListEntry.getValue();
            String join = String.join(" ", value);
            String standard = join.trim();
            toResult.add(key + ":," + value.size() + " " + join);
        }
        for (String s : toResult)
        {
            System.out.println(s);
        }
        Files.write(output, toResult, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        File file = new File("c:/test/input.txt");
        Map<String, String> map = changFileToMap(file);
        System.out.println(map);
    }

    private static Map<String, List<String>> getResult(List<String> results)
    {
        Map<String, List<String>> result = new HashMap<>();
        List<String> values = null;
        for (String line : results)
        {
            String[] keyvalue = line.split(":");
            String key = keyvalue[0];
            String value = keyvalue[1];
            if (!result.containsKey(keyvalue[0]))
            {
                values = new ArrayList<>();
                values.add(value);
                result.put(key, values);
            }
            else
            {
                result.get(key).add(value);
                result.put(key, values);
            }
        }
        return result;
    }

    private static Map<String, String> changFileToMap(File f) throws IOException
    {
        Path path = Paths.get(f.getAbsolutePath());
        List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
        Map<String, String> maps = new HashMap<>();
        for (int i = 0; i < lines.size(); i += 3)
        {
            String key = lines.get(i);
            String value = lines.get(i + 1);
            maps.put(key, value);
        }
        return maps;
    }
}
