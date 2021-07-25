package huawei;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
/**
 * Author：胡灯
 * Date：2021-07-25 11:41
 * Description：<描述>
 */
public class TestInvokList
{
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CountDownLatch countDownLatch = new CountDownLatch(10);
        List<Callable<String>> callableList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
        {
            callableList.add(new TestCallable(countDownLatch));
        }
        executorService.invokeAll(callableList);
        countDownLatch.await(20,TimeUnit.SECONDS);
        System.out.println("all finish~~~");
        executorService.shutdown();
    }

}
@Data
@NoArgsConstructor
@AllArgsConstructor
class TestCallable implements Callable<String>
{
    CountDownLatch countDownLatch;

    @Override
    public String call() throws Exception
    {
        int i = new Random().nextInt(7000);
        Thread.sleep(i);
        countDownLatch.countDown();
        System.out.println("test"+i+" finish");
        return "test"+i;
    }
}
