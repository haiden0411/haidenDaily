package com.huawei.javaNewFeture.CompletableFuture;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
/**
 * Author：胡灯
 * Date：2021-05-26 21:33
 * Description：<描述>
 */
public class BoxscoreRetriever implements Function<List<String>, List<String>>
{
    @SuppressWarnings("ConstantConditions")
    public Optional<String> gamePattern2Result(String pattern)
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        // 获取响应
        if (Long.valueOf(pattern) % 5 == 0)
        {
            System.out.println("Box score not found for " + pattern);
            return Optional.empty();
        }
        return Optional.ofNullable(pattern);
    }
    @Override
    public List<String> apply(List<String> strings)
    {
        return strings.stream()
                .map(this::gamePattern2Result)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
    private void saveResultList(List<String> results)
    {
        results.parallelStream().forEach(this::saveResultToFile);
    }
    public void saveResultToFile(String result)
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println(result + "save to file");
    }
    private int getTotalScore(String result)
    {
// 两队得分之和
        return IntStream.rangeClosed(0, result.length() - 1).mapToObj(result::charAt)
                .map(String::valueOf)
                .mapToInt(Integer::valueOf)
                .sum();
    }
    public OptionalInt getMaxScore(List<String> results)
    {
        return results.stream()
                .mapToInt(this::getTotalScore)
                .max();
    }
    public Optional<String> getMaxGame(List<String> results)
    {
        return results.stream()
                .max(Comparator.comparingInt(this::getTotalScore));
    }
    public void printGames(LocalDate localDate, int days)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(100);

       CompletableFuture<List<String>> future = CompletableFuture.supplyAsync(new GamePageLinksSupplier(localDate, days),executorService).thenApplyAsync(new BoxscoreRetriever(),executorService);

       /* CompletableFuture<Void> futureWrite = future.thenAcceptAsync(this::saveResultList)
                .exceptionally(e ->
                {
                    System.out.println(e.getMessage());
                    return null;
                });
        CompletableFuture<OptionalInt> futureMaxScore = future.thenApplyAsync(this::getMaxScore);
        CompletableFuture<Optional<String>> futureMaxGame = future.thenApplyAsync(this::getMaxGame);
        CompletableFuture<String> futureMax = futureMaxScore.thenCombine(futureMaxGame, (score, game) -> String.format("Highest score: %d, Max Game: %s",
                score.orElse(0), game.orElse(null)));
        CompletableFuture.allOf(futureWrite,futureMax).join();
        future.join().forEach(System.out::println);
       System.out.println(futureMax.join());*/
        future.join().forEach(System.out::println);
    }
    public static void main(String[] args)
    {
        long start = System.currentTimeMillis();
        BoxscoreRetriever box = new BoxscoreRetriever();
        box.printGames(LocalDate.now(),10);
        System.out.println("takes:"+(System.currentTimeMillis()-start));
    }
}
