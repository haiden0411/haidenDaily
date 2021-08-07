package huawei;
import com.huawei.javaNewFeture.Adjusters;
import com.huawei.javaNewFeture.PaydayAdjuster;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
/**
 * Author：胡灯
 * Date：2021-01-22 23:22
 * Description：<描述>
 */
public class TestDate
{
    @Test
    public void testDateConvert()
    {
        Date date = new Date();
        System.out.println(date.getTime());
        System.out.println(date2LocalDate(date));
        LocalDate now = LocalDate.now();
        System.out.println(localeDate2Date(now));
        LocalDate test = LocalDate.of(50000, 12, 30);
        Date date1 = localeDate2Date(test);
        System.out.println(date1.getTime());
        System.out.println(convertUtilDateToLocalDate(date1));
        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
    }
    @Test
    public void testZone()
    {
        LocalDateTime dateTime = LocalDateTime.of(2017, Month.JULY, 4, 13, 20, 45);
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.of("America/New_York"));
        ZonedDateTime london = zonedDateTime.withZoneSameInstant(ZoneId.of("Europe/London"));
        LocalDateTime now = LocalDateTime.now();
        System.out.println(ZoneId.systemDefault());
        ZonedDateTime zonedDateTime1 = now.atZone(ZoneId.of("+8"));
        ZonedDateTime zonedDateTime2 = zonedDateTime1.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
        LocalDateTime localDateTime = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);
        Date from = Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
    }
    @Test
    public void testMothWeek()
    {
        System.out.println(Month.FEBRUARY.length(false));
        LocalDateTime now = LocalDateTime.now();
    }
    @Test
    public void testMonth()
    {
        System.out.println(Month.AUGUST.firstDayOfYear(true));
        System.out.println(Month.AUGUST.firstDayOfYear(false));
        System.out.println(Month.of(1));
        System.out.println(Month.JANUARY.plus(2));
        System.out.println(Month.MARCH.minus(2));
    }
    @Test
    public void testPlusMinus()
    {
        LocalDateTime now = LocalDateTime.now();
        Period period = Period.of(2, 3, 4);
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.plus(period);
        System.out.println(start);
        System.out.println(end);
        end = start.plus(3, ChronoUnit.HALF_DAYS);
        System.out.println(end);
        end = start.minus(2, ChronoUnit.CENTURIES);
        System.out.println(end);
        end = start.plus(3, ChronoUnit.MILLENNIA);
        System.out.println(end);
    }
    @Test(expected = DateTimeException.class)
    public void testWith()
    {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        start.withDayOfMonth(29);
    }
    @Test
    public void testTemporaFild()
    {
        LocalDateTime start = LocalDateTime.of(2017, Month.JANUARY, 31, 11, 30);
        LocalDateTime end = start.with(ChronoField.MONTH_OF_YEAR, 2);
        System.out.println(end);
    }
    @Test
    public void testAjust()
    {
        LocalDateTime start = LocalDateTime.of(2017, Month.FEBRUARY, 2, 11, 30);
        LocalDateTime end = start.with(TemporalAdjusters.firstDayOfNextMonth());
        System.out.println(end);
        end = start.with(TemporalAdjusters.firstDayOfNextYear());
        System.out.println(end);
        end = start.with(TemporalAdjusters.lastDayOfYear());
        System.out.println(end);
        end = start.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        System.out.println(end);
    }
    @Test
    public void testPayDayAdjuster()
    {
        PaydayAdjuster paydayAdjuster = new PaydayAdjuster();
       /* IntStream.rangeClosed(1,14).mapToObj(day->LocalDate.of(2021,Month.JANUARY,day))
                .forEach(date-> assertEquals(15,date.with(paydayAdjuster).getDayOfMonth()));
        IntStream.rangeClosed(15,31).mapToObj(day->LocalDate.of(2021,Month.JANUARY,day))
                .forEach(date-> assertEquals(29,date.with(paydayAdjuster).getDayOfMonth()));*/
        IntStream.rangeClosed(1, 14).mapToObj(day -> LocalDate.of(2021, Month.JANUARY, day))
                .forEach(date -> assertEquals(15, date.with(Adjusters::adjustInto).getDayOfMonth()));
        IntStream.rangeClosed(15, 31).mapToObj(day -> LocalDate.of(2021, Month.JANUARY, day))
                .forEach(date -> assertEquals(29, date.with(Adjusters::adjustInto).getDayOfMonth()));
    }
    @Test
    public void testTempQuery()
    {
        System.out.println(LocalDate.now().query(TemporalQueries.precision()));
        System.out.println(LocalDate.now().get(ChronoField.ALIGNED_WEEK_OF_YEAR));
        System.out.println(LocalDate.now().get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR));
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(localDate);
        Date from = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(from);
        //System.out.println(Instant.from(localDate).toEpochMilli());
        System.out.println("0000");
        System.out.println(date.getTime());
        System.out.println("1111");
        System.out.println(Instant.now().toEpochMilli());
        Instant instant = Instant.MAX;
        System.out.println("2222");
        System.out.println(Instant.ofEpochMilli(date.getTime()));
        LocalDate of = LocalDate.of(1970, Month.JANUARY, 2);
        Date date1 = Date.from(of.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date1.getTime());
        Temporal temporal = of.adjustInto(LocalDateTime.now().atZone(ZoneId.systemDefault()));
        LocalDate with = of.with(LocalDate.now());
        System.out.println(temporal);
        System.out.println(with);
    }
    @Test
    public void testParseFormat()
    {
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_WEEK_DATE));
        LocalDate date = LocalDate.of(2017, Month.MARCH, 13);
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.FRANCE)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.CHINA)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.JAPAN)));
        System.out.println(date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(new Locale("him", "IN"))));
    }
    @Test
    public void testFunnyOffset()
    {
        Instant instant = Instant.now();
        ZonedDateTime curent = instant.atZone(ZoneId.systemDefault());
        System.out.printf("current time is %s%n%n", curent);
        System.out.printf("%10s %20s %13s%n", "Offset", "ZoneId", "Time");
        ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .filter(zoneId ->
                {
                    ZoneOffset offset = instant.atZone(zoneId).getOffset();
                    return offset.getTotalSeconds() % (60 * 60) != 0;
                })
                .sorted(Comparator.comparingInt(zoneId -> instant.atZone(zoneId).getOffset().getTotalSeconds()))
                .forEach(zoneId ->
                {
                    ZonedDateTime zdt = curent.withZoneSameInstant(zoneId);
                    System.out.printf("%10s %25s %10s%n", zdt.getOffset(), zoneId, zdt.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)));
                });
    }
    @Test
    public void testOffset()
    {
        ZoneOffset zoneOffset = ZoneOffset.of("+8");
        List<String> regionByOffest = getRegionByOffest(zoneOffset);
        regionByOffest.forEach(System.out::println);
    }
    @Test
    public void testDayBetween()
    {
        LocalDate doubleEleven = LocalDate.of(2021, Month.JANUARY, 26);
        LocalDate today = LocalDate.now();
        long between = ChronoUnit.DAYS.between(today, doubleEleven);
        Period period = today.until(doubleEleven);
        System.out.println(between);
        System.out.println(period.getDays());
    }
    @Test
    public void testGeneric()
    {
        List<String> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Object> list3 = new ArrayList<>();
        printList(list1);
        printList(list2);
        printList(list3);

        List<Integer> ints = Arrays.asList(1, 2, 3, 4, 5);
        List<Double> doubles = Arrays.asList(1.0, 2.0, 3.0, 4.0, 5.0);
        List<BigDecimal> bigDecimals = Arrays.asList(
                new BigDecimal("1.0"),
                new BigDecimal("2.0"),
                new BigDecimal("3.0"),
                new BigDecimal("4.0"),
                new BigDecimal("5.0")
        );
        System.out.println(sumList(ints));
        System.out.println(sumList(doubles));
        System.out.println(sumList(bigDecimals));
    }

    private static void printList(List<?> list){
        list.forEach(System.out::println);
    }

    private static double sumList(List<? extends Number> list){
        return list.stream().mapToDouble(Number::doubleValue).sum();
    }

    private static LocalDate date2LocalDate(Date date)
    {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
    private static Date localeDate2Date(LocalDate localDate)
    {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    public LocalDate convertUtilDateToLocalDate(java.util.Date date)
    {
        return new java.sql.Date(date.getTime()).toLocalDate();
    }

    private static List<String> getRegionByOffest(ZoneOffset offset){
        LocalDateTime now = LocalDateTime.now();
        return ZoneId.getAvailableZoneIds()
                .stream()
                .filter(s -> now.atZone(ZoneId.of(s)).getOffset().equals(offset))
                .collect(Collectors.toList());
    }
}
