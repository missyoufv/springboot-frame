package cn.duw.frame.stream;

import java.io.File;
import java.net.URL;

/**
 * java中路径问题
 *  1、资源文件被打包到jar中，不能以new File读取，因为resources目录中的文件并不是直接存在系统中，而是嵌套在jar文件中，以流的方式读取
 *      &eg:new ClassPathResource("application.properties").getInputStream() ,如果想拷贝出来可以使用 FileUtils.copyToFile(resource.getInputStream(), "从流中拷贝到目标文件");
 *  2、类加载路径、资源文件路径
 */
public class FilePathUtils {

    public static void main(String[] args) throws Exception{

        // 获取类加载的根路径 /F:/git/springboot-frame/springboot-io/target/classes/
        System.out.println(FilePathUtils.class.getResource("/").getPath());

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  /F:/git/springboot-frame/springboot-io/target/classes/cn/duw/frame/stream/
        System.out.println(FilePathUtils.class.getResource("").getPath());

        // 获取项目路径 F:\git\springboot-frame
        File file = new File("");
        System.out.println(file.getCanonicalPath());

        // 获取类加载的根路径     file:/F:/git/springboot-frame/springboot-io/target/classes/
        URL resource = FilePathUtils.class.getClassLoader().getResource("");
        System.out.println(resource);

        // 获取当前工程路径
        System.out.println(System.getProperty("user.dir"));

    }
}
