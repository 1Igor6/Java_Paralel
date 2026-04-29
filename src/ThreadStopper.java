import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ThreadStopper {
    private final Thread thread;

    private static final Map<Integer, Integer> timers = new HashMap<>();
    private static final List<ThreadCalculator> threads = new ArrayList<>();

    public ThreadStopper() {
        thread = new Thread(ThreadStopper::stopper);
    }

    private static void stopper() {
        List<Map.Entry<Integer, Integer>> timersSorted =
                new ArrayList<>(timers.entrySet());

        timersSorted.sort(Comparator.comparingInt(Map.Entry::getValue));

        int time = 0;
        for (Map.Entry<Integer, Integer> timer : timersSorted) {
            try {
                Thread.sleep(timer.getValue() - time);
            } catch (InterruptedException e) {
                return;
            }

            time = timer.getValue();
            threads.get(timer.getKey() - 1).stop();
        }
    }

    public void addThread(ThreadCalculator threadCalculator) {
        threads.add(threadCalculator);

        int random = new Random().nextInt(8001) + 2000;
        timers.put(threadCalculator.getThreadId(), random);

        System.out.println(random);
    }

    public void start() {
        thread.start();
    }
}