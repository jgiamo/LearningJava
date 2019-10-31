package test;

/**
 * Created by jingjie3 on 2018/9/10.
 */
public class Singleton {
    private static final Singleton singleton = new Singleton();

    public static Singleton getInstance(){
        return singleton;
    }

    private Singleton(){

    }

    public static void main(String[] args){
        System.out.println(Singleton.class);
    }
}
