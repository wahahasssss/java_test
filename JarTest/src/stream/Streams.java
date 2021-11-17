package stream;

/**
 * Created by CTWLPC on 2017/4/24.
 */
public class Streams {
    enum Status {
        OPEN,
        CLOSED
    }

    public static final class Task {
        private final Status status;
        private final Integer points;

        Task(final Status status, final Integer points) {
            this.points = points;
            this.status = status;
        }

        public Integer getPoints() {
            return points;
        }

        public Status getStatus() {
            return status;
        }

        @Override
        public String toString() {
            return String.format("[%s %d]", status, points);
        }


    }
}
