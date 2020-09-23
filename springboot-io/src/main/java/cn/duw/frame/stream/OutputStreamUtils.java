package cn.duw.frame.stream;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Slf4j
public class OutputStreamUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";

    public static void write(String content,String file) {
        write(content,file,DEFAULT_CHARSET);
    }

    public static void write(String content,String filePath,String charsetName) {
        if (StringUtils.isEmpty(content) || StringUtils.isEmpty(filePath)) {
            return;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            byte[] data = content.getBytes(charsetName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data);
        } catch (Exception e) {
            log.error("write data to file error ,info is:{}", e);
        } finally {
            try {
                fileOutputStream.close();
            } catch (IOException e) {
                log.error("close the stream error,info is:{}",e);
            }
        }
    }
}
