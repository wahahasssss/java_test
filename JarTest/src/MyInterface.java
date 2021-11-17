/**
 * Created by CTWLPC on 2017/4/24.
 */
public interface MyInterface {
    default void hello() {
        System.out.println("hello a...");
    }
}
