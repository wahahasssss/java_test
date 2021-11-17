package datetime;

import java.time.Clock;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class DateTimeApiTest {
    public static void main(String[] args) {
        final Clock clock = Clock.systemUTC();
        System.out.println(clock.millis());
        System.out.println(clock.instant());
    }
}
