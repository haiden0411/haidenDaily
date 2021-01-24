package com.huawei;
import com.huawei.Daliy.dataSource.MyDataSource;
import com.huawei.springboot.domain.Apple;
import com.huawei.springboot.domain.Trader;
import com.huawei.springboot.domain.Transaction;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static java.util.Comparator.comparing;

import java.util.concurrent.*;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.counting;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.huawei.springboot.domain.Dish;
import org.junit.Before;
import org.junit.Test;

import static java.util.stream.Collectors.*;
/**
 * Author：胡灯
 * Date：2019-09-01 23:19
 * Description：<描述>
 */
public class TestJava8
{
    private List<String> lists = new ArrayList<>();
    private List<Apple> apples = new ArrayList<>();
    List<Dish> menu = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();
    private void print(Collection<?> collection)
    {
        collection.forEach(System.out::println);
    }
    private void printSeparate()
    {
        System.out.println("---------------------");
    }
    @Before
    public void setup()
    {
        lists = Arrays.asList("aa", "bbb", "ccccc", "chinasoft", "begin", "shen", "haiden", "missxiaoasfafafafa", "c");
        apples = Arrays.asList(new Apple("gree", 20),
                new Apple("red", 30), new Apple("back", 40),
                new Apple("yello", 80));
        menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beff", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rick", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.MEAT),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH));
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }
    @Test
    public void test1()
    {
        Thread t = new Thread(() ->
        {
            System.out.println("aa");
        });
        t.start();
        Comparator<Apple> c = (o1, o2) -> o1.getWeight().compareTo(o2.getWeight());
        //apples.sort(Comparator.comparing(Apple::getWeight));
        apples.sort(comparing(Apple::getWeight).reversed());
        apples.stream().forEach(System.out::println);
    }
    @Test
    public void test02()
    {
        List<Dish> vegetarian = menu.stream().filter(Dish::isVegetarian).collect(toList());
        printCollection(vegetarian);
        List<Integer> numbers = Arrays.asList(1, 2, 2, 2, 4, 3, 4, 5, 6, 7, 20, 45);
        numbers.stream().filter(i -> i % 2 == 0).distinct().forEach(System.out::println);
        List<Dish> dishes = menu.stream().filter(dish -> dish.getCalories() > 300).skip(2).collect(toList());
        printCollection(dishes);
        //练习
        System.out.println("-----------------------");
        List<Dish> meateDish = menu.stream().filter(dish -> dish.getType() == Dish.Type.MEAT).limit(2).collect(toList());
        printCollection(meateDish);
    }
    @Test
    public void testStreamMap()
    {
        List<String> dishNames = menu.stream().map(Dish::getName).collect(toList());
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordsNumber = words.stream().map(String::length).collect(toList());
        printSeparate();
        List<Integer> collect = menu.stream().map(Dish::getName).map(String::length).collect(toList());
        List<String> words2 = Arrays.asList("hello", "world");
        List<String> collect1 = words2.stream().map(s -> s.split("")).flatMap(Arrays::stream).distinct().collect(toList());
        List<Integer> numbers = Arrays.asList(1, 2, 2, 2, 4, 3, 4, 5, 6, 7, 20, 45);
        List<Integer> collect2 = numbers.stream().map(i -> i * i).distinct().collect(toList());
        print(collect2);
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);
        //List<int[]> collect3 = number1.stream().flatMap(i -> number2.stream().map(j -> new int[]{i, j})).collect(toList());
        number1.stream().flatMap(i -> number2.stream().map(j -> new int[]{i, j})).collect(toList());
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(System.out::println);
    }
    @Test
    public void testReduce()
    {
        List<Integer> numbers = Arrays.asList(1, 2, 2, 2, 4, 3, 4, 5, 6, 7, 20, 45);
        List<Integer> numbers2 = Arrays.asList(1, 2, 2, 2);
        System.out.println(numbers.stream().reduce(0, (a, b) -> (a + b)));
        System.out.println(numbers2.stream().reduce(1, (a, b) -> a * b));
        System.out.println(numbers.stream().reduce(Integer::sum));
        //测试
        Optional<Integer> reduce = menu.stream().map(dish -> 1).reduce(Integer::sum);
        System.out.println(reduce.get());
    }
    @Test
    public void testTrader()
    {
        //找出2011年的所有交易并按交易额排序（从低到高）
        List<Transaction> collect = transactions.stream().filter(t -> t.getYear() == 2011).sorted(comparing(Transaction::getValue)).collect(toList());
        print(collect);
        printSeparate();
        //交易员都在哪些不同的城市工作过
        List<String> cities = transactions.stream().map(transaction -> transaction.getTrader().getCity()).distinct().collect(toList());
        print(cities);
        printSeparate();
        //查找所有来自于剑桥的交易员，并按姓名排序
        List<Trader> traders = transactions.stream().filter(t -> t.getTrader().getCity() == "Cambridge").map(t -> t.getTrader()).sorted(comparing(Trader::getName)).distinct().collect(toList());
        print(traders);
        printSeparate();
        //返回所有交易员的姓名字符串，按字母顺序排序
        String names = transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted().collect(joining("~"));
        System.out.println(names);
        //有没有交易员是在米兰工作的
        printSeparate();
        boolean b = transactions.stream().anyMatch(t -> t.getTrader().getCity() == "Milan");
        System.out.println(b);
        //打印生活在剑桥的交易员的所有交易额
        printSeparate();
        transactions.stream().filter(t -> t.getTrader().getCity() == "Cambridge").map(Transaction::getValue).forEach(System.out::println);
        //所有交易中，最高的交易额是多少
        printSeparate();
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::max));
        //找到交易额最小的交易
        printSeparate();
        //1
        System.out.println(transactions.stream().map(Transaction::getValue).reduce(Integer::min));
        //2
        transactions.stream().min(comparing(Transaction::getValue));
    }
    @Test
    public void testDataStream()
    {
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        System.out.println(max.orElse(1));
        System.out.println(sum);
        List<Integer> collect = IntStream.range(1, 100).filter(i -> i % 2 == 0).mapToObj(operand -> operand).collect(toList());
        ;
        //生成勾股数
        printSeparate();
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed().flatMap
                (a -> IntStream.rangeClosed(a, 100).
                        filter(b -> Math.sqrt(a * a + b * b) % 1 == 0).
                        mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        IntStream.rangeClosed(1, 100).boxed().flatMap(a -> IntStream.rangeClosed(a, 100).
                mapToObj(b -> new double[]{a, b, (double) Math.sqrt(a * a + b * b)}).filter(t -> t[2] % 1 == 0));
        pythagoreanTriples.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));
    }
    @Test
    public void testCreatStream() throws IOException
    {
        //由值创建流
        Stream<String> stream = Stream.of("Java", "in", "action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        Stream<Object> empty = Stream.empty();
        //由数组创建流
        printSeparate();
        int[] numbers = {1, 3, 45, 24};
        System.out.println(Arrays.stream(numbers).sum());
        //由文件生成流
        printSeparate();
        File testFile = new File("F:\\test\\IO\\newPom.txt");
        long uniqueWorlds = 0;
        Path path = Paths.get("F:\\test\\IO\\newPom.txt");
        try (Stream<String> lines = Files.lines(path, Charset.defaultCharset()))
        {
            System.out.println(lines.flatMap(t -> Arrays.stream(t.split(""))).distinct().count());
        }
        catch (IOException e)
        {
        }
        //统计文件中使用次数最多的单词
        //由函数生成流：创建无限流
        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        // 测验5.4：斐波纳契元组序列
        printSeparate();
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(20).forEach(t -> System.out.println("(" + t[0] + "," + t[1] + ")"));
    }
    @Test
    public void testCollector()
    {
        Comparator<Dish> caloryComparator = Comparator.comparingInt(Dish::getCalories);
        Optional<Dish> collect = menu.stream().collect(maxBy(caloryComparator));
        System.out.println(collect.get());
        System.out.println(menu.stream().collect(summingInt(Dish::getCalories)));
        IntSummaryStatistics summaryStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(summaryStatistics);
        menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        //找出热量最高的菜
        Optional<Dish> highestDish = menu.stream().collect(reducing((dish1, dish2) ->
                dish1.getCalories() > dish2.getCalories() ? dish1 : dish2));
        Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6).stream();
        List<Integer> reduce = stream.reduce(new ArrayList<>(),
                (List<Integer> l, Integer e) ->
                {
                    l.add(e);
                    return l;
                },
                (List<Integer> integers, List<Integer> integers2) ->
                {
                    integers.addAll(integers2);
                    return integers;
                }
        );
        IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
        //：用reducing连接字符串
        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        menu.stream().collect(reducing("", Dish::getName, (s, s2) -> s + s2));
        menu.stream().map(Dish::getName).collect(reducing((s, s2) -> s + s2));
    }
    @Test
    public void testGroup()
    {
        Map<Dish.Type, List<Dish>> collect = menu.stream().collect(groupingBy(Dish::getType));
        System.out.println(collect);
        final String SENTENCE =
                " Nel mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        Stream<Character> characterStream = IntStream.rangeClosed(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
    }
    @Test
    public void testFuture()
    {
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<Double> submit = exec.submit(new Callable<Double>()
        {
            @Override
            public Double call() throws Exception
            {
                return doSomethingAction();
            }
        });
        try
        {
            Double result = submit.get(1, TimeUnit.SECONDS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (TimeoutException e)
        {
            e.printStackTrace();
        }
    }
    @Test
    public void testSort()
    {
        List<String> collect = lists.stream().sorted(comparing(String::length)).collect(toList());
        collect.forEach(System.out::println);
    }
    @Test
    public void testMaxMin()
    {
        Optional<Integer> max = menu.stream().min(comparingInt(Dish::getCalories)).map(dish -> dish.getCalories());
        System.out.println(max.get());
        lists.stream().collect(toCollection(HashSet::new));
    }
    @Test
    public void testParll()
    {
        for (int i = 0; i < 1000; i++)
        {
            //List<Integer> lists = new ArrayList<>();
            List<Integer> lists = new CopyOnWriteArrayList<>();
            IntStream.range(0, 100).parallel().forEach(lists::add);
        }
    }

    @Test
    public void testJoin(){
        List<String> lists = Arrays.asList("spring", "struts", "hibernate","css","html","bat");
        String collect = lists.stream().collect(joining("||", "[", "]"));
        System.out.println(collect);
        Map<Boolean, List<String>> collect1 = lists.stream().collect(partitioningBy(s -> s.length() > 4));
        System.out.println(collect1);
        Map<Dish.Type, Long> collect2 = menu.stream().collect(groupingBy(dish -> dish.getType(), counting()));
        System.out.println(collect2);
        IntSummaryStatistics collect3 = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(collect3);
    }

    @Test
    public void testTry()
    {
        String path = "";
        try( OutputStream ops = new FileOutputStream(path);)
        {

            ops.write("aaa".getBytes("UTF-8"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Test
    public void testDatasourceInsert() throws SQLException
    {
        MyDataSource dataSource = new MyDataSource();
        Connection conn = dataSource.getConnection();
        String sql1 = "insert into t_customer(address,gender,name,telephone) values (?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql1);
        ps.setString(1,"英山");
        ps.setString(2,"女");
        ps.setString(3,"小贺");
        ps.setString(4,"1589985835");
        int i = ps.executeUpdate();
        System.out.println(i);
        ps.close();
        conn.close();
    }

    @Test
    public void testDatasoruceQuery() throws SQLException
    {
        MyDataSource dataSource = new MyDataSource();
        Connection conn = dataSource.getConnection();
        String sql = "select * from t_customer";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next())
        {
            System.out.println(rs.getInt(1));
        }
        rs.close();
        ps.close();
        conn.close();
    }
    @Test
    public void testCollector2()
    {
        System.out.println("-----------sort");
        List<Dish> collect = menu.stream().sorted(comparing(Dish::getCalories).reversed()).collect(toList());
        printCollection(collect);
        System.out.println("-----------summaryInt");
        Integer intValue = menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(intValue);
        System.out.println("-----------maptoint");
        int sum = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum);
        System.out.println("-----------reducet");
        Integer sum2 = menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);
        System.out.println(sum2);
    }
    private void printCollection(Iterable<Dish> collect)
    {
        collect.forEach(System.out::println);
    }
    private Double doSomethingAction()
    {
        return new Double(250);
    }
}
