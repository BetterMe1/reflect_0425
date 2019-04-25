package exercise.reflect_0425.pattern;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Testpattern {
    public static void main(String[] args) {
        /*Subject realSubject = new RealSubject();
        Subject proxySubjext = new ProxySubject(realSubject);
        proxySubjext.eat();*/

        /*Subject realSubject =
                ProxyFactory.getInstance("exercise.reflect_0425.pattern.RealSubject");
        if(realSubject != null){
            Subject proxySubject =
                    ProxyFactory.getInstance("exercise.reflect_0425.pattern.ProxySubject",realSubject);
            if(proxySubject != null){
                proxySubject.eat();
            }
        }*/
        Subject proxySubject = ProxyFactory.getProxyIntance(
                "exercise.reflect_0425.pattern.RealSubject",
                "exercise.reflect_0425.pattern.ProxySubject");
        if(proxySubject != null){
            proxySubject.eat();
        }
    }
}

//代理接口
interface  Subject{
    void eat();
}
interface Message{
    void greet(String name);
}
//工厂
class ProxyFactory{
    private ProxyFactory(){

    }
    //通过反射获取业务对象
    public static <T> T getInstance(String classname){
        try {
            Class cls = Class.forName(classname);
            return (T) cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
    //通过反射获取代理对象
    public static <T> T getInstance(String classname,Object realObject){
        try {
            Class cls = Class.forName(classname);
            Class interfaceCls = cls.getInterfaces()[0];
            Constructor constructor = cls.getConstructor(interfaceCls);
            return (T) constructor.newInstance(realObject);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static <T> T getProxyIntance(String realClassname,String proxyClassname){
        Subject realSubject =
                ProxyFactory.getInstance(realClassname);
        if(realSubject != null){
            Subject proxySubject =
                    ProxyFactory.getInstance(proxyClassname,realSubject);
            if(proxySubject != null){
               return (T) proxySubject;
            }
        }
        return null;
    }
}
class RealSubject implements Subject,Message{

    @Override
    public void eat() {
        System.out.println("吃饭");
    }

    @Override
    public void greet(String name) {
        System.out.println("欢迎"+name);
    }
}
//代理类
class ProxySubject implements Subject{
    private final  Subject target;
    public ProxySubject(Subject target){
        this.target = target;
    }
    public void before(){
        System.out.println("做饭");
    }
    @Override
    public void eat() {
        this.before();
        target.eat();
        this.after();
    }
    public void after(){
        System.out.println("洗碗");
    }
}