package com.huawei.Daliy.sort;
import com.huawei.utils.Java8Utils;

import java.util.Arrays;
import java.util.Random;
/**
 * Author：胡灯
 * Date：2019-12-27 22:20
 * Description：<描述>
 */
public class Sort
{
    //选择排序
    public static void select_sort(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int small_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[j] < arr[small_index])
                {
                    small_index = j;
                }
            }
            swap(arr, i, small_index);
        }
    }
    //冒泡排序
    public static void bubel_sort(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length - i - 1; j++)
            {
                if (arr[j] > arr[j + 1])
                {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
    //插入排序
    public static void insert_sort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            int j = i - 1;
            int key = arr[i];
            while (j >= 0)
            {
                if (arr[j] > key)
                {
                    arr[j + 1] = arr[j];
                    arr[j] = key;
                }
                j -= 1;
            }
        }
    }
    //快速排序
    public static void quick_sort(int[] arr)
    {
        subSort(arr, 0, arr.length - 1);
    }
    public static void main(String[] args)
    {
        int[] arr = new Random().ints(100000,-100000, 100000).toArray();
       //System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        //select_sort(arr);
        //bubel_sort(arr);
        //insert_sort(arr);
        quick_sort(arr);
        //Arrays.sort(arr);
        long end = System.currentTimeMillis();
        System.out.println("一共用时:" + (end - start) + "ms");
        //System.out.println(Arrays.toString(arr));
    }
    private static void subSort(int[] arr, int start, int end)
    {
        if (start < end)
        {
            int base = arr[start];
            int i = start;
            int j = end + 1;
            while (true)
            {
                while (i < end && arr[++i] <= base) ;
                while (j > start && arr[--j] >= base) ;
                if (i < j)
                {
                    swap(arr, i, j);
                } else
                {
                    break;
                }
            }
            swap(arr, start, j);
            subSort(arr, start, j - 1);
            subSort(arr, j + 1, end);
        }
    }
    private static void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
