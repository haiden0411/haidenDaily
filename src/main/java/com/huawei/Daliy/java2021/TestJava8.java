package com.huawei.Daliy.java2021;
import java.util.*;
import java.util.stream.Collectors;
/**
 * Author：胡灯
 * Date：2021-07-25 0:04
 * Description：<描述>
 */
public class TestJava8
{
    public static void main(String[] args)
    {
        List<Student> list = Arrays.asList(new Student(32),new
                Student(33),new Student(21),new Student(29),new Student(18));
        Optional<Student> max = list.stream().max(Comparator.comparingInt(Student::getAge));
        System.out.println(max);
        int asInt = list.stream().mapToInt(Student::getAge).max().getAsInt();
        System.out.println(asInt);
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        numbers.stream().forEach(System.out::println);
        System.out.println("======================");
        numbers.parallelStream().forEach(System.out::println);
    }
}
