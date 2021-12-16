package com.huawei.pattern.composite;
import java.io.File;
import java.util.ArrayList;
/**
 * @Author：胡灯
 * @Date：2021-12-15 22:18
 * @Description：Test
 */
public class Test
{
    public static void main(String[] args)
    {

    }


}

class FileLeaf{
    String fileName;
    public FileLeaf(String fileName)
    {
        this.fileName = fileName;
    }
    public void display()
    {
        System.out.println(fileName);
    }
}

class DirectNode{
    String nodeName;
    ArrayList<FileLeaf> leafList = new ArrayList<>();
    ArrayList<DirectNode> nodeList = new ArrayList<>();
    public DirectNode(String nodeName)
    {
        this.nodeName = nodeName;
    }
    public void addNode(DirectNode node)
    {
        nodeList.add(node);
    }
    public void addLeaf(FileLeaf fileLeaf)
    {
        leafList.add(fileLeaf);
    }
    public void display()
    {
        
    }

}
