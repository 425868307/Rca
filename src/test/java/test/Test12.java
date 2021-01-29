package test;

/**
 * Created by yaof on 2020-10-20.
 */
public interface Test12 {

    String name = "aaaa";

    default void sayHello() {
        System.out.println("hello");
    }
}
