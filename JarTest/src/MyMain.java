import java.awt.font.TextLayout;
import java.util.Arrays;
import java.util.List;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class MyMain {
    public static void main(String[] args){
//        MyImplement myImplement = new MyImplement();
//        myImplement.hello();
//        int num = 123;
//        Arrays.asList("a", "b", "c").forEach((String e)->{
//            System.out.println(e);
//            System.out.println(e+"1"+"num:"+String.valueOf(num));
//        });
//        Arrays.asList("a","b","c").sort((e1, e2)->{
//            int result = e1.compareToIgnoreCase(e2);
//                System.out.println(result);
//            return result;
//        });
//        Defaultable defaultable = DefaultableFactory.create(DefaultableImpl::new);
//        System.out.println(defaultable.notRequired());
//        defaultable = DefaultableFactory.create(OverridableImpl::new);
//        System.out.println(defaultable.notRequired());
        Car car = Car.create(Car::new);
        List<Car> cars = Arrays.asList(car);
        System.out.println(cars.toString());
        cars.forEach((c)->{
            Car.collide(c);
        });
       cars.forEach(Car::repair);
       final Car police = Car.create(Car::new);
       cars.forEach(police::follow);
    }
}
