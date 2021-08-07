package com.huawei;

import com.alibaba.fastjson.JSON;
import com.google.common.base.*;
import com.google.common.collect.*;
import com.google.common.io.ByteSink;
import com.google.common.net.InetAddresses;
import com.huawei.springboot.domain.User;
import com.huawei.springboot.utils.Java8Utils;
import com.huawei.springboot.utils.ZipTools;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.*;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Author：胡灯
 * Date：2019-08-11 21:34
 * Description：<描述>
 */
public class TestUserFulApi
{
    @Test(expected = IndexOutOfBoundsException.class)
    public void testPrecondition()
    {
        User user = new User();
        user.setName("aa");
        user.setAge(201);
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    @Test
    public void testObjecs()
    {
        User user1 = new User();
        user1.setName("haiden1");
        User user2 = new User();
        user2.setName("hujai");
        User user3 = new User();
        user3.setName("bda");
        ArrayList<User> users = Lists.newArrayList(user1,user2,user3);
        System.out.println(users.size());
        Ordering<User> ordering = Ordering.natural().nullsFirst().onResultOf(User::getName);
        User max = ordering.max(users);
        System.out.println(max.getName());
        System.out.println(ordering.isOrdered(users));
    }

    @Test(expected = ArithmeticException.class)
    public void testThrowable()
    {
        try
        {
            System.out.println(1 / 0);
        }
        catch (Throwable e)
        {
            System.out.println(Throwables.getRootCause(e));
            throw e;
        }
    }

    @Test
    public void testMaps()
    {
        HashMap<String, String> map1 = Maps.newHashMap();
        map1.put("aa", "bb");
        map1.put("bb", "cc");
        map1.put("cc", "dd");
        HashMap<String, String> map2 = Maps.newHashMap();
        map2.put("bb", "cc");
        map2.put("ff", "gg");
        map2.put("cc", "cddc");
        MapDifference<String, String> difference = Maps.difference(map1, map2);
        Map<String, String> stringStringMap = difference.entriesInCommon();
        Map<String, MapDifference.ValueDifference<String>> stringValueDifferenceMap = difference.entriesDiffering();
        System.out.println(stringStringMap);
        System.out.println(stringValueDifferenceMap);
    }

    @Test
    public void testFunction()
    {
        String s = "this is a best marry test today is a good date";
        Iterable<String> split = Splitter.on(" ").split(s);
        System.out.println(split);
        HashMultiset<String> multiset = HashMultiset.create(split);
        Set<String> strings = Sets.newHashSet(split);
        for (String string : strings)
        {
            System.out.println(string + "---->count:" + multiset.count(string));
        }
        ImmutableSet<String> strings1 = ImmutableSet.copyOf(split);
        System.out.println(strings1.size());
        ImmutableSet.Builder<Object> add = strings1.builder().add("aaa").add("bb");
        ImmutableSet<Object> build = add.build();
        System.out.println(build.size());
        String s1 = Strings.commonPrefix("aaabbbbbb", "aadafafa");
        System.out.println(s1);
        System.out.println(Strings.repeat("haiden", 10));
        System.out.println(Utf8.encodedLength("aaabbb"));
    }

    @Test
    public void testCharactor()
    {
        String aa = " dafafafaf3332fasfs54f5fw2f12ffafafafaf12f1afa ";
        System.out.println(CharMatcher.digit().retainFrom(aa));
        String s = CharMatcher.whitespace().trimFrom(aa);
        System.out.println(s);
    }

    @Test
    public void testFiles() throws IOException
    {
        File dest = new File("c:/test/guava-19.CHM");
        File file = new File("c:/test/guava-18.CHM");
        System.out.println(file.exists());
        System.out.println(dest.exists());
        dest.deleteOnExit();
        ByteSink byteSink = com.google.common.io.Files.asByteSink(dest);
        byteSink.openStream();
        byteSink.write( com.google.common.io.Files.toByteArray(file));
    }

    @Test
    public void testNet() throws SocketException
    {
        Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
        System.out.println(networkInterfaces);
        String ip = null;
        while (networkInterfaces.hasMoreElements())
        {
            NetworkInterface networkInterface = networkInterfaces.nextElement();
            //System.out.println(networkInterface.getName());
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements())
            {
                InetAddress inetAddress = inetAddresses.nextElement();
                if (inetAddress != null && inetAddress instanceof Inet4Address && inetAddress.isSiteLocalAddress() && !"127.0.0.1".equals(inetAddress.getHostAddress()))
                {
                    ip = inetAddress.getHostAddress();
                    System.out.println(ip);
                }
            }
        }
    }

