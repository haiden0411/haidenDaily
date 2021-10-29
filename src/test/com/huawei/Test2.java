package huawei;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Strings;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Author：胡灯
 * Date：2021-06-19 21:25
 * Description：<描述>
 */
public class Test2 extends Date
{

    public void test(){
        System.out.println(super.getClass().getName());
    }

    public static void main(String[] args)
    {
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 = Integer.valueOf(59);
        Integer i04 = new Integer(59);
        Integer i05 = new Integer(59);
        Integer i06 = 56;
        Integer i07 = 56;
        System.out.println(i01 == i02);
        System.out.println(i01 == i03);
        System.out.println(i03 == i04);
        System.out.println(i02 == i04);
        System.out.println(i04 == i05);
        System.out.println(i06 == i07);

        System.out.println(2<<3);
        List<Integer> integers = Arrays.asList(1, 2, 3, 7, 5);
        Integer[] integers1 = integers.toArray(new Integer[0]);
        System.out.println(Arrays.toString(integers1));
        String str = "aa_bb_cc";
        System.out.println(str.substring(str.lastIndexOf("_")+1));

        int[] arr = {1, 3, 4, 6};
        int[] remove = ArrayUtils.remove(arr, 3);
        System.out.println(Arrays.toString(remove));
        System.out.println(1 << 29);

    }
}
