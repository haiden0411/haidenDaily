package huawei.service;/**
 * Author：胡灯
 * Date：2021-11-21 16:31
 * Description：<描述>
 */
import com.huawei.Daily_App;
import com.huawei.springboot.service.AdvancedEtcdService;
import com.huawei.springboot.service.EtcdService;
import com.huawei.springboot.service.impl.AdvancedEtcdServiceImpl;
import com.huawei.springboot.service.impl.EtcdServiceImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * @description: AdvancedEtcdServiceImplTest
 * @author Administrator
 * @date 2021/11/21 16:31
 */
public class AdvancedEtcdServiceImplTest
{

    // 为了便于维护，不轮是单元测试还是springboot应用，endpoint都来自AdvancedOperateApplication.endpoints这个变量
    private static final String endpoints = Daily_App.endpoints;

    private static EtcdService etcdService = new EtcdServiceImpl(endpoints);

    private static AdvancedEtcdService advancedEtcdService = new AdvancedEtcdServiceImpl(endpoints);

    private static String key(String name) {
        return "/AdvancedEtcdServiceImplTest/" + name + "-" + System.currentTimeMillis();
    }

    @AfterAll
    static void close() {
        etcdService.close();
        advancedEtcdService.close();
    }

    @Test
    void cas() throws Exception {
        // 本次要操作的键
        String key = key("cas");

        // 初始值，在cas操作之前的值
        String firstValue = "aaa";

        // 第一次cas操作时要写入的值
        String secondValue = "bbb";

        // 第二次cas操作时要写入的值
        String thirdValue = "ccc";

        // cas操作前，将值写为"aaa"
        etcdService.put(key, firstValue);

        // 第一次cas操作，查出的值如果等于"aaa"，就将其改为"bbb"
        // 此时因为值等于"aaa"，所以cas操作成功，值被改为"bbb"
        boolean casRlt = advancedEtcdService.cas(key, firstValue, secondValue);

        // 更新成功返回true
        assertTrue(casRlt);

        // 通过查询来验证值已经更新为"bbb"
        assertEquals(secondValue, etcdService.getSingle(key));

        // 第二次case操作，查出的值如果等于"aaa"，就将其改为"bbb"
        // 此时因为值等于"bbb"，和期望值"aaa"不想等，因此cas失败，没有发生任何写操作，值还是"bbb"
        casRlt = advancedEtcdService.cas(key, firstValue, thirdValue);

        // cas失败就会返回false
        assertFalse(casRlt);

        // 确认最新的值还是上次更新的"bbb"，没有被更新为"ccc"
        assertEquals(secondValue, etcdService.getSingle(key));
    }

}
