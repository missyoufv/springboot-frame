package cn.duw.frame.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LamdaDemo {

    public static void main(String[] args) throws Exception{
        List<String> list = new ArrayList<>(Arrays.asList("aa", "bb", "cc", "ee"));
        list.forEach((item)-> System.out.println(item));

        Stream<String> stream = Stream.of("abc", "efgabc", "xiaoming");
        stream.map(item->item.length()).forEach(System.out::print);

        List<String> words = Arrays.asList("Hello", "World");
        List<String[]> worldList = words.stream().map(item -> item.split("")).collect(Collectors.toList());

        Stream<Stream<String>> streamStream = words.stream().map(word -> word.split("")).map(Arrays::stream);
//        streamStream.flatMap()
        worldList.forEach(System.out::println);

        List<Student> students = Arrays.asList(new Student(25,"river"),
                new Student(12,"lucky"),
                new Student(22,"frank"),
                new Student(33,"kim"));
        Integer reduce = students.stream().map(Student::getAge).reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

        Stream<Integer> integerStream = Stream.of(1, 3, 4, 5, 6);
        Integer result = integerStream.reduce(0, (a, b) -> a + b);
        System.out.println(result);

        Stream<Integer> tem1 = Stream.of(1, 3, 4, 5, 6);
//        tem1.reduce((a, b) -> a + b).ifPresent(System.out::println);
        Integer reduce1 = tem1.reduce(0, (a, b) -> a > b ? a : b);
        System.out.println(reduce1);

        Stream<String> stringStream = Stream.of("hello", "world");
        List<String> collect = stringStream.map(item -> item.split("")).flatMap(item -> Arrays.stream(item)).distinct().collect(Collectors.toList());
        System.out.println(collect);

        List<String> testArrayList = new ArrayList<>();
        System.out.println(testArrayList.size());
        testArrayList.add("abc");
        System.out.println(testArrayList.size());

        Class<LinkedList> clazz = LinkedList.class;
        Field last = clazz.getDeclaredField("last");
        Field first = clazz.getDeclaredField("first");
        last.setAccessible(true);
        first.setAccessible(true);
        LinkedList<String> linkedList = clazz.newInstance();
        linkedList.add("abc");
        System.out.println(last.get(linkedList));
        System.out.println(first.get(linkedList));
        boolean check = last.get(linkedList) == first.get(linkedList);
        System.out.println(check);
    }

    @Data
    @AllArgsConstructor
    static class Student{
        private int age;
        private String name;
    }
}
