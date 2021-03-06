package com.tec.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tec.file.Person;
import javafx.util.Pair;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Test8 {
    public static void main(String[] args) {
        testLambda();
        testisPresent();
        testDistinct();
        testMap();
        testMapMerge();
        testLimit();
        testSkip();
        testComprehensive();
        testFlatMap();
        testPredicate();
        testReduce();
        testFunction();
        testConverter();
        testColon();
        testSupplier();
        testInstant();
        testBinaryOperator();
        testOptional();
        testMatch();
        testBiConsumer();
        testNullable();
        testGroupingBy();
        testSummarizingInt();
        testFinance();
        testJoin();
        testComparator();
        testUnaryOperator();
        testcompareTo();
        testCompletableFuture();
        testListToArray();
        testAssert();
        testPartitioningBy();
        testReturn();
        testBeanUtils();
        testComputeIfAbsent();
        testStopWatch();
    }

    private static void testMapMerge() {
        List<Dish> menu1 = new ArrayList<>();
        Dish d1 = new Dish();
        d1.setName("pork");
        d1.setCalories(800);
        menu1.add(d1);
        Dish d2 = new Dish();
        d2.setName("beef");
        d2.setCalories(700);
        menu1.add(d2);

        List<Dish> menu2 = new ArrayList<>();
        Dish d11 = new Dish();
        d11.setName("pork");
        d11.setVegetarian(false);
        menu2.add(d11);
        Dish d22 = new Dish();
        d22.setName("beef");
        d22.setVegetarian(false);
        menu2.add(d22);
        System.out.println(menu1);
        System.out.println(menu2);
        //针对两个list进行merge
        Map<String, Dish> m1 = menu1.stream().collect(Collectors.toMap(Dish::getName, dish -> dish));
        System.out.println("m1:"+m1);

        Map<String, Dish> m2 = menu2.stream().collect(Collectors.toMap(Dish::getName, dish -> dish));
        System.out.println("m2:"+m2);
        m1.forEach((s, dish) -> {
            Dish tempDish = m2.get(s);
            tempDish.setCalories(dish.getCalories());
        });
        System.out.println(m2);
        List<Dish> resultDish = m2.values().stream().collect(Collectors.toList());
        System.out.println(resultDish);
        System.out.println("|||||||||||||||||||||||||||||||||testMapMerge||||||||||||||||||||||||||||||||");

    }

    private static void testStopWatch() {
        StopWatch sw = new StopWatch("aaaaaaa");
        sw.start("task1");
        extracted();
        sw.stop();

        sw.start("task2");
        extracted();
        sw.stop();
        System.out.println(sw.prettyPrint());
        System.out.println("|||||||||||||||||||||||||||||||||testStopWatch||||||||||||||||||||||||||||||||");

    }

    private static void extracted() {
        for (int i = 0; i <100000 ; i++) {
            System.out.println(new Random().nextInt(10));
        }
    }

    private static void testComputeIfAbsent() {
        // 创建一个 HashMap
        HashMap<String, Integer> prices = new HashMap<>();
        // 往HashMap中添加映射项
        prices.put("Shoes", 200);
        prices.put("Bag", 300);
        prices.put("Pant", 150);
        System.out.println("HashMap: " + prices);
        // 计算 Shirt 的值
        int shirtPrice = prices.computeIfAbsent("Shirt", key -> 280);
        //Updated HashMap: {Pant=150, Shirt=280, Bag=300, Shoes=200}
        System.out.println("Price of Shirt: " + shirtPrice);
        int Shoes = prices.computeIfAbsent("Shoes", key -> 280);
        //Price of Shoes: 200
        System.out.println("Price of Shoes: " + Shoes);
        // 输出更新后的HashMap
        System.out.println("Updated HashMap: " + prices);
        System.out.println("|||||||||||||||||||||||||||||||||testComputeIfAbsent||||||||||||||||||||||||||||||||");
    }

    private static void testBeanUtils() {
        String substring = "19";
        System.out.println("substring" + substring.substring(0, 2));
        String str = "sunlei4";
        List<String> collect1 = Arrays.stream(str.split("\\|")).collect(Collectors.toList()).stream().map(s -> s + "@fehorizon.com").collect(Collectors.toList());
        System.out.println(JSON.toJSONString(collect1.stream().toArray(String[]::new)));
        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits(2); // 设置两位小数位
        double result = (double) 31 / 45;
        System.out.println(nf.format(result));


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        String startDate = simpleDateFormat.format(calendar.getTime());
        String endDate = simpleDateFormat.format(date);
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(LocalDateTime.of(LocalDate.now().minusDays(365), LocalTime.MIN).toString());
        Employee e = null;
        if (e == null || e.getName() == null) {
            System.out.println("条件前面符合及进入if方法");
        }
        Employee employee = new Employee();
        employee.setName("sl");
        employee.setAge(19);
        List<Address> listAddress = new ArrayList<>();
        Address address = new Address();
        address.setCode(300000);
        address.setValue("滨海");
        listAddress.add(address);
        Address address2 = new Address();
        address2.setCode(300200);
        address2.setValue("市区");
        listAddress.add(address2);
        employee.setListAddress(listAddress);
        Employee target = new Employee();
        BeanUtils.copyProperties(employee, target);
        System.out.println(JSON.toJSONString(target));
        List<String> l = new ArrayList<>();
        l.add("a");
        l.add("b");
        System.out.println(l.indexOf("c"));
        System.out.println(l.indexOf("a"));

        BigDecimal b1 = new BigDecimal(1);
        System.out.println(BigDecimal.ZERO.compareTo(b1) <= 0);
        System.out.println("|||||||||||||||||||||||||||||||||testBeanUtils||||||||||||||||||||||||||||||||");
    }

    private static int testReturn() {
        System.out.println("StringUtils.isAllBlank(\"1\",null)" + StringUtils.isAllBlank("1", null));
        System.out.println("StringUtils.isAllBlank(null,null)" + StringUtils.isAllBlank(null, null));
        try {
            int a = 1 / 0;
            return a;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } finally {
            System.out.println("finally 执行");
        }
    }

    private static void testPartitioningBy() {
        Stream<String> stream = Stream.of("Alen", "Hebe", "Zebe", "张成瑶", "钟其林");
        final Map<Boolean, List<String>> map = stream.collect(Collectors.partitioningBy(s -> {
            int code = s.codePointAt(0);
            return (code >= 65 && code <= 90) || (code >= 97 && code <= 122);
        }));
        map.forEach((k, v) -> {
            if (k) {
                System.out.println("英文名称如下：");
                v.forEach(vv -> System.out.println("\t" + vv));
            } else {
                System.out.println("中文名称如下：");
                v.forEach(vv -> System.out.println("\t" + vv));
            }
//            v.forEach(vv -> System.out.println("\t" + vv));
        });

        try {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Thread.currentThread().interrupt() finally 执行");
        }

        System.out.println("|||||||||||||||||||||||||||||||||testPartitioningBy||||||||||||||||||||||||||||||||");
    }

    private static void testAssert() {
//        Assert.isTrue(true,"true");
//        Assert.isTrue(false,"false");
        Assert.isNull(null, "is null");
        //Exception in thread "main" java.lang.IllegalArgumentException: is not null
//        Assert.isNull(new People(),"is not null");
        //Exception in thread "main" java.lang.IllegalArgumentException: must not null
//        Assert.notNull(null,"must not null");
        System.out.println("|||||||||||||||||||||||||||||||||testAssert||||||||||||||||||||||||||||||||");

    }

    private static void testListToArray() {
        List l1 = new ArrayList();
        l1.add(new People("A", 10));
        l1.add(new People("B", 30));
        l1.add(new People("C", 30));
        l1.add(new People("D", 30));
        l1.add(new People("E", 42));
        System.out.println(JSON.toJSON(l1.toArray()));
        System.out.println("000000000000000000");
        System.out.println(JSON.toJSON(l1.toArray(new People[l1.size()])));
        System.out.println("|||||||||||||||||||||||||||||||||testListToArray||||||||||||||||||||||||||||||||");

    }

    private static void testCompletableFuture() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("testCompletableFuture");
        CompletableFuture<People> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new People("jason", 10);
        });
        CompletableFuture<People> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return new People("harry", 20);
        });
        CompletableFuture.allOf(completableFuture, completableFuture1);
        stopWatch.stop();
        try {
            Optional.ofNullable(completableFuture.get()).ifPresent(people -> System.out.println(JSON.toJSON(people)));
            Optional.ofNullable(completableFuture1.get()).ifPresent(people -> System.out.println(JSON.toJSON(people)));
            stopWatch.prettyPrint();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("|||||||||||||||||||||||||||||||||testCompletableFuture||||||||||||||||||||||||||||||||");

    }

    private static void testcompareTo() {
        String a = "c";
        boolean r = a.compareTo("b") > 0;
        System.out.println(r);
        BigDecimal b1 = new BigDecimal(10.2);
        System.out.println("b1.compareTo(BigDecimal.ZERO) : " + b1.compareTo(BigDecimal.ZERO));
        System.out.println("b1.compareTo(new BigDecimal(10.2)) : " + b1.compareTo(new BigDecimal(10.2)));
        System.out.println("b1.compareTo(new BigDecimal(10.3)) : " + b1.compareTo(new BigDecimal(10.3)));
        System.out.println("|||||||||||||||||||||||||||||||||testcompareTo||||||||||||||||||||||||||||||||");

    }

    private static void testUnaryOperator() {
        UnaryOperator<Integer> unaryOperator = x -> x + 10;
        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;
        System.out.println(unaryOperator.apply(10)); //20
        System.out.println(binaryOperator.apply(5, 10)); //15
        //继续看看BinaryOperator提供的两个静态方法   也挺好用的
        BinaryOperator<Integer> min = BinaryOperator.minBy(Integer::compare);
        BinaryOperator<Integer> max = BinaryOperator.maxBy(Integer::compareTo);
        System.out.println("min.apply : " + min.apply(10, 20)); //10
        System.out.println("max.apply : " + max.apply(10, 20)); //20
        TernaryFunction<Integer, Integer, Integer, Integer> three = (x, y, z) -> x + y * z;
        System.out.println("TernaryFunction" + three.apply(2, 3, 4));

        BinaryOperator<BigDecimal> minD = BinaryOperator.minBy(BigDecimal::compareTo);
        //BigDecimal.ROUND_DOWN 直接去掉多余的位数
        //BigDecimal.ROUND_UP 进位处理
        //BigDecimal.ROUND_CEILING 正数进位向上，负数舍位向上
        //BigDecimal.ROUND_FLOOR 正数舍位向下，负数进位向下
        //BigDecimal.ROUND_HALF_UP 四舍五入（若舍弃部分>=.5，就进位）
        //BigDecimal.ROUND_HALF_DOWN 四舍五入（若舍弃部分>.5,就进位）
        System.out.println("BigDecimal minD : " + minD.apply(new BigDecimal(10.2).setScale(2, RoundingMode.HALF_UP), new BigDecimal(10.3).setScale(3, BigDecimal.ROUND_DOWN)));
        BigDecimal negate = new BigDecimal(10.12313);
        System.out.println("negate.negate() : " + negate.negate());
        System.out.println(new BigDecimal(10.123).subtract(negate.negate()));

        BigDecimal a1 = new BigDecimal(20.1);
        BigDecimal a2 = new BigDecimal(30.1);
        System.out.println(a1.subtract(a2).compareTo(BigDecimal.ZERO) < 1);
        System.out.println("|||||||||||||||||||||||||||||||||testUnaryOperator||||||||||||||||||||||||||||||||");
    }

    private static void testComparator() {
        List l1 = new ArrayList();
        People people = new People("A", 30);
        l1.add(people);
        List l2 = new ArrayList();
        l1.add(new People("A", 10));
        l1.add(new People("B", 30));
        l1.add(new People("C", 30));
        l1.add(new People("D", 30));
        l1.add(new People("E", 42));
        //以上db查询出来的  数值与内部值相同，待排名对象放前面就排前面，放后面就排后面
//        People people = new People("R", 30);
//        l1.add(people);
        l1.sort(Comparator.comparing(People::getAge).reversed());
        l1.forEach(p -> System.out.println(p.toString()));
        System.out.println("倒序排名为:" + (l1.indexOf(people) + 1));

        l2.addAll(l1);
        l2.sort(Comparator.comparing(People::getAge));
        System.out.println("正序排名为:" + (l2.indexOf(people) + 1));
        List<People> newSorted = (List<People>) l1.stream().sorted(Comparator.comparing(People::getAge).thenComparing((People::getName))).collect(Collectors.toList());
        DoubleList doubleList = new DoubleList();
        doubleList.setStatusList(Arrays.asList(1, 2, 3, 4, 5));
        doubleList.setIds(Arrays.asList(11L, 12L, 15L));
        System.out.println(JSON.toJSON(doubleList));

        System.out.println("按照两个字段正排： " + (newSorted.indexOf(people) + 1));

        People p1 = new People("A", 30);
        People p2 = new People("A", 30);
        System.out.println("p1.equals(p2):" + p1.equals(p2));
        System.out.println("(p1==p2):" + (p1 == p2));
        System.out.println("|||||||||||||||||||||||||||||||||testComparator||||||||||||||||||||||||||||||||");
    }

    private static void testJoin() {
        StringJoiner sj = new StringJoiner(",", "[", "]");
        sj.add("JASON").add("TOM").add("Everything");
        String desiredString = sj.toString();
        //["JASON","TOM","Everything"]
        System.out.println(desiredString);
        System.out.println("|||||||||||||||||||||||||||||||||testJoin||||||||||||||||||||||||||||");
    }

    private static void testFinance() {
        BiFunction<Long, Double, Double> result = (a, b) -> a * Math.pow(1 + b, 10);
        //1790847.6965428547
        System.out.println(result.apply(1000000L, 0.06));
        System.out.println("|||||||||||||||||||||||||||||||||testFinance||||||||||||||||||||||||||||");
    }

    private static void testSummarizingInt() {
        List<People> peopleList = Arrays.asList(
                new People("jason", 79)
                , new People("jason", 90)
                , new People("Hanna", 98));
        int size = peopleList.stream().collect(Collectors.groupingBy(People::getName)).size();
        Map<String, Long> collect = peopleList.stream().collect(Collectors.groupingBy(People::getName, Collectors.counting()));
        System.out.println("Collectors.counting() : " + collect);
        //peopleList :2
        System.out.println("peopleList :" + peopleList.size());
        //peopleList grouping by:1
        System.out.println("peopleList grouping by:" + size);
        Map<String, IntSummaryStatistics> collect1 = peopleList.stream().collect(Collectors.groupingBy(People::getName, Collectors.summarizingInt(People::getAge)));
        System.out.println(collect1.get("jason").getMax());
        System.out.println(collect1.get("jason").getMin());
        System.out.println(collect1.get("jason").getAverage());
        System.out.println(collect1.get("jason").getCount());
        System.out.println(collect1.get("jason").getSum());
        //collect1: {"jason":{"average":84.5,"min":79,"max":90,"count":2,"sum":169}}
        System.out.println("collect1: " + JSONObject.parseObject(JSON.toJSONString(collect1)));
//        Multimap<String, Integer> multiMap = ArrayListMultimap.create();

        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt(v -> v).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

        //{17=17, 2=2, 19=19, 3=3, 5=5, 23=23, 7=7, 11=11, 29=29, 13=13}
        Map<Integer, Integer> collectMap1 = primes.stream().collect(Collectors.toMap(Function.identity(), i -> i));
        System.out.println(collectMap1);

        //{0=2, 1=3, 2=5, 3=7, 4=11, 5=13, 6=17, 7=19, 8=23, 9=29}
        Map<Integer, Integer> collectMap2 = primes.stream().collect(Collectors.toMap(o -> primes.indexOf(o), i -> i));
        System.out.println(collectMap2);
        System.out.println("|||||||||||||||||||||||||||||||||testSummarizingInt||||||||||||||||||||||||||||");
    }


    private static void testGroupingBy() {
        List<User> users = Arrays.asList(
                new User("zhangsan", 20, "TJ"),
                new User("wangwu", 30, "BJ"),
                new User("heliu", 30, "NJ"),
                new User("zhaosi", 40, "LN"),
                new User("ningqi", 40, "LN"),
                new User("heliu", 32, "NJ"));
        Map<String, Integer> map = new HashMap();
        users.forEach(user -> map.merge(user.getName() + user.getCityName(), user.getAge(), Integer::sum));
//        users.forEach(user -> map.merge(user.getName()+user.getCityName(), user.getAge(), (n1,n2)->n1+n2));
        System.out.println("merge map : " + map);
        Map<String, List<User>> collect4 = users.stream().collect(Collectors.groupingBy(user -> user.getName() + user.getCityName()));
        System.out.println("Map<String, Long> 多个字段GroupingBy ：" + JSON.toJSONString(collect4) + "size : " + collect4.size());
        TreeMap<String, Set<String>> collect1 = users.stream().collect(Collectors.groupingBy(User::getCityName, TreeMap::new,
                Collectors.mapping(User::getName, Collectors.toSet())));
        System.out.println("collect1 :" + collect1);
        List<People> peopleList = Arrays.asList(new People("jason", 79), new People("jason", 89));
        Map<String, List<People>> collect = peopleList.stream().collect(Collectors.groupingBy(People::getName));
        System.out.println("collect:" + JSONObject.parseObject(JSON.toJSONString(collect)));
        final Integer[] jasonAges = new Integer[1];
        collect.keySet().forEach(k -> {
            if (Objects.equals(k, "jason")) {
                Optional<Integer> jason = collect.get("jason").stream().map(People::getAge).reduce(Integer::sum);
                jasonAges[0] = jason.get();
            }
        });
        System.out.println(jasonAges[0]);
//        Iterators.removeIf(peopleList.iterator(), StringUtils::isEmpty);

        Map<String, Map<String, IntSummaryStatistics>> collect2 = peopleList.stream().collect(Collectors.groupingBy(People::getName, Collectors.groupingBy(People::getName, Collectors.summarizingInt(People::getAge))));
        System.out.println("collect2 : " + collect2);


        Integer integer = users.stream().map(User::getAge).reduce(Integer::sum).orElse(0);
        Integer integer1 = users.stream().map(User::getAge).reduce(Integer::sum).get();

        System.out.println(integer);
        System.out.println(integer1);
        System.out.println("|||||||||||||||||||||||||||||||||testGroupingBy||||||||||||||||||||||||||||");
    }

    private static void testNullable() {
        List<Person> listPerson = null;
//        listPerson = Optional.ofNullable(listPerson).orElse(Collections.EMPTY_LIST);
        //NPE
//        if (Optional.of(listPerson).isPresent()) {
//            System.out.println("Optional.of(listPerson).isPresent():pass");
//        }
        if (Optional.ofNullable(listPerson).isPresent()) {
            System.out.println("Optional.ofNullable(listPerson).isPresent():pass");
        }
        System.out.println("|||||||||||||||||||||||||||||||||testNullable||||||||||||||||||||||||||||");

    }

    private static void testBiConsumer() {
        BiConsumer<Integer, Integer> integerBiConsumer = biConsumer();
        integerBiConsumer.accept(10, 20);
        int age = 20;
        integerBiConsumer.andThen(biConsumer());
        add("hello", "world", (a1, a2) -> System.out.println(a1 + a2));
        add(1, 2, biConsumer());
        Consumer<People> peopleConsumer = p -> {
            p.setAge(p.getAge() + age);
        };
        People p = new People();
//        peopleConsumer.accept(p);
//        System.out.println("1 accept:"+p.getAge());
        peopleConsumer.andThen(peopleConsumer).accept(p);//40
        System.out.println("2 accept:" + p.getAge());

        String[] array = {"迪丽热巴,女", "古力娜扎,女"};
        Arrays.asList(array).stream().forEach((m) -> {
            //两种写法
            nameConsumer().andThen(ageConsumer()).accept(m);
            getConsumerConsumerBiConsumer(m).accept(nameConsumer(), ageConsumer());
        });
        System.out.println("|||||||||||||||||||||||||||||||||testBiConsumer||||||||||||||||||||||||||||");

    }

    private static BiConsumer<Consumer, Consumer> getConsumerConsumerBiConsumer(String s) {
        return (x, y) -> {
            x.andThen(y).accept(s);
        };
    }

    private static Consumer<String> nameConsumer() {
        return (m) -> {
            String name = m.split(",")[0];
            System.out.println("姓名:" + name);
        };
    }

    private static Consumer<String> ageConsumer() {
        return (m) -> {
            String age = m.split(",")[1];
            System.out.println("年龄:" + age);
        };
    }

    private static BiConsumer<Integer, Integer> biConsumer() {
        return (x, y) -> System.out.println(x + y);
    }

    private static <S, T> void add(S a1, T a2, BiConsumer<S, T> c) {
        System.out.println(" <S,T> void add--");
        c.accept(a1, a2);
    }

    private static void testMatch() {
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> "a".equals(str));
        boolean bb = strs.stream().allMatch(str -> "a".equals(str));
        boolean cc = strs.stream().noneMatch(str -> "a".equals(str));
        long count = strs.stream().filter(str -> "a".equals(str)).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4
        Byte b = 5;
        Arrays.stream(ConsultationTypeEnums.values()).filter(v -> !v.equals(ConsultationTypeEnums.OWN_EXPENSE)).forEach(v -> System.out.println(v));
        boolean booleanResult = Arrays.stream(ConsultationTypeEnums.values()).filter(v -> !v.equals(ConsultationTypeEnums.OWN_EXPENSE)).anyMatch(v -> v.getToByte().equals(b));
        System.out.println("booleanResult : " + booleanResult);
        System.out.println("|||||||||||||||||||||||||||||||||testMatch||||||||||||||||||||||||||||");


    }

    private static void testOptional() {
        Map<String, String> result = null;
        Optional<Map> o = Optional.ofNullable(result);
        System.out.println(o);
        if (o.isPresent()) {
            System.out.println(o.get());
        } else {
            System.out.println("o is null");
        }
        if (!o.isPresent()) {
            System.out.println("o  i s  n u l l ");
        }

        Object o1 = o.orElse(null);
        System.out.println("o1 : " + o1);

        Optional<Object> user = Optional.ofNullable(null);
        System.out.println("user.isPresent() : " + user.isPresent());
        o.ifPresent(o2 -> {
                    //不进方法
                    o2.put("a", "a");
                    System.out.println("o2.get(\"a\") : " + o2.get(""));
                }
        );

        List<People> peopleList = null;
        peopleList = Optional.ofNullable(peopleList).orElse(Arrays.asList(new People("jason", 19)));
        System.out.println("peopleList.get(0).toString() : {}" + peopleList.get(0).toString());
        System.out.println("|||||||||||||||||||||||||||||||||testOptional||||||||||||||||||||||||||||");
    }

    private static void testBinaryOperator() {
        System.out.println(BinaryOperator.maxBy(Integer::compareTo).apply(1, 2));
        System.out.println("|||||||||||||||||||||||||||||||||testBinaryOperator||||||||||||||||||||||||||||");
    }

    private static void testInstant() {
        Instant instant = Instant.now();
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS").format(LocalDateTime.now()));
        System.out.println("|||||||||||||||||||||||||||||||||testInstant||||||||||||||||||||||||||||");

    }

    private static void testSupplier() {
        Supplier<People> personSupplier = People::new;
        personSupplier.get();
    }

    private static void testColon() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        Consumer<String> methodParam = Test8::printValue;
        list.forEach(methodParam::accept);
        String s1 = "1";
        String s2 = "2";

        //JDK8中有双冒号的用法，就是把方法当做参数传到stream内部，使stream的每个元素都传入到该方法里面执行一下
        list.forEach(Test8::printValue);
        System.out.println("|||||||||||||||||||||||||||||||||testColon||||||||||||||||||||||||||||");


    }

    private static void testConverter() {
        Something something = new Something();
        Converter<String, String> test = something::startsWith;
        System.out.println(test.covert("JAVA"));
        System.out.println("|||||||||||||||||||||||||||||||||testConverter||||||||||||||||||||||||||||");

    }

    private static void testFunction() {
        BiFunction<String, String, Integer> biFun = getStringStringIntegerBiFunction();
        biFun.apply("a", "b");
        Function<Integer, String> converter = (i) -> Integer.toString(i);
        Function<Integer, String> converter1 = i -> Integer.toString(i);
        Function<Integer, Double> area = (r) -> {
            return Math.pow(r, 2) * Math.PI;
        };
        System.out.println(area.apply(10));

        Function<Integer, Integer> times2 = i -> i * 2;
        Function<Integer, Integer> squared = i -> i * i;
        System.out.println(times2.apply(4));
        System.out.println(squared.apply(4));
//      =32  先4×4然后16×2,先执行apply(4)，在times2的apply(16),先执行参数，再执行调用者
//      使用compose函数，简单的说，就是从右向左处理
        System.out.println(times2.compose(squared).apply(4));
//      =64  先4×2,然后8×8,先执行times2的函数，在执行squared的函数
//      使用andThen函数，简单的说，就是从左向右处理。
        System.out.println(times2.andThen(squared).apply(4));
//      =16
        System.out.println(Function.identity().compose(squared).apply(4));
        System.out.println(squared.apply(4));
        System.out.println("|||||||||||||||||||||||||||||||||testFunction||||||||||||||||||||||||||||");
    }

    private static BiFunction<String, String, Integer> getStringStringIntegerBiFunction() {
        return (s1, s2) -> s1.length() + s2.length();
    }

    private static class AA {
        private BigDecimal a;
        private BigDecimal b;

        public BigDecimal getA() {
            return a;
        }

        public void setA(BigDecimal a) {
            this.a = a;
        }

        public BigDecimal getB() {
            return b;
        }

        public void setB(BigDecimal b) {
            this.b = b;
        }
    }

    private static void testReduce() {
        int result1 = Stream.of(1, 2, 3).reduce(2, (x, y) -> x * y);
        System.out.println("result1 : " + result1);
        Optional<Integer> result2 = Stream.of(1, 2, 3).reduce((x, y) -> x * y);
        System.out.println("result2 : " + result2.orElse(-1));
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int reduce = Arrays.stream(numbers).reduce(0, (a1, a2) -> a1 + a2);
        System.out.println("reduce :" + reduce);


        List<AA> list = new ArrayList<>();
        AA a = new AA();
        a.setA(new BigDecimal("1.5"));
        a.setB(new BigDecimal("2.00"));
        list.add(a);
        a = new AA();
        a.setA(new BigDecimal("2"));
        a.setB(new BigDecimal("3.20"));
        list.add(a);
        Optional<BigDecimal> result = list.stream().map(b -> b.getA().multiply(b.getB())).reduce(BigDecimal::add);
        BigDecimal bi = result.orElse(new BigDecimal("0"));

        System.out.println(bi);
        System.out.println("|||||||||||||||||||||||||||||||||testReduce||||||||||||||||||||||||||||");
    }

    private static void testPredicate() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 67, 87, 123, 21, 32, 99);
        List<Integer> newList = getNewList(list, Test8::cal, 4);
        list.stream().sorted((a, b) -> a.compareTo(b));
        Predicate<List<Integer>> pp = l -> l.size() > 100;
        System.out.println(pp.test(list));
        System.out.println(newList);
        System.out.println("|||||||||||||||||||||||||||||||||testPredicate||||||||||||||||||||||||||||");
    }

    private static <A> List<Integer> getNewList(List<Integer> list, Predicate<Integer> num, A a) {
        return list.stream()
                .filter(num)
                .map(n -> {
                    if (a instanceof Integer) {
                        return n * (Integer) a;
                    }
                    return n;
                })
                .collect(Collectors.toList());
    }

    private static boolean cal(Integer num) {
        return num % 3 == 0;
    }

    private static void testFlatMap() {
        Employee employee = new Employee();
        employee.setName("sl");
        employee.setAge(19);
        List<Address> listAddress = new ArrayList<>();
        Address address = new Address();
        address.setCode(300000);
        address.setValue("滨海");
        listAddress.add(address);
        Address address2 = new Address();
        address2.setCode(300200);
        address2.setValue("市区");
        listAddress.add(address2);
        employee.setListAddress(listAddress);

        Employee target = new Employee();
        target.setName("test");
        target.setAge(4);
        List<Address> listAddress2 = new ArrayList<>();
        Address address3 = new Address();
        address3.setCode(302000);
        address3.setValue("市民广场");
        listAddress2.add(address3);
        Address address4 = new Address();
        address4.setCode(310200);
        address4.setValue("市区");
        listAddress2.add(address4);
        target.setListAddress(listAddress2);


        List<Employee> list = new ArrayList<>();
        list.add(employee);
        list.add(target);
        System.out.println(JSON.toJSONString(list));
        //[{"age":19,"listAddress":[{"code":300000,"value":"滨海"},{"code":300200,"value":"市区"}],"name":"sl"},{"age":4,"listAddress":[{"code":302000,"value":"市民广场"},{"code":310200,"value":"市区"}],"name":"test"}]
        List<Address> collect1 = list.stream().flatMap(e -> e.getListAddress().stream()).collect(Collectors.toList());
        //[{"code":300000,"value":"滨海"},{"code":300200,"value":"市区"},{"code":302000,"value":"市民广场"},{"code":310200,"value":"市区"}]
        System.out.println("flatMap new List " + JSON.toJSONString(collect1));

        List<String> words = Arrays.asList("hello world", "hello java", "hello hello");
        words.stream().flatMap(w -> {
            return Stream.of(w + " copy");
        }).collect(Collectors.toList());
        /**
         * peek : hello world
         * peek : hello java
         * peek : hello hello
         */
        words.stream().peek(w -> System.out.println("peek : " + w)).collect(Collectors.toList());

        List<String> wordList = Arrays.asList("aa", "bb", "cc");
		/*words.stream()
				// 对words的每一项都做切割操作，把每一个字符串都转换为数组
				// 执行完后数据结构如下{["hello","world"],["hello","java"],["hello","hello"]}
				.map(item -> item.split(" "))
				// 对每个数组做单独的遍历操作
				.forEach(arr -> {
					for (String item : arr) {
						System.out.println(item);
					}
				});*/
        words.stream()
                // 把每一项转化为数组包含列表的Stream流
                // 然后这个函数把所有的List里面的字符串都取出来放在了一个集合中，这个集合做下一次执行的数据源
                // {"hello","world","hello","java","hello","hello"}
                .flatMap(item -> {
                    String[] arr = item.split(" ");
                    return Arrays.asList(arr).stream();
                })
                .collect(Collectors.toList())
                // 根据每一项的HashCode和equals方法做去重操作
//				.distinct()
                // 打印每一项
                /**
                 * hello
                 * world
                 * hello
                 * java
                 * hello
                 * hello
                 */
                .forEach(item -> System.out.println(item));

        List<String> teamIndia = Arrays.asList("Virat", "Dhoni", "Jadeja");
        List<String> teamAustralia = Arrays.asList("Warner", "Watson", "Smith");
        List<String> teamEngland = Arrays.asList("Alex", "Bell", "Broad");
        List<String> teamNewZeland = Arrays.asList("Kane", "Nathan", "Vettori");
        List<String> teamSouthAfrica = Arrays.asList("AB", "Amla", "Faf");
        List<String> teamWestIndies = Arrays.asList("Sammy", "Gayle", "Narine");
        List<String> teamSriLanka = Arrays.asList("Mahela", "Sanga", "Dilshan");
        List<String> teamPakistan = Arrays.asList("Misbah", "Afridi", "Shehzad");

        List<List<String>> playersInWorldCup2016 = new ArrayList<>();
        playersInWorldCup2016.add(teamIndia);
        playersInWorldCup2016.add(teamAustralia);
        playersInWorldCup2016.add(teamEngland);
        playersInWorldCup2016.add(teamNewZeland);
        playersInWorldCup2016.add(teamSouthAfrica);
        playersInWorldCup2016.add(teamWestIndies);
        playersInWorldCup2016.add(teamSriLanka);
        playersInWorldCup2016.add(teamPakistan);

        // Let's print all players before Java 8
        List<String> listOfAllPlayers = new ArrayList<>();

        for (List<String> team : playersInWorldCup2016) {
            for (String name : team) {
                listOfAllPlayers.add(name);
            }
        }

        System.out.println("Players playing in world cup 2016");
        System.out.println(listOfAllPlayers);


        // Now let's do this in Java 8 using FlatMap
        List<String> flatMapList = playersInWorldCup2016.stream()
                .flatMap(pList -> pList.stream())
                .collect(Collectors.toList());

        System.out.println("List of all Players using Java 8");
        System.out.println(flatMapList);

        List<Person> l1 = Arrays.asList(new Person("A", 10), new Person("B", 20));
        List<Person> l2 = Arrays.asList(new Person("C", 30), new Person("D", 40));
        List<List<Person>> l3 = new ArrayList<>();
        l3.add(l1);
        l3.add(l2);
        List<Person> collect = l3.stream().flatMap(lsub -> lsub.stream()).collect(Collectors.toList());
        System.out.println("l3:" + JSON.toJSON(collect));
        System.out.println("||||||||||||||||||||||||||||||||testFlatMap|||||||||||||||||||||||||||||");
    }

    private static void testComprehensive() {
        List<Integer> nums = Arrays.asList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        System.out.println("sum is:" + nums.stream().filter(num -> num != null)
                //1,1,2,3,4,5,6,7,8,9,10
                //.peek(x -> System.out.println("peek0: "+x))
                .distinct()
                //1,2,3,4,5,6,7,8,9,10
                .mapToInt(num -> num * 2)
                //2,4,6,8,10,12,14,16,18,20
                .skip(2)
                //6,8,10,12,14,16,18,20
                .limit(4)
                .peek(System.out::println)
                //6,8,10,12
                .sum());
        //36
        System.out.println("|||||||||||||||||||||||||||||||||testComprehensive||||||||||||||||||||||||||||");
    }

    public <T> void testClass(T t, List<? extends T> list, List<? super People> listP) {
    }

    /**
     * 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
     */
    private static void testSkip() {
        List<Integer> nums = Arrays.asList(18, 2, 3, 4, 5, 6, 7, 50, 100);
        //7 50 100
        nums.stream().skip(6).forEach(s -> System.out.println(s));
        System.out.println("|||||||||||||||||||||||||||||||||testSkip||||||||||||||||||||||||||||");
    }

    private static void testLimit() {
        List<Integer> nums = Arrays.asList(18, 2, 3, 4, 5, 6, 7, 50, 100);
        nums.stream().limit(6).forEach(System.out::println);
        System.out.println("|||||||||||||||||||||||||||||||||testLimit||||||||||||||||||||||||||||");
    }

    private static void testMap() {
        List<Integer> nums = Arrays.asList(50, 100);
//		nums.stream().peek(e -> System.out.println(e*100));
        nums.stream().map(n -> "成绩" + n.toString()).forEach(System.out::println);
        nums.stream().map(m -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("A", m);
            return map;
        }).collect(Collectors.toList()).forEach(r -> System.out.println(r));

        List<Pair<String, Double>> listArray = new ArrayList<>();
        listArray.add(new Pair<>("version", 1.1));
        listArray.add(new Pair<>("version", 1.2));
        listArray.add(new Pair<>("version", 1.3));
        Map<String, Double> collect1 = listArray.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (k, v) -> k));
        System.out.println("Map<String, Double> 1: " + collect1);
        Map<String, Double> collect2 = listArray.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (k, v) -> v));
        System.out.println("Map<String, Double> 2: " + collect2);
        List<String> asList = Arrays.asList("A", "A", "B");
        Map<Integer, String> collect3 = asList.stream().collect(Collectors.toMap(String::hashCode, String::trim, (k, v) -> v));
        //Map<String, Double> : {version=1.3}
        //Arrays.asList : {65=A, 66=B}
        System.out.println("Arrays.asList : " + collect3);
        System.out.println("|||||||||||||||||||||||||||||||||||testMap||||||||||||||||||||||||||");
    }

    private static void testDistinct() {
        List<String> names = Arrays.asList("A", "B", "A");
        names.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);

        List<People> peopleList = Arrays.asList(new People("jason", 79),
                new People("jason", 90),
                new People("jason", 90),
                new People("shawn", 90));

        System.out.println("peopleList原长度：" + peopleList.size() + "\n distinct后 : " + peopleList.stream().distinct().collect(Collectors.toList()));

