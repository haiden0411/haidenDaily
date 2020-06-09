package com.huawei.sort;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
/**
 * Author：胡灯
 * Date：2020-03-06 22:01
 * Description：<描述>
 */
public class Sort
{
    public static void bubble_sort(int[] arr)
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
    public static void select_sort(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int min_index = i;
            for (int j = i; j < arr.length; j++)
            {
                if (arr[min_index] > arr[j])
                {
                    min_index = j;
                }
            }
            swap(arr, i, min_index);
        }
    }
    public static void insert_sort(int[] arr)
    {
        for (int i = 1; i < arr.length; i++)
        {
            int j = i - 1;
            int index = arr[i];
            while (j >= 0)
            {
                if (arr[j] > arr[j + 1])
                {
                    arr[j + 1] = arr[j];
                    arr[j] = index;
                }
                j--;
            }
        }
    }
    public static void quick_sort(int[] arr)
    {
        sub_sort(arr,0,arr.length-1);
    }
    private static void sub_sort(int[] arr, int start, int end)
    {
        if (start < end)
        {
            int i = start;
            int j = end + 1;
            int base = arr[start];
            while (true)
            {
                while (i < end && arr[++i] <= base);
                while (j > start && arr[--j] >= base);
                if (i < j)
                {
                    swap(arr, i, j);
                } else
                {
                    break;
                }
            }
            swap(arr,start,j);
            sub_sort(arr,start,j-1);
            sub_sort(arr,j+1,end);
        }
    }
    public static void main(String[] args)
    {
        int[] arr = new Random().ints(30, -30, 30).toArray();
        //bubble_sort(arr);
        //select_sort(arr);
        //insert_sort(arr);
        quick_sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    private static void swap(int arr[], int i, int j)
    {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
