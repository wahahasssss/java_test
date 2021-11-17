package com.hdu;

import com.hdu.interfaces.TriFunction;
import com.hdu.model.Apple;
import com.hdu.model.Person;
import com.sun.jersey.spi.inject.PerRequestTypeInjectableProvider;

import java.awt.*;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.stream.Collectors.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * DESCRIPTION:
 *
 * @author shushoufu
 * @Date 2018/3/19
 * @Time 下午8:16
 */
public class Start {
    private final static String CLAZZ_DRIVER = "";
    private final static String JDBC_URI = "";
    private final static String USER_NAME = "";
    private final static String PASSWORD = "";

    //    static <T> Collection<T> filter(Collection<T> c, Predicate<T> predicate);
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Apple> apples = new ArrayList<Apple>();
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int tmp = random.nextInt(20);
            Apple apple = new Apple((double) 2 + tmp / 10.0f, Color.red);
            apples.add(apple);
        }
        apples.sort(Comparator.comparingDouble(Apple::getWeight));
        for (Apple apple :
                apples) {
            System.out.println(apple.toString());
        }
        System.out.println("--------------------------------");
        List<Apple> qualifiedApples = apples.stream()
                .filter((Apple a) -> a.getWeight() > 3)
                .collect(toList());


        Thread t = new Thread(() -> System.out.println("this is a function "));
        t.start();

        List<Apple> sortedList = apples.stream().sorted((Apple a, Apple b) -> a.getWeight().compareTo(b.getWeight())).collect(toList());

        List<String> colors = Arrays.asList("red", "green", "black", "orange", "blue", "purple");
        BiFunction<Double, Color, Apple> appleBiFunction = Apple::new;
        Apple theBiApple = appleBiFunction.apply(12.2d, Color.black);
        System.out.println(theBiApple.toString());

        TriFunction<Integer, Integer, Integer, Color> tri = Color::new;
        Color black = tri.apply(0, 0, 0);
        System.out.println(black.toString());


        Map<Double, List<Apple>> doubleListMap = apples
                .parallelStream()
                .collect(groupingBy(Apple::getWeight));
        System.out.println(doubleListMap.toString());

        System.out.println("===============>>>>>>>>..");
        String appleStr = apples.stream()
                .map(Apple::toString)
                .collect(joining(", "));
        System.out.println(appleStr);

//        LongStream.rangeClosed(1,Long.MAX_VALUE).parallel().forEach((num)->{
//            System.out.println(String.format("the number is %d", num));
//        });
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, 100)
                .parallel()
                .forEach(accumulator::add);
        System.out.println(String.format("sum is %d", accumulator.getSum()));

        List<String> listStr = Arrays.asList("one", "two", "three", "four");
        List<String> tmpList = listStr.stream()
                .sorted(Comparator.naturalOrder())
                .collect(toList());
        System.out.println(tmpList);


        Person per = new Person(Optional.of("ssf"), Optional.of(12));
        System.out.println(per);


        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("Y-D");
        String date = localDate.format(pattern);
        System.out.println(date);

        Instant instant = Instant.now();
        System.out.println("timestamp is" + instant.getEpochSecond());

        LocalDateTime start = LocalDateTime.now();
        Thread.sleep(1000);
        LocalDateTime end = LocalDateTime.now();

        Duration duration = Duration.between(start, end);
//        System.out.println(String.format(duration.getNano()));


    }

}
