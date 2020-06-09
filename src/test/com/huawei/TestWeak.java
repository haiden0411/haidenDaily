package huawei;
import org.junit.Test;

import java.lang.ref.SoftReference;
/**
 * Author：胡灯
 * Date：2019-12-23 22:39
 * Description：<描述>
 */
public class TestWeak
{
    @Test
    public void testSoftReferce()
    {
        System.out.println("start");
        Obj obj = new Obj();
        SoftReference<Obj> objSoftReference = new SoftReference<Obj>(obj);
        System.gc();
        System.out.println(objSoftReference.get());
        System.out.println("end");
    }
}

class  Obj {

    int [] obj;
    public Obj(){
        obj = new int[10000];
    }
}