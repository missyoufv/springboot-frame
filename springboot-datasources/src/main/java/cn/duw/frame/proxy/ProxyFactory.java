package cn.duw.frame.proxy;

import cn.duw.frame.datasource.DataSourceSelector;
import cn.duw.frame.datasource.DataSourceType;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class ProxyFactory implements MethodInterceptor {



    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务。。。");
        Object object = methodProxy.invokeSuper(obj, args);
        System.out.println("结束事务。。。");
        return object;
    }

    public static Object getInstance(Object object){
        // 通过CGLIB动态代理获取代理对象的过程
        Enhancer enhancer = new Enhancer();
        // 设置enhancer对象的父类
        enhancer.setSuperclass(object.getClass());
        // 设置enhancer的回调对象
        enhancer.setCallback(new ProxyFactory());
        return enhancer.create();
    }
}
