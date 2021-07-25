package huawei;
import io.swagger.models.auth.In;

import java.util.Date;
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
    }
}
