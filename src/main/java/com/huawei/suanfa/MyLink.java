package com.huawei.suanfa;
/**
 * Author：胡灯
 * Date：2020-03-22 11:35
 * Description：<描述>
 */
public class MyLink
{
    MyNode head;
    public MyLink()
    {
        head =new MyNode();
        head.setNext(null);
    }

    public void addNode(String data){
        MyNode p = head;
        while (p.getNext() != null)
        {
            p = p.getNext();
        }
        p.setNext(new MyNode(data));
    }
    public void delNode(String data){
        MyNode p = head;
        while (p.getNext() != null)
        {
            if (p.getNext().getData().equals(data))
            {
                p.setNext(p.getNext().getNext());
            }
            p = p.getNext();
        }
    }

    public void insertNode(String parm,String data){
        MyNode p = head;
        MyNode newNode = new MyNode(data);
        while (p.getNext() != null)
        {
            if (p.getNext().getData().equals(parm))
            {
                newNode.setNext(p.getNext().getNext());
                p.getNext().setNext(newNode);
                break;
            }
            p = p.getNext();
        }
    }

    public void findNode(String data){
        MyNode p = head;
        while (p.getNext() != null)
        {
            if (p.getNext().getData().equals(data))
            {
                System.out.println("找到:" + data);
                break;
            }
            p = p.getNext();
        }
    }

    public void display(){
        MyNode p = head;
        while (p.getNext() != null)
        {
            System.out.print(p.getNext().getData() + "->");
            p = p.getNext();
        }
    }
    public int size(){
        int n = 0;
        MyNode p = head;
        while (p.getNext() != null)
        {
            n++;
            p = p.getNext();
        }
        return  n;
    }
    public static void main(String[] args)
    {
        MyLink link = new MyLink();
        link.addNode("haiden");
        link.addNode("gree");
        link.addNode("fangling");
        link.addNode("yuhui");
        link.addNode("hujie");
        link.addNode("chunxiang");
        System.out.println(link.size());
        link.findNode("gree");
        link.insertNode("gree","meiling");
        link.display();
        System.out.println("size:"+link.size());

    }

}
