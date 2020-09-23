package cn.duw.frame.stream;

import cn.duw.frame.entity.Student;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 字节数组流
 * <p>
 * ByteArrayInputStream
 * ByteArrayOutputStream
 */
public class ByteArrayStreamUtils {

    public static <T> T cloneObject(T t) {
        T obj = null;
        try {
            // 写入字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            oos.close();
            // 读取字节流
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            obj = (T) ois.readObject();
            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) throws Exception{

        Student student = new Student(3l, "zhangsan", "30");
        Student newStudent = cloneObject(student);
        System.out.println(student);
        System.out.println(newStudent);
        System.out.println(student == newStudent);
    }
}
