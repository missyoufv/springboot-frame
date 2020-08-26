package cn.duw.frame.test;

import cn.duw.frame.stream.InputStreamUtils;
import cn.duw.frame.stream.OutputStreamUtils;

import java.io.OutputStreamWriter;

public class IODemo {

    public static void main(String[] args) {
        String filePath = "a/b/c/1.txt";
        String data = "hello world!";
        OutputStreamUtils.write(data, filePath);
        String result = InputStreamUtils.read(filePath);
        System.out.println(result);
    }
}
