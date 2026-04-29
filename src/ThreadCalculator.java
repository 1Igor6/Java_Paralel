public class ThreadCalculator {
    private final Thread thread;
    private int threadId;
    private volatile boolean run = true;

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int id) {
        threadId = id;
    }

    public ThreadCalculator(int id) {
        thread = new Thread(this::startSumming);
        setThreadId(id);
    }

    public void stop() {
        run = false;
    }

    public void startSumming() {
        long sum = 0;
        long count = 0;

        do {
            count++;
            sum += 3;
        } while (run);

        System.out.println("ThreadId: " + threadId + " | Sum: " + sum + " | Amount of elements: " + count);
    }

    public void start() {
        thread.start();
    }
}