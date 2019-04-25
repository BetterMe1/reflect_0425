package exercise.reflect_0425.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface ClassAnnotation{
    Class name();
}
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
}