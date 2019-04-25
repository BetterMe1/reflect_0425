package exercise.reflect_0425.annotation;

//import com.sun.javafx.charts.ChartLayoutAnimator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class TestAnnotationFactory {
    public static void main(String[] args) {
        /*Fruit fruit = FruitFactory.getIntance();
        fruit.eat();//苹果*/
        Fruit fruit = ObjectFactory.getIntance();
        fruit.eat();//苹果
    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface FruitAnnotation{
    Class name();
}

/*@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ClassAnnotation{
    Class name();
}*/

@FruitAnnotation(name = Apple.class)
interface  Fruit{
    void eat();
}
class Apple implements Fruit{

    @Override
    public void eat() {
        System.out.println("苹果");
    }
}
class FruitFactory{
    private FruitFactory(){

    }
    public static Fruit getIntance(){
        FruitAnnotation fruitAnnotation = Fruit.class.getAnnotation(FruitAnnotation.class);
        Class cls = fruitAnnotation.name();
        try {
            return (Fruit) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}

/*
//对象工厂
@ClassAnnotation(name = Apple.class)
class ObjectFactory{
    private ObjectFactory(){

    }
    public static <T> T getIntance(){
        ClassAnnotation fruitAnnotation =
                ObjectFactory.class.getAnnotation(ClassAnnotation.class);
        Class cls = fruitAnnotation.name();
        try {
            return (T) cls.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }
}*/
