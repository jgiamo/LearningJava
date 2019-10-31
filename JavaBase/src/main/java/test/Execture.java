package test;

import java.lang.reflect.Constructor;

/**
 * Created by jingjie3 on 2018/9/10.
 */
public class Execture {
    public static void main(String[] args){
//        Student.Builder a = new Student.Builder();
//        Student student = a.age(10).chineseScore(85).englishScore(95).mathScore(90).build();
//        System.out.println(student);

        try {
            Class<?> category = Class.forName("test.Singleton");
            Constructor constructor = category.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object newInstance = constructor.newInstance();

//            Singleton singleton = (Singleton) category.newInstance();

            System.out.println(newInstance);
            System.out.println(Singleton.getInstance());
        }catch (Exception e){
            System.out.println(e);
        }

    }
}
