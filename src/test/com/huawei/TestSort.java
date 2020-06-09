package com.huawei;
import com.huawei.Daliy.sort.Sort;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.*;
/**
 * Author：胡灯
 * Date：2020-05-14 21:11
 * Description：<描述>
 */
public class TestSort
{
    int [] arr1 = null;
    int [] arr2 = null;
    @Before
    public void setUp()
    {
        arr1 = new Random().ints(30,-100,100).toArray();
        arr2 = Arrays.copyOf(arr1,arr1.length);
    }
    @Test
    public void testSelectSort()
    {
        Sort.select_sort(arr1);
        Arrays.sort(arr2);
        assertArrayEquals(arr2,arr1);
    }
    @Test
    public void testInsertSort()
    {
        Sort.insert_sort(arr1);
        Arrays.sort(arr2);
        assertArrayEquals(arr2,arr1);
    }
    @Test
    public void testBubbleSort()
    {
        Sort.bubel_sort(arr1);
        Arrays.sort(arr2);
        assertArrayEquals(arr2,arr1);
    }
    @Test
    public void testQuickSort()
    {
        Sort.quick_sort(arr1);
        Arrays.sort(arr2);
        assertArrayEquals(arr2,arr1);
    }
}
