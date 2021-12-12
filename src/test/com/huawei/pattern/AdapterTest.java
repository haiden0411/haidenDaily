package com.huawei.pattern;
import com.huawei.pattern.adapter.IPassportForThird;
import com.huawei.pattern.adapter.PassportForThirdAdapter;
import org.junit.Test;
/**
 * @Author：胡灯
 * @Date：2021-12-12 16:42
 * @Description：AdapterTest
 */
public class AdapterTest
{
    @Test
    public void testAdapter(){
        IPassportForThird adapter = new PassportForThirdAdapter();
        adapter.loginForQQ("sdfadfffd");
    }
}
