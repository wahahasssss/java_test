package parallearrays;

import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class ParallelArrays {
    class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            if (o1 > o2)
                return 1;
            if (o1 < o2)
                return -1;
            return 0;
        }
    }

    public static void main(String[] args) {
        long[] arrayOfLong = new long[20000];
        Arrays.parallelSetAll(arrayOfLong,
                index -> ThreadLocalRandom.current().nextInt(1000000));
        Arrays.stream(arrayOfLong).limit(10).forEach(i -> {
            System.out.println(i + "");
        });
        System.out.println();

        Arrays.parallelSort(arrayOfLong);
        Arrays.stream(arrayOfLong).limit(10).forEach(
                i -> System.out.println(i + " ")
        );
        System.out.println();
    }
}
