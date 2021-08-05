package com.huawei.Daliy.sort;

/**
 *
 */
public class CircleArrayQueue {

    int rear;
    int front;
    int[] arr;
    int maxSize;

    public CircleArrayQueue(int maxArrSize) {
        this.maxSize = maxArrSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1)%maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        if (isFull()) {
            throw new RuntimeException("队列已满");
        }
        arr[rear] = n;
        rear = (rear+1)%maxSize;
    }

    public int getQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    public int size() {
        return (rear+maxSize-front)%maxSize;
    }
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空");
        }
        return arr[front];
    }

    public void showQueue() {
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
}
