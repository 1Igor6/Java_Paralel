public class Main {
    public static void main(String[] args) {
        int threadCount = 3;

        if (args.length > 0) {
            try {
                threadCount = Integer.parseInt(args[0]);
                if (threadCount <= 0) {
                    threadCount = 3;
                }
            } catch (NumberFormatException e) {
                System.out.println("Некоректна кількість потоків. Буде використано 3.");
                threadCount = 3;
            }
        }

        SequenceWorker[] workers = new SequenceWorker[threadCount];

        for (int i = 0; i < threadCount; i++) {
            workers[i] = new SequenceWorker(i + 1, i + 1);
            workers[i].start();
        }

        ControllerThread controller = new ControllerThread(workers, 2000);
        controller.start();

        try {
            for (SequenceWorker worker : workers) {
                worker.join();
            }
            controller.join();
        } catch (InterruptedException e) {
            System.out.println("Головний потік було перервано.");
        }

        System.out.println("Усі потоки завершили роботу.");
    }
}