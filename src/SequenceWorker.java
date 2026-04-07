class SequenceWorker extends Thread {
    private final int id;
    private final int step;
    private volatile boolean stopAllowed = false;

    private long sum = 0;
    private long count = 0;

    public SequenceWorker(int id, int step) {
        this.id = id;
        this.step = step;
    }

    public void allowStop() {
        stopAllowed = true;
    }

    public int getWorkerId() {
        return id;
    }

    @Override
    public void run() {
        long current = 0;

        while (!stopAllowed) {
            sum += current;
            count++;
            current += step;

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println(
                "Потік #" + id +
                        ": сума = " + sum +
                        ", кількість елементів = " + count +
                        ", крок = " + step
        );
    }
}