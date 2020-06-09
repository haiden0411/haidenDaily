package com.huawei.suanfa;
/**
 * Author：胡灯
 * Date：2020-03-22 8:33
 * Description：<描述>
 */
public class Link
{
    Node head;
    public Link()
    {
        head = new Node();
        head.next = null;
    }
    public void addNode(String data)
    {
        Node p = head;
        while (p.next != null)
        {
            p = p.next;
        }
        p.next = new Node(data);
    }
    public void delNode(String data)
    {
        Node p = head;
        while (p.next != null)
        {/*
            if (p.next.name.equals(data))
            {
                p.next = p.next.next;
                System.out.println("删除成功");
                break;
            }
            p = p.next;*/
            if (p.next.name.equals(data))
            {
                p.next = p.next.next;
                System.out.println("删除成功");
                break;
            }else {
                p = p.next;
            }
        }
    }
    public void display()
    {
        Node p = head;
        while (p.next!=null){
            System.out.print(p.next.name + "->");
            p = p.next;
        }
    }
    public void findNode(String data)
    {
        Node p = head;
        while (p.next != null)
        {
            if (p.next.name.equals(data))
            {
                System.out.println("找到节点:" + p.next.name);
                break;
            }
            p = p.next;
        }

    }
    public void insertNode(String param, String data)
    {
        Node p = head;
        Node newNode = new Node(data);
        while (p.next != null)
        {
            if (p.next.name.equals(param))
            {

               newNode.next = p.next.next;
               p.next.next = newNode;
                System.out.println("插入data:" + data + " 成功");
                break;
            }
            p = p.next;
        }

    }
    public int size()
    {
        int n = 0;
        Node p = head;
        while (p.next != null)
        {
            n++;
            p = p.next;
        }
        return n;
    }
    public static void main(String[] args)
    {
        Link link = new Link();
        link.addNode("haiden");
        link.addNode("gree");
        link.addNode("fangling");
        link.addNode("yuhui");
        link.addNode("hujie");
        link.addNode("chunxiang");
        System.out.println("size:"+link.size());
        link.findNode("gree");
        link.insertNode("gree","meiling");
        link.display();
        System.out.println("size:"+link.size());
        for (int i = 0; i < 10000; i++)
        {
            link.addNode("gree"+i);
        }

    }
}
