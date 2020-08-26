package cn.duw.frame.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 字节输入流
 * 从磁盘读取文件到内存
 */
@Slf4j
public class InputStreamUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";

    public static String read(String file) {
        return read(file,DEFAULT_CHARSET);
    }


    public static String read(String filePath,String charsetName) {
        if (StringUtils.isEmpty(filePath)) {
            return null;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return null;
        }
        FileInputStream fileInputStream = null;
        try {
            StringBuffer sb = new StringBuffer();
            fileInputStream = new FileInputStream(file);
            byte[] data = new byte[1024];
            int length;
            while ((length = fileInputStream.read(data)) != -1) {
                sb.append(new String(data, 0, length, charsetName));
            }
            return sb.toString();
        } catch (Exception ex) {
            log.error("read file error,info is :{}",ex);
            return null;
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
               log.error("close the stream error,info is:{}",e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(read("a.txt", "utf-8"));
    }


}
