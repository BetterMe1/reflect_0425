package exercise.reflect_0425.pattern;

import com.sun.org.glassfish.external.statistics.impl.RangeStatisticImpl;

import java.lang.reflect.*;
import java.text.MessageFormat;
import java.util.Arrays;

public class TestProxy {
    public static void main(String[] args) {
        //1.代理接口 Subject
        //2.业务类 RealSubject
        //3.增强业务类 RealSubjectHandler
        //4.代理类（代理对象）
        Subject realSubject = new RealSubject();
        InvocationHandler handler = new RealSubjectHander(realSubject);
       /*
       Subject proxyObject = (Subject) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                new Class[]{Subject.class},
                handler
        );
        proxyObject.eat();
        */
        Message proxyObject = (Message) Proxy.newProxyInstance(RealSubject.class.getClassLoader(),
                new Class[]{Subject.class,Message.class},
                handler
        );
        //System.out.println("ProxySubject的类接口："+ Arrays.toString(proxyObject.getClass().getInterfaces()));
        proxyObject.greet("Jack");
        //1.代理类？
        //代理类：class exercise.reflect_0425.pattern.$Proxy0
        System.out.println("代理类："+proxyObject.getClass());
        //代理类的接口：[interface exercise.reflect_0425.pattern.Subject, interface exercise.reflect_0425.pattern.Message]
        System.out.println("代理类的接口："+Arrays.toString(proxyObject.getClass().getInterfaces()));
        /*代理类的方法：
        equals
        oString
        hashCode
        greet
        eat
        isProxyClass
        getInvocationHandler
        getProxyClass
        newProxyInstance
        wait
        wait
        wait
        getClass
        notify
        notifyAll*/
        System.out.println("代理类的方法：");
        for(Method m : proxyObject.getClass().getMethods()){
            System.out.println(m.getName());
        }
        /*代理类的属性：
        m1 class java.lang.reflect.Method
        m3 class java.lang.reflect.Method
        m2 class java.lang.reflect.Method
        m4 class java.lang.reflect.Method
        m0 class java.lang.reflect.Method*/
        System.out.println("代理类的属性：");
        for(Field field : proxyObject.getClass().getDeclaredFields()){
            System.out.println(field.getName() +" "+field.getType());
        }
        /*
        代理类的构造方法：
        exercise.reflect_0425.pattern.$Proxy0 [interface java.lang.reflect.InvocationHandler]
         */
        System.out.println("代理类的构造方法：");
        for(Constructor constructor : proxyObject.getClass().getConstructors()){
            System.out.println(constructor.getName()+" "+
                    Arrays.toString(constructor.getParameterTypes()));//参数类型
        }
        //2.动态代理的代理类是哟JVM运行时生成的
        //3.动态生成了一个代理类
    }
}
