package cn.duw.frame.stream;

import cn.duw.frame.entity.Student;

import java.io.*;

/**
 * 对象输入、输出流
 * ObjectInputStream
 * ObjectOutputStream
 */
public class ObjectStreamUtils {


    public static void main(String[] args) {
        String file = "temp.txt";
        FileOutputStream fos = null;
        Student student = new Student(1l,"duw","26");
        try {
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(student);
            oos.flush();
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Student newStu = (Student) ois.readObject();
            newStu.setId(2l);
            System.out.println(newStu);
            System.out.println(student);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
