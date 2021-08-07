package com.huawei.javaNewFeture.optional;
import org.junit.Test;

import java.util.Optional;
import java.util.Properties;

import static org.junit.Assert.*;
/**
 * Author：胡灯
 * Date：2021-04-18 17:03
 * Description：<描述>
 */
public class TestOptional
{
    public static String getInsuranceName(Person person){
        Optional<Person> optPerson = Optional.of(person);
        return optPerson.flatMap(Person::getCar)
                        .flatMap(Car::getInsurance)
                        .map(Insurance::getName)
                        .orElse("Unknow");
    }

    public static Insurance findCheapestInsurance(Person person, Car car) {
        if (person!=null&&car!=null)
        {
            return new Insurance("aa");
        }
        return null;
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person,Optional<Car> car)
    {
        if (person.isPresent()&&car.isPresent())
        {
            return Optional.of(findCheapestInsurance(person.get(),car.get()));
        }else {
            return Optional.empty();
        }
    }

    public static Optional<Insurance> nullSafeFindCheapestInsurance2(Optional<Person> person,Optional<Car> car)
    {
        return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
    }

    public static int readDuration(Properties prop,String name)
    {
        String value = prop.getProperty(name);
        if (value!=null)
        {
            try
            {
                int i = Integer.parseInt(value);
                if (i>0)
                {
                    return i;
                }
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public static int readDuration2(Properties prop,String name)
    {
        return Optional.ofNullable(prop.getProperty(name))
                .flatMap(TestOptional::stringToInt)
                .filter(i->i>0)
                .orElse(0);
    }


    public static Optional<Integer> stringToInt(String s)
    {
        try
        {
            return Optional.of(Integer.parseInt(s));
        }
        catch (NumberFormatException e)
        {
           return Optional.empty();
        }
    }


    @Test
    public void testGetInsturance(){
        Person person = new Person(null);
        Car car = new Car(null);
        Insurance insurance = new Insurance(null);
        car.setInsurance(insurance);
        person.setCar(car);
        String insuranceName = getInsuranceName(person);
        assertEquals("Unknow",insuranceName);
    }
    @Test
    public void testGetcheapesePrice()
    {
        Person person = null;
        Car car = null;
        Optional<Person> person1 = Optional.ofNullable(person);
        Optional<Car> car1 = Optional.ofNullable(car);
        Optional<Insurance> insurance = nullSafeFindCheapestInsurance2(person1, car1);
        assertEquals(Optional.empty(),insurance);
    }
    static Boolean i;
    @Test
    public void test20()
    {
        Integer i = 128;
        Integer j = 128;
        System.out.println(i == j);
    }
    @Test
    public void testNull()
    {
        PrReponseVo prReponseVo = new PrReponseVo();
        PrLineVo prLineVo = new PrLineVo();
        ItemRevision itemRevision = new ItemRevision();
        itemRevision.setName("haiden");
        prLineVo.setSourceId(121);
        prLineVo.setItemRevision(null);
        prReponseVo.setId(111);
        prReponseVo.setPrLineVo(prLineVo);
      //  System.out.println(prReponseVo.getPrLineVo().getItemRevision().getName());
        boolean str = Optional.ofNullable(prReponseVo)
                .map(PrReponseVo::getPrLineVo)
                .map(PrLineVo::getItemRevision)
                .map(ItemRevision::getName)
                .isPresent();
        System.out.println(str);
    }


}
