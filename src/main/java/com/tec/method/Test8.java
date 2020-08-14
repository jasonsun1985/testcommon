package com.tec.method;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.tec.file.Person;
import javafx.util.Pair;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.collect.ImmutableMap.of;

public class Test8 {
    public static void main(String[] args) {
        testLambda();
        testisPresent();
        testDistinct();
        testMap();
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
    }

    private static void testGroupingBy() {
        List<People> peopleList = Arrays.asList(new People("jason", 79), new People("jason", 89), new People("shawn", 39));
        Map<String, List<People>> collect = peopleList.stream().collect(Collectors.groupingBy(People::getName));
        System.out.println(JSONObject.parseObject(JSON.toJSONString(collect)));
        final Integer[] jasonAges = new Integer[1];
        collect.keySet().forEach(k->{
        if (Objects.equals(k,"jason")) {
            Optional<Integer> jason = collect.get("jason").stream().map(People::getAge).reduce(Integer::sum);
            jasonAges[0] = jason.get();
        }});
        System.out.println(jasonAges[0]);


    }

    private static void testNullable() {
        List<Person> listPerson = null;
        System.out.println(Optional.ofNullable(listPerson).orElse(Collections.EMPTY_LIST).size());
        //NPE
//        if(Optional.of(listPerson).isPresent()){
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
        add("hello", "world", (a1, a2) -> System.out.println(a1 + a2));
        add(1, 2, biConsumer());
        System.out.println("|||||||||||||||||||||||||||||||||testBiConsumer||||||||||||||||||||||||||||");

    }

    private static BiConsumer<Integer, Integer> biConsumer() {
        return (x, y) -> System.out.println(x + y);
    }

    private static <S> void add(S a1, S a2, BiConsumer<S, S> c) {
        c.accept(a1, a2);
    }

    private static void testMatch() {
        List<String> strs = Arrays.asList("a", "a", "a", "a", "b");
        boolean aa = strs.stream().anyMatch(str -> str.equals("a"));
        boolean bb = strs.stream().allMatch(str -> str.equals("a"));
        boolean cc = strs.stream().noneMatch(str -> str.equals("a"));
        long count = strs.stream().filter(str -> str.equals("a")).count();
        System.out.println(aa);// TRUE
        System.out.println(bb);// FALSE
        System.out.println(cc);// FALSE
        System.out.println(count);// 4

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
        List<String> words = Arrays.asList("hello world", "hello java", "hello hello");
        words.stream().flatMap(w -> {
            return Stream.of(w + " copy");
        }).collect(Collectors.toList());
        words.stream().peek(w -> System.out.println("peek : " + w)).collect(Collectors.toList());

        List<String> wordList = Arrays.asList("aa", "bb", "cc");
		/*words.stream()
				// 对words的每一项都做切割操作，把每一个字符串都转换为数组
				// 执行完后数据结构如下{["hello","world"],["hello","java"],["hello","hello"]}
				.map(item -> item.split(" "))
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
                    String[] arr = item.split(" ");
                    return Arrays.asList(arr).stream();
                })
                .collect(Collectors.toList())
                // 根据每一项的HashCode和equals方法做去重操作
//				.distinct()
                // 打印每一项
                .forEach(item -> System.out.println(item));
        System.out.println("||||||||||||||||||||||||||||||||testFlatMap|||||||||||||||||||||||||||||");
    }

    private static void testComprehensive() {
        List<Integer> nums = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
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
        List<Integer> nums = Lists.newArrayList(18, 2, 3, 4, 5, 6, 7, 50, 100);
        nums.stream().skip(6).forEach(s -> System.out.println(s));
        System.out.println("|||||||||||||||||||||||||||||||||testSkip||||||||||||||||||||||||||||");
    }

    private static void testLimit() {
        List<Integer> nums = Lists.newArrayList(18, 2, 3, 4, 5, 6, 7, 50, 100);
        nums.stream().limit(6).forEach(System.out::println);
        System.out.println("|||||||||||||||||||||||||||||||||testLimit||||||||||||||||||||||||||||");
    }

    private static void testMap() {
        List<Integer> nums = Lists.newArrayList(50, 100);
//		nums.stream().peek(e -> System.out.println(e*100));
        nums.stream().map(n -> "成绩" + n.toString()).forEach(System.out::println);
        nums.stream().map(m -> {
            Map<String, Integer> map = new HashMap<>();
            map.put("A", m);
            return map;
        }).collect(Collectors.toList()).forEach(r -> System.out.println(r));
        ImmutableMap<Object, Object> ofImMap = of("A", "优秀");
        System.out.println("ImmutableMap : " + ofImMap);
        List<Pair<String, Double>> listArray = new ArrayList<>();
        listArray.add(new Pair<>("version", 1.1));
        listArray.add(new Pair<>("version", 1.2));
        listArray.add(new Pair<>("version", 1.3));
        Map<String, Double> collect1 = listArray.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (k, v) -> v));
        List<String> asList = Arrays.asList("A", "A", "B");
        Map<Integer, String> collect2 = asList.stream().collect(Collectors.toMap(String::hashCode, String::trim, (k, v) -> v));
        //Map<String, Double> : {version=1.3}
        System.out.println("Map<String, Double> : " + collect1);
        //Arrays.asList : {65=A, 66=B}
        System.out.println("Arrays.asList : " + collect2);
        System.out.println("|||||||||||||||||||||||||||||||||||testMap||||||||||||||||||||||||||");
    }

    private static void testDistinct() {
        List<String> names = Lists.newArrayList("A", "B", "A");
        names.stream().distinct().collect(Collectors.toList()).forEach(System.out::println);
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
        List<String> names = Lists.newArrayList("A", "B");
        names.stream().map((String n1) -> {
            return n1.toLowerCase();
        }).collect(Collectors.toList());
        names.stream().map((n2) -> n2.toLowerCase()).collect(Collectors.toList());
        names.stream().map(String::toLowerCase).collect(Collectors.toList());
        System.out.println(Optional.ofNullable(null).orElse(10));

        consumer1.accept("asdf");
        Supplier<Integer> integerSupplier = () -> new Random().nextInt();
        integerSupplier.get();
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        //parallelStream多线程输出
        numbers.parallelStream().forEach(System.out::println);
        IntStream.range(0, 10).forEach(System.out::println);
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