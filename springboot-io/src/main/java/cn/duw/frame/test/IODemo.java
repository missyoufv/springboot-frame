package cn.duw.frame.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class IODemo {

    public static void main(String[] args) {
//        String filePath = "a/b/c/1.txt";
//        String data = "hello world!";
//        OutputStreamUtils.write(data, filePath);
//        String result = InputStreamUtils.read(filePath);
//        System.out.println(result);

        /**
         * lambda两层for循环
         */
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.forEach(item->build().forEach(obj-> System.out.println(""+item +"X"+obj+"="+item*obj)));

        List<String> list = new ArrayList(Arrays.asList("aaa", "bbb", "ccc"));
        list.stream().forEach(item ->{
            if (item.contains("a")) {
                return;
            }
            System.out.println(item);
        });
    }

    private static Stream<Integer> build() {
        return Stream.of(1, 2, 3, 4, 5);
    }
}
