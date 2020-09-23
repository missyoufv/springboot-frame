package cn.duw.frame.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.*;

public class Person implements Cloneable, Serializable {

    private String name;

    private int age;

    private Address address;

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
//
//    /**
//     * 浅拷贝实现
//     * @return
//     * @throws CloneNotSupportedException
//     */
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }

    /**
     * 深拷贝实现
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    protected Object clone()throws CloneNotSupportedException{
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream ops = new ObjectOutputStream(bos);
            ops.writeObject(this);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bos.toByteArray()));
            return ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex);
            throw new CloneNotSupportedException("person 深度拷贝失败");
        }
    }


    public static void main(String[] args) throws Exception{
        Address address = new Address("湖北省", "武汉市");
        Person person = new Person("张三", 21, address);
        Person p2 = (Person) person.clone();
        System.out.println(person);
        System.out.println(p2);
        System.out.println("修改前");
        System.out.println("person name :" + person.getName()+ "hashcode: "+person.getName().hashCode());
        System.out.println("person name :" + p2.getName()+ "hashcode: "+p2.getName().hashCode());
        System.out.println("person city :" + person.getAddress()+ "hashcode: "+person.getAddress().hashCode());
        System.out.println("person city :" + p2.getAddress()+ "hashcode: "+p2.getAddress().hashCode());
        System.out.println("修改台");
        person.setName("李四");
        person.getAddress().setCity("湖南");
        System.out.println("person name :" + person.getName()+ "hashcode: "+person.getName().hashCode());
        System.out.println("person name :" + p2.getName()+ "hashcode: "+p2.getName().hashCode());
        System.out.println("person city :" + person.getAddress()+ "hashcode: "+person.getAddress().hashCode());
        System.out.println("person city :" + p2.getAddress()+ "hashcode: "+p2.getAddress().hashCode());


        Thread t = new Thread(()-> System.out.println("test 一个thread start两次"));
        t.start();
        t.start();

    }
}