//        peopleList.stream().collect(Collectors.)
        new People("jason", 79);
        System.out.println(new People("jason", 79));
        System.out.println("||||||||||||||||||||||||||||||||||||testDistinct|||||||||||||||||||||||||");
    }

    private static void testisPresent() {
        String str = "JASON";
//		String str = null;
        Optional<String> name = Optional.ofNullable(str);
        System.out.println(name.isPresent());
        name.ifPresent((value) -> {
            System.out.println("name is : " + value);
        });
        name.orElseGet(() -> "a");
        System.out.println("|||||||||||||||||||||||||||||||||||||testisPresent||||||||||||||||||||||||");
    }

    private static void testLambda() {
        List<String> names = Arrays.asList("A", "B");
        names.stream().map((String n1) -> {
            return n1.toLowerCase();
        }).collect(Collectors.toList());
        names.stream().map((n2) -> n2.toLowerCase()).collect(Collectors.toList());
        names.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(Optional.ofNullable(null).orElse(10));
        names.stream().collect(Collectors.joining(""));
        consumer1.accept("asdf");
        Supplier<Integer> integerSupplier = () -> new Random().nextInt();
        integerSupplier.get();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //parallelStream多线程输出
        numbers.parallelStream().forEach(System.out::println);
        IntStream.range(0, 10).forEach(System.out::println);
        System.out.println(numbers.stream().distinct().collect(Collectors.toList()).hashCode());
        System.out.println("|||||||||||||||||||||||||||||||||||||testLambda||||||||||||||||||||||||");

    }

    public static void printValue(String str) {
        System.out.println("print value : " + str);
    }

    public static void printValue(String str1, String str2) {
        System.out.println("print value : " + str1.concat(str2));
    }

    public static Consumer<String> consumer1 = (s) -> System.out.println(s);
}