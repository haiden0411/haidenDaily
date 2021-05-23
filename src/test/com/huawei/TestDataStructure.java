package huawei;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
/**
 * Author：胡灯
 * Date：2021-04-13 18:01
 * Description：<描述>
 */
public class TestDataStructure
{
    @Test
    public void testSparseArray() throws IOException
    {
        int chessArr[][] = new int[11][11];
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        System.out.println("原始的二维数组:");
        print2dArr(chessArr);
        //将二维数组转为稀疏数组
        //得到非零的个数
        int sum = 0;
        for (int i = 0; i < chessArr.length; i++)
        {
            for (int j = 0; j < chessArr.length; j++)
            {
                if (chessArr[i][j]!=0)
                {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);
        //创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //遍历二维数组,将非零值放入稀疏数组中
        int count = 0;
        for (int i = 0; i < chessArr.length; i++)
        {
            for (int j = 0; j < chessArr.length; j++)
            {
                if (chessArr[i][j]!=0)
                {
                    count++;
                    sparseArr[count][0] =i;
                    sparseArr[count][1] =j;
                    sparseArr[count][2] = chessArr[i][j];
                }
            }
        }

        //遍历对应的稀疏数组
        System.out.println("遍历对应的稀疏数组");
        print2dArr(sparseArr);

        //将稀疏数组存入文件中
        System.out.println("将稀疏数组存入文件中");
        writeData2File(sparseArr,"d:/test/aa/data.map");

        //从文件中读取二维数据
        int[][] sparseFile = readDataFromFile("d:/test/aa/data.map");
        System.out.println("从文件中读取数组");
        print2dArr(sparseFile);

        //将稀疏数组还原成对应的二维数据
        int [][] chessArr2 = new int[sparseFile[0][0]][sparseFile[0][1]];
        for (int i = 1; i < sparseFile.length; i++)
        {
            chessArr2[sparseFile[i][0]][sparseFile[i][1]] = sparseFile[i][2];
        }

        print2dArr(chessArr2);


    }


    private void print2dArr(int[][] sparseArr)
    {
        for (int[] row : sparseArr)
        {
            System.out.println(Arrays.toString(row));
        }
    }

    private void writeData2File(int[][] sparseArr, String path) throws IOException
    {
        List<String> arryList = new ArrayList<>();
        for (int[] ints : sparseArr)
        {
            //String s = ints[0] + "," + ints[1] + "," + ints[2];
            String s = Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(","));
            //String s = Stream.of(ints).map(String::valueOf).collect(Collectors.joining(","));
            arryList.add(s);
        }
        Path filePath = Paths.get(path);
        Files.write(filePath,arryList, Charset.forName("UTF-8"));
    }

    private int[][] readDataFromFile(String path) throws IOException
    {
        List<String> datas = Files.readAllLines(Paths.get("d:/test/aa/data.map"));
        int row = datas.size();
        int clomn = datas.get(0).split(",").length;
        int[][] arr = new int[row][clomn];

        for (int i = 0; i < arr.length; i++)
        {
            String[] split = datas.get(i).split(",");
            int[] ints = Arrays.stream(split).mapToInt(Integer::valueOf).toArray();
            arr[i] = ints;
        }
        return arr;
    }

}
