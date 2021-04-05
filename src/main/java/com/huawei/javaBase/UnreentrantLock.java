package com.huawei.javaBase;
/**
 * Author：胡灯
 * Date：2021-03-31 9:33
 * Description：<描述>
 */
public class UnreentrantLock
{
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException {
        System.out.println("进⼊入lock加锁 "+Thread.currentThread().getName());
//判断是否已经被锁，如果被锁则当前请求的线程进⾏行行等待
        while (isLocked){
            System.out.println("进⼊入wait等待 "+Thread.currentThread().getName());
                    wait();
        }
//进⾏行行加锁
        isLocked = true;
    }
    public synchronized void unlock(){
        System.out.println("进⼊入unlock解锁 "+Thread.currentThread().getName());
        isLocked = false;
//唤醒对象锁池⾥里里⾯面的⼀一个线程
        notify();
    }
}
class Main {
    private UnreentrantLock unreentrantLock = new UnreentrantLock();
    //加锁建议在try⾥里里⾯面，解锁建议在finally
    public void methodA(){
        try {
            unreentrantLock.lock();
            System.out.println("methodA⽅方法被调⽤用");
            methodB();
        }catch (InterruptedException e){
            e.fillInStackTrace();
        } finally {
            unreentrantLock.unlock();
        }
    }
    public void methodB(){
        try {
            unreentrantLock.lock();
            System.out.println("methodB⽅方法被调⽤用");
        }catch (InterruptedException e){
            e.fillInStackTrace();
        } finally {
            unreentrantLock.unlock();
        }
    }
    public static void main(String [] args){
//演示的是同个线程
        new Main().methodA();
    }
}