    @Test
    public void testNet2()
    {
        InetAddress ip = InetAddresses.forString("192.168.1.107");
        InetAddress decrement = InetAddresses.decrement(ip);
        System.out.println(decrement.getHostAddress());
        int i = InetAddresses.coerceToInteger(ip);
        System.out.println(i);
        Inet4Address inet4Address = InetAddresses.fromInteger(-1062731413);
        System.out.println(inet4Address.getHostAddress());
    }

    @Test
    public void testMutiMap()
    {
        ArrayListMultimap<String, Integer> muti = ArrayListMultimap.create();
        muti.put("foo", 1);
        muti.put("foo", 5);
        muti.put("foo", 3);
        muti.put("foo", 4);
        muti.put("bar", 4);
        muti.put("bar", 4);
        muti.put("bar", 4);
        System.out.println("muti1:" + muti.size());
        HashMultimap<String, Integer> muti2 = HashMultimap.create();
        muti2.put("foo", 1);
        muti2.put("foo", 1);
        muti2.put("foo", 1);
        muti2.put("foo", 1);
        muti2.put("foo", 1);
        muti2.put("foo", 2);
        muti2.put("foo", 3);
        System.out.println("muti2:" + muti2.size());
        System.out.println(muti2.get("foo"));
    }

    @Test
    public void testZip() throws IOException
    {
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("c:/test/testZip/aa.zip"));
        ZipEntry zipEntry = new ZipEntry("windows-version.txt");
        zipEntry.setComment("test zip file");
        zos.putNextEntry(zipEntry);
        InputStream input = new FileInputStream("c:/test/testZip/windows-version.txt");
        int len = 0;
        while ((len = input.read()) != -1)
        {
            zos.write(len);
        }
        input.close();
        zos.close();

    }

    @Test
    public void testZipUtils() throws Exception
    {

        //ZipTools.zipFile("C:\\study_doc\\2019\\guava","C:\\study_doc\\2019\\guava\\guava.zip");
        //ZipTools.deleteDirFile("C:\\study_doc\\2019\\guava\\guava-master");
        ZipTools.upZipFile("C:\\study_doc\\2019\\guava\\guava-master.zip");

    }

    @Test
    public void testGuavaFiles() throws Exception
    {
        //System.out.println(Files.toString(new File("G:\\haiden重要\\日常查资料\\daliywork.txt"), Charset.forName("GBK")));
        //Files.createParentDirs(new File("d:/test/haiden/gree/aa.txt"));
        //Files.touch(new File("d:/test/haiden/gree/aa.txt"));

        //File tempFile = File.createTempFile("d:/test/aa/cc/haiden", "txt");
        File tempFile = File.createTempFile("d:/test/aa/cc/haiden", ".txt");
        System.out.println(tempFile.getAbsolutePath());
    }

    @Test
    public void testClone()
    {
        int arr[] = {5,10,15,20,35,125,111111,1,3,5};
        int[] clone = arr.clone();
        Arrays.sort(clone);
        System.out.println(clone[clone.length-1]);

       Integer[] brr = {5,10,15,20,35,125,1111111,1,3,5};
        List<Integer> integers = Arrays.asList(brr);
        TreeSet<Integer> ts = new TreeSet<>(integers);
        System.out.println(ts.last());
        System.out.println(ts.lower(ts.lower(ts.last())));
    }

    @Test
    public void testParaPerformace(){
        long testNum = 100_000_000;
        //System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::sequentialSum, testNum));
        //System.out.println("squence sum done in" + Java8Utils.measureSumPerf(Java8Utils::parallelSum, testNum));
        //System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::iterativeSum, testNum));
        //System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::rangSum, testNum));
        //System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::parallRangSum, testNum));
        //System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::sideEffectSum, testNum));
        System.out.println("squence sum done in " + Java8Utils.measureSumPerf(Java8Utils::forkJoinSum, testNum));

    }
    @Test
    public void testSpiltor(){
        final String SENTENCE =
                " Nel  mezzo del cammin di nostra vita " +
                        "mi ritrovai in una selva oscura" +
                        " ché la dritta via era smarrita ";
        System.out.println("Fount " + Java8Utils.countWordsIteratively(SENTENCE) + " words");
        Stream<Character> stream = IntStream.rangeClosed(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
        
    }

    @Test
    public void testStreamMap(){
        int [] aa = {1,2,4};
        int [] bb = {5,4,8};
        //两两匹配

    }

    @Test
    public void testJava8Date(){
        LocalDate date = LocalDate.of(2014, 3, 18).with(ChronoField.MONTH_OF_YEAR,9).plusYears(2).minusMonths(10);
        //date = date.plusYears(2).minusMonths(10);
        System.out.println(date);
    }
    @Test
    public void testJava8Files() throws IOException
    {
        Path path = Paths.get("J:\\应用宝\\应用宝照片备份");
        Path path1 =  Paths.get("J:\\应用宝\\应用宝照片备份\\一加3t照片\\OnePlus ONEPLUS A3010照片备份\\2018年02月\\IMG_20180203_171105.jpg");
        Path path2 =  Paths.get("J:\\应用宝\\应用宝照片备份\\一加3t照片\\OnePlus ONEPLUS A3010照片备份\\2018年05月");
        System.out.println(Files.exists(path));
        System.out.println(Files.exists(path1));
        System.out.println(path1.toString().endsWith(".jpg"));
        long count = Files.walk(path).filter(p -> Files.isRegularFile(p)
                && p.toString().endsWith("jpg")).count();
        System.out.println(count);
        FileStore fileStore = Files.getFileStore(Paths.get("d:"));
        System.out.println(JSON.toJSON(fileStore));
        System.out.println(fileStore.getTotalSpace()/1024/1024/1024+"-"+fileStore.getUnallocatedSpace()/1024/1024/1024);
        UserPrincipal owner = Files.getOwner(path);
        System.out.println(owner.getName());
        URL url = new URL("http://aaa.com");
        //Files.copy(url.openConnection().getInputStream(),Paths.get("d://aa/a.tar.gz"));
        Map<String, Object> stringObjectMap = Files.readAttributes(path, "*");
        System.out.println(JSON.toJSON(stringObjectMap));
    }
    /**
     * 在执⾏行行try、catch中的return之前⼀一定会执⾏行行finally中的代码（如果finally存在），如
     * 果finally中有return语句句，就会直接执⾏行行finally中的return⽅方法，所以finally中的return语句句
     * ⼀一定会被执⾏行行的
     * 执⾏行行流程：finally执⾏行行前的代码⾥里里⾯面有包含return，则会先确定return返回值，然后再执
     * ⾏行行finally的代码，最后再执⾏行行return
     */
    @Test
    public void testTryCatch(){
        System.out.println(test1());
        System.out.println(test2());
    }
    @Test
    public void testCharString()
    {
        String name = "haiden";
        String s = "'";
        char c = s.charAt(0);
        System.out.println(c);
        System.out.println(Strings.padStart(name, name.length()+1, c));
        System.out.println(StringUtils.leftPad(name, name.length() + 1, c));
        StringBuilder append = new StringBuilder().append("'").append(name).append("'");
        System.out.println(append);
        List<LocalDate> collect = Stream.iterate(LocalDate.now(), d -> d.plusDays(1)).limit(10).collect(Collectors.toList());
        collect.forEach(System.out::println);
        System.out.println("string".charAt(0)*new Random().nextDouble());
    }
    private static int test1() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
        }
        return a;
    }

    private static int test2() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
            return a;
        }
    }

}
