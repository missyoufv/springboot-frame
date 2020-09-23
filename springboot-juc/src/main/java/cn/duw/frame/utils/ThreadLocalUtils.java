package cn.duw.frame.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal 线程变量副本
 */
public class ThreadLocalUtils {

    /**
     * 斐波拉契黄金分隔数
     */
    private static final int HASH_INCREMENT = 0x61c88647;

    private static ThreadLocal<ThreadLocalUtils> holder = ThreadLocal.withInitial(ThreadLocalUtils::new);

    private List<String> list = new ArrayList<>();

    public static void add(String message) {
        holder.get().list.add(message);
    }

    public static List<String> clear() {
        List<String> list = holder.get().list;
        holder.remove();
        System.out.println("size: " + holder.get().list.size());
        return list;
    }


    public static void main(String[] args) throws Exception{
//        ThreadLocalUtils.add("good good study,day day up !");
//        System.out.println(holder.get().list);
//        ThreadLocalUtils.clear();


//        Thread t = new Thread(()->test("abc",false));
//        t.start();
//        t.join();
//        System.out.println("--gc后--");
//        Thread t2 = new Thread(() -> test("def", true));
//        t2.start();
//        t2.join();
        test1();
//        test2();
    }

    public static void test(String s,boolean isGC) {
        try {
            new ThreadLocal<>().set(s);
            if (isGC) {
                System.gc();
            }
            Thread thread = Thread.currentThread();
            Class<? extends Thread> clazz = thread.getClass();
            Field threadLocals = clazz.getDeclaredField("threadLocals");
            threadLocals.setAccessible(true);
            Object threadLocalMap = threadLocals.get(thread);
            Class<?> threadLocalMapClass = threadLocalMap.getClass();
            Field table = threadLocalMapClass.getDeclaredField("table");
            table.setAccessible(true);
            Object[] arr = (Object[]) table.get(threadLocalMap);
            for(Object item : arr){
                if (item != null) {
                    Class<?> itemClass = item.getClass();
                    Field value = itemClass.getDeclaredField("value");
                    Field referenceField = itemClass.getSuperclass().getSuperclass().getDeclaredField("referent");
                    value.setAccessible(true);
                    referenceField.setAccessible(true);
                    System.out.println(String.format("弱引用key:%s,值:%s", referenceField.get(item), value.get(item)));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * hashcode 均匀分布 通过斐波那契数 也叫 黄金分割数
     */
    public static void test1() {
        AtomicInteger hashCode = new AtomicInteger(0);
        for (int i = 0; i < 17; i++) {
            hashCode.getAndAdd(HASH_INCREMENT);
            int bucket = hashCode.get() & 15;
            System.out.println(i + " 桶中的位置是："+bucket);
        }

        System.out.println("====================");
        System.out.println(HASH_INCREMENT & 15);
        System.out.println(HASH_INCREMENT*17 & 15);

    }

    /**
     * 判断基数偶数
     */
    public static void test2() {
        System.out.println((4&1) == 0);
    }
}
