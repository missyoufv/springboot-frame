package cn.duw.frame.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Properties;

/**
 * 配置文件与流之间的转换
 */
public class PropertiesUtils {

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("name", "zhangsan");
        properties.setProperty("age", "20");
        FileWriter fileWriter = new FileWriter("temp.txt");
        properties.store(fileWriter, "");

        Properties properties1 = new Properties();
        FileReader fileReader = new FileReader("temp.txt");
        properties1.load(fileReader);
        System.out.println(properties1);
    }
}
