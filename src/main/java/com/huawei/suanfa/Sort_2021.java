package com.huawei.suanfa;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import static org.junit.Assert.*;
/**
 * Author：胡灯
 * Date：2021-04-14 18:07
 * Description：各种排序
 */
public class Sort_2021
{
    public int partithon(int[] arr, int low, int high)
    {
        //初始化
        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j)
        {
            while (i<j&&arr[j] > pivot){
                j--;
            }
            if (i<j)
            {
                swap(arr, i++, j);
            }
            while (i<j&&arr[i] < pivot)
            {
                i++;
            }
            if (i<j)
            {
                swap(arr, i, j--);
            }
        }
        return i;
    }

    public int partithon2(int[] arr, int low, int high)
    {

        int pivot = arr[low];
        int i = low;
        int j = high;
        while (i < j)
        {
            while (i<j&&arr[j] > pivot)
            {
                j--;
            }
            while (i<j&&arr[i] <= pivot)
            {
                i++;
            }
            if (i<j)
            {
                swap(arr, i++, j--);
            }
        }
        if (arr[i] > pivot)
        {
            swap(arr,i-1,low);
            return i-1;
        }
        swap(arr,i,low);
        return i;
    }
    public void quickSort(int[] arr, int low, int high)
    {
        if (low < high)
        {
            int mid = partithon(arr, low, high);
            quickSort(arr, low, mid - 1);
            quickSort(arr, mid + 1, high);
        }
    }
    private void swap(int[] arr, int i, int j)
    {
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    @Test
    public void testQuickSort()
    {
        for (int i = 0; i < 100; i++)
        {
            int[] arr = new Random().ints(3000, -10000, 10000).toArray();
            int[] arr1 = Arrays.copyOf(arr, arr.length);
            Arrays.sort(arr1);
            quickSort(arr, 0, arr.length - 1);
            assertArrayEquals(arr1,arr);
        }

    }
}
