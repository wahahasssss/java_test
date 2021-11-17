package stream;

import javax.net.ssl.SSLEngineResult;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class StreamTest {
    public static void main(String[] args) {
        final Collection<Streams.Task> tasks = Arrays.asList(
                new Streams.Task(Streams.Status.OPEN, 5),
                new Streams.Task(Streams.Status.OPEN, 13),
                new Streams.Task(Streams.Status.CLOSED, 8)
        );
        final long points = tasks.stream()
                .filter(task -> task.getStatus() == Streams.Status.OPEN)
                .mapToInt(Streams.Task::getPoints)
                .sum();
        System.out.println("total points:" + points);

        final double totalPoints = tasks
                .stream()
                .parallel()
                .map(task -> task.getPoints())
                .reduce(0, Integer::sum);
        System.out.println("totalpoints:" + totalPoints);

        final Map<Streams.Status, List<Streams.Task>> map = tasks
                .stream()
                .collect(Collectors.groupingBy(Streams.Task::getStatus));
        System.out.println(map);
    }
}
