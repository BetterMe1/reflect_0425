package exercise.reflect_0425.annotation;

import java.io.Serializable;
import java.lang.annotation.*;
import java.lang.reflect.Method;

public class TestAnnotation {
    public static void code1(){
        Class cls = Member2.class;
        //获得类的注解
        Annotation[] annotations = cls.getAnnotations();
        for(Annotation annotation : annotations){
            System.out.println(annotation.toString());//@java.lang.Deprecated()
        }
        //获得方法的注解
        try {
            Method method  = cls.getMethod("toString");
            Annotation[] annotations1 = method.getAnnotations();
            for(Annotation annotation : annotations1){
                System.out.println(annotation.toString());//@java.lang.Deprecated() @java.lang.Deprecated()
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        //自定义注解
        Class cls = Member3.class;
        MyAnnotation annotation = (MyAnnotation) cls.getAnnotation(MyAnnotation.class);
        System.out.println(annotation.getClass());//class exercise.reflect_0425.annotation.$Proxy1
        System.out.println(annotation.toString());//@exercise.reflect_0425.annotation.MyAnnotation(name=Jack, age=20)
        System.out.println(annotation.age());//20
        System.out.println(annotation.name());//Jack
    }
}

@SuppressWarnings(value = {"unused"})
@Deprecated
class Member2 implements Serializable{
    @Deprecated
    @Override
    public String toString() {
        return super.toString();
    }
}

//@MyAnnotation(name = "Jack",age = 20)
@MyAnnotation
class Member3{

}
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface  MyAnnotation{
    String name() default "Jack";
    int age() default 20;
}