class ControllerThread extends Thread {
    private final SequenceWorker[] workers;
    private final int delayMs;

    public ControllerThread(SequenceWorker[] workers, int delayMs) {
        this.workers = workers;
        this.delayMs = delayMs;
    }

    @Override
    public void run() {
        for (int i = 0; i < workers.length; i++) {
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException e) {
                return;
            }

            System.out.println("Керуючий потік дозволяє завершити потік #" + workers[i].getWorkerId());
            workers[i].allowStop();
        }
    }
}