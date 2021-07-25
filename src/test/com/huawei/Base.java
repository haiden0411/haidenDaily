package huawei;
import io.swagger.models.auth.In;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2021-06-19 21:32
 * Description：<描述>
 */


public class Base
{
    private String baseName = "base";

    static class Sub extends Base
    {
        private String baseName = "sub";
        @Override
        public void callName()
        {
            System.out.println(baseName);
        }
    }

    public Base()
    {
        callName();
    }
    public void callName()
    {
        System.out.println(baseName);
    }

    static char c;
    static float f;
    public static void main(String[] args) throws InterruptedException
    {
        Base a = new Sub();
        String temp = " aa bb ";
        temp.trim();
        System.out.println("start" + temp + "end");
        Integer aa = 500;
        int bb = 500;
        Integer cc = new Integer(500);
        System.out.println(aa.equals(bb));
        System.out.println(aa == cc);

        System.out.println("c = " + c);
        System.out.println("f = " + f);

        List list1 = new ArrayList<>();
        list1.add(0);
        List list2 = list1;
        System.out.println(list1.get(0) instanceof Integer);
        System.out.println(list2.get(0) instanceof Integer);

        Object o = new Object(){
            @Override
            public boolean equals(Object obj)
            {
                return true;

            }
        };
        System.out.println(o.equals("fred"));
        long l = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        long l1 = System.currentTimeMillis();
        long l2 = new Date().getTime();
        long l3 = System.nanoTime() ;
        long l4 = Instant.now().toEpochMilli();
        System.out.println(l);
        System.out.println(l1);
        System.out.println(l2);
        System.out.println(l3);
        System.out.println(l4);

        List<String> list = List.of("springboot","java","html","","git").stream().dropWhile(obj->!obj.isEmpty()).collect(Collectors.toList());
        System.out.println(list.size());

        List<String> set = Set.of("springboot","java","html","","git").stream().takeWhile(obj->!obj.isEmpty()).collect(Collectors.toList());
        set.forEach(System.out::println);
        list.forEach(System.out::println);
    }
}
