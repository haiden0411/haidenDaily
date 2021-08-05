package com.huawei.Daliy.sort;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author：胡灯
 * @Date：2021-08-05 21:33
 * @Description：单向队列
 */
public class ArrayQueue {
    int rear;
    int front;
    int[] arr;
    int maxSize;

    public ArrayQueue(int maxArrSize) {
        rear = -1;
        front = -1;
        this.maxSize = maxArrSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return rear + 1 == maxSize;
    }

    public boolean isEmpty() {
        return rear==front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        rear++;
        arr[rear] = n;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        front++;
        return arr[front];
    }

    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front+1];
    }

    public void showQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        //输出一个菜单
        while(loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': //取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': //查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': //退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }

}
